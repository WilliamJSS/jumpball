package williamjss.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import williamjss.view.Frame;
import williamjss.view.Menu;
import williamjss.view.Sair;

public class ControladorSair implements KeyListener {

    private Frame frame;
    private Menu menu;
    private Sair sair;
    private GerenciadorSom gs;
    private boolean selecting;

    public ControladorSair(Frame frame, Menu menu, Sair sair, GerenciadorSom gs) {
        this.frame = frame;
        this.menu = menu;
        this.sair = sair;
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
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && sair.getBotaoSelecionado() == Sair.BOTAO_SIM && !isSelecting()) {
            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    sair.setBotaoSelecionado(Sair.BOTAO_NAO);
                    gs.playToqueNavegarMenu();

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
        if (e.getKeyCode() == KeyEvent.VK_LEFT && sair.getBotaoSelecionado() == Sair.BOTAO_NAO && !isSelecting()) {
            new Thread(new Runnable() {

                @Override
                public void run() {

                    setSelecting(true);

                    sair.setBotaoSelecionado(Sair.BOTAO_SIM);
                    gs.playToqueNavegarMenu();

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
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            frame.setContentPane(menu);
            frame.repaint();
            frame.validate();
        }

        // Selecionar botao
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) && !isSelecting()) {

            gs.playToqueSelecionarBotao();

            // Verificar qual botao estava selecionado quando o usuario pressionou espaco
            switch (sair.getBotaoSelecionado()) {

                case Sair.BOTAO_SIM:

                    System.exit(0);
                    break;

                case Sair.BOTAO_NAO:

                    frame.setContentPane(menu);
                    frame.repaint();
                    frame.validate();

            }
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
