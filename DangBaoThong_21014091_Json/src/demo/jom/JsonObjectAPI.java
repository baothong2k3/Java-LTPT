package demo.jom;

import demo.entity.Address;
import demo.entity.Person;
import demo.entity.PhoneNumber;
import jakarta.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonObjectAPI {
    public static ArrayList<Person> readJson(String fileName){

        ArrayList<Person> pA = new ArrayList<>();

        try(JsonReader reader = Json.createReader(new FileReader(fileName))){
            JsonArray ja = reader.readArray();
            for (JsonValue jv : ja){
                Person p = new Person();
                JsonObject object = (JsonObject) jv;
                p.setFirstName(object.getString("firstName"));
                p.setLastName(object.getString("lastName"));
                p.setAge(object.getInt("age"));

                //address
                Address add = new Address();
                JsonObject objectAddress = object.getJsonObject("address");
                add.setCity(objectAddress.getString("city"));
                add.setState(objectAddress.getString("state"));
                add.setStreetAddress(objectAddress.getString("streetAddress"));
                add.setPostalCode(objectAddress.getInt("postalCode"));
                p.setAddress(add);

                //phone number
                PhoneNumber phone = new PhoneNumber();
                ArrayList<PhoneNumber> phones = new ArrayList<>();
                JsonArray jsonArrayPhone = object.getJsonArray("phoneNumbers");
                for (JsonValue item : jsonArrayPhone){
                    JsonObject jsonObject = (JsonObject) item;
                    phone.setType(jsonObject.getString("type"));
                    phone.setNumber(jsonObject.getString("number"));
                    phones.add(phone);
                    p.setPhoneNumbers(phones);
                }
                pA.add(p);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return pA;
    }
    public static void writeJson(ArrayList<Person> personArrayList, String fileName){
        try(JsonWriter jsonWriter = Json.createWriter(new FileWriter(fileName))) {

            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

            for (Person person : personArrayList){
                //address
                Address address = person.getAddress();
                JsonObject jsonAddress = jsonObjectBuilder.add("streetAddress", address.getStreetAddress())
                        .add("city", address.getCity())
                        .add("postalCode", address.getPostalCode())
                        .add("state", address.getState())
                        .build();

                //phone number
                List<PhoneNumber> phoneNumbers = person.getPhoneNumbers();
                JsonArrayBuilder jsonArrayBuilderPhone = Json.createArrayBuilder();
                for (PhoneNumber phoneNumber : phoneNumbers){
                    JsonObject objectPhone = jsonObjectBuilder
                            .add("type", phoneNumber.getType())
                            .add("number", phoneNumber.getNumber())
                            .build();
                    jsonArrayBuilderPhone.add(objectPhone);
                }
                JsonArray jsonArrayPhone =  jsonArrayBuilderPhone.build();

                //person
                JsonObject jsonObject = jsonObjectBuilder.add("firstName", person.getFirstName())
                        .add("lastName", person.getLastName())
                        .add("age", person.getAge())
                        .add("address", jsonAddress)
                        .add("phoneNumbers", jsonArrayPhone).build();

                jsonArrayBuilder.add(jsonObject);
            }

            JsonArray jsonArray = jsonArrayBuilder.build();
            jsonWriter.writeArray(jsonArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
