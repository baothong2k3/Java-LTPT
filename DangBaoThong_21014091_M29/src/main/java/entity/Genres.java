package entity;

public class Genres {
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Genres() {
    }

    public Genres(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genres{" +
                "genre='" + genre + '\'' +
                '}';
    }
}
