package app_zoo.Pets;

import java.util.LinkedList;

public class Dog extends Pet_animals {
    private static String type = "Собака";

    public Dog(int num, String name, String dayOfBirdth, LinkedList<String> comm) {
        super(num, type, name, dayOfBirdth, comm);
    }
}
