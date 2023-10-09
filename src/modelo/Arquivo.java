package modelo;

import java.io.File;

public class Arquivo {

	private static File arquivoLog = new File("recursos/arquivo/config.txt");

	private static File arquivoConfigInicial = new File("recursos/arquivo/config-inicial.txt");

	public static File getArquivoLog() {
		return arquivoLog;
	}
	
	public static File getArquivoConfigInicial() {
		return arquivoConfigInicial;
	}
	
}
