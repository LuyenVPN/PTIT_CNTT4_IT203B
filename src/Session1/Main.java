package Session1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       Date birthday = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your birthday (dd/mm/yyyy): ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            birthday = sdf.parse(sc.nextLine());
            System.out.println("Your birthday is: " + sdf.format(birthday));
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in dd/mm/yyyy format.");
        }
    }
}