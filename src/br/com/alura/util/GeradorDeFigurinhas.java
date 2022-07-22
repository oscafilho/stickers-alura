package br.com.alura.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.FontMetrics;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var font = new Font("Comic Sans MS", Font.BOLD, 64);
        graphics.setFont(font);
        graphics.setColor(Color.RED);

        // escrever uma frase na nova imagem
        String txtImagem = "TOPZERA";
        FontMetrics fm = graphics.getFontMetrics();
        int x = ((largura - fm.stringWidth(txtImagem)) / 2);

        graphics.drawString(txtImagem, x, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        String path = "saida/";
        File pathAsFile = new File(path);
        if (!Files.exists(Paths.get(path))) {
            pathAsFile.mkdirs();
        }

        ImageIO.write(novaImagem, "png", new File(path + nomeArquivo));

    }

}