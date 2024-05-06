import java.io.*;
import java.util.*;

public class AirbnbBubblesort {

    static class Acomodacao {
        private int roomId;
        private int hostId;
        private double overallSatisfaction;
        private ArrayList<String> dados;

        public Acomodacao() {
            dados = new ArrayList<>();
        }

        public void ler(String linha) {
            String[] partes = linha.split("\t");
            roomId = Integer.parseInt(partes[0]);
            hostId = Integer.parseInt(partes[1]);
            overallSatisfaction = Double.parseDouble(partes[7]);
    
            dados.clear(); 
            for (int i = 2; i < partes.length; i++) {
                dados.add(partes[i]);
            }
        }

        public String imprimir() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(roomId).append(" ## ");
            sb.append(hostId).append(" ## ");
            for (int i = 0; i < dados.size(); i++) {
                sb.append(dados.get(i));
                if (i < dados.size() - 1) {
                    sb.append(" ## ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

        public int compareTo(Acomodacao other) {
            if (this.overallSatisfaction == other.overallSatisfaction) {
                return Integer.compare(this.roomId, other.roomId); 
            }
            return Double.compare(other.overallSatisfaction, this.overallSatisfaction); 
        }
    }

    private static int comparacoes = 0;
    private static int movimentacoes = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Acomodacao> listaAcomodacoes = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("/tmp/dados_airbnb.txt"));
        br.readLine(); 
        String linha;
        while ((linha = br.readLine()) != null) {
            Acomodacao a = new Acomodacao();
            a.ler(linha);
            listaAcomodacoes.add(a); 
        }
        
        br.close();

        Acomodacao[] acomodacoes = new Acomodacao[listaAcomodacoes.size()];
        listaAcomodacoes.toArray(acomodacoes);

      //  System.out.println("Digite os roomIds das acomodações e 'FIM' para encerrar:");
        List<Integer> roomIds = new ArrayList<>();
        String input = scanner.nextLine();
        try {
            while (!(input = scanner.nextLine()).equals("FIM")) {
                try {
                    roomIds.add(Integer.parseInt(input));
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um número ou 'FIM' para encerrar.");
                }
            }
        } catch (NoSuchElementException e) {
           // System.out.println("Entrada finalizada prematuramente.");
        }

        for (int roomId : roomIds) {
            Acomodacao found = buscarPorRoomId(acomodacoes, roomId);
            if (found != null) {
                System.out.println(found.imprimir());
            } else {
                System.out.println("Acomodação não encontrada.");
            }
        }
    
        FileWriter logWriter = new FileWriter("matricula_bolha.txt");
        logWriter.write("778546\t" + comparacoes + "\t" + movimentacoes);
        logWriter.close();
    }

    private static void bubbleSort(Acomodacao[] acomodacoes) {
        int n = acomodacoes.length;
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                comparacoes++;  
                if (acomodacoes[j].compareTo(acomodacoes[j + 1]) > 0) {
                    Acomodacao temp = acomodacoes[j];
                    acomodacoes[j] = acomodacoes[j + 1];
                    acomodacoes[j + 1] = temp;
                    movimentacoes++;
                }
            }
        }
    }

    private static Acomodacao buscarPorRoomId(Acomodacao[] acomodacoes, int roomId) {
        for (Acomodacao a : acomodacoes) {
            if (a.roomId == roomId) {
                return a;
            }
        }
        return null;
    }
}
