import java.util.LinkedList;
import java.util.Scanner;


public class EstacionamentoFilas {
    LinkedList<Carro> queue = new LinkedList<>();
    LinkedList<String[]> commands = new LinkedList<>();


    public void arrive(String placa) {
        queue.add(new Carro(placa));
        System.out.println("Carro de placa " + placa + " entrou no estacionamento.");
    }

    public void tirarCarro(String placa) {
        boolean achou = false;
        int numMoves = 0;

        for (int i = 0; i < queue.size(); i++) {
            Carro carro = queue.get(i);
            if (carro.placa.equals(placa)) {
                achou = true;
                queue.remove(i);
                System.out.println("Carro de placa " + placa + " saiu do estacionamento.");
                System.out.println("Esse carro foi manobrado " + carro.moves + " vezes.");
                break;
            } else {
                carro.moves++;
            }
        }

        if (!achou) {
            System.out.println("Carro nao encontrado!");
        }
    }
    public void processCommands() {
        for (String[] command : commands) {
            String action = command[0];
            String placa = command[1];

            if (action.equalsIgnoreCase("C")) {
                arrive(placa);
            } else if (action.equalsIgnoreCase("P")) {
                tirarCarro(placa);
            }
        }
    }

    public void imprimirCarrosNoEstacionamento() {
        if (!queue.isEmpty()) {
           
            for (Carro carro : queue) {
                System.out.println(carro.placa);
            }
        } else {
            System.out.println("Nenhum carro está no estacionamento.");
        }
    }

    public static void main(String[] args) {
        EstacionamentoFilas estacionamento = new EstacionamentoFilas();
        //System.out.println("Digite os comandos e as placas: ");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("FIM")) {
                break;
            }

            String[] parts = line.split(" ");
            if (parts.length < 2) {
                continue; 
            }
            String command = parts[0];
            StringBuilder placaBuilder = new StringBuilder();
            for (int i = 1; i < parts.length; i++) {
                if (i > 1) placaBuilder.append(" ");
                placaBuilder.append(parts[i]);
            }
            String placa = placaBuilder.toString();
            estacionamento.commands.add(new String[]{parts[0], placa});
        }

        estacionamento.processCommands();
        estacionamento.imprimirCarrosNoEstacionamento();
        scanner.close();
    }
}

class Carro {
    String placa;
    int moves;

    Carro(String placa) {
        this.placa = placa;
        this.moves = 0;
    }
}
