package controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import animations.ball.*;
import animations.coin.*;
import animations.platform.*;
import modelo.Imagens;
import objetosCenario.Bloco;
import objetosCenario.Bola;
import objetosCenario.Moeda;
import visao.Cenario;
import visao.Fases;
import visao.Frame;
import visao.Menu;

public class ControladorCenario implements KeyListener {

	private Frame frame;
	private Cenario cenario;
	private GerenciadorCenario gc;
	private Bola bola;
	private ArrayList<Bloco> plataformaInferior;
	private ArrayList<Bloco> plataformas;
	private ArrayList<Moeda> moedas;
	private Menu menu;
	private Fases fases;
	private GerenciadorSom gs;
	private GerenciadorLog gl;
	private int tipoCenario, fase;

	public ControladorCenario(Frame frame, Menu menu, Fases fases, Cenario cenario,
			GerenciadorCenario gc, GerenciadorSom gs, GerenciadorLog gl) {
		this.frame = frame;
		this.cenario = cenario;
		this.gc = gc;
		this.menu = menu;
		this.fases = fases;
		this.gs = gs;
		this.gl = gl;
	}

	public void addEventos() {
		gs.playMusicaCenario();
		frame.addKeyListener(this);
	}

	public void removeEventos() {
		gs.stopMusicaCenario();
		frame.removeKeyListener(this);
	}

	public void iniciarObjetosCenario(int tipoCenario, int fase) {

		this.tipoCenario = tipoCenario;
		this.fase = fase;

		// cenario.setInicioDeJogo(true);
		cenario.setFimDeJogo(false);

		// Definir o tipo de cenario, cada tipo possui um fundo, plataformas e bolas
		// diferentes
		gc.definirCenario(tipoCenario, fase);

		// Adicionar a bola ao cenario
		bola = gc.getBola();
		bola.setLocation(bola.getLocalizacaoInicial());
		cenario.add(bola);

		// Carregar bandeira
		gc.gerarBandeira();

		// Carregar plataforma inferior no cenario
		plataformaInferior = gc.getPlataformaInferior();
		for (int i = 0; i < plataformaInferior.size(); i++) {
			cenario.add(plataformaInferior.get(i));
		}

		// Carregar plataformas restantes
		plataformas = gc.getPlataformas();
		for (int i = 0; i < plataformas.size(); i++) {
			if (plataformas.get(i).isVisible()) {
				cenario.add(plataformas.get(i));
			}
		}

		// Carregar as moedas
		gc.gerarMoedas();
		moedas = gc.getMoedas();
		for (int i = 0; i < moedas.size(); i++) {
			cenario.add(moedas.get(i));
		}

		// Girar as moedas
		AnimationCoinSpin animationCoinSpin = new AnimationCoinSpin(cenario, moedas);
		animationCoinSpin.start();

		cenario.setInicioDeJogo(true);
		cenario.exibirTelaInicial();
		cenario.repaint();
	}

	public void iniciarJogo() {

		// Fazer a bola girar
		bola.setRodando(true);

		AnimationBallSpin animationBallSpin = new AnimationBallSpin(bola, gs, cenario);
		animationBallSpin.start();

		// Movimenta as plataformas
		AnimationPlatformMain animationPlatformMain = new AnimationPlatformMain(cenario, bola, gc, gs);
		AnimationPlatformBottom animationPlatformBottom = new AnimationPlatformBottom(bola, gc);
		animationPlatformMain.start();
		animationPlatformBottom.start();

		// Movimenta as moedas
		AnimationCoinMove animationCoinMove = new AnimationCoinMove(cenario, bola, moedas, gs);
		animationCoinMove.start();

		cenario.setInicioDeJogo(false);
	}

