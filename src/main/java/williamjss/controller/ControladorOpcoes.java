package williamjss.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import williamjss.view.Frame;
import williamjss.view.Menu;
import williamjss.view.Options;

public class ControladorOpcoes implements KeyListener {

    private Frame frame;
    private Options options;
    private Menu menu;
    private GerenciadorSom gs;
    private boolean selecting;

    public ControladorOpcoes(Frame frame, Options options, Menu menu, GerenciadorSom gs) {
        this.frame = frame;
        this.menu = menu;
        this.options = options;
        this.gs = gs;
        this.selecting = false;
    }

    public void addEventos() {
        frame.addKeyListener(this);
    }

    public void removeEventos() {
        frame.removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Mover entre os botoes
        if (e.getKeyCode() == KeyEvent.VK_UP && !isSelecting() && options.getBotaoSelecionado() != Options.BOTAO_REINICIAR) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    gs.playToqueNavegarMenu();

                    options.setBotaoSelecionado(options.getBotaoSelecionado() - 1);

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();
        }

        // Mover entre os botoes
        if (e.getKeyCode() == KeyEvent.VK_DOWN && !isSelecting() && options.getBotaoSelecionado() != Options.BOTAO_EFEITOS) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    gs.playToqueNavegarMenu();

                    options.setBotaoSelecionado(options.getBotaoSelecionado() + 1);

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();

        }

        // Voltar para o menu
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !isSelecting()) {

            frame.setContentPane(menu);
            frame.repaint();
            frame.validate();
        }
    }

    public boolean isSelecting() {
        return selecting;
    }

    public void setSelecting(boolean selecting) {
        this.selecting = selecting;
    }

    // Metodos nao utilizados
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
