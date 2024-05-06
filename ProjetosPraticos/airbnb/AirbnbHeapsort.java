import java.io.*;
import java.util.*;

public class AirbnbHeapsort {

    static class Acomodacao {
        private int roomId;
        private int hostId;
        private double overallSatisfaction;
        private int reviews;
        private ArrayList<String> dados;

        public Acomodacao() {
            dados = new ArrayList<>();
        }

        public void ler(String linha) {
            String[] partes = linha.split("\t");
            roomId = Integer.parseInt(partes[0]);
            hostId = Integer.parseInt(partes[1]);
            reviews = Integer.parseInt(partes[6]);
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
            if (this.reviews != other.reviews) {
                return Integer.compare(this.reviews, other.reviews);
            } else {
                return Integer.compare(this.roomId, other.roomId);
            }
        }
    }

    private static int comparacoes = 0;
    private static int movimentacoes = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Acomodacao> listaAcomodacoes = new ArrayList<>();

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
        
        heapSort(acomodacoes);

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
            Acomodacao found = null;
            for (Acomodacao a : acomodacoes) {
                if (a.roomId == roomId) {
                    found = a;
                    System.out.println(a.imprimir());
                    break;
                }
            }
            if (found == null) {
                System.out.println("Acomodação não encontrada.");
            }
        }

        FileWriter logWriter = new FileWriter("matricula_heapsort.txt");
        logWriter.write("778546\t" + comparacoes + "\t" + movimentacoes);
        logWriter.close();
    }

    private static void heapSort(Acomodacao[] acomodacoes) {
        int n = acomodacoes.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(acomodacoes, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(acomodacoes, 0, i);
            movimentacoes++;
            heapify(acomodacoes, i, 0);
        }
    }

    private static void heapify(Acomodacao[] acomodacoes, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n && acomodacoes[esquerda].compareTo(acomodacoes[maior]) > 0) {
            maior = esquerda;
        }
        if (direita < n && acomodacoes[direita].compareTo(acomodacoes[maior]) > 0) {
            maior = direita;
        }
        if (maior != i) {
            swap(acomodacoes, i, maior);
            movimentacoes++;
            heapify(acomodacoes, n, maior);
        }
    }

    private static void swap(Acomodacao[] acomodacoes, int i, int j) {
        Acomodacao temp = acomodacoes[i];
        acomodacoes[i] = acomodacoes[j];
        acomodacoes[j] = temp;
    }
}
