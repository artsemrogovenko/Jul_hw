package app_zoo.Pets;

import java.util.LinkedList;

public class Hamster extends Pet_animals{
    private static String type = "Хомяк";

    public Hamster(int num, String name, String dayOfBirdth, LinkedList<String> comm) {
        super(num, type, name, dayOfBirdth, comm);
    }
}
