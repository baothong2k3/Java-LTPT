package entity;

import java.util.ArrayList;

public class Movie {
    private long _id;
    private String plot;
    private ArrayList<Genres> genres;
    private int runtime;
    private ArrayList<Cast> cast;
    private String num_mflix_comments;
    private String title;
    private ArrayList<Language> languages;
    private ArrayList<Director> directors;
    private String rated;
    private Award awards;
    private int year;

    public Movie() {
    }

    public Movie(long _id, String plot, ArrayList<Genres> genres, int runtime, ArrayList<Cast> cast, String num_mflix_comments, String title, ArrayList<Language> languages, ArrayList<Director> directors, String rated, Award awards, int year) {
        this._id = _id;
        this.plot = plot;
        this.genres = genres;
        this.runtime = runtime;
        this.cast = cast;
        this.num_mflix_comments = num_mflix_comments;
        this.title = title;
        this.languages = languages;
        this.directors = directors;
        this.rated = rated;
        this.awards = awards;
        this.year = year;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public ArrayList<Genres> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genres> genres) {
        this.genres = genres;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }

    public String getNum_mflix_comments() {
        return num_mflix_comments;
    }

    public void setNum_mflix_comments(String num_mflix_comments) {
        this.num_mflix_comments = num_mflix_comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Language> languages) {
        this.languages = languages;
    }

    public ArrayList<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<Director> directors) {
        this.directors = directors;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public Award getAwards() {
        return awards;
    }

    public void setAwards(Award awards) {
        this.awards = awards;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "_id=" + _id +
                ", plot='" + plot + '\'' +
                ", genres=" + genres +
                ", runtime=" + runtime +
                ", cast=" + cast +
                ", num_mflix_comments='" + num_mflix_comments + '\'' +
                ", title='" + title + '\'' +
                ", languages=" + languages +
                ", directors=" + directors +
                ", rated='" + rated + '\'' +
                ", awards=" + awards +
                ", year=" + year +
                '}';
    }
}