	public void finalizarJogo() {
		cenario.setFimDeJogo(false);

		gs.playToqueSelecionarBotao();
		gs.stopToqueDerrota();
		gs.stopToqueVitoria();

		cenario.limparTelaFinal();

		gc.removeObjetosCenario();
		cenario.repaint();

		cenario.addComponentes();
		cenario.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// Inicio do jogo
		if (e.getKeyCode() == KeyEvent.VK_SPACE && cenario.isInicioDeJogo()) {

			cenario.setInicioDeJogo(false);

			// Inicia o contador
			new Thread(new Runnable() {

				@Override
				public void run() {

					for (int i = 0; i < Imagens.getContagemRegressiva().size(); i++) {
						cenario.getContagem().setIcon(Imagens.getContagemRegressiva().get(i));
						cenario.repaint();
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					cenario.limparTelaInicial();
					cenario.repaint();

					iniciarJogo();
				}
			}).start();
		}

		// Durante o jogo
		if (bola.isRodando()) {

			// Acao de pulo
			if (e.getKeyCode() == KeyEvent.VK_UP && !bola.isPulando() && !bola.isCaindo()) {

				AnimationBallDown animationBallDown = new AnimationBallDown(bola, gc, gs);
				AnimationBallUp animationBallUp = new AnimationBallUp(bola, animationBallDown);

				animationBallUp.start();

				gs.playEfeitoPulo();

				bola.setPulando(true);
				bola.setEmCimaPlataforma(false);
			}

			// Acao de descer da plataforma
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (!bola.isCaindo() && !bola.isPulando() && !bola.isDead()) {
					bola.setCaindo(true);
					bola.setCaindoDeProposito(true);
					bola.setEmCimaPlataforma(false);
					
					AnimationBallDown animationBallDown = new AnimationBallDown(bola, gc, gs);
					animationBallDown.start();
				}
			}
		}

		// Fim de jogo
		if (cenario.isFimDeJogo()) {

			// Tela de derrota
			if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) && bola.isDead()) {

				gs.playToqueNavegarMenu();

				if (cenario.getBotaoSelecionado() == Cenario.BOTAO_REPETIR) {
					cenario.setBotaoSelecionado(Cenario.BOTAO_MENU);
				} else if (cenario.getBotaoSelecionado() == Cenario.BOTAO_MENU) {
					cenario.setBotaoSelecionado(Cenario.BOTAO_REPETIR);
				}

			}

			// Tela de vitoria
			if (e.getKeyCode() == KeyEvent.VK_LEFT && !bola.isDead()) {

				gs.playToqueNavegarMenu();

				if (cenario.getBotaoSelecionado() != Cenario.BOTAO_REPETIR) {
					cenario.setBotaoSelecionado(cenario.getBotaoSelecionado() - 1);
				} else {
					cenario.setBotaoSelecionado(Cenario.BOTAO_AVANCAR);
				}
			}

			// Tela de vitoria
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && !bola.isDead()) {

				gs.playToqueNavegarMenu();

				if (cenario.getBotaoSelecionado() != Cenario.BOTAO_AVANCAR) {
					cenario.setBotaoSelecionado(cenario.getBotaoSelecionado() + 1);
				} else {
					cenario.setBotaoSelecionado(Cenario.BOTAO_REPETIR);
				}

			}

			// Selecionar botao no menu de fim de jogo
			if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {

				int qntEstrelas = (bola.getQntMoedas() * 100 / 15) / 30;
				boolean vitoria = !bola.isDead();

				finalizarJogo();

				switch (cenario.getBotaoSelecionado()) {

					case Cenario.BOTAO_AVANCAR:

						gl.atualizarLog(tipoCenario, qntEstrelas);
						gs.playMusicaMenu();
						frame.setContentPane(fases);
						break;

					case Cenario.BOTAO_REPETIR:

						gs.playMusicaCenario();
						iniciarObjetosCenario(tipoCenario, fase);
						break;

					case Cenario.BOTAO_MENU:

						if (vitoria) {
							gl.atualizarLog(tipoCenario, qntEstrelas);
						}

						gs.playMusicaMenu();
						frame.setContentPane(menu);
						break;
				}

				frame.repaint();
				frame.validate();
			}
		}

	}

	// Metodos nao utilizados
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
