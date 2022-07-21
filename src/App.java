
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;

public class App {

    public static void main(String[] args) throws Exception {

        try (InputStream input = new FileInputStream("src/resources/config.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            // fazer uma conexao HTTP e buscar os top 250 filmes
            // String url = prop.getProperty("imdb.url") +
            // prop.getProperty("imdb.user.key");
            String url = prop.getProperty("imdb.url.250Movies");
            URI endereco = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            String body = response.body();

            Gson g = new Gson();
            Imdb c = g.fromJson(body, Imdb.class);
            List<Map<String, String>> listaDeFilmes = c.items;

            // exibir e manipular os dados
            var geradora = new GeradorDeFigurinhas();
            for (Map<String, String> filme : listaDeFilmes) {

                var urlImage = filme.get("image");
                var titulo = filme.get("title");
                var classificacao = filme.get("imDbRating");

                InputStream inputStream = new URL(urlImage).openStream();
                String nomeArquivo = titulo + ".png";

                geradora.cria(inputStream, nomeArquivo);

                System.out.println("Titulo: \u001b[1m" + titulo);
                System.out.println("Poster: \u001b[1m" + urlImage);
                System.out.println("\u001b[37m \u001b[45m Classificação: " + classificacao + "\u001b[m");

                int intRating = (int) Double.parseDouble(classificacao);
                System.out.println("\u2B50".repeat(intRating));
                System.out.println("===========================================");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
