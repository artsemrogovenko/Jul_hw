package app_zoo;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class Animal {

       
        private String name; // имя
        private String bornDate; // дата рождения        
        private final int id;
        private String group;
        protected String type;
        private Set<String> commands=new LinkedHashSet<>(); // lists comands
        
        public Animal(Integer num,String group, String name, String dayOfBirdth, LinkedList<String> comm) {
                this.name = name;
                this.bornDate = dayOfBirdth;
                this.id = num;
                for (String str : comm) {
                        addCommand(str);
                }
                this.group = group;
        }

        public Animal(String group,String name,String dayOfBirdth) {
                this.name = name;
                this.bornDate = dayOfBirdth;
                this.id = Counter.add();   
                this.group=group;              
        }

        public void info() {
                System.out.printf("id %s ;%s ;%s ;%s ;%s ;%s;\n",
                                id, name, group, type, bornDate, allCommands());
        }

        public int getId() {
                return id;
        }

        public String allCommands() {    
      /*           String s =""; 
                for (String command : commands) {
                        s=String.join(",",command);
                }    
            return s; */
           return commands.toString();
        }
    
        public void addCommand(String new_command) {
            commands.add(new_command.toLowerCase());
        
        }
    
}
