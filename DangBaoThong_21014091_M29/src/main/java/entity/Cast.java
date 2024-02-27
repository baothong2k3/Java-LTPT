package entity;

public class Cast {
    private String cast;

    public Cast(String cast) {
        this.cast = cast;
    }

    public Cast() {
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "cast='" + cast + '\'' +
                '}';
    }
}
