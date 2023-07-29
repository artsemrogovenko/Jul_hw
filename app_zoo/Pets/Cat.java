package app_zoo.Pets;

import java.util.LinkedList;

public class Cat extends Pet_animals{
        private static String type = "Кот";

        public Cat(int num, String name, String dayOfBirdth, LinkedList<String> comm) {
            super(num, type, name, dayOfBirdth, comm);
        }
}
