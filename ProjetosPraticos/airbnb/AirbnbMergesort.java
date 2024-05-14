import java.io.*;
import java.util.*;

public class AirbnbMergesort {

    static int comparacoes = 0;
    static int movimentacoes = 0;

    static class Acomodacao {
        private int roomId;
        private int hostId;
        private String roomType;
        private String country;
        private String city;
        private String neighbourhood;
        private int reviews;
        private double overallSatisfaction;
        private int accommodates;
        private double bedrooms;
        private double price;
        private String propertyType;

        public Acomodacao(String linha) {
            String[] dados = linha.split("\t");
            roomId = Integer.parseInt(dados[0]);
            hostId = Integer.parseInt(dados[1]);
            roomType = dados[2];
            country = dados[3];
            city = dados[4];
            neighbourhood = dados[5];
            reviews = Integer.parseInt(dados[6]);
            overallSatisfaction = Double.parseDouble(dados[7]);
            accommodates = Integer.parseInt(dados[8]);
            bedrooms = Double.parseDouble(dados[9]);
            price = Double.parseDouble(dados[10]);
            propertyType = dados[11];
        }

        public String imprimir() {
            return String.format("[%d ## %d ## %s ## %s ## %s ## %s ## %d ## %.1f ## %d ## %.1f ## %.1f ## %s]",
                roomId, hostId, roomType, country, city, neighbourhood, reviews, overallSatisfaction, accommodates, bedrooms, price, propertyType);
        }

        public int compareTo(Acomodacao other) {
            if (this.price != other.price) {
                comparacoes++;
                return Double.compare(this.price, other.price);
            } else {
                comparacoes++;
                return Integer.compare(this.roomId, other.roomId);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Acomodacao[] acomodacoes = lerAcomodacoes("/tmp/dados_airbnb.txt");

        long startTime = System.currentTimeMillis();
        mergeSort(acomodacoes, 0, acomodacoes.length - 1);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        List<Integer> roomIds = new ArrayList<>();
        String input;
        while (scanner.hasNextLine() && !(input = scanner.nextLine()).equals("FIM")) {
            try {
                roomIds.add(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número ou 'FIM' para encerrar.");
            }
        }

        for (int roomId : roomIds) {
            Acomodacao found = buscarPorRoomId(acomodacoes, roomId);
            if (found != null) {
                System.out.println(found.imprimir());
            } else {
                System.out.println("Acomodação com roomId " + roomId + " não encontrada.");
            }
        }

        FileWriter logWriter = new FileWriter("778546_bolha.txt");
        logWriter.write("778546\t" + elapsedTime + "\t" + comparacoes + "\t" + movimentacoes);
        logWriter.close();
    }

    private static Acomodacao[] lerAcomodacoes(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine();
            return br.lines().map(Acomodacao::new).toArray(Acomodacao[]::new);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new Acomodacao[0];
        }
    }

    private static void mergeSort(Acomodacao[] acomodacoes, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(acomodacoes, inicio, meio);
            mergeSort(acomodacoes, meio + 1, fim);
            merge(acomodacoes, inicio, meio, fim);
        }
    }

    private static void merge(Acomodacao[] acomodacoes, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        Acomodacao[] L = new Acomodacao[n1];
        Acomodacao[] R = new Acomodacao[n2];

        System.arraycopy(acomodacoes, inicio, L, 0, n1);
        System.arraycopy(acomodacoes, meio + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = inicio;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                acomodacoes[k] = L[i];
                i++;
            } else {
                acomodacoes[k] = R[j];
                j++;
            }
            k++;
            movimentacoes++;
        }

        while (i < n1) {
            acomodacoes[k] = L[i];
            i++;
            k++;
            movimentacoes++;
        }

        while (j < n2) {
            acomodacoes[k] = R[j];
            j++;
            k++;
            movimentacoes++;
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
