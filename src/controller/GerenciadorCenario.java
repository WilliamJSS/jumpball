package controller;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import components.Block;
import components.Ball;
import components.Coin;
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
	private ArrayList<Block> plataformaInferior;
	private ArrayList<Block> plataformas;
	private ArrayList<Coin> moedas;
	private ArrayList<int[]> padroes;
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

	//  Padroes para gerar plataformas
	public ArrayList<int[]> getPadroes() {
		if (padroes == null) {
			padroes = new ArrayList<int[]>();

			padroes.add(new int[]{1,4,7});		// 0 - toda parte de cima
			padroes.add(new int[]{2,5,8});		// 1 - toda parte do meio
			padroes.add(new int[]{3,6,9});		// 2 - toda parte de baixo

			padroes.add(new int[]{1,5,9});		// 3 - escadinha descendo
			padroes.add(new int[]{1,2,5,8,9});	// 4 - escadinha descendo + toda parte do meio
			padroes.add(new int[]{1,4,5,7,9});	// 5 - escadinha descendo + toda parte de cima
			padroes.add(new int[]{1,3,5,6,9});	// 6 - escadinha descendo + toda parte de baixo

			padroes.add(new int[]{3,5,7});		// 7 - escadinha subindo
			padroes.add(new int[]{1,3,4,5,7});	// 8 - escadinha subindo + toda parte de cima
			padroes.add(new int[]{3,5,6,7,9});	// 9 - escadinha subindo + toda parte de baixo
			padroes.add(new int[]{2,3,5,7,8});	// 10 - escadinha subindo + toda parte do meio

			padroes.add(new int[]{1,5,7});		// 11 - no meio e em cima -_-
			padroes.add(new int[]{2,6,8});		// 12 - em baixo e no meio -_-
			padroes.add(new int[]{2,4,8});		// 13 - no meio e em cima _-_
			padroes.add(new int[]{3,5,9});		// 14 - em baixo e no meio _-_

			// Outros padroes
			padroes.add(new int[]{2,6,9});		// 15
			padroes.add(new int[]{1,3,4,6,8});	// 16
			padroes.add(new int[]{2,4,6,9});	// 17
			padroes.add(new int[]{2,3,4,6,7,9});// 18
			padroes.add(new int[]{3,6,8});		// 19
			padroes.add(new int[]{2,4,7,9});    // 20
			padroes.add(new int[]{2,4,6,7,9});  // 21
			padroes.add(new int[]{2,3,5,6,7,8});// 22
			padroes.add(new int[]{2,5,6,8});    // 23
			padroes.add(new int[]{3,6,8,9});    // 24
			padroes.add(new int[]{2,4,5,9});    // 25
			padroes.add(new int[]{3,4,5,7});    // 26
			padroes.add(new int[]{1,3,5,7,9});  // 27
			padroes.add(new int[]{1,3,5,8});    // 28
			padroes.add(new int[]{2,8,9});      // 29
			padroes.add(new int[]{2,3,4,5,7,9});// 30
			padroes.add(new int[]{1,5,8,9});	// 31
			padroes.add(new int[]{1,2,5,6,8});	// 32
		}
		return padroes;
	}

	//  Define o cenario, o tipo da bola e o tipo das plataformas
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

	//  Gera uma plataforma com a quantidade de blocos e tipo especificado
	public ArrayList<Block> gerarPlataforma(int qntBlocos, int tipo) {

		ArrayList<Block> plataforma = new ArrayList<Block>();

		ImageIcon imgBloco1, imgBloco2;

		switch (tipo) {

		case Block.TIPO_AGUA:
			imgBloco1 = Imagens.getBlocoAgua();
			imgBloco2 = Imagens.getBlocoAgua();
			break;

		case Block.TIPO_GELO:
			imgBloco1 = Imagens.getBlocoGelo();
			imgBloco2 = Imagens.getBlocoGelo();
			break;

		case Block.TIPO_GRAMA:
			imgBloco1 = Imagens.getBlocoGrama1();
			imgBloco2 = Imagens.getBlocoGrama2();
			break;

		case Block.TIPO_GRAMADO:
			imgBloco1 = Imagens.getBlocoGrama();
			imgBloco2 = Imagens.getBlocoGrama();
			break;

		case Block.TIPO_LAVA:
			imgBloco1 = Imagens.getBlocoLava();
			imgBloco2 = Imagens.getBlocoLava();
			break;

		case Block.TIPO_PEDRA:
			imgBloco1 = Imagens.getBlocoPedra();
			imgBloco2 = Imagens.getBlocoPedra();
			break;

		case Block.TIPO_AREIA:
			imgBloco1 = Imagens.getBlocoAreia();
			imgBloco2 = Imagens.getBlocoAreia();
			break;

		case Block.TIPO_ESPINHO:
			imgBloco1 = Imagens.getBlocoEspinho();
			imgBloco2 = Imagens.getBlocoEspinho();
			break;

		case Block.TIPO_NEVE:
			imgBloco1 = Imagens.getBlocoNeve();
			imgBloco2 = Imagens.getBlocoNeve();
			break;

		case Block.TIPO_BASQUETE:
			imgBloco1 = Imagens.getBlocoBasquete();
			imgBloco2 = Imagens.getBlocoBasquete();
			break;

		default:
			System.out.println("Erro ao gerar plataforma: o tipo de plataforma '" + tipo + "' nao existe.");
			return null;
		}

		// Cria um bloco inicial da plataforma, antes de adicionar os demais
		Block blocoInicial = new Block();
		blocoInicial.atualizarBloco(tipo, imgBloco1);
		plataforma.add(blocoInicial);

		for (int i = 1; i < qntBlocos; i++) {
			Block blocoAnterior = plataforma.get(plataforma.size()-1);
			Block blocoAtual = new Block();

			// Verificar se vai adicionar o lado esquerdo ou direito do bloco
			if (i % 2 == 0)
				blocoAtual.atualizarBloco(tipo, imgBloco1);
			else
				blocoAtual.atualizarBloco(tipo, imgBloco2);

			// Adiciona o bloco atual ao lado do bloco anterior
			blocoAtual.setLocation(blocoAnterior.getX() + blocoAnterior.getWidth(), blocoInicial.getY());

			plataforma.add(blocoAtual);
		}

		return plataforma;
	}

	//  Gera a plataforma inferior de acordo com o tipo especificado
	public ArrayList<Block> gerarPlataformaInferior(int tipo) {
		plataformaInferior = gerarPlataforma(35, tipo);

		Block blocoInicial = plataformaInferior.get(0);
		blocoInicial.setLocation(0, Block.NIVEL_INFERIOR);

		for (int i = 1; i < plataformaInferior.size(); i++) {
			Block blocoAnterior = plataformaInferior.get(i-1);
			Block blocoAtual = plataformaInferior.get(i);

			// Adicionando os blocos restantes subsequentemente ao primeiro
			blocoAtual.setLocation(blocoAnterior.getX() + blocoAnterior.getWidth(), blocoInicial.getY());
		}

		return plataformaInferior;
	}

	//  Gera uma plataforma com 4 blocos do tipo especificado na posicao determinada
	public ArrayList<Block> gerarPadraoPlataforma(int posicao, int tipo) {

		int dx;
		ArrayList<Block> padraoPlataforma = new ArrayList<Block>();
		padraoPlataforma.addAll(gerarPlataforma(4, tipo));

		switch (posicao) {

		case 1: // Plataforma no nivel SUPERIOR / esquerda

			for (int i = 0; i < padraoPlataforma.size(); i++) {
				Block bloco = padraoPlataforma.get(i);
				bloco.setLocation(bloco.getX(), Block.NIVEL_SUPERIOR);
			}

			break;

		case 2: // Plataforma no nivel MEDIO ALTO / esquerda

			for (int i = 0; i < padraoPlataforma.size(); i++) {
				Block bloco = padraoPlataforma.get(i);
				bloco.setLocation(bloco.getX(), Block.NIVEL_MEDIO_ALTO);
			}

			break;

		case 3: // Plataforma no nivel MEDIO BAIXO / esquerda

			for (int i = 0; i < padraoPlataforma.size(); i++) {
				Block bloco = padraoPlataforma.get(i);
				bloco.setLocation(bloco.getX(), Block.NIVEL_MEDIO_BAIXO);
			}

			break;

		case 4: // Plataforma no nivel SUPERIOR / meio
			padraoPlataforma.addAll(gerarPlataforma(4, tipo));
			dx = 4 * padraoPlataforma.get(0).getHeight(); // 4 blocos de distancia do ponto inicial

			for (int i = 0; i < padraoPlataforma.size(); i++) {
				Block bloco = padraoPlataforma.get(i);
				bloco.setLocation(bloco.getX() + dx, Block.NIVEL_SUPERIOR);
			}

			break;

		case 5: // Plataforma no nivel MEDIO ALTO / meio
			dx = 4 * padraoPlataforma.get(0).getHeight(); // 4 blocos de distancia do ponto inicial

			for (int i = 0; i < padraoPlataforma.size(); i++) {
				Block bloco = padraoPlataforma.get(i);
				bloco.setLocation(bloco.getX() + dx, Block.NIVEL_MEDIO_ALTO);
			}

			break;

		case 6: // Plataforma no nivel MEDIO BAIXO / meio
			dx = 4 * padraoPlataforma.get(0).getHeight(); // 4 blocos de distancia do ponto inicial

			for (int i = 0; i < padraoPlataforma.size(); i++) {
				Block bloco = padraoPlataforma.get(i);
				bloco.setLocation(bloco.getX() + dx, Block.NIVEL_MEDIO_BAIXO);
			}

			break;

		case 7: // Plataforma no nivel SUPERIOR / direita
			padraoPlataforma.addAll(gerarPlataforma(4, tipo));
			dx = 8 * padraoPlataforma.get(0).getHeight(); // 8 blocos de distancia do ponto inicial

			for (int i = 0; i < padraoPlataforma.size(); i++) {
				Block bloco = padraoPlataforma.get(i);
				bloco.setLocation(bloco.getX() + dx, Block.NIVEL_SUPERIOR);
			}

			break;

		case 8: // Plataforma no nivel MEDIO ALTO / direita
			dx = 8 * padraoPlataforma.get(0).getHeight(); // 8 blocos de distancia do ponto inicial

			for (int i = 0; i < padraoPlataforma.size(); i++) {
				Block bloco = padraoPlataforma.get(i);
				bloco.setLocation(bloco.getX() + dx, Block.NIVEL_MEDIO_ALTO);
			}

			break;

		case 9: // Plataforma no nivel MEDIO BAIXO / direita
			dx = 8 * padraoPlataforma.get(0).getHeight(); // 8 blocos de distancia do ponto inicial

			for (int i = 0; i < padraoPlataforma.size(); i++) {
				Block bloco = padraoPlataforma.get(i);
				bloco.setLocation(bloco.getX() + dx, Block.NIVEL_MEDIO_BAIXO);
			}

		}

		return padraoPlataforma;
	}

	//  Gera uma sequencia de plataformas para montar cada fase
	public ArrayList<Block> gerarPlataformas(int codFase, int tipo){

		ArrayList<Block> novasPlataformas = new ArrayList<Block>();

		int[] fase;
		int tamanho, codPadrao;
		int dx = 12 * 30; // 12 blocos de distancia

		// Considerando a velocidade padrao de movimento das plataformas, com 8 padroes
		// o jogo duraria cerca de 20s, criar arrays com 8 padroes para cada fase...

		switch (codFase) {

		case FASE_DE_TESTES:
			fase = new int[]{2,19};
			setPosicaoMoedas(new int[]{1});
			break;

		case FASE_1:
			fase = new int[]{2,2,9,10,6,1,13,10,9};
			//			setPosicaoMoedas(new int[]{19,21,23,37,49,58,65,74,90,92,94,102,114,122,128});
			setPosicaoMoedas(new int[]{19,21,23,38,49,58,65,74,88,93,90,103,112,120,128}); // Correcao de Juao
			break;

		case FASE_2:
			fase = new int[]{2,9,15,10,16,17,18,13,19};
			//			setPosicaoMoedas(new int[]{21,25,38,42,46,58,66,81,84,93,104,112,125,133,137});
			setPosicaoMoedas(new int[]{17,25,38,42,46,58,66,80,84,93,98,102,136,138,149}); // Correcao de Juao
			break;

		case FASE_3:
			fase = new int[]{2,24,20,25,21,7,22,12,23};
			//			setPosicaoMoedas(new int[]{18,22,34,42,50,62,66,82,86,94,102,106,114,126,141});
			setPosicaoMoedas(new int[]{18,22,34,40,60,74,78,88,92,100,104,110,114,126,141}); // Correcao de Juao
			break;

		case FASE_4:
			fase = new int[]{2,25,10,6,26,27,28,29,12};
			//			setPosicaoMoedas(new int[]{30,38,50,62,66,78,90,94,102,110,118,122,130,134});
			setPosicaoMoedas(new int[]{17,38,50,62,66,78,90,94,102,110,118,130,146,158}); // Correcao de Juao
			break;

		case FASE_5:
			fase = new int[]{2,17,16,0,17,30,7,31,32};
			//			setPosicaoMoedas(new int[]{18,38,46,54,58,66,74,78,86,102,110,118,126,134,142});
			setPosicaoMoedas(new int[]{14,34,42,54,66,74,78,86,102,106,113,122,138,150,158}); // Correcao de Juao
			break;

		default:
			System.out.println("Erro ao gerar a fase: o codigo de fase '" + codFase + "' nao existe");
			return null;
		}

		// Adiciona os padroes da sequencia especificada ao arrayList
		for (int i = 0; i < fase.length; i++) {

			codPadrao = fase[i];

			// Adiciona um padrao de plataformas ao arrayList
			tamanho = novasPlataformas.size();
			for (int j = 0; j < getPadroes().get(codPadrao).length; j++) {
				novasPlataformas.addAll(gerarPadraoPlataforma(getPadroes().get(codPadrao)[j], tipo));
			}

			// Atualiza a posicao X dos blocos do padrao recem adicionados
			for (int k = tamanho; k < novasPlataformas.size(); k++) {
				Block bloco = novasPlataformas.get(k);
				bloco.setLocation(bloco.getX() + i*dx, bloco.getY());
			}
		}

		return novasPlataformas;
	}

	public int[] getPosicaoMoedas() {
		return posicaoMoedas;
	}

	public void setPosicaoMoedas(int[] posicaoMoedas) {
		this.posicaoMoedas = posicaoMoedas;
	}

	public ArrayList<Block> getPlataformaInferior(){
		return plataformaInferior;
	}

	public void setPlataformaInferior(ArrayList<Block> plataformaInferior) {
		this.plataformaInferior = plataformaInferior;
	}

	public ArrayList<Block> getPlataformas(){
		return plataformas;
	}

	public void setPlataformas(ArrayList<Block> plataformas) {
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
