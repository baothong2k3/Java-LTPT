package demo.js;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demo.entity.Address;
import demo.entity.Person;
import demo.entity.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class JsonHandlerStream {
    public static ArrayList<Person> readFromFile(String fileName) {

        ArrayList<Person> pA = new ArrayList<>();
        Person p = null;
        ArrayList<PhoneNumber> phones = null;
        Address add = null;
        try (JsonParser parser = Json.createParser(new FileReader(fileName));) {

            String keyName = "";
            while (parser.hasNext()) {
                Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            add = new Address();
                            JsonObject jO = parser.getObject();
                            add.setStreetAddress(jO.getString("streetAddress"));
                            add.setCity(jO.getString("city"));
                            add.setState(jO.getString("state"));
                            add.setPostalCode(jO.getInt("postalCode"));
                        } else {
                            p = new Person();
                        }
                        break;
                    case START_ARRAY:
                        if (keyName.equals("phoneNumbers")) {
                            phones = new ArrayList<>();
                            JsonArray jsonArray = parser.getArray();

                            for (JsonValue item : jsonArray) {
                                JsonObject jsonObject = (JsonObject) item;
                                PhoneNumber phoneNumber = new PhoneNumber();
                                phoneNumber.setType(jsonObject.getString("type"));
                                phoneNumber.setNumber(jsonObject.getString("number"));
                                phones.add(phoneNumber);
                            }
                        }
                        break;

                    case KEY_NAME:
                        keyName = parser.getString();
                        break;

                    case VALUE_STRING:
                        String sV = parser.getString();
                        switch (keyName) {
                            case "firstName":
                                p.setFirstName(sV);
                                break;
                            case "lastName":
                                p.setLastName(sV);
                                break;
                        }
                        break;

                    case VALUE_NUMBER:
                        Integer num = parser.getInt();
                        switch (keyName) {
                            case "age":
                                p.setAge(num);
                        }
                        break;

                    case VALUE_TRUE:
                        break;
                    case VALUE_FALSE:
                        break;
                    case VALUE_NULL:
                        break;
                    case END_OBJECT:
                        p.setAddress(add);
                        p.setPhoneNumbers(phones);
                        pA.add(p);
                        break;
                    case END_ARRAY:
                        break;
                    default:
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pA;
    }
    public static void toFile(ArrayList<Person> personArrayList, String fileName){
        try(JsonGenerator gen = Json.createGenerator(new FileWriter(fileName))){
            gen.writeStartArray();

            for (Person p : personArrayList){
                gen.writeStartObject().write("firstName", p.getFirstName())
                        .write("lastName", p.getLastName())
                        .write("age", p.getAge());

                //address
                Address address = p.getAddress();
                if (address != null){
                    gen.writeStartObject("address")
                            .write("streetAddress", address.getStreetAddress())
                            .write("city", address.getCity())
                            .write("state", address.getState())
                            .write("postalCode", address.getPostalCode()).writeEnd();
                    gen.writeEnd();
                }

                //phone number
                List<PhoneNumber> phoneNumberArrayList = p.getPhoneNumbers();
                if(phoneNumberArrayList != null) {
                    gen.writeStartArray();
                    for (PhoneNumber phoneNumber : phoneNumberArrayList) {
                        gen.writeStartObject()
                                .write("type", phoneNumber.getType())
                                .write("number", phoneNumber.getNumber()).writeEnd();
                    }
                    gen.writeEnd();
                }
            }
            gen.writeEnd();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
