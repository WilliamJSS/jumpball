package modelo;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class CoresFontes {

	// TODO Cores

	private static Color azulEscuro = new Color(0, 68, 169);
	
	private static Color azulClaro = new Color(26, 138, 224);
	
	public static Color getAzulEscuro() {
		return azulEscuro;
	}
	
	public static Color getAzulClaro() {
		return azulClaro;
	}

	// TODO Fontes

	private static Font fonteHemiHead = new Font("Hemi Head Rg", Font.PLAIN, 36);
	
	private static Font fonteBaloo = new Font("Baloo", Font.PLAIN, 36);
	
	private static Font fonteBotao = new Font("Luckiest Guy Regular", Font.PLAIN, 36);
	
	private static Font fonteMensagemSair = new Font("Kids Magazine", Font.PLAIN, 20);
	
	private static Font fonteMensagemMoedas = new Font("Kids Magazine", Font.PLAIN, 24);
	
	private static Font fonteTitulo = new Font("Kids Magazine", Font.PLAIN, 36);
	
	private static Font fonteTituloJogo = new Font("Kids Magazine", Font.PLAIN, 32);

	public static void carregarFontes() {
		
		try {
			
			Font fonte1 = Font.createFont(Font.TRUETYPE_FONT, new File("resources/font/hemi head bd it.otf"));
			
			Font fonte2 = Font.createFont(Font.TRUETYPE_FONT, new File("resources/font/Baloo-Regular.ttf"));
			
			Font fonte3 = Font.createFont(Font.TRUETYPE_FONT, new File("resources/font/LuckiestGuy-Regular.ttf"));
			
			Font fonte4 = Font.createFont(Font.TRUETYPE_FONT, new File("resources/font/Kids Magazine.ttf"));
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			
			ge.registerFont(fonte1);
			ge.registerFont(fonte2);
			ge.registerFont(fonte3);
			ge.registerFont(fonte4);
			
			// Exibir as fontes registradas
//			String []fontFamilies = ge.getAvailableFontFamilyNames();
//			
//			for (int i = 0; i < fontFamilies.length; i++) {
//				System.out.println(fontFamilies[i]);
//			}
			
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Font getFonteTituloJogo() {
		return fonteTituloJogo;
	}

	public static Font getFonteHemiHead() {
		return fonteHemiHead;
	}

	public static Font getFonteBaloo() {
		return fonteBaloo;
	}

	public static Font getFonteBotao() {
		return fonteBotao;
	}

	public static Font getFonteTitulo() {
		return fonteTitulo;
	}

	public static Font getFonteMensagemSair() {
		return fonteMensagemSair;
	}

	public static Font getFonteMensagemMoedas() {
		return fonteMensagemMoedas;
	}

}
