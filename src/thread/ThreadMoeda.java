package thread;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import controle.GerenciadorSom;
import objetosCenario.Bloco;
import objetosCenario.Bola;
import objetosCenario.Moeda;
import visao.Cenario;

public class ThreadMoeda {

	private Cenario cenario;
	private Bola bola;
	private ArrayList<Moeda> moedas;
	private ArrayList<ImageIcon> sprite;
	private GerenciadorSom gs;

	public ThreadMoeda(Cenario cenario, Bola bola, ArrayList<Moeda> moedas, GerenciadorSom gs) {
		this.cenario = cenario;
		this.bola = bola;
		this.moedas = moedas;
		this.sprite = moedas.get(0).getSprite();
		this.gs = gs;
	}

	public ThreadGirarMoeda getThreadGirarMoeda() {
		return new ThreadGirarMoeda();
	}

	public ThreadMoverMoeda getThreadMoverMoeda() {
		return new ThreadMoverMoeda();
	}

	public class ThreadGirarMoeda extends Thread {

		public ThreadGirarMoeda() {
			super("ThreadGirarMoeda");
		}

		@Override
		public void run() {

			while (!cenario.isFimDeJogo()) {

				// Girar as moedas
				for (int i = 0; i < sprite.size(); i++) {
					
					try {
						sleep(Moeda.DELAY_GIRO);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					for (int j = 0; j < moedas.size(); j++) {
						Moeda moeda = moedas.get(j);
						moeda.setIcon(sprite.get(i));
					}
					
				}
				
			}
			
		}

	}

	public class ThreadMoverMoeda extends Thread {


		public ThreadMoverMoeda() {
			super("ThreadMoverMoeda");
		}

		@Override
		public void run() {

			while (bola.isRodando()) {

				// Movimentar as moedas
				for (int i = 0; i < moedas.size(); i++) {
					Moeda moeda = moedas.get(i);
					
					// Verificar colisao com a bola
					if (bola.verificaColisao(moeda) && moeda.isVisible()) {
						bola.setQntMoedas(bola.getQntMoedas() + 1);
						moeda.setVisible(false);
						
						gs.playEfeitoPegarMoeda();
						//System.out.println("Quantidade de moedas: " + bola.getQntMoedas());
					}
					
					moeda.setLocation(moeda.getX() - 5, moeda.getY());

					// Caso a moeda va para fora do cenario, ela eh apagada da existencia
					if (moeda.getX() + moeda.getWidth() < 0) {
						moeda.setVisible(false);
						cenario.repaint();
					}
				}

				// Delay para mover as moedas
				try {
					sleep(Bloco.SPEED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}

	}

}


