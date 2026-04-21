package util;

import model.research.ResearchPaper;

import java.util.Comparator;

public class DateComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper firstPaper, ResearchPaper secondPaper) {
        return safeString(firstPaper.getPublicationDate()).compareTo(safeString(secondPaper.getPublicationDate()));
    }

    private String safeString(String value) {
        if (value == null) {
            return "";
        }

        return value;
    }
}
