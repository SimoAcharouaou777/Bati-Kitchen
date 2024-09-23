import UserInerface.UserInterface;
import Utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.getInstance();
        UserInterface ui = new UserInterface();
        ui.showMainMenu();
    }
}