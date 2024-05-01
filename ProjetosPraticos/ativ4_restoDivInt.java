public class ativ4_restoDivInt {
    public static int calculaResto(int dividendo, int divisor) {
        if (dividendo == 0){
            return 0; 
        }
        while (dividendo >= divisor){
            dividendo -= divisor;
        }

        return dividendo;
    }
    public static void main(String[] args) {
        int dividendo = 16;
        int divisor = 3;
        
        int resto = calculaResto(dividendo, divisor);
        
        System.out.println(resto);
    }
}