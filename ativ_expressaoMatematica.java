import java.util.*;

public class ativ_expressaoMatematica {
    private static int precedence(String ch) {
        switch (ch) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return -1;
        }
    }

    private static boolean isOperator(String c) {
        return "+-*/".contains(c);
    }

    private static String infixToPostfix(String expression) {
        Stack<String> operators = new Stack<>();
        StringBuilder output = new StringBuilder();
        
        for (String token : expression.split("\\s+")) {
            if (token.matches("\\d+") || token.matches("[a-zA-Z]+")) { // Identificador ou número
                output.append(token).append(" ");
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.append(operators.pop()).append(" ");
                }
                operators.pop();
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    output.append(operators.pop()).append(" ");
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            output.append(operators.pop()).append(" ");
        }

        return output.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if ("FIM".equals(line)) break;
            System.out.println(infixToPostfix(line));
        }
        scanner.close();
    }
}


class Stack<E> {
    private static class StackNode<E> {
        E data;
        StackNode<E> next;

        StackNode(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private StackNode<E> top;

    public void push(E item) {
        StackNode<E> node = new StackNode<>(item);
        node.next = top;
        top = node;
    }

    public E pop() {
        if (top == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        E data = top.data;
        top = top.next;
        return data;
    }

    public E peek() {
        if (top == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}