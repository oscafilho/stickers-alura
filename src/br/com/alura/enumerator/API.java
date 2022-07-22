package br.com.alura.enumerator;

import br.com.alura.extrator.ExtratorDeConteudo;
import br.com.alura.extrator.ExtratorDeConteudoDaNasa;
import br.com.alura.extrator.ExtratorDeConteudoDeMeme;
import br.com.alura.extrator.ExtratorDeConteudoDoIMDB;

public enum API {

    IMDB("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json",
            new ExtratorDeConteudoDoIMDB()),

    NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json",
            new ExtratorDeConteudoDaNasa()),

    MEME("https://api.mocki.io/v2/549a5d8b/Memes", new ExtratorDeConteudoDeMeme());

    private String url;

    private ExtratorDeConteudo extrator;

    API(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String url() {
        return url;
    }

    public ExtratorDeConteudo extrator() {
        return extrator;
    }

}