package demo.jom;

import java.util.List;

import demo.entity.Address;
import demo.entity.Person;

public class ObjectModelAPIDemo {
    public static void main(String[] args) {

        List<Person> list = List.of(
                new Person("Ti", "Le", 20, new Address("12 nguyen Van Bao", "P4", "GV HCM", 10000)),
                new Person("Le", "Te", 23, new Address("124 nguyen Van Bao", "P4", "GV HCM", 10000)),
                new Person("Le", "Le", 23, new Address("12455 nguyen Van Bao", "P4", "GV HCM", 10000))
        );

        JsonHandler.toFile(list, "data/people.json");
    }
}

//Checked Exception
//Unchecked Exception --> RuntimeException
