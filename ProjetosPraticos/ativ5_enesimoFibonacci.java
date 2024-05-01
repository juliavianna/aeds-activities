import java.util.Scanner;

public class ativ5_enesimoFibonacci {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
        
            String entrada = scanner.nextLine();
            scanner.close(); 

            try {
                int n = Integer.parseInt(entrada);
                int resultado = fibonacci(n);
                System.out.println(resultado);
            } catch (NumberFormatException e) {
                System.out.println("insira um número inteiro válido.");
            }
    
        }
    
        public static int fibonacci(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    