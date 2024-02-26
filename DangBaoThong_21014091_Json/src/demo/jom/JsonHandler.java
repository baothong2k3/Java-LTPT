package demo.jom;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import demo.entity.Address;
import demo.entity.Person;
import demo.entity.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

public class JsonHandler {
	public static String toJson(Person p) {
		StringWriter sw = null;

		try (JsonWriter writer = Json.createWriter(sw = new StringWriter())) {

			JsonObjectBuilder oBuilder = Json.createObjectBuilder();

			Address add = p.getAddress();
			JsonObject joAdd = oBuilder.add("streetAddress", add.getStreetAddress()).add("city", add.getCity())
					.add("state", add.getState()).add("postalCode", add.getPostalCode()).build();

			JsonObject jo = oBuilder.add("firstName", p.getFirstName()).add("lastName", p.getLastName())
					.add("age", p.getAge()).add("address", joAdd).build();

			writer.writeObject(jo);
		}

		return sw.toString();
	}

	public static String toJson(List<Person> persons) {
		JsonArrayBuilder aBuilder = Json.createArrayBuilder();
		Address add = null;
		for (Person p : persons) {
			JsonObjectBuilder oBuilder = Json.createObjectBuilder();

			add = p.getAddress();
			JsonObject joAdd = oBuilder.add("streetAddress", add.getStreetAddress()).add("city", add.getCity())
					.add("state", add.getState()).add("postalCode", add.getPostalCode()).build();
			JsonObject jo = oBuilder.add("firstName", p.getFirstName()).add("lastName", p.getLastName())
					.add("age", p.getAge()).add("address", joAdd).build();

			aBuilder.add(jo);
		}
		JsonArray ja = aBuilder.build();
		StringWriter sw = null;
		try (JsonWriter writer = Json.createWriter(sw = new StringWriter())) {
			writer.writeArray(ja);
		}
		return sw.toString();
	}

	public static List<Person> fromJsonArray(String json) {
		List<Person> persons = new ArrayList<>();

		try (JsonReader reader = Json.createReader(new StringReader(json))) {
			JsonArray ja = reader.readArray();
			for (JsonValue jv : ja) {
				JsonObject jo = (JsonObject) jv;
				Person p = new Person();
				p.setFirstName(jo.getString("firstName"));
				p.setLastName(jo.getString("lastName"));
				p.setAge(jo.getInt("age"));

				JsonObject joAdd = jo.getJsonObject("address");
				Address add = new Address();
				add.setStreetAddress(joAdd.getString("streetAddress"));
				add.setCity(joAdd.getString("city"));
				add.setState(joAdd.getString("state"));
				add.setPostalCode(joAdd.getInt("postalCode"));

				p.setAddress(add);
				persons.add(p);
			}
		}
		return persons;
	}

	public static Person fromJsonObject(String json) {
		Person p = null;

		try (JsonReader reader = Json.createReader(new StringReader(json))) {

			JsonObject jo = reader.readObject();
			if (jo != null) {
				p = new Person();
				p.setFirstName(jo.getString("firstName"));
				p.setAge(jo.getInt("age"));
			}

		}

		return p;
	}

	public static Person fromFile(String fileName) {
		Person p = null;
		Address add = null;

		try (JsonReader reader = Json.createReader(new FileReader(fileName));) {

			JsonObject jo = reader.readObject();
			if (jo != null) {
				p = new Person();
				p.setFirstName(jo.getString("firstName"));
				p.setLastName(jo.getString("lastName"));
				p.setAge(jo.getInt("age"));

				JsonObject joAdd = jo.getJsonObject("address");
				add = new Address();
				add.setStreetAddress(joAdd.getString("streetAddress"));

				p.setAddress(add);
				JsonArray ja = jo.getJsonArray("phoneNumbers");
				List<PhoneNumber> phones = new ArrayList<>();

				for (JsonValue jv : ja) {
					JsonObject joPh = (JsonObject) jv;
					PhoneNumber ph = new PhoneNumber(joPh.getString("type"), joPh.getString("number"));
					phones.add(ph);
				}

				p.setPhoneNumbers(phones);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return p;

	}

	public static void toJsonFile(List<Person> persons, String fileName) {
		try (JsonWriter writer = Json.createWriter(new FileWriter(fileName));) {

			JsonArrayBuilder aBuilder = Json.createArrayBuilder();
			JsonObjectBuilder oBuilder = Json.createObjectBuilder();

			for (Person p : persons) {

				Address add = p.getAddress();
				JsonObject joAdd = oBuilder.add("name", add.getStreetAddress()).add("city", add.getCity()).build();

				JsonObject jo = oBuilder.add("firstName", p.getFirstName()).add("lastName", p.getLastName())
						.add("age", p.getAge()).add("address", joAdd).build();

				aBuilder.add(jo);
			}

			JsonArray ja = aBuilder.build();
			writer.write(ja);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void toFile(List<Person> persons, String fileName) {
		try (JsonWriter writer = Json.createWriter(new FileWriter(fileName));) {

			JsonArrayBuilder aBuilder = Json.createArrayBuilder();
			JsonObjectBuilder oBuilder = Json.createObjectBuilder();

			for (Person p : persons) {

				Address add = p.getAddress();
				JsonObject joAdd = oBuilder.add("name", add.getStreetAddress()).add("city", add.getCity()).build();

				JsonObject jo = oBuilder.add("firstName", p.getFirstName()).add("lastName", p.getLastName())
						.add("age", p.getAge()).add("address", joAdd).build();

				aBuilder.add(jo);
			}

			JsonArray ja = aBuilder.build();
			writer.write(ja);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//autoboxing 
//unboxing

//upcasting
//downcasting
