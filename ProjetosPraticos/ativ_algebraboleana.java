import java.util.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ativ_algebraboleana {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String entrada = "3 0 0 0 or(or(and(not(and(A , B)) , not(C)) , and(not(A) , B , C) , and(A , B , C) , and(A , not(B) , not(C))) , and(A , not(B) , C))";
            if (entrada.equals("0")) {
                break;
            }
            
            String[] parts = entrada.split(" ");
            int n = Integer.parseInt(parts[0]);

            // Substituindo valores das variáveis na expressão
            String expression = entrada.substring(entrada.indexOf(parts[n + 1]));
            for (int i = 0; i < n; i++) {
                expression = expression.replace("(" + (char)('A' + i) + ")", parts[i + 1]);
            }

            // Avaliando a expressão modificada
            int result = evaluate(expression) ? 1 : 0;
            System.out.println(result);
        }
    }

    private static boolean evaluate(String expression) {
        expression = expression.replaceAll(" ", "").toLowerCase();
        if (expression.equals("1")) return true;
        if (expression.equals("0")) return false;

        // Avaliação de not
        if (expression.startsWith("not(")) {
            return !evaluate(expression.substring(4, expression.length() - 1));
        }

        // Avaliação de and
        if (expression.contains("and")) {
            String[] operands = expression.split("and");
            return evaluate(operands[0].substring(operands[0].indexOf('(') + 1)) && evaluate(operands[1].substring(0, operands[1].length() - 1));
        }

        // Avaliação de or
        if (expression.contains("or")) {
            String[] operands = expression.split("or");
            return evaluate(operands[0].substring(operands[0].indexOf('(') + 1)) || evaluate(operands[1].substring(0, operands[1].length() - 1));
        }

        throw new IllegalArgumentException("Expressão não reconhecida: " + expression);
    }
}

