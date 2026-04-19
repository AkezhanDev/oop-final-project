package services;

import model.academic.Course;
import model.communication.News;
import model.research.Journal;
import model.research.ResearchPaper;
import storage.DataStore;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private final DataStore dataStore;

    public SearchService() {
        this.dataStore = DataStore.getInstance();
    }

    public List<Course> searchCoursesByKeyword(String keyword) {
        List<Course> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Course course : dataStore.getCourses()) {
            if (course.getCourseCode().toLowerCase().contains(lowerKeyword)
                    || course.getTitle().toLowerCase().contains(lowerKeyword)
                    || course.getMajorFor().toLowerCase().contains(lowerKeyword)) {
                result.add(course);
            }
        }

        return result;
    }

    public List<News> searchNewsByKeyword(String keyword) {
        List<News> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (News news : dataStore.getNewsList()) {
            if (news.getTitle().toLowerCase().contains(lowerKeyword)
                    || news.getContent().toLowerCase().contains(lowerKeyword)
                    || news.getTopic().toLowerCase().contains(lowerKeyword)) {
                result.add(news);
            }
        }

        return result;
    }

    public List<ResearchPaper> searchResearchPapersByKeyword(String keyword) {
        List<ResearchPaper> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Journal journal : dataStore.getJournals()) {
            for (ResearchPaper paper : journal.getPapers()) {
                if (paper.getTitle().toLowerCase().contains(lowerKeyword)
                        || paper.getAuthors().toLowerCase().contains(lowerKeyword)
                        || paper.getJournal().toLowerCase().contains(lowerKeyword)
                        || paper.getDoi().toLowerCase().contains(lowerKeyword)) {
                    result.add(paper);
                }
            }
        }

        return result;
    }
}