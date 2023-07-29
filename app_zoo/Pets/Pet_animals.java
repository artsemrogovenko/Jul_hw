package app_zoo.Pets;

import java.util.LinkedList;

import app_zoo.Animal;

public abstract class Pet_animals extends Animal {

    private static final String group ="Домашнее животное";

    public Pet_animals(int num,String pet, String name, String dayOfBirdth, LinkedList<String> comm) {
        super(num,group, name, dayOfBirdth, comm);
        super.type = pet;
    }


}
