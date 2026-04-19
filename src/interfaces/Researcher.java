package interfaces;

import model.research.ResearchPaper;
import model.research.ResearchProject;

import java.util.Comparator;
import java.util.List;

public interface Researcher {
    int calculateHIndex();

    void publishPaper(ResearchPaper paper);

    void printPapers(Comparator<ResearchPaper> comparator);

    void joinResearchProject(ResearchProject project);

    List<ResearchPaper> getResearchPapers();
}