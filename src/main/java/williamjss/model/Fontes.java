package williamjss.model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Fontes {

    private static Font hemiHead;
    private static Font baloo;
    private static Font botao;
    private static Font mensagemSair;
    private static Font mensagemMoedas;
    private static Font titulo;
    private static Font tituloJogo;

    public static void carregarFontes() throws URISyntaxException {

        try {

            URL resourceFonte1 = Fontes.class.getClassLoader().getResource("font/hemi head bd it.otf");
            URL resourceFonte2 = Fontes.class.getClassLoader().getResource("font/Baloo-Regular.ttf");
            URL resourceFonte3 = Fontes.class.getClassLoader().getResource("font/LuckiestGuy-Regular.ttf");
            URL resourceFonte4 = Fontes.class.getClassLoader().getResource("font/Kids Magazine.ttf");

            Font fonte1 = Font.createFont(Font.TRUETYPE_FONT, new File(resourceFonte1.toURI()));
            Font fonte2 = Font.createFont(Font.TRUETYPE_FONT, new File(resourceFonte2.toURI()));
            Font fonte3 = Font.createFont(Font.TRUETYPE_FONT, new File(resourceFonte3.toURI()));
            Font fonte4 = Font.createFont(Font.TRUETYPE_FONT, new File(resourceFonte4.toURI()));

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(fonte1);
            ge.registerFont(fonte2);
            ge.registerFont(fonte3);
            ge.registerFont(fonte4);

            // Exibir as fontes registradas
            // String[] fontFamilies = ge.getAvailableFontFamilyNames();

            // for (int i = 0; i < fontFamilies.length; i++) {
            // System.out.println(fontFamilies[i]);
            // }

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Font getTituloJogo() {
        if (tituloJogo == null) {
            tituloJogo = new Font("Kids Magazine", Font.PLAIN, 32);
        }
        return tituloJogo;
    }

    public static Font getHemiHead() {
        if (hemiHead == null) {
            hemiHead = new Font("Hemi Head Rg", Font.PLAIN, 36);
        }
        return hemiHead;
    }

    public static Font getBaloo() {
        if (baloo == null) {
            baloo = new Font("Baloo", Font.PLAIN, 36);
        }
        return baloo;
    }

    public static Font getBotao() {
        if (botao == null) {
            botao = new Font("Luckiest Guy Regular", Font.PLAIN, 36);
        }
        return botao;
    }

    public static Font getTitulo() {
        if (titulo == null) {
            titulo = new Font("Kids Magazine", Font.PLAIN, 36);
        }
        return titulo;
    }

    public static Font getMensagemSair() {
        if (mensagemSair == null) {
            mensagemSair = new Font("Kids Magazine", Font.PLAIN, 20);
        }
        return mensagemSair;
    }

    public static Font getMensagemMoedas() {
        if (mensagemMoedas == null) {
            mensagemMoedas = new Font("Kids Magazine", Font.PLAIN, 24);
        }
        return mensagemMoedas;
    }

}
