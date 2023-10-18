package williamjss.model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Fontes {

	private static Font hemiHead = new Font("Hemi Head Rg", Font.PLAIN, 36);

	private static Font baloo = new Font("Baloo", Font.PLAIN, 36);

	private static Font botao = new Font("Luckiest Guy Regular", Font.PLAIN, 36);

	private static Font mensagemSair = new Font("Kids Magazine", Font.PLAIN, 20);

	private static Font mensagemMoedas = new Font("Kids Magazine", Font.PLAIN, 24);

	private static Font titulo = new Font("Kids Magazine", Font.PLAIN, 36);

	private static Font tituloJogo = new Font("Kids Magazine", Font.PLAIN, 32);

	public static void carregarFontes() {

		try {

			Font fonte1 = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/hemi head bd it.otf"));

			Font fonte2 = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Baloo-Regular.ttf"));

			Font fonte3 = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/LuckiestGuy-Regular.ttf"));

			Font fonte4 = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/Kids Magazine.ttf"));

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			ge.registerFont(fonte1);
			ge.registerFont(fonte2);
			ge.registerFont(fonte3);
			ge.registerFont(fonte4);

			// Exibir as fontes registradas
			// String []fontFamilies = ge.getAvailableFontFamilyNames();

			// for (int i = 0; i < fontFamilies.length; i++) {
			// System.out.println(fontFamilies[i]);
			// }

		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Font getTituloJogo() {
		return tituloJogo;
	}

	public static Font getHemiHead() {
		return hemiHead;
	}

	public static Font getBaloo() {
		return baloo;
	}

	public static Font getBotao() {
		return botao;
	}

	public static Font getTitulo() {
		return titulo;
	}

	public static Font getMensagemSair() {
		return mensagemSair;
	}

	public static Font getMensagemMoedas() {
		return mensagemMoedas;
	}

}
