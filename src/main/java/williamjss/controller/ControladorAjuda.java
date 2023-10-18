package williamjss.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import williamjss.view.Ajuda;
import williamjss.view.Frame;
import williamjss.view.Menu;

public class ControladorAjuda implements KeyListener{

	private Frame frame;
	private Ajuda ajuda;
	private Menu menu;
	private GerenciadorSom gs;

	public ControladorAjuda(Frame frame, Ajuda ajuda, Menu menu, GerenciadorSom gs) {
		this.frame = frame;
		this.menu = menu;
		this.ajuda = ajuda;
		this.gs = gs;
	}

	public void addEventos() {
		frame.addKeyListener(this);
	}

	public void removeEventos() {
		frame.removeKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {

			if (ajuda.getPinSelecionado() != Ajuda.PIN_4) {
				ajuda.atualizarSelecao(ajuda.getPinSelecionado() + 1);
			} else {
				ajuda.atualizarSelecao(Ajuda.PIN_1);
			}

			gs.playToqueNavegarMenu();
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT) {

			if (ajuda.getPinSelecionado() != Ajuda.PIN_1) {
				ajuda.atualizarSelecao(ajuda.getPinSelecionado() - 1);
			} else {
				ajuda.atualizarSelecao(Ajuda.PIN_4);
			}

			gs.playToqueNavegarMenu();
		}

		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			frame.setContentPane(menu);
			frame.repaint();
			frame.validate();
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
