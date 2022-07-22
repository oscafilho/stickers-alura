package br.com.alura.main;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import br.com.alura.enumerator.API;
import br.com.alura.http.ClienteHttp;
import br.com.alura.model.Conteudo;
import br.com.alura.util.GeradorDeFigurinhas;

public class App {

    public static void main(String[] args) throws Exception {


        API minhaApi = API.MEME;

        // fazer uma conexao HTTP

        var http = new ClienteHttp();
        String json = http.buscaDados(minhaApi.url());

        // exibir e manipular os dados
        List<Conteudo> conteudos = minhaApi.extrator().extraiConteudos(json);

        var geradora = new GeradorDeFigurinhas();
        for (int i = 0; i < 2; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("Titulo: \u001b[1m" + conteudo.getTitulo());
            System.out.println("Poster: \u001b[1m" + conteudo.getUrlImage());

            System.out.println("===========================================");
        }

    }
}
