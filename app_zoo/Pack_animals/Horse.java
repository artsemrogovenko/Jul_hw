package app_zoo.Pack_animals;

import java.util.LinkedList;

public class Horse extends Pack_animals {
    private static String type = "Лошадь";

    public Horse(int num, String name, String dayOfBirdth, LinkedList<String> comm) {
        super(num, type, name, dayOfBirdth, comm);
    }
}
