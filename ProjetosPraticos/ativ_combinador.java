import java.util.Scanner;

public class ativ_combinador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) { 
            String primeiro = scanner.nextLine();
            if (primeiro.equals("FIM")) break; 
            
            if (scanner.hasNextLine()) { 
                String segundo = scanner.nextLine();
                System.out.println(combinador(primeiro, segundo)); 
            }
        }
        scanner.close();
    }

    public static String combinador(String s1, String s2) {
        StringBuilder result = new StringBuilder();
        int maxLength = Math.max(s1.length(), s2.length());
        
        for (int i = 0; i < maxLength; i++) {
            if (i < s1.length()) {
                result.append(s1.charAt(i));
            }
            if (i < s2.length()) {
                result.append(s2.charAt(i));
            }
        }
        
        return result.toString();
    }
}
