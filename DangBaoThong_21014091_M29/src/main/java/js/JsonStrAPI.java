package js;

import entity.*;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonParser;

import java.io.*;
import java.util.ArrayList;

public class JsonStrAPI {
    public static ArrayList<Movie> readJson(String fileName) {

        ArrayList<Movie> movies = new ArrayList<>();
        Award award = null;
        Movie movie = null;
        ArrayList<Language> languages = null;
        ArrayList<Genres> genres = null;
        ArrayList<Director> directors = null;
        ArrayList<Cast> cast = null;

        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            String keyName = "";
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY:
                        if(keyName.equals("languages")) {
                            languages = new ArrayList<>();
                            JsonArray jsonArray = parser.getArray();
                            for (JsonValue jv : jsonArray){
                                JsonObject jsonObject = (JsonObject) jv;
                                Language language = new Language();
                                language.setLanguage(jsonObject.getString("languages"));
                                languages.add(language);
                            }
                        } else if (keyName.equals("genres")) {
                            genres = new ArrayList<>();
                            JsonArray jsonArray = parser.getArray();
                            for (JsonValue jv : jsonArray){
                                JsonObject jsonObject = (JsonObject) jv;
                                Genres genre = new Genres();
                                genre.setGenre(jsonObject.getString("genres"));
                            }
                            movie.setGenres(genres);
                        } else if (keyName.equals("directors")) {
                            directors = new ArrayList<>();
                            JsonArray jsonArray = parser.getArray();
                            for (JsonValue jv : jsonArray){
                                JsonObject jsonObject = (JsonObject) jv;
                                Director director = new Director();
                                director.setDerector(jsonObject.getString("directors"));
                            }
                            movie.setDirectors(directors);
                        } else if (keyName.equals("cast")) {
                            cast = new ArrayList<>();
                            JsonArray jsonArray = parser.getArray();
                            for (JsonValue jv : jsonArray){
                                JsonObject jsonObject = (JsonObject) jv;
                                Cast cast1 = new Cast();
//                                cast1.setName(jsonObject.getString("name"));
//                                cast1.setCharacter(jsonObject.getString("character"));
                                cast.add(cast1);
                            }
                            movie.setCast(cast);
                        }
                        break;
                    case START_OBJECT:
                        if (keyName.equals("awards")) {
                            award = new Award();
                            JsonObject jsonObject = parser.getObject();
                            award.setNominations(jsonObject.getInt("nominations"));
                            award.setText(jsonObject.getString("text"));
                            award.setWins(jsonObject.getInt("wins"));
                        } else {
                            movie = new Movie();
                        }
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        break;
                    case VALUE_NUMBER:
                        break;
                    case VALUE_TRUE:
                        break;
                    case VALUE_FALSE:
                        break;
                    case VALUE_NULL:
                        break;
                    case END_OBJECT:
                        movie.setLanguages(languages);
                        movies.add(movie);
                        break;
                    case END_ARRAY:
                        break;
                }
            }
            return movies;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
