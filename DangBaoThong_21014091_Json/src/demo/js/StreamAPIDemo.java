package demo.js;

import demo.entity.Person;
import jakarta.json.Json;

import java.util.ArrayList;

public class StreamAPIDemo {
	
	public static void main(String[] args) {
		
		ArrayList<Person> p = JsonHandlerStream.readFromFile("data/person.json");
		JsonHandlerStream.toFile(p,"data/person1.json");
	}
	
}
