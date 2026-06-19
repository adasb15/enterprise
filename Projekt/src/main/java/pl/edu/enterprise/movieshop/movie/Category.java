package pl.edu.enterprise.movieshop.movie;

public enum Category {
    DRAMA("Dramat"),
    COMEDY("Komedia"),
    FAMILY("Familijny"),
    ACTION("Akcja"),
    SCIENCE_FICTION("Science fiction"),
    THRILLER("Thriller");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
