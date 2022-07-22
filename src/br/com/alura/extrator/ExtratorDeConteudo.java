package br.com.alura.extrator;

import java.util.List;

import br.com.alura.model.Conteudo;

public interface ExtratorDeConteudo {
    
    public List<Conteudo> extraiConteudos(String json);
}