import java.io.*;
import java.util.*;

public class AirbnbSelecao {

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
            if (!this.country.equals(other.country)) {
                return this.country.compareTo(other.country);
            } else if (!this.city.equals(other.city)) {
                return this.city.compareTo(other.city);
            } else if (!this.neighbourhood.equals(other.neighbourhood)) {
                return this.neighbourhood.compareTo(other.neighbourhood);
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

        selectionSort(acomodacoes);

        System.out.println("Digite os roomIds das acomodações e 'FIM' para encerrar:");
        List<Integer> roomIds = new ArrayList<>();
        String input;
        while (!(input = scanner.nextLine()).equals("FIM")) {
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
                System.out.println("Acomodação não encontrada.");
            }
        }

        FileWriter logWriter = new FileWriter("matricula_selectionsort.txt");
        logWriter.write("778546\t" + comparacoes + "\t" + movimentacoes);
        logWriter.close();
    }

    private static Acomodacao[] lerAcomodacoes(String arquivo) throws IOException {
        List<Acomodacao> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); // Descarta a primeira linha (cabeçalho)
            String linha;
            while ((linha = br.readLine()) != null) {
                lista.add(new Acomodacao(linha));
            }
        }
        return lista.toArray(new Acomodacao[0]);
    }

    private static void selectionSort(Acomodacao[] acomodacoes) {
        int n = acomodacoes.length;
        for (int i = 0; i < n - 1; i++) {
            int menorIndice = i;
            for (int j = i + 1; j < n; j++) {
                comparacoes++;
                if (acomodacoes[j].compareTo(acomodacoes[menorIndice]) < 0) {
                    menorIndice = j;
                }
            }
            if (menorIndice != i) {
                Acomodacao temp = acomodacoes[i];
                acomodacoes[i] = acomodacoes[menorIndice];
                acomodacoes[menorIndice] = temp;
                movimentacoes++;
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
