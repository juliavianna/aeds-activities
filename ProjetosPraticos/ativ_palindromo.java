import java.util.ArrayList;
import java.util.Scanner;

public class ativ_palindromo {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equals("#")) {
                break;
            }
            
            String entradaLimpa = entrada.replaceAll("[^a-zA-Z]", "").toLowerCase();
            
            boolean ehPalindromo = verificarPalindromo(entradaLimpa, 0, entradaLimpa.length() - 1);
            System.out.println(ehPalindromo ? "SIM" : "NAO");
        }
        
        scanner.close();
    }

    private static boolean verificarPalindromo(String str, int inicio, int fim) {
        if (inicio >= fim) {
            return true;
        }
        if (str.charAt(inicio) != str.charAt(fim)) {
            return false;
        }
        return verificarPalindromo(str, inicio + 1, fim - 1);
    }
}
