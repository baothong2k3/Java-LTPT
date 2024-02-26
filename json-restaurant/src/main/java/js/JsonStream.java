package js;

import entity.Address;
import entity.Grades;
import entity.Restaurant;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonStream {
    public static Restaurant readFromFile(String fileName) {
        Restaurant r = null;
        ArrayList<Grades> gradesList = null;
        Address add = null;
        String keyName = "";
        return r;
        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            add = new Address();
                        } else
                            r = new Restaurant();
                        break;
                    case START_ARRAY:
                        gradesList = new ArrayList<Grades>();
                        JsonArray ja = parser.getArray();
                        for (JsonValue jv : ja){
                            JsonObject job = (JsonObject) jv;
                            gradesList.add(new Grades())
                }
            }

        }
    } catch(
    IOException e)

    {
        e.printStackTrace();
    }
}
}
