import java.util.*;
//2- Escreva um código recursivo em JAVA para somar os digitos de um num inteiro

public class ativ2_somarDigitosNumInteiro{
    public static int somaDigitos(int num) {
        if (num == 0) {
            return 0;
        }
        return num % 10 + somaDigitos(num / 10);
    }
    
    public static void main(String[] args) {
        int numero = 582; 
        int resultado = somaDigitos(numero);
        System.out.println(resultado); 
    }

}