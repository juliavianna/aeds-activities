import java.io.*;
import java.util.*;


public class AirbnbQuicksort {

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
                return Double.compare(this.price, other.price);
            } else if (!this.roomType.equals(other.roomType)) {
                return this.roomType.compareTo(other.roomType);
            } else {
                return Integer.compare(this.roomId, other.roomId);
            }
        }
    }

    private static int comparacoes = 0;
    private static int movimentacoes = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Acomodacao[] acomodacoes = lerAcomodacoes("/tmp/dados_airbnb.txt");

        quickSort(acomodacoes, 0, acomodacoes.length - 1);

       // System.out.println("Digite os roomIds das acomodações e 'FIM' para encerrar:");
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

        FileWriter logWriter = new FileWriter("matricula_quicksort.txt");
        logWriter.write("778546\t" + comparacoes + "\t" + movimentacoes);
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

    private static void quickSort(Acomodacao[] acomodacoes, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionar(acomodacoes, inicio, fim);
            quickSort(acomodacoes, inicio, indicePivo - 1);
            quickSort(acomodacoes, indicePivo + 1, fim);
        }
    }

    private static int particionar(Acomodacao[] acomodacoes, int inicio, int fim) {
        Acomodacao pivo = acomodacoes[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            comparacoes++;
            if (acomodacoes[j].compareTo(pivo) <= 0) {
                i++;
                Acomodacao temp = acomodacoes[i];
                acomodacoes[i] = acomodacoes[j];
                acomodacoes[j] = temp;
                movimentacoes++;
            }
        }
        Acomodacao temp = acomodacoes[i + 1];
        acomodacoes[i + 1] = acomodacoes[fim];
        acomodacoes[fim] = temp;
        movimentacoes++;
        return i + 1;
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
