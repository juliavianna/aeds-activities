import java.util.Random;
import java.util.Scanner;

public class ativ_alteracaoAleatoria {
    
    private static final Random random = new Random(4);
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("FIM")) {
                break;
            }
            
            String saida = substituirLetras(entrada);
            System.out.println(saida);
        }
        scanner.close();
    }

    private static String substituirLetras(String str) {
        char letra1 = (char) ('a' + random.nextInt(26));
        char letra2;
        do {
            letra2 = (char) ('a' + random.nextInt(26));
        } while (letra1 == letra2);
        
        return substituirLetrasRecursivamente(str, letra1, letra2, 0);
    }

    private static String substituirLetrasRecursivamente(String str, char letra1, char letra2, int index) {
        if (index == str.length()) {
            return str;
        }
        
        if (str.charAt(index) == letra1) {
            str = str.substring(0, index) + letra2 + str.substring(index + 1);
        }
        
        return substituirLetrasRecursivamente(str, letra1, letra2, index + 1);
    }
}
