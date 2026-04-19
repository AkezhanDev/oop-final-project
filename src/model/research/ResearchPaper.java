package model.research;

import enums.CitationFormat;

public class ResearchPaper {
    private String title;
    private String authors;
    private String journal;
    private int pages;
    private String publicationDate;
    private int citations;
    private String doi;

    public ResearchPaper() {
    }

    public ResearchPaper(String title, String authors, String journal, int pages,
                         String publicationDate, int citations, String doi) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.pages = pages;
        this.publicationDate = publicationDate;
        this.citations = citations;
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getJournal() {
        return journal;
    }

    public int getPages() {
        return pages;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public int getCitations() {
        return citations;
    }

    public String getDoi() {
        return doi;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void setPages(int pages) {
        if (pages >= 0) {
            this.pages = pages;
        }
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setCitations(int citations) {
        if (citations >= 0) {
            this.citations = citations;
        }
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getCitation(CitationFormat format) {
        if (format == CitationFormat.BIBTEX) {
            return "@article{" + doi +
                    ", title={" + title + "}" +
                    ", author={" + authors + "}" +
                    ", journal={" + journal + "}" +
                    ", year={" + publicationDate + "}" +
                    "}";
        }

        return authors + ". " + title + ". " + journal +
                ", " + publicationDate +
                ", pages: " + pages +
                ", citations: " + citations +
                ", doi: " + doi;
    }

    @Override
    public String toString() {
        return "ResearchPaper: title=" + title +
                ", authors=" + authors +
                ", journal=" + journal +
                ", pages=" + pages +
                ", publicationDate=" + publicationDate +
                ", citations=" + citations +
                ", doi=" + doi;
    }
}