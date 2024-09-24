import UserInerface.UserInterface;
import Utils.DatabaseConnection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.getInstance();
        UserInterface ui = new UserInterface();
        ui.showMainMenu();


        ArrayList<String> names = new ArrayList<>(Arrays.asList("mohamed","soufian","rabie"));
        String longest = names.stream().max(Comparator.comparingInt(String::length)).get();
        int max = longest.length();

        System.out.println("the longest name is : "+longest);
//        for(String name : names){
//          name.stream
//        }
//        System.out.println("the longest name is : " +longName);
    }
}

