package controller;

import java.util.ArrayList;

import components.Block;
import components.Ball;
import components.Coin;
import components.Platform;
import model.Imagens;
import view.Cenario;

public class GerenciadorCenario {

	// Fases
	public static final int FASE_DE_TESTES = 0;
	public static final int FASE_1 = 1;
	public static final int FASE_2 = 2;
	public static final int FASE_3 = 3;
	public static final int FASE_4 = 4;
	public static final int FASE_5 = 5;

	private Cenario cenario;
	private Ball bola;
	private Platform plataformaInferior;
	private Platform plataformas;
	private ArrayList<Coin> moedas;
	private int[] posicaoMoedas;

	public GerenciadorCenario(Cenario cenario) {
		this.cenario = cenario;
	}

	public void gerarBandeira() {
		Block bandeira = new Block();
		bandeira.atualizarBloco(Block.TIPO_BANDEIRA, Imagens.getImgBandeira());

		Block ultimoBloco = getPlataformas().get(getPlataformas().size() - 1);
		bandeira.setLocation(ultimoBloco.getX() - 3, ultimoBloco.getY() - bandeira.getHeight());

		getPlataformas().add(bandeira);
	}

	public void gerarMoedas() {
		ArrayList<Coin> moedas = new ArrayList<Coin>();
		for (int i = 0; i < getPosicaoMoedas().length; i++) {
			Coin moeda = new Coin();
			Block bloco = getPlataformas().get(getPosicaoMoedas()[i]);
			moeda.setLocation(bloco.getX(), bloco.getY() - moeda.getHeight() - 10);
			moedas.add(moeda);
		}
		setMoedas(moedas);
	}

	// Deletar os objetos do cenario ao final do jogo
	public void removeObjetosCenario() {
		setPosicaoMoedas(null);
		setMoedas(null);
		setPlataformaInferior(null);
		setPlataformas(null);
		setBola(null);

		cenario.removeAll();
	}

	// Define o cenario, o tipo da bola e o tipo das plataformas
	public void definirCenario(int tipoCenario, int fase) {

		switch (tipoCenario) {

			case Cenario.TIPO_CAMPO:

				getBola().setTipo(Ball.TIPO_FUTEBOL);

				setPlataformas(gerarPlataformas(fase, Block.TIPO_GRAMA));
				setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AGUA));

				cenario.setImgCenario(Imagens.getCenarioCampo());

				break;

			case Cenario.TIPO_MONTANHAS:

				getBola().setTipo(Ball.TIPO_ESPINHO);

