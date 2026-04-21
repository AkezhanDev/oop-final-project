package util;

import model.research.ResearchPaper;

import java.util.Comparator;

public class CitationComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper firstPaper, ResearchPaper secondPaper) {
        return Integer.compare(secondPaper.getCitations(), firstPaper.getCitations());
    }
}
