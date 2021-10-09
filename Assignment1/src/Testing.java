import java.util.Scanner;

public class Testing
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("HELLO PORTAL");
        System.out.print("Vaccine Name: ");
        String name = sc.nextLine().toLowerCase();
        System.out.print("Number of Doses: ");
        int doses = sc.nextInt();
        System.out.println(name);
        System.out.println(doses);
    }
}
