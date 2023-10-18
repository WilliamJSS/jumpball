package williamjss.model;

import java.io.File;

public class Audios {

	// Musicas

	private static File musicaMenu = new File("src/resources/audio/On The Go.wav");

	private static File musicaCenario = new File("src/resources/audio/Saiko Music Fundo.wav");

	public static File getMusicaMenu() {
		return musicaMenu;
	}

	public static File getMusicaCenario() {
		return musicaCenario;
	}

	// Toques

//	private static File toqueVitoria = new File("src/resources/audio/bolinha de golfe.wav");
	private static File toqueVitoria = new File("src/resources/audio/toque-vitoria.wav");

	private static File toqueDerrota = new File("src/resources/audio/toque-derrota.wav");
//	private static File toqueDerrota = new File("src/resources/audio/splash-agua.wav");
//	private static File toqueDerrota = new File("src/resources/audio/splash-lava.wav");

	private static File navegarMenu = new File("src/resources/audio/navegar-menu-2.wav");

	private static File selecionarBotao = new File("src/resources/audio/navegar-menu.wav");

	public static File getToqueDerrota() {
		return toqueDerrota;
	}

	public static File getToqueVitoria() {
		return toqueVitoria;
	}

	public static File getToqueNavegarMenu() {
		return navegarMenu;
	}

	public static File getToqueSelecionarBotao() {
		return selecionarBotao;
	}

	// Efeitos sonoros

	private static File efeitoPulo = new File("src/resources/audio/pulo.wav");

	private static File efeitoPegarMoeda = new File("src/resources/audio/pegar-moeda.wav");

	public static File getEfeitoPulo() {
		return efeitoPulo;
	}

	public static File getEfeitoPegarMoeda() {
		return efeitoPegarMoeda;
	}

}
