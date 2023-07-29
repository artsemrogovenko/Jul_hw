package app_zoo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import app_zoo.Pack_animals.*;
import app_zoo.Pets.*;


public class Nurcery extends Controller{
     
    private static LinkedHashMap<Integer,Animal> animals =new LinkedHashMap<>();
    private static int count;

    public static void addNew(){
        System.out.println("кого добавляем?");
        System.out.println(            
            "1 Верблюд\n" +
            "2 Осёл\n" +
            "3 Лошадь\n" +
            "4 Кот\n" +
            "5 Собака\n" +
            "6 Хомяк\n" +
                        
            "0 выход\n");
            int selector= -1;

            try {   
                selector = Integer.parseInt(sc.nextLine());
            

            if(selector>0 && selector<7){
            
                String[] propties = new String[3];
              
                    propties = description();
                    count = Counter.add();
                
                    LinkedList<String> list = new LinkedList<>();
                    if (!propties[2].isEmpty()) { // список команд
                        String[] commands = propties[2].toLowerCase().split(" ");
                        for (String str : commands) {
                            
                            if (Action.find(str)) {
                                list.add(str);
                            }
                        }

            if (list.size() > 0)
                System.out.println("успешно добавлено:" + list.toString());
            else
                System.out.println("нет таких команд, список будет пуст");
        }

        Animal obj ;
    
            switch (selector) {
                case 1:
                    obj = new Camel(count,propties[0], propties[1], list);
                    animals.put(obj.getId(), obj);
                    break;
                case 2:
                    obj = new Donkey(count,propties[0], propties[1], list);
                    animals.put(obj.getId(), obj);
                    break;
                case 3:
                    obj = new Horse(count,propties[0], propties[1], list);
                    animals.put(obj.getId(), obj);
                    break;
                case 4:
                    obj = new Cat(count,propties[0], propties[1], list);
                    animals.put(obj.getId(), obj);
                    break;
                case 5:
                    obj = new Dog(count,propties[0], propties[1], list);
                    animals.put(obj.getId(), obj);
                    break;
                case 6:
                    obj = new Hamster(count,propties[0], propties[1], list);
                    animals.put(obj.getId(), obj);
                    break;
    
                default:
                    System.out.println("такого варианта еще нет");
            }}
            } //end  if(selector>0 && selector<7){
                catch (NumberFormatException e) {
                    System.out.println("неверное значение\n");
                    addNew();
                 }
                catch (Exception e) {
                    System.out.println("Ошибка - "+e.getMessage());

                }

      
    }

    private static String[] description() throws Exception {
        String[] data = new String[3];
        System.out.println("имя?");        
            data[0] = sc.nextLine();  
            if (novalid(data[0])) {                
                throw new Exception("недопустимое имя");
            }
        data[1] = getAnimalBirthday();
        System.out.println("| какие команды выполняет? ввести через пробел |\n" +
                "> список доступных команд: " + Action.show() +
                "<\nесли команд нет оставьте поле пустым\n");
        data[2] = sc.nextLine();
        return data;
    }

    private static boolean novalid(String s) {       
        int count = 0;
        for (char el : s.toLowerCase().toCharArray()) {
            if(Character.isAlphabetic(el)){
                count++;
            }
        }
        if (count<s.length()-1 || s.length()==0){
            return true;
        }
         return false;
    }

    public static String getAnimalBirthday() {
        SimpleDateFormat myformat = new SimpleDateFormat("dd.MM.yyyy");
        Date animalBirthday = new Date();
        
        String generated = myformat.format(animalBirthday);
        System.out.println("Введите дату dd.mm.yyyy для пустого поля будет задана текущая дата");
        String getdate = sc.nextLine();

        try {
            if (getdate.isEmpty()) {
                throw new NullPointerException();
            }
            animalBirthday = myformat.parse(getdate);
            
        } catch (ParseException e) {
            System.out.println("Неверное значение");
            return getAnimalBirthday();

        } catch (NullPointerException e) {
            return generated;
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
        return animalBirthday.toString();
    }


    public static void showCommands() {
        System.out.println(animals.get(parseid()).allCommands());
    }

    public static void addCommand() {
        try {
            Animal o = animals.get(parseid());

            System.out.println("Введите новую команду из списка " + Action.show());
            String newCommand = sc.nextLine();
            if (Action.find(newCommand)) {
                o.addCommand(newCommand);
            } else {
                System.out.println("недоступная команда");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage()); 
        }

    }

    public static void removeAnimal() {
        animals.remove(parseid());
        System.out.println("Готово");
    }

    public static int parseid(){        
        System.out.print("Введи id животного ");
        int id = Integer.parseInt(sc.nextLine());
        if (animals.containsKey(id)){
            return id;
        }else {
            throw new NullPointerException("такого id нет");
        }
    }

    public static void showAnimals(){
        for (Map.Entry<Integer,Animal> e : animals.entrySet()) {
            e.getValue().info();
        }
    }

}
/* 
class WrongDataInput extends MyException {
    public WrongDataInput(String mes, String val) {
        super(mes, val);
    }
}

class EmptyString extends MyException {
    public EmptyString(String mes, String val) {
        super(mes, val);
    }
}

abstract class MyException extends Exception {
    private final String x;

    public MyException(String message, String x) {
        super(message);
        this.x = x;
    }

    public String getErr() {
        return x;
    }
} */