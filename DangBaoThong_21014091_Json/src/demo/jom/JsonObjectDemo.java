package demo.jom;

import demo.entity.Person;

import java.util.ArrayList;

public class JsonObjectDemo {
    public static void main(String[] args) {
        ArrayList<Person> persons = JsonObjectAPI.readJson("data/person.json");
        JsonObjectAPI.writeJson(persons, "data/persons.json" );
    }
}
