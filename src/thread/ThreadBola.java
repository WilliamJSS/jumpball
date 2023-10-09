package thread;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import objetosCenario.Bloco;
import objetosCenario.Bola;
import visao.Cenario;
import controle.GerenciadorCenario;
import controle.GerenciadorSom;

public class ThreadBola {

	private Bola bola;
	private ArrayList<Bloco> plataformas;
	private Cenario cenario;
	private GerenciadorSom gs;

	public ThreadBola(Cenario cenario, Bola bola, GerenciadorCenario gc, GerenciadorSom gs) {
		this.cenario = cenario;
		this.bola = bola;
		this.plataformas = gc.getPlataformas();
		this.gs = gs;
	}

	public ThreadPulo getThreadPulo() {
		return new ThreadPulo();
	}

	public ThreadQueda getThreadQueda() {
		return new ThreadQueda();
	}

	public ThreadGirarBola getThreadGirarBola() {
		return new ThreadGirarBola();
	}

	public class ThreadPulo extends Thread {

		int alturaPulo = bola.getAlturaPulo();

		public ThreadPulo() {
			super("ThreadPulo");
		}

		@Override
		public void run() {

			while (alturaPulo > 0) {

				// Delay de pulo
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				bola.setLocation(bola.getX(), bola.getY() - 5);
				alturaPulo -= 5;
			}

			bola.setPulando(false);
			bola.setCaindo(true);

			try {
				sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			getThreadQueda().start();
		}
	}

	public class ThreadQueda extends Thread {

		public ThreadQueda() {
			super("ThreadQueda");	
		}

		@Override
		public void run() {
			
			int delayQueda = 10;
			int posicaoInicial = bola.getY();
			int dy = Bloco.NIVEL_MEDIO_ALTO - Bloco.NIVEL_SUPERIOR;

			while (!bola.isEmCimaPlataforma()) {

				// Diminui o delay de queda da bola, depois de percorrida determinadas distancias
				if (bola.getY() > posicaoInicial + 2*dy) {
					delayQueda = 6;
				} else if (bola.getY() > posicaoInicial + 3*dy/2) {
					delayQueda = 7;
				} else if (bola.getY() > posicaoInicial + dy) {
					delayQueda = 8;
				} else if (bola.getY() > posicaoInicial + dy/2) {
					delayQueda = 9;
				}
				
				// Delay de queda
				try {
					sleep(delayQueda);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				bola.setLocation(bola.getX(), bola.getY() + 5);

				// Verifica se a bola caiu em cima de alguma plataforma
				for (int i = 0; i < plataformas.size(); i++) {
					Bloco bloco = plataformas.get(i);
					if (bola.verificaColisao(bloco) && bloco.isVisible()) {
						bola.setEmCimaPlataforma(true);
						break;
					}
				}

				// Verifica se a bola caiu na plataforma inferior
				if (bola.getY() + bola.getHeight() > Bloco.NIVEL_INFERIOR) {
					bola.setEmCimaPlataforma(true);
					bola.setDead(true);
					bola.setRodando(false);
					gs.playToqueDerrota();
				}

			}

			bola.setCaindoDeProposito(false);
			bola.setCaindo(false);
		}
	}

	public class ThreadGirarBola extends Thread {

		private ArrayList<ImageIcon> spriteBola = bola.getSprite();

		public ThreadGirarBola() {
			super("ThreadGirarBola");
		}

		@Override
		public void run() {
			
			// Enquanto ela estiver rodando, eh feita a "animacao" disso
			while (bola.isRodando()) {

				// Altera os sprites da bola
				for (int i = 0; i < spriteBola.size(); i++) {
					
					bola.setIcon(spriteBola.get(i));
					
					// Delay para alterar o icone da bola
					try {
						sleep(80);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					// Assim que a bola parar de rodar, ja encerra a animacao do sprite
					if (!bola.isRodando()) {
						break;
					}

				}

			}

			// Para a bola quando o jogador cai da plataforma (bola.isDead) ou conclui o jogo (!bola.isRodando)
			gs.stopMusicaCenario();
			cenario.setFimDeJogo(true);
			cenario.exibirTelaFinal(!bola.isDead(), bola.getQntMoedas());
			cenario.repaint();
		}
	}

}
