package model;

import java.io.File;

public class Arquivo {

	private static File arquivoLog = new File("resources/archive/config.txt");

	private static File arquivoConfigInicial = new File("resources/archive/config-inicial.txt");

	public static File getArquivoLog() {
		return arquivoLog;
	}
	
	public static File getArquivoConfigInicial() {
		return arquivoConfigInicial;
	}
	
}
