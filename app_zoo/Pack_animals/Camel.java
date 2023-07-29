package app_zoo.Pack_animals;

import java.util.LinkedList;

public class Camel extends Pack_animals {
    private static String type = "Верблюд";

    public Camel(int num, String name, String dayOfBirdth, LinkedList<String> comm) {
        super(num, type, name, dayOfBirdth, comm);
    }

}
