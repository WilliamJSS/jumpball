package williamjss.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import williamjss.view.Ajuda;
import williamjss.view.Fases;
import williamjss.view.Frame;
import williamjss.view.Menu;
import williamjss.view.Sair;

public class ControladorMenu implements KeyListener {

    private Frame frame;
    private Menu menu;
    private Fases fases;
    private Sair sair;
    private Ajuda ajuda;
    private GerenciadorSom gs;
    private boolean selecting;

    public ControladorMenu(Frame frame, Menu menu, Ajuda ajuda, Fases fases, Sair sair, GerenciadorSom gs) {
        this.frame = frame;
        this.menu = menu;
        this.fases = fases;
        this.sair = sair;
        this.ajuda = ajuda;
        this.gs = gs;
        this.selecting = false;

        gs.playMusicaMenu();
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
        if (e.getKeyCode() == KeyEvent.VK_UP && !isSelecting()) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    gs.playToqueNavegarMenu();

                    if (menu.getBotaoSelecionado() == Menu.BOTAO_JOGAR) {
                        menu.setBotaoSelecionado(Menu.BOTAO_SAIR);
                    } else {
                        menu.setBotaoSelecionado(menu.getBotaoSelecionado() - 1);
                    }

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
        if (e.getKeyCode() == KeyEvent.VK_DOWN && !isSelecting()) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    gs.playToqueNavegarMenu();

                    if (menu.getBotaoSelecionado() == Menu.BOTAO_SAIR) {
                        menu.setBotaoSelecionado(Menu.BOTAO_JOGAR);
                    } else {
                        menu.setBotaoSelecionado(menu.getBotaoSelecionado() + 1);
                    }

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();

        }

        // Selecionar botao
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) && !isSelecting()) {

            gs.playToqueSelecionarBotao();

            switch (menu.getBotaoSelecionado()) {

                case Menu.BOTAO_JOGAR:
                    frame.setContentPane(fases);
                    break;

                case Menu.BOTAO_AJUDA:
                    frame.setContentPane(ajuda);
                    break;

                case Menu.BOTAO_SAIR:
                    frame.setContentPane(sair);
                    break;
            }

            frame.repaint();
            frame.validate();
        }

        // Redireciona para a tela de sair ao pressionar ESC
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !isSelecting()) {
            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    frame.setContentPane(sair);
                    frame.repaint();
                    frame.validate();

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    setSelecting(false);
                }
            }).start();
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
