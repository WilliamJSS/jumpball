package controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import modelo.Arquivo;
import visao.Cenario;
import visao.Fases;
import visao.MiniCenario;

public class GerenciadorLog {

	private Fases fases;

	private MiniCenario miniCenarioCampo;
	private MiniCenario miniCenarioMontanhas;
	private MiniCenario miniCenarioNeve;
	private MiniCenario miniCenarioPraia;
	private MiniCenario miniCenarioVulcao;
	private MiniCenario miniCenarioVolei;
	private MiniCenario miniCenarioBasquete;
	private MiniCenario miniCenarioGolfe;

	private File arquivo;
	private int totalEstrelas;

	public GerenciadorLog(Fases fases) {
		this.fases = fases;
		this.miniCenarioCampo = fases.getMiniCenarioCampo();
		this.miniCenarioMontanhas = fases.getMiniCenarioMontanhas();
		this.miniCenarioNeve = fases.getMiniCenarioNeve();
		this.miniCenarioPraia = fases.getMiniCenarioPraia();
		this.miniCenarioVulcao = fases.getMiniCenarioVulcao();
		this.miniCenarioVolei = fases.getMiniCenarioVolei();
		this.miniCenarioBasquete = fases.getMiniCenarioBasquete();
		this.miniCenarioGolfe = fases.getMiniCenarioGolfe();
		this.arquivo = Arquivo.getArquivoLog();
		
		lerArquivoLog();
		carregarConfig();
	}

	public void carregarConfig() {

		miniCenarioCampo.setEstrelasRestantes(miniCenarioCampo.getEstrelasDesbloquear() - getTotalEstrelas());
		miniCenarioCampo.atualizarMiniCenario();
		if (miniCenarioCampo.getEstrelasRestantes() <= 0 && getTotalEstrelas() > 0 && miniCenarioCampo.isBloqueado()) {
			miniCenarioCampo.desbloquearCenario();
		}

		miniCenarioMontanhas.setEstrelasRestantes(miniCenarioMontanhas.getEstrelasDesbloquear() - getTotalEstrelas());
		miniCenarioMontanhas.atualizarMiniCenario();
		if (miniCenarioMontanhas.getEstrelasRestantes() <= 0 && getTotalEstrelas() > 0 && miniCenarioMontanhas.isBloqueado()) {
			miniCenarioMontanhas.desbloquearCenario();
		}

		miniCenarioNeve.setEstrelasRestantes(miniCenarioNeve.getEstrelasDesbloquear() - getTotalEstrelas());
		miniCenarioNeve.atualizarMiniCenario();
		if (miniCenarioNeve.getEstrelasRestantes() <= 0 && getTotalEstrelas() > 0 && miniCenarioNeve.isBloqueado()) {
			miniCenarioNeve.desbloquearCenario();
		}

		miniCenarioPraia.setEstrelasRestantes(miniCenarioPraia.getEstrelasDesbloquear() - getTotalEstrelas());
		miniCenarioPraia.atualizarMiniCenario();
		if (miniCenarioPraia.getEstrelasRestantes() <= 0 && getTotalEstrelas() > 0 && miniCenarioPraia.isBloqueado()) {
			miniCenarioPraia.desbloquearCenario();
		}

		miniCenarioVulcao.setEstrelasRestantes(miniCenarioVulcao.getEstrelasDesbloquear() - getTotalEstrelas());
		miniCenarioVulcao.atualizarMiniCenario();
		if (miniCenarioVulcao.getEstrelasRestantes() <= 0 && getTotalEstrelas() > 0 && miniCenarioVulcao.isBloqueado()) {
			miniCenarioVulcao.desbloquearCenario();
		}
		
		miniCenarioVolei.setEstrelasRestantes(miniCenarioVolei.getEstrelasDesbloquear() - getTotalEstrelas());
		miniCenarioVolei.atualizarMiniCenario();
		if (miniCenarioVolei.getEstrelasRestantes() <= 0 && getTotalEstrelas() > 0 && miniCenarioVolei.isBloqueado()) {
			miniCenarioVolei.desbloquearCenario();
		}

		miniCenarioBasquete.setEstrelasRestantes(miniCenarioBasquete.getEstrelasDesbloquear() - getTotalEstrelas());
		miniCenarioBasquete.atualizarMiniCenario();
		if (miniCenarioBasquete.getEstrelasRestantes() <= 0 && getTotalEstrelas() > 0 && miniCenarioBasquete.isBloqueado()) {
			miniCenarioBasquete.desbloquearCenario();
		}

		miniCenarioGolfe.setEstrelasRestantes(miniCenarioGolfe.getEstrelasDesbloquear() - getTotalEstrelas());
		miniCenarioGolfe.atualizarMiniCenario();
		if (miniCenarioGolfe.getEstrelasRestantes() <= 0 && getTotalEstrelas() > 0 && miniCenarioGolfe.isBloqueado()) {
			miniCenarioGolfe.desbloquearCenario();
		}

	}

