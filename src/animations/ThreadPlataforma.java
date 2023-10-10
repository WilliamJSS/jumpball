package animations;

import java.util.ArrayList;

import animations.ball.AnimationBallDown;
import controle.GerenciadorCenario;
import controle.GerenciadorSom;
import objetosCenario.Bloco;
import objetosCenario.Bola;
import visao.Cenario;

public class ThreadPlataforma {

	private Cenario cenario;
	private GerenciadorCenario gc;
	private Bola bola;
	private ArrayList<Bloco> plataformas;
	private ArrayList<Bloco> plataformaInferior;
	private GerenciadorSom gs;

	public ThreadPlataforma(Cenario panelCenario, Bola bola, GerenciadorCenario gc, GerenciadorSom gs) {
		this.cenario = panelCenario;
		this.gc = gc;
		this.bola = bola;
		this.plataformas = gc.getPlataformas();
		this.plataformaInferior = gc.getPlataformaInferior();
		this.gs = gs;
	}
	
	public ThreadPlataformaInferior getThreadPlataformaInferior() {
		return new ThreadPlataformaInferior();
	}
	
	public ThreadMovePlataformas getThreadMovePlataformas() {
		return new ThreadMovePlataformas();
	}
	
	/*
	 * Thread responsavel por mover a plataforma inferior
	 */
	public class ThreadPlataformaInferior extends Thread {
		
		public ThreadPlataformaInferior() {
			super("ThreadPlataformaInferior");
		}
		
		@Override
		public void run() {
			
			while (bola.isRodando()) {
				
				for (int i = 0; i < plataformaInferior.size(); i++) {
					Bloco blocoTemp = plataformaInferior.get(i);
					blocoTemp.setLocation(blocoTemp.getX() - 5, blocoTemp.getY());

					// Caso a plataforma va para fora do cenario, envia ela para o final do arrayList
					if (blocoTemp.getX() + blocoTemp.getWidth() < 0) {
						plataformaInferior.remove(i);
						
						Bloco blocoAnterior = plataformaInferior.get(plataformaInferior.size() - 1);
						blocoTemp.setLocation(blocoAnterior.getX() + blocoAnterior.getWidth(), blocoTemp.getY());
						
						plataformaInferior.add(blocoTemp);
					}
				}
				
				// Delay para movimentar a plataforma
				try {
					sleep(Bloco.SPEED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Thread responsavel por verificar colisoes e mover as plataformas
	 */
	public class ThreadMovePlataformas extends Thread {
		
		public ThreadMovePlataformas() {
			super("ThreadMovePlataformas");
		}
		
		@Override
		public void run() {

			while (bola.isRodando()) {
				
				boolean vaiCair;
				
				if (!bola.isCaindo() && !bola.isPulando() && !bola.isDead()) {
					
					// Supondo que a bola vai cair de uma plataforma, verifico se ela esta em cima de alguma para que
					// eu possa dizer o contrario
					vaiCair = true;
					
					// Subtrair por um porque a ultima posicao eh a bandeira, verificar se a bola nao esta caindo de proposito
					// pra tentar resolver um bug quando desce da plataforma e pula ao mesmo tempo, a bola trava no meio da plataforma
					for (int i = 0; i < plataformas.size()-1; i++) {
						if (bola.verificaColisao(plataformas.get(i)) && plataformas.get(i).isVisible() && !bola.isCaindoDeProposito()) {
							vaiCair = false;
							break;
						}
					}
					
					// Caso a bola nao esteja em cima de nenhuma plataforma, ela cai
					if (vaiCair && !bola.isCaindoDeProposito()) {
						AnimationBallDown animationBallDown = new AnimationBallDown(bola, gc, gs);
						animationBallDown.start();
						bola.setEmCimaPlataforma(false);
						bola.setCaindo(true);
					}	
					
				}
				
				// Verifica se a bola passou pela bandeira vermelha
				if (bola.getBounds().intersects(plataformas.get(plataformas.size()-1).getBounds()) && bola.isEmCimaPlataforma()) {
					bola.setRodando(false);
					gs.playToqueVitoria();
				}
				
				// Movimentar as plataformas
				for (int i = 0; i < plataformas.size(); i++) {
					Bloco bloco = plataformas.get(i);
					bloco.setLocation(bloco.getX() - 5, bloco.getY());

					// Caso o bloco va para fora do cenario, ele eh apagado da existencia
					if (bloco.getX() + bloco.getWidth() < 0) {
						bloco.setVisible(false);
						cenario.repaint();
					}
				}

				// Delay para movimentar as plataformas
				try {
					sleep(Bloco.SPEED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}

}
