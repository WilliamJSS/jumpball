package controle;

import modelo.CoresFontes;
import visao.Ajuda;
import visao.Cenario;
import visao.Fases;
import visao.Frame;
import visao.Menu;
import visao.Sair;

public class ControladorJumpBall {

	private Frame frame;

	private Menu menu;
	private Cenario cenario;
	private Fases fases;
	private Sair sair;
	private Ajuda ajuda;

	private GerenciadorCenario gc;
	private GerenciadorSom gs;
	private GerenciadorLog gl;

	private ControladorMenu controladorMenu;
	private ControladorCenario controladorCenario;
	private ControladorSair controladorSair;
	private ControladorFases controladorFases;
	private ControladorAjuda controladorAjuda;
	
	private ThreadControlador tc;

	public ControladorJumpBall() {

		CoresFontes.carregarFontes();
		
		frame = new Frame();

		menu = new Menu();
		cenario = new Cenario();
		fases = new Fases();
		sair = new Sair();
		ajuda = new Ajuda();
		
		gc = new GerenciadorCenario(cenario);
		gs = new GerenciadorSom();
		gl = new GerenciadorLog(fases);

		frame.setContentPane(menu);
		frame.repaint();
		frame.validate();

		controladorCenario = new ControladorCenario(frame, menu, fases, cenario, gc, gs, gl);
		controladorMenu = new ControladorMenu(frame, menu, ajuda, fases, sair, gs);
		controladorSair = new ControladorSair(frame, menu, sair, gs);
		controladorFases = new ControladorFases(frame, fases, controladorCenario, cenario, menu, gs);
		controladorAjuda = new ControladorAjuda(frame, ajuda, menu, gs);

		tc = new ThreadControlador();
		tc.start();
	}

	public class ThreadControlador extends Thread {

		public ThreadControlador() {
			super("ThreadControlador");
		}

		@Override
		public void run() {

			while (true) {

				if (frame.getContentPane().equals(menu)) {
					controladorMenu.addEventos();
					
					while (frame.getContentPane().equals(menu))
						System.out.print("");
					
					controladorMenu.removeEventos();
				}

				else if (frame.getContentPane().equals(cenario)) {
					controladorCenario.addEventos();
					
					while (frame.getContentPane().equals(cenario))
						System.out.print("");
					
					controladorCenario.removeEventos();
				}

				else if (frame.getContentPane().equals(sair)) {
					controladorSair.addEventos();

					while (frame.getContentPane().equals(sair))
						System.out.print("");
					
					controladorSair.removeEventos();
				}
				
				else if (frame.getContentPane().equals(fases)) {
					controladorFases.addEventos();

					while (frame.getContentPane().equals(fases))
						System.out.print("");
					
					controladorFases.removeEventos();
				}
				
				else if (frame.getContentPane().equals(ajuda)) {
					controladorAjuda.addEventos();

					while (frame.getContentPane().equals(ajuda))
						System.out.print("");
					
					controladorAjuda.removeEventos();
				}
			}
		}
	}
	
}