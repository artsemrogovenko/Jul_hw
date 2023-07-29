package app_zoo;

import java.util.Scanner;

public class Controller{

   protected static Scanner sc = new Scanner(System.in);
public static void start() {
   
        boolean loop = true;
        while (loop) {
            System.out.println(
            "\nГлавное меню:\n" +
            "1 Завести новое животное\n" +
            "2 Просмотр команд, которое выполняет животное\n" +
            "3 Обучить животное новым командам\n" +
            "4 Просмотр всех животных\n" +
            "5 Удалить из списка\n" +
            
                        
            "0 выход\n");
            switch (sc.nextLine()) {
                case "1": Nurcery.addNew(); break;
                case "2": Nurcery.showCommands(); break;
                case "3": Nurcery.addCommand(); break;
                case "4": Nurcery.showAnimals(); break;
                case "5": Nurcery.removeAnimal(); break;
                
                
                case "0": loop = false; sc.close(); break;
                default: System.out.println("такого варианта еще нет");
            }

        }
         sc.close();
       }



}