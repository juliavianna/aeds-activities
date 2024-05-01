import java.util.*;
//1- Escreva um código recursivo em JAVA que conte os dígitos de um número inteiro.

import java.util.Scanner;

public class ativ1_contarDigitosNumInteiro{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.printf("Digite um numero: ");
        int numero = scanner.nextInt();
        
        String s = Integer.toString(numero);
        System.out.println(s.length());
    }
}