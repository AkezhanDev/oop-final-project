package util;

import model.research.ResearchPaper;

import java.util.Comparator;

public class PagesComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper firstPaper, ResearchPaper secondPaper) {
        return Integer.compare(firstPaper.getPages(), secondPaper.getPages());
    }
}