				setPlataformas(gerarPlataformas(fase, Block.TIPO_PEDRA));
				setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AGUA));

				cenario.setImgCenario(Imagens.getCenarioMontanhas());

				break;

			case Cenario.TIPO_NEVE:

				getBola().setTipo(Ball.TIPO_PEDRA);

				setPlataformas(gerarPlataformas(fase, Block.TIPO_GELO));
				setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_NEVE));

				cenario.setImgCenario(Imagens.getCenarioNeve());

				break;

			case Cenario.TIPO_PRAIA:

				getBola().setTipo(Ball.TIPO_PRAIA);

				setPlataformas(gerarPlataformas(fase, Block.TIPO_AREIA));
				setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AGUA));

				cenario.setImgCenario(Imagens.getCenarioPraia());

				break;

			case Cenario.TIPO_VULCAO:

				getBola().setTipo(Ball.TIPO_VULCAO);

				setPlataformas(gerarPlataformas(fase, Block.TIPO_PEDRA));
				setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_LAVA));

				cenario.setImgCenario(Imagens.getCenarioVulcao());

				break;

			case Cenario.TIPO_VOLEI:

				getBola().setTipo(Ball.TIPO_VOLEI);

				setPlataformas(gerarPlataformas(fase, Block.TIPO_AGUA));
				setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AREIA));

				cenario.setImgCenario(Imagens.getCenarioVolei());

				break;

			case Cenario.TIPO_BASQUETE:

				getBola().setTipo(Ball.TIPO_BASQUETE);

				setPlataformas(gerarPlataformas(fase, Block.TIPO_BASQUETE));
				setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_ESPINHO));

				cenario.setImgCenario(Imagens.getCenarioBasquete());

				break;

			case Cenario.TIPO_GOLFE:

				getBola().setTipo(Ball.TIPO_GOLFE);

				setPlataformas(gerarPlataformas(fase, Block.TIPO_GRAMADO));
				setPlataformaInferior(gerarPlataformaInferior(Block.TIPO_AGUA));

				cenario.setImgCenario(Imagens.getCenarioGolfe());

				break;
		}

	}

	// Gera a plataforma inferior de acordo com o tipo especificado
	public Platform gerarPlataformaInferior(int tipo) {
		Platform plataformaInferior = new Platform(tipo);
		return plataformaInferior;
	}

	// Gera uma sequencia de plataformas para montar cada fase
	public Platform gerarPlataformas(int codFase, int tipo) {

		int[] fase;

		// Considerando a velocidade padrao de movimento das plataformas, com 8 padroes
		// o jogo duraria cerca de 20s, criar arrays com 8 padroes para cada fase...

		switch (codFase) {

			case FASE_DE_TESTES:
				fase = new int[] { 2, 19 };
				setPosicaoMoedas(new int[] { 1 });
				break;

			case FASE_1:
				fase = new int[] { 2, 2, 9, 10, 6, 1, 13, 10, 9 };
				setPosicaoMoedas(new int[] { 19, 21, 23, 38, 49, 58, 65, 74, 88, 93, 90, 103, 112, 120, 128 });
				break;

			case FASE_2:
				fase = new int[] { 2, 9, 15, 10, 16, 17, 18, 13, 19 };
				setPosicaoMoedas(new int[] { 17, 25, 38, 42, 46, 58, 66, 80, 84, 93, 98, 102, 136, 138, 149 });
				break;

			case FASE_3:
				fase = new int[] { 2, 24, 20, 25, 21, 7, 22, 12, 23 };
				setPosicaoMoedas(new int[] { 18, 22, 34, 40, 60, 74, 78, 88, 92, 100, 104, 110, 114, 126, 141 });
				break;

			case FASE_4:
				fase = new int[] { 2, 25, 10, 6, 26, 27, 28, 29, 12 };
				setPosicaoMoedas(new int[] { 17, 38, 50, 62, 66, 78, 90, 94, 102, 110, 118, 130, 146, 158 });
				break;

			case FASE_5:
				fase = new int[] { 2, 17, 16, 0, 17, 30, 7, 31, 32 };
				setPosicaoMoedas(new int[] { 14, 34, 42, 54, 66, 74, 78, 86, 102, 106, 113, 122, 138, 150, 158 });
				break;

			default:
				System.out.println("Erro ao gerar a fase: o codigo de fase '" + codFase + "' nao existe");
				return null;
		}

		Platform plataformas = new Platform(tipo, fase);

		return plataformas;
	}

	public int[] getPosicaoMoedas() {
		return posicaoMoedas;
	}

	public void setPosicaoMoedas(int[] posicaoMoedas) {
		this.posicaoMoedas = posicaoMoedas;
	}

	public Platform getPlataformaInferior() {
		return plataformaInferior;
	}

	public void setPlataformaInferior(Platform plataformaInferior) {
		this.plataformaInferior = plataformaInferior;
	}

	public Platform getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(Platform plataformas) {
		this.plataformas = plataformas;
	}

	public ArrayList<Coin> getMoedas() {
		return moedas;
	}

	public void setMoedas(ArrayList<Coin> moedas) {
		this.moedas = moedas;
	}

	public Ball getBola() {
		if (bola == null) {
			bola = new Ball();
		}
		return bola;
	}

	public void setBola(Ball bola) {
		this.bola = bola;
	}
}
