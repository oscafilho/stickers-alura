package br.com.alura.extrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.alura.model.Conteudo;
import br.com.alura.util.JsonParser;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {

        // extrair só os dados que interessam (titulo, poster, classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudo
        for (Map<String, String> atributos : listaDeAtributos) {

            String titulo = atributos.get("title");
            String urlImage = atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            var conteudo = new Conteudo(titulo, urlImage);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}