package application;

import backend.MetroAPI;

import java.util.Scanner;

public class App {

    private static final String INPUT_FILE_PATH = "./src/main/resources/wienerlinien.csv";

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        MetroAPI api = new MetroAPI(INPUT_FILE_PATH);
        while (true) {
            System.out.println("Type the name of the start station: ");
            String from = scanner.nextLine();
            if(from.equals("")) {
                break;
            }
            System.out.println("Type the name of your station");
            String to = scanner.nextLine();
            System.out.println("Route:");
            try {
                System.out.println(api.getRoute(from, to));
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Your input was not correct! Maybe the station name does not exist.");
            }
        }
    }
}
