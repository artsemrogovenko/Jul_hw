package app_zoo;

public enum Action {
    Go,
    Stop,
    Walk,
    To_me,
    Jump,
    Pretend,
    Get_up;

    public static String show() {
        String s="";
        for (Object name : Action.values()) {
            s=s+ name.toString() + " ";
        }
        return s;
    }

/*     public static boolean find(String s) {       
        try {
            Action.valueOf(s);
            return true;
       } catch (IllegalArgumentException e) {
            return false;
       }      
    } */
public static boolean find(String s) {
    for (Object name : Action.values()) {
        if (name.toString().toLowerCase().equals(s.toLowerCase())) {
            return true;
        }
    }
    return false;
}
}
