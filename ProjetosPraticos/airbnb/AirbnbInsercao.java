import java.io.*;
import java.util.*;


public class AirbnbInsercao {
    private static int comparacoes = 0;
    private static int movimentacoes = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String arquivo = "/tmp/dados_airbnb.txt";
        Acomodacao[] acomodacoes = lerAcomodacoes(arquivo);

        long startTime = System.currentTimeMillis();
        insertionSort(acomodacoes);
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println("Digite os roomIds das acomodações e 'FIM' para encerrar:");
        List<Integer> roomIds = new ArrayList<>();
        String input;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input.equals("FIM")) {
                break;
            }
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
        FileWriter logWriter = new FileWriter("matricula_insercao.txt");
        logWriter.write("778546\t" + timeElapsed + "\t" + comparacoes + "\t" + movimentacoes);
        logWriter.close();
    }

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

        public int getRoomId() {
            return roomId;
        }

        public String imprimir() {
            return String.format("[%d ## %d ## %s ## %s ## %s ## %s ## %d ## %.1f ## %d ## %.1f ## %.1f ## %s]",
                roomId, hostId, roomType, country, city, neighbourhood, reviews, overallSatisfaction, accommodates, bedrooms, price, propertyType);
        }
    }

    public static Acomodacao[] lerAcomodacoes(String arquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine();  // Descarta a primeira linha (cabeçalho)
            String linha;
            Acomodacao[] acomodacoes = br.lines().map(Acomodacao::new).toArray(Acomodacao[]::new);
            return acomodacoes;
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            throw e;
        }
    }

    private static void insertionSort(Acomodacao[] acomodacoes) {
        for (int i = 1; i < acomodacoes.length; i++) {
            Acomodacao key = acomodacoes[i];
            int j = i - 1;

            while (j >= 0 && compareAcomodacoes(acomodacoes[j], key) > 0) {
                acomodacoes[j + 1] = acomodacoes[j];
                movimentacoes++;
                j--;
            }
            acomodacoes[j + 1] = key;
            movimentacoes++;
        }
    }

    private static int compareAcomodacoes(Acomodacao a1, Acomodacao a2) {
        comparacoes++;
        if (a1.accommodates == a2.accommodates) {
            return Integer.compare(a1.roomId, a2.roomId);
        }
        return Integer.compare(a1.accommodates, a2.accommodates);
    }

    private static Acomodacao buscarPorRoomId(Acomodacao[] acomodacoes, int roomId) {
        for (Acomodacao a : acomodacoes) {
            if (a.getRoomId() == roomId) {
                return a;
            }
        }
        return null;
    }
}
