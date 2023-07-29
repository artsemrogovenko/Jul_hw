package app_zoo.Pack_animals;

import java.util.LinkedList;

import app_zoo.Animal;

public abstract class Pack_animals  extends Animal{

    private static String group ="Вьючое животное";
    
    public Pack_animals(int num,String pet, String name, String dayOfBirdth, LinkedList<String> comm) {
        super(num,group, name, dayOfBirdth, comm);
        super.type = pet;       
    }


}
