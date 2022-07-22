
package br.com.alura.extrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.alura.model.Conteudo;
import br.com.alura.util.JsonParser;

public class ExtratorDeConteudoDeMeme implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json) {

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("meme");
            String urlImagem = atributos.get("urlImagem");

            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
