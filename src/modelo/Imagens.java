package modelo;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Imagens {

	// Blocos

	private static ImageIcon blocoAgua = new ImageIcon("resources/img/bloco/bloco-agua.png");

	private static ImageIcon blocoGelo = new ImageIcon("resources/img/bloco/bloco-gelo.png");

	private static ImageIcon blocoGrama1 = new ImageIcon("resources/img/bloco/bloco-grama1.png");

	private static ImageIcon blocoGrama2 = new ImageIcon("resources/img/bloco/bloco-grama2.png");

	private static ImageIcon blocoGrama = new ImageIcon("resources/img/bloco/bloco-grama.png");

	private static ImageIcon blocoLava = new ImageIcon("resources/img/bloco/bloco-lava.png");

	private static ImageIcon blocoPedra = new ImageIcon("resources/img/bloco/bloco-pedra.png");

	private static ImageIcon blocoAreia = new ImageIcon("resources/img/bloco/bloco-areia.png");

	private static ImageIcon blocoEspinho = new ImageIcon("resources/img/bloco/bloco-espinho.png");

	private static ImageIcon blocoNeve = new ImageIcon("resources/img/bloco/bloco-neve.png");

	private static ImageIcon blocoBasquete = new ImageIcon("resources/img/bloco/bloco-basquete.png");

	public static ImageIcon getBlocoGrama() {
		return blocoGrama;
	}

	public static ImageIcon getBlocoBasquete() {
		return blocoBasquete;
	}

	public static ImageIcon getBlocoNeve() {
		return blocoNeve;
	}

	public static ImageIcon getBlocoAgua() {
		return blocoAgua;
	}

	public static ImageIcon getBlocoGelo() {
		return blocoGelo;
	}

	public static ImageIcon getBlocoGrama1() {
		return blocoGrama1;
	}

	public static ImageIcon getBlocoGrama2() {
		return blocoGrama2;
	}

	public static ImageIcon getBlocoLava() {
		return blocoLava;
	}

	public static ImageIcon getBlocoPedra() {
		return blocoPedra;
	}

	public static ImageIcon getBlocoEspinho() {
		return blocoEspinho;
	}

	public static ImageIcon getBlocoAreia() {
		return blocoAreia;
	}

	// Bolas

	private static ImageIcon bolaPadrao = new ImageIcon("resources/img/bola/bola-padrao.png");

	private static ArrayList<ImageIcon> spriteBolaPedra = new ArrayList<ImageIcon>();

	private static ArrayList<ImageIcon> spriteBolaFutebol = new ArrayList<ImageIcon>();

	private static ArrayList<ImageIcon> spriteBolaPraia = new ArrayList<ImageIcon>();

	private static ArrayList<ImageIcon> spriteBolaEspinho = new ArrayList<ImageIcon>();

	private static ArrayList<ImageIcon> spriteBolaVolei = new ArrayList<ImageIcon>();

	private static ArrayList<ImageIcon> spriteBolaGolfe = new ArrayList<ImageIcon>();

	private static ArrayList<ImageIcon> spriteBolaBasquete = new ArrayList<ImageIcon>();

	private static ArrayList<ImageIcon> spriteBolaVulcao = new ArrayList<ImageIcon>();

	public static ImageIcon getBolaPadrao() {
		return bolaPadrao;
	}

	public static ArrayList<ImageIcon> getSpriteBolaVulcao() {
		if (spriteBolaVulcao.size() == 0) {
			for (int i = 1; i <= 8; i++) {
				spriteBolaVulcao.add(new ImageIcon("resources/img/bola/vulcao/bola-vulcao-" + i + ".png"));
			}
		}
		return spriteBolaVulcao;
	}

	public static ArrayList<ImageIcon> getSpriteBolaBasquete() {
		if (spriteBolaBasquete.size() == 0) {
			for (int i = 1; i <= 8; i++) {
				spriteBolaBasquete.add(new ImageIcon("resources/img/bola/basquete/bola-basquete-" + i + ".png"));
			}
		}
		return spriteBolaBasquete;
	}

	public static ArrayList<ImageIcon> getSpriteBolaPedra() {
		if (spriteBolaPedra.size() == 0) {
			for (int i = 1; i <= 8; i++) {
				spriteBolaPedra.add(new ImageIcon("resources/img/bola/pedra/bola-pedra-" + i + ".png"));
			}
		}
		return spriteBolaPedra;
	}

	public static ArrayList<ImageIcon> getSpriteBolaFutebol() {
		if (spriteBolaFutebol.size() == 0) {
			for (int i = 1; i <= 8; i++) {
				spriteBolaFutebol.add(new ImageIcon("resources/img/bola/futebol/bola-futebol-" + i + ".png"));
			}
		}
		return spriteBolaFutebol;
	}

	public static ArrayList<ImageIcon> getSpriteBolaPraia() {
		if (spriteBolaPraia.size() == 0) {
			for (int i = 1; i <= 8; i++) {
				spriteBolaPraia.add(new ImageIcon("resources/img/bola/praia/bola-praia-" + i + ".png"));
			}
		}
		return spriteBolaPraia;
	}

	public static ArrayList<ImageIcon> getSpriteBolaEspinho() {
		if (spriteBolaEspinho.size() == 0) {
			for (int i = 1; i <= 16; i++) {
				spriteBolaEspinho.add(new ImageIcon("resources/img/bola/espinho/bola-espinho-" + i + ".png"));
			}
		}
		return spriteBolaEspinho;
	}

	public static ArrayList<ImageIcon> getSpriteBolaVolei() {
		if (spriteBolaVolei.size() == 0) {
			for (int i = 1; i <= 8; i++) {
				spriteBolaVolei.add(new ImageIcon("resources/img/bola/volei/bola-volei-" + i + ".png"));
			}
		}
		return spriteBolaVolei;
	}

	public static ArrayList<ImageIcon> getSpriteBolaGolfe() {
		if (spriteBolaGolfe.size() == 0) {
			for (int i = 1; i <= 8; i++) {
				spriteBolaGolfe.add(new ImageIcon("resources/img/bola/golfe/bolinha-de-golfe-" + i + ".png"));
			}
		}
		return spriteBolaGolfe;
	}

	// Botoes

	private static ImageIcon imgBotao1 = new ImageIcon("resources/img/botao/botao-1.png");

	private static ImageIcon imgBotao2 = new ImageIcon("resources/img/botao/botao-2.png");

	private static ImageIcon imgBotao1Selecionado = new ImageIcon("resources/img/botao/botao-1-selecionado.png");

	private static ImageIcon imgBotao2Selecionado = new ImageIcon("resources/img/botao/botao-2-selecionado.png");

	private static ImageIcon imgBotaoVoltar = new ImageIcon("resources/img/botao/botao-voltar.png");

	private static ImageIcon imgBotaoRepetir = new ImageIcon("resources/img/botao/botao-repetir.png");

	private static ImageIcon imgBotaoRepetirSelecionado = new ImageIcon("resources/img/botao/botao-repetir-selecionado.png");

	private static ImageIcon imgBotaoMenu = new ImageIcon("resources/img/botao/botao-menu.png");

	private static ImageIcon imgBotaoMenuSelecionado = new ImageIcon("resources/img/botao/botao-menu-selecionado.png");

	private static ImageIcon imgBotaoFechar = new ImageIcon("resources/img/botao/botao-fechar.png");

	private static ImageIcon imgBotaoAvancar = new ImageIcon("resources/img/botao/botao-avancar.png");

	private static ImageIcon imgBotaoAvancarSelecionado = new ImageIcon("resources/img/botao/botao-avancar-selecionado.png");

	private static ImageIcon imgBotaoAvancarBloqueado = new ImageIcon("resources/img/botao/botao-avancar-bloqueado.png");


	public static ImageIcon getImgBotaoRepetirSelecionado() {
		return imgBotaoRepetirSelecionado;
	}

	public static ImageIcon getImgBotaoMenuSelecionado() {
		return imgBotaoMenuSelecionado;
	}

	public static ImageIcon getImgBotaoAvancar() {
		return imgBotaoAvancar;
	}

	public static ImageIcon getImgBotaoAvancarSelecionado() {
		return imgBotaoAvancarSelecionado;
	}

	public static ImageIcon getImgBotaoAvancarBloqueado() {
		return imgBotaoAvancarBloqueado;
	}

	public static ImageIcon getImgBotaoFechar() {
		return imgBotaoFechar;
	}

	public static ImageIcon getImgBotao1() {
		return imgBotao1;
	}

	public static ImageIcon getImgBotao2() {
		return imgBotao2;
	}

	public static ImageIcon getImgBotao1Selecionado() {
		return imgBotao1Selecionado;
	}

	public static ImageIcon getImgBotao2Selecionado() {
		return imgBotao2Selecionado;
	}

	public static ImageIcon getImgBotaoRepetir() {
		return imgBotaoRepetir;
	}

	public static ImageIcon getImgBotaoMenu() {
		return imgBotaoMenu;
	}

	public static ImageIcon getImgBotaoVoltar() {
		return imgBotaoVoltar;
	}

	// Cenarios

	private static ImageIcon cenarioMenu = new ImageIcon("resources/img/cenario/cenario-menu.png");

	private static ImageIcon cenarioNeve = new ImageIcon("resources/img/cenario/cenario-neve.png");

	private static ImageIcon cenarioMontanhas = new ImageIcon("resources/img/cenario/cenario-montanhas.png");

	private static ImageIcon cenarioPraia = new ImageIcon("resources/img/cenario/cenario-praia.png");

	private static ImageIcon cenarioVulcao = new ImageIcon("resources/img/cenario/cenario-vulcao.png");

	private static ImageIcon cenarioCampo = new ImageIcon("resources/img/cenario/cenario-campo.png");

	private static ImageIcon cenarioGolfe = new ImageIcon("resources/img/cenario/cenario-golfe.png");

	private static ImageIcon cenarioBasquete = new ImageIcon("resources/img/cenario/cenario-basquete.png");

	private static ImageIcon cenarioVolei = new ImageIcon("resources/img/cenario/cenario-volei.png");

	private static ImageIcon fundoCenario = new ImageIcon("resources/img/cenario/fundo-cenario.png");

	private static ImageIcon fundoMiniCenario = new ImageIcon("resources/img/cenario/fundo-minicenario.png");

	private static ImageIcon miniCenarioNeve = new ImageIcon("resources/img/cenario/minicenario-neve.png");

	private static ImageIcon miniCenarioMontanhas = new ImageIcon("resources/img/cenario/minicenario-montanhas.png");

	private static ImageIcon miniCenarioPraia = new ImageIcon("resources/img/cenario/minicenario-praia.png");

	private static ImageIcon miniCenarioVulcao = new ImageIcon("resources/img/cenario/minicenario-vulcao.png");

	private static ImageIcon miniCenarioCampo = new ImageIcon("resources/img/cenario/minicenario-campo.png");

	private static ImageIcon miniCenarioGolfe = new ImageIcon("resources/img/cenario/minicenario-golfe.png");

	private static ImageIcon miniCenarioBasquete = new ImageIcon("resources/img/cenario/minicenario-basquete.png");

	private static ImageIcon miniCenarioVolei = new ImageIcon("resources/img/cenario/minicenario-volei.png");


	public static Image getCenarioVolei() {
		return cenarioVolei.getImage();
	}

	public static ImageIcon getMiniCenarioVolei() {
		return miniCenarioVolei;
	}

	public static Image getCenarioGolfe() {
		return cenarioGolfe.getImage();
	}

	public static Image getCenarioBasquete() {
		return cenarioBasquete.getImage();
	}

	public static ImageIcon getFundoMiniCenario() {
		return fundoMiniCenario;
	}

	public static ImageIcon getMiniCenarioNeve() {
		return miniCenarioNeve;
	}

	public static ImageIcon getMiniCenarioMontanhas() {
		return miniCenarioMontanhas;
	}

	public static ImageIcon getMiniCenarioPraia() {
		return miniCenarioPraia;
	}

	public static ImageIcon getMiniCenarioVulcao() {
		return miniCenarioVulcao;
	}

	public static ImageIcon getMiniCenarioCampo() {
		return miniCenarioCampo;
	}

	public static ImageIcon getMiniCenarioGolfe() {
		return miniCenarioGolfe;
	}

	public static ImageIcon getMiniCenarioBasquete() {
		return miniCenarioBasquete;
	}

	public static Image getCenarioNeve() {
		return cenarioNeve.getImage();
	}

	public static Image getCenarioMontanhas() {
		return cenarioMontanhas.getImage();
	}

	public static Image getCenarioMenu() {
		return cenarioMenu.getImage();
	}

	public static Image getCenarioPraia() {
		return cenarioPraia.getImage();
	}

	public static Image getCenarioVulcao() {
		return cenarioVulcao.getImage();
	}

	public static Image getCenarioCampo() {
		return cenarioCampo.getImage();
	}

	public static ImageIcon getFundoCenario() {
		return fundoCenario;
	}

	// Icones

	private static ImageIcon iconeJumpBall = new ImageIcon("resources/img/icone/icone-jump-ball.png");

	private static ImageIcon fundoSair = new ImageIcon("resources/img/icone/fundo-sair.png");

	private static ImageIcon fundoMenu = new ImageIcon("resources/img/icone/fundo-menu.png");

	private static ImageIcon imgCadeado = new ImageIcon("resources/img/icone/cadeado.png");

	private static ImageIcon imgEstrela = new ImageIcon("resources/img/icone/estrela.png");

	private static ImageIcon imgEstrelaGrande = new ImageIcon("resources/img/icone/estrela-grande.png");

	private static ImageIcon imgEstrelaVermelha = new ImageIcon("resources/img/icone/estrela-vermelha.png");

	private static ImageIcon imgEstrelaCinza = new ImageIcon("resources/img/icone/estrela-cinza.png");

	private static ImageIcon imgEstrelaCinzaGrande = new ImageIcon("resources/img/icone/estrela-cinza-grande.png");

	private static ArrayList<ImageIcon> contagemRegressiva = new ArrayList<ImageIcon>();

	private static ImageIcon pinAzul = new ImageIcon("resources/img/icone/pin-azul.png");

	private static ImageIcon pinBranco = new ImageIcon("resources/img/icone/pin-branco.png");

	private static ImageIcon logoJumpBall = new ImageIcon("resources/img/icone/logo-jump-ball.png");

	private static ImageIcon setasTeclado = new ImageIcon("resources/img/icone/setas-cima-baixo.png");

	public static ImageIcon getSetasTeclado() {
		return setasTeclado;
	}

	public static ImageIcon getFundoMenu() {
		return fundoMenu;
	}

	public static ImageIcon getFundoSair() {
		return fundoSair;
	}

	public static ImageIcon getLogoJumpBall() {
		return logoJumpBall;
	}

	public static Image getIconeJumpBall() {
		return iconeJumpBall.getImage();
	}

	public static ImageIcon getImgCadeado() {
		return imgCadeado;
	}

	public static ImageIcon getImgEstrela() {
		return imgEstrela;
	}

	public static ImageIcon getImgEstrelaGrande() {
		return imgEstrelaGrande;
	}

	public static ImageIcon getImgEstrelaVermelha() {
		return imgEstrelaVermelha;
	}

	public static ImageIcon getImgEstrelaCinza() {
		return imgEstrelaCinza;
	}

	public static ImageIcon getImgEstrelaCinzaGrande() {
		return imgEstrelaCinzaGrande;
	}

	public static ImageIcon getPinAzul() {
		return pinAzul;
	}

	public static ImageIcon getPinBranco() {
		return pinBranco;
	}

	public static ArrayList<ImageIcon> getContagemRegressiva() {
		if (contagemRegressiva.size() == 0) {
			for (int i = 1; i <= 4; i++) {
				contagemRegressiva.add(new ImageIcon("resources/img/icone/contagem-" + i + ".png"));
			}
		}
		return contagemRegressiva;
	}

	// Objetos

	private static ImageIcon imgBandeira = new ImageIcon("resources/img/objeto/bandeira.png");

	private static ArrayList<ImageIcon> spriteMoeda = new ArrayList<ImageIcon>();

	public static ImageIcon getImgBandeira() {
		return imgBandeira;
	}

	public static ArrayList<ImageIcon> getSpriteMoeda() {
		if (spriteMoeda.size() == 0) {
			for (int i = 1; i <= 6; i++) {
				spriteMoeda.add(new ImageIcon("resources/img/objeto/moeda-" + i + ".png"));
			}
		}
		return spriteMoeda;
	}

}
