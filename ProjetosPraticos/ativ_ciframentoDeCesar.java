import java.util.Scanner;

public class ativ_ciframentoDeCesar {
    public static void main(String[] args) {
        // Exemplos de entrada
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("FIM")) {
                break;
            }
            String mensagemCifrada = cifrar(entrada, 0);
            System.out.println(mensagemCifrada);
        }
        scanner.close();
            
    }
    
    public static String cifrar(String mensagem, int index) {
        if (index == mensagem.length()) {
            return "";
        }
            
        char cifrado = cifrarCaractere(mensagem.charAt(index));
        return cifrado + cifrar(mensagem, index + 1);
    }
    
    private static char cifrarCaractere(char caractere) {
        final int deslocamento = 3;
        if (caractere == ' ') {
            return '#';
        } else if (caractere >= 'a' && caractere <= 'z') {
            return (char) ((caractere - 'a' + deslocamento) % 26 + 'a');
        } else if (caractere >= 'A' && caractere <= 'Z') {
            return (char) ((caractere - 'A' + deslocamento) % 26 + 'A');
        } else if (caractere >= '0' && caractere <= '9') {
            return (char) ((caractere - '0' + deslocamento) % 10 + '0');
        } else if ((caractere >= '!' && caractere <= '/')) { 
            return (char) ((caractere - '!' + deslocamento) % ( '/' - '!' + 1) + '!');
        }
        return caractere;
    }
}
    