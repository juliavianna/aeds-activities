
    public class ativ3_dividirInt {
        public static void main(String[] args) {
            int dividendo = 25;
            int divisor = 5;
            
            int quociente = dividir(dividendo, divisor);
            System.out.println("Quociente: " + quociente);
        }
    
        public static int dividir(int dividendo, int divisor) {
            if (dividendo < divisor) {
                return 0;
            }
            
            return 1 + dividir(dividendo - divisor, divisor);
        }
    }
    
