import java.util.ArrayList;

public class testing
{
    public static void main(String[] args) {
        final String[] arr = new String[5];
        System.out.println(arr.length);
        arr[2] = "asdf";
        
        for (String s : arr) {
            if (s == null) System.out.println("YES it is null");
            else System.out.println(s + "NO");
        }
    }
}
