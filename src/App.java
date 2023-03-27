
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_zfdftcdl";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
       

        // pegar só so dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
    
        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("Filme: " + "\u001B[35m" + filme.get("title") + "\u001B[35m");
            System.out.println("Ano: " + "\u001B[32m" + filme.get("year") + "\u001B[32m");
            System.out.println("Avaliação do IMDb: " + "\u001B[33m" + filme.get("imDbRating") + "\u001B[33m" + " \u2B50");
            System.out.println();
        }
    }
}
