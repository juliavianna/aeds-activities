import java.io.*;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        CarregarDadosAirbnb loader = new CarregarDadosAirbnb();
        ArrayList<Acomodacao> acomodacoes = loader.carregarAcomodacoes("dados_airbnb.txt");

        bubbleSort(acomodacoes);  

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o id:");
        int roomIdDigitado = scanner.nextInt();

        for (Acomodacao acomodacao : acomodacoes) {
            if (acomodacao.roomId == roomIdDigitado) {
                acomodacao.imprimir();
                break;
            }
        }
        scanner.close();
    }

    public static void bubbleSort(ArrayList<Acomodacao> acomodacoes) {
        int n = acomodacoes.size();
        Acomodacao temp;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (acomodacoes.get(j).compareTo(acomodacoes.get(j + 1)) > 0) {
                    temp = acomodacoes.get(j);
                    acomodacoes.set(j, acomodacoes.get(j + 1));
                    acomodacoes.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}

class Acomodacao implements Comparable<Acomodacao> {
    int roomId;
    int hostId;
    String roomType;
    String country;
    String city;
    String propertyType;
    String neighbourhood;
    int reviews;
    double overallSatisfaction;
    int accomodates;
    double bedrooms;
    double price;

    @Override
    public int compareTo(Acomodacao o) {
        if (this.overallSatisfaction != o.overallSatisfaction) {
            return Double.compare(o.overallSatisfaction, this.overallSatisfaction);
        }
        return Integer.compare(this.roomId, o.roomId);  /
    }

    public void imprimir() {
        System.out.println("[" + roomId + " ## " + hostId + " ## " + roomType + " ## " + country + " ## " + city + " ## " + neighbourhood + " ## " + reviews + " ## " + overallSatisfaction + " ## " + accomodates + " ## " + bedrooms + " ## " + price + "]");
    }
}

class CarregarDadosAirbnb {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("/tmp/dados_airbnb.txt"));
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        fileReader.readLine();

        int n = Integer.parseInt(stdin.readLine().trim());

        Map<Integer, Acomodacao> map = new HashMap<>();
        String line;
        while ((line = fileReader.readLine()) != null) {
            Acomodacao acom = new Acomodacao(line);
            map.put(acom.roomId, acom);
        }
        fileReader.close();

        // Reading roomIds from stdin and filling the list to sort
        List<Acomodacao> accommodations = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int roomId = Integer.parseInt(stdin.readLine().trim());
            if (map.containsKey(roomId)) {
                accommodations.add(map.get(roomId));
            }
        }

        long startTime = System.currentTimeMillis();
        bubbleSort(accommodations);
        long endTime = System.currentTimeMillis();

        accommodations.forEach(System.out::println);

        try (PrintWriter logWriter = new PrintWriter(new FileWriter("7778546_bolha.txt"))) {
            logWriter.println("7778546\t" + (endTime - startTime) + "\t" + comparisons + "\t" + swaps);
        }
    }

    static int comparisons = 0, swaps = 0;

    public static void bubbleSort(List<Acomodacao> list) {
        boolean swapped;
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