	public void lerArquivoLog(){

		String linha;
		boolean bloqueado;
		int qntEstrelas, tipo;

		setTotalEstrelas(0);

		try {

			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);

			while (br.ready()) {

				linha = br.readLine();

				if (linha.contains("Cenario")) {

					linha = br.readLine();
					bloqueado = Boolean.parseBoolean(linha.substring(linha.indexOf('=') + 1));

					linha = br.readLine();
					qntEstrelas = Integer.parseInt(linha.substring(linha.indexOf('=') + 1));

					linha = br.readLine();
					tipo = Integer.parseInt(linha.substring(linha.indexOf('=') + 1));

					setTotalEstrelas(getTotalEstrelas() + qntEstrelas);

					switch (tipo) {

					case Cenario.TIPO_CAMPO:
						miniCenarioCampo.setBloqueado(bloqueado);
						miniCenarioCampo.setQntEstrelas(qntEstrelas);
						break;

					case Cenario.TIPO_MONTANHAS:
						miniCenarioMontanhas.setBloqueado(bloqueado);
						miniCenarioMontanhas.setQntEstrelas(qntEstrelas);
						break;

					case Cenario.TIPO_NEVE:
						miniCenarioNeve.setBloqueado(bloqueado);
						miniCenarioNeve.setQntEstrelas(qntEstrelas);
						break;

					case Cenario.TIPO_PRAIA:
						miniCenarioPraia.setBloqueado(bloqueado);
						miniCenarioPraia.setQntEstrelas(qntEstrelas);
						break;

					case Cenario.TIPO_VULCAO:
						miniCenarioVulcao.setBloqueado(bloqueado);
						miniCenarioVulcao.setQntEstrelas(qntEstrelas);
						break;
						
					case Cenario.TIPO_VOLEI:
						miniCenarioVolei.setBloqueado(bloqueado);
						miniCenarioVolei.setQntEstrelas(qntEstrelas);
						break;

					case Cenario.TIPO_BASQUETE:
						miniCenarioBasquete.setBloqueado(bloqueado);
						miniCenarioBasquete.setQntEstrelas(qntEstrelas);
						break;

					case Cenario.TIPO_GOLFE:
						miniCenarioGolfe.setBloqueado(bloqueado);
						miniCenarioGolfe.setQntEstrelas(qntEstrelas);
						break;

					default:
						break;
					}

				}

			}

			br.close();
			fr.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void escreverArquivoLog() {

		boolean bloqueado;
		int qntEstrelas;

		try {

			FileWriter fw = new FileWriter(arquivo, false);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 1; i <= 8; i++) {

				bloqueado = fases.getMiniCenario(i).isBloqueado();
				qntEstrelas = fases.getMiniCenario(i).getQntEstrelas();

				bw.write("Cenario\n"
						+ "bloqueado=" + bloqueado + "\n"
						+ "qntEstrelas=" + qntEstrelas + "\n"
						+ "tipoCenario=" + i + "\n");

				bw.newLine();
			}

			bw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void atualizarLog(int cenario, int qntEstrelas) {

		boolean atualizar = false;

		switch (cenario) {

		case Cenario.TIPO_CAMPO:
			if (miniCenarioCampo.getQntEstrelas() < qntEstrelas) {
				miniCenarioCampo.setQntEstrelas(qntEstrelas);
				atualizar = true;
			}
			break;

		case Cenario.TIPO_MONTANHAS:
			if (miniCenarioMontanhas.getQntEstrelas() < qntEstrelas) {
				miniCenarioMontanhas.setQntEstrelas(qntEstrelas);
				atualizar = true;
			}
			break;

		case Cenario.TIPO_NEVE:
			if (miniCenarioNeve.getQntEstrelas() < qntEstrelas) {
				miniCenarioNeve.setQntEstrelas(qntEstrelas);
				atualizar = true;
			}
			break;

		case Cenario.TIPO_PRAIA:
			if (miniCenarioPraia.getQntEstrelas() < qntEstrelas) {
				miniCenarioPraia.setQntEstrelas(qntEstrelas);
				atualizar = true;
			}
			break;

		case Cenario.TIPO_VULCAO:
			if (miniCenarioVulcao.getQntEstrelas() < qntEstrelas) {
				miniCenarioVulcao.setQntEstrelas(qntEstrelas);
				atualizar = true;
			}
			break;
			
		case Cenario.TIPO_VOLEI:
			if (miniCenarioVolei.getQntEstrelas() < qntEstrelas) {
				miniCenarioVolei.setQntEstrelas(qntEstrelas);
				atualizar = true;
			}
			break;

		case Cenario.TIPO_BASQUETE:
			if (miniCenarioBasquete.getQntEstrelas() < qntEstrelas) {
				miniCenarioBasquete.setQntEstrelas(qntEstrelas);
				atualizar = true;
			}
			break;

		case Cenario.TIPO_GOLFE:
			if (miniCenarioGolfe.getQntEstrelas() < qntEstrelas) {
				miniCenarioGolfe.setQntEstrelas(qntEstrelas);
				atualizar = true;
			}
			break;

		default:
			break;
		}

		if (atualizar) {

			setTotalEstrelas(0);
			setTotalEstrelas(getTotalEstrelas() + miniCenarioCampo.getQntEstrelas());
			setTotalEstrelas(getTotalEstrelas() + miniCenarioMontanhas.getQntEstrelas());
			setTotalEstrelas(getTotalEstrelas() + miniCenarioNeve.getQntEstrelas());
			setTotalEstrelas(getTotalEstrelas() + miniCenarioPraia.getQntEstrelas());
			setTotalEstrelas(getTotalEstrelas() + miniCenarioVulcao.getQntEstrelas());
			setTotalEstrelas(getTotalEstrelas() + miniCenarioVolei.getQntEstrelas());
			setTotalEstrelas(getTotalEstrelas() + miniCenarioBasquete.getQntEstrelas());
			setTotalEstrelas(getTotalEstrelas() + miniCenarioGolfe.getQntEstrelas());

			carregarConfig();

			escreverArquivoLog();
		}

	}

	public int getTotalEstrelas() {
		return totalEstrelas;
	}

	public void setTotalEstrelas(int totalEstrelas) {
		this.totalEstrelas = totalEstrelas;
	}

}
