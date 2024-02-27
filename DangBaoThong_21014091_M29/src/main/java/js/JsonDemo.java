package js;

import entity.Movie;

import java.util.ArrayList;

public class JsonDemo {
    public static void main(String[] args) {
        ArrayList<Movie> movies = JsonStrAPI.readJson("src/data/movies.json");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
