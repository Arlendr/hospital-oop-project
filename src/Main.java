import menu.HospitalMenu;
import menu.HospitalMenuImpl;

public class Main {
    public static void main(String[] args) {
        HospitalMenu menu = new HospitalMenuImpl();
        menu.run();
    }
}