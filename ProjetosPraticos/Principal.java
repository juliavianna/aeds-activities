import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        CarregarDadosAirbnb loader = new CarregarDadosAirbnb();
        ArrayList<Acomodacao> acomodacoes = loader.carregarAcomodacoes("dados_airbnb.txt");
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
}

class Acomodacao {
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

    public Acomodacao(int roomId, int hostId, String roomType, String country, String city, String propertyType, int reviews, double overallSatisfaction, int accomodates, double bedrooms, double price) {
        this.roomId = roomId;
        this.hostId = hostId;
        this.roomType = roomType;
        this.country = country;
        this.city = city;
        this.propertyType = propertyType;
        this.neighbourhood = propertyType; // Ajustado para uso correto
        this.reviews = reviews;
        this.overallSatisfaction = overallSatisfaction;
        this.accomodates = accomodates;
        this.bedrooms = bedrooms;
        this.price = price;
    }

    public void imprimir() {
        System.out.println("[" + roomId + " ## " + hostId + " ## " + roomType + " ## " + country + " ## " + city + " ## " + neighbourhood + " ## " + reviews + " ## " + overallSatisfaction + " ## " + accomodates + " ## " + bedrooms + " ## " + price + "]");
    }
}

class CarregarDadosAirbnb {
    public ArrayList<Acomodacao> carregarAcomodacoes(String fileName) {
        ArrayList<Acomodacao> acomodacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // Lê e descarta a primeira linha

            while ((line = reader.readLine()) != null && !line.equals("FIM")) {
                String[] data = line.split("\t"); // dados estão separados por tab
                Acomodacao acomodacao = new Acomodacao(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        Integer.parseInt(data[6]),
                        Double.parseDouble(data[7]),
                        Integer.parseInt(data[8]),
                        Double.parseDouble(data[9]),
                        Double.parseDouble(data[10])
                );
                acomodacoes.add(acomodacao);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acomodacoes;
    }
}
