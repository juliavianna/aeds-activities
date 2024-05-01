import java.util.Scanner;
public class ativ_isRecursivo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("FIM")) {
                break;
            }

            String resultado = classifyString(entrada);
            System.out.println(resultado);
        }
        scanner.close();

    }

    private static String classifyString(String str) {
        str = str.replaceAll("\\s+", ""); 
        str = str.replaceAll("[.,]", ""); 
        return (isOnlyVowels(str, 0) ? "SIM" : "NAO") + " " +
               (isOnlyConsonants(str, 0) ? "SIM" : "NAO") + " " +
               (isInteger(str, 0) ? "SIM" : "NAO") + " " +
               (isRealNumber(str, 0) ? "SIM" : "NAO");
    }

    private static boolean isOnlyVowels(String s, int index) {
        if (index == s.length()) return true;
        char c = Character.toLowerCase(s.charAt(index));
        return "aeiou".indexOf(c) >= 0 && isOnlyVowels(s, index + 1);
    }

    private static boolean isOnlyConsonants(String s, int index) {
        if (index == s.length()) return true;
        char c = Character.toLowerCase(s.charAt(index));
        return "bcdfghjklmnpqrstvwxyz".indexOf(c) >= 0 && isOnlyConsonants(s, index + 1);
    }

    private static boolean isInteger(String s, int index) {
        if (s.isEmpty() || (s.charAt(0) == '-' && s.length() == 1)) return false; // Trata "-" como não inteiro
        if (index == s.length()) return true;
        char c = s.charAt(index);
        return Character.isDigit(c) || (c == '-' && index == 0) && isInteger(s, index + 1);
    }

    private static boolean isRealNumber(String s, int index) {
        if (s.isEmpty()) return false;
        if (index == s.length()) return true;
        char c = s.charAt(index);
        if (index == 0 && (c == '-' || c == '+')) return isRealNumber(s, index + 1); // Permite sinal no início
        return Character.isDigit(c) || c == '.' && countMatches(s, '.') == 1 && isRealNumber(s, index + 1);
    }

    private static int countMatches(String s, char search) {
        return (int) s.chars().filter(ch -> ch == search).count();
    }
}