package services;

import enums.CitationFormat;
import model.research.Journal;
import model.research.ResearchPaper;
import model.research.ResearchProject;
import storage.DataStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResearchService {
    private DataStore dataStore;

    public ResearchService() {
        this.dataStore = DataStore.getInstance();
    }

    public void addResearchProject(ResearchProject researchProject) {
        dataStore.addResearchProject(researchProject);
    }

    public void addJournal(Journal journal) {
        dataStore.addJournal(journal);
    }

    public void publishPaperToJournal(Journal journal, ResearchPaper paper) {
        journal.addPaper(paper);
    }

    public void addPaperToProject(ResearchProject project, ResearchPaper paper) {
        project.addPublishedPaper(paper);
    }

    public void sortPapersByCitations(List<ResearchPaper> papers) {
        papers.sort(Comparator.comparingInt(ResearchPaper::getCitations).reversed());
    }

    public void sortPapersByPages(List<ResearchPaper> papers) {
        papers.sort(Comparator.comparingInt(ResearchPaper::getPages));
    }

    public void sortPapersByDate(List<ResearchPaper> papers) {
        papers.sort(Comparator.comparing(ResearchPaper::getPublicationDate));
    }

    public List<Journal> getAllJournals() {
        return dataStore.getJournals();
    }

    public List<ResearchProject> getAllResearchProjects() {
        return dataStore.getResearchProjects();
    }

    public List<ResearchPaper> getAllPapersFromAllJournals() {
        List<ResearchPaper> result = new ArrayList<>();

        for (Journal journal : dataStore.getJournals()) {
            result.addAll(journal.getPapers());
        }

        return result;
    }

    public String getPaperCitation(ResearchPaper paper, CitationFormat format) {
        return paper.getCitation(format);
    }

    public String findTopCitedResearcher() {
        Map<String, Integer> citationMap = new HashMap<>();

        for (ResearchPaper paper : getAllPapersFromAllJournals()) {
            String[] authors = paper.getAuthors().split(",");

            for (String author : authors) {
                String trimmedAuthor = author.trim();
                citationMap.put(trimmedAuthor,
                        citationMap.getOrDefault(trimmedAuthor, 0) + paper.getCitations());
            }
        }

        String topResearcher = null;
        int maxCitations = -1;

        for (Map.Entry<String, Integer> entry : citationMap.entrySet()) {
            if (entry.getValue() > maxCitations) {
                maxCitations = entry.getValue();
                topResearcher = entry.getKey();
            }
        }

        return topResearcher;
    }

    public int getTotalCitationsForResearcher(String researcherName) {
        int total = 0;

        for (ResearchPaper paper : getAllPapersFromAllJournals()) {
            String[] authors = paper.getAuthors().split(",");

            for (String author : authors) {
                if (author.trim().equalsIgnoreCase(researcherName)) {
                    total += paper.getCitations();
                }
            }
        }

        return total;
    }
}