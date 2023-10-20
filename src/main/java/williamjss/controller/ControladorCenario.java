package williamjss.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import williamjss.animations.ball.*;
import williamjss.animations.coin.*;
import williamjss.animations.platform.*;
import williamjss.components.Ball;
import williamjss.components.Block;
import williamjss.components.Coin;
import williamjss.components.Platform;
import williamjss.model.image.ImageObject;
import williamjss.view.Cenario;
import williamjss.view.Fases;
import williamjss.view.Frame;
import williamjss.view.Menu;

public class ControladorCenario implements KeyListener {

    private Frame frame;
    private Cenario cenario;
    private GerenciadorCenario gc;
    private Ball bola;
    private Platform plataformaInferior;
    private Platform plataformas;
    private ArrayList<Coin> moedas;
    private Menu menu;
    private Fases fases;
    private GerenciadorSom gs;
    private ControladorMiniCenario gl;
    private int tipoCenario, fase;
    private boolean selecting;

    public ControladorCenario(Frame frame, Menu menu, Fases fases, Cenario cenario,
            GerenciadorCenario gc, GerenciadorSom gs, ControladorMiniCenario gl) {
        this.frame = frame;
        this.cenario = cenario;
        this.gc = gc;
        this.menu = menu;
        this.fases = fases;
        this.gs = gs;
        this.gl = gl;
        this.selecting = false;
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
        for (Block bloco : plataformaInferior) {
            cenario.add(bloco);
        }

        // Carregar plataformas restantes
        plataformas = gc.getPlataformas();
        for (Block bloco : plataformas) {
            if (bloco.isVisible()) {
                cenario.add(bloco);
            }
        }

        // Carregar as moedas
        gc.gerarMoedas();
        moedas = gc.getMoedas();
        for (Coin moeda : moedas) {
            cenario.add(moeda);
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

                    for (int i = 0; i < ImageObject.getContagemRegressiva().size(); i++) {
                        cenario.getContagem().setIcon(ImageObject.getContagemRegressiva().get(i));
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
            if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) && bola.isDead()
                    && !isSelecting()) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        setSelecting(true);

                        gs.playToqueNavegarMenu();

                        if (cenario.getBotaoSelecionado() == Cenario.BOTAO_REPETIR) {
                            cenario.setBotaoSelecionado(Cenario.BOTAO_MENU);
                        } else if (cenario.getBotaoSelecionado() == Cenario.BOTAO_MENU) {
                            cenario.setBotaoSelecionado(Cenario.BOTAO_REPETIR);
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

            // Tela de vitoria
            if (e.getKeyCode() == KeyEvent.VK_LEFT && !bola.isDead() && !isSelecting()) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        setSelecting(true);

                        gs.playToqueNavegarMenu();

                        if (cenario.getBotaoSelecionado() != Cenario.BOTAO_REPETIR) {
                            cenario.setBotaoSelecionado(cenario.getBotaoSelecionado() - 1);
                        } else {
                            cenario.setBotaoSelecionado(Cenario.BOTAO_AVANCAR);
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

            // Tela de vitoria
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && !bola.isDead() && !isSelecting()) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        setSelecting(true);

                        gs.playToqueNavegarMenu();

                        if (cenario.getBotaoSelecionado() != Cenario.BOTAO_AVANCAR) {
                            cenario.setBotaoSelecionado(cenario.getBotaoSelecionado() + 1);
                        } else {
                            cenario.setBotaoSelecionado(Cenario.BOTAO_REPETIR);
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

            // Selecionar botao no menu de fim de jogo
            if ((e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) && !isSelecting()) {

                int qntEstrelas = (bola.getQntMoedas() * 100 / Coin.QNT_TOTAL) / 30;
                boolean vitoria = !bola.isDead();

                finalizarJogo();

                switch (cenario.getBotaoSelecionado()) {

                    case Cenario.BOTAO_AVANCAR:

                        gl.update(tipoCenario, qntEstrelas);
                        gs.playMusicaMenu();
                        frame.setContentPane(fases);
                        break;

                    case Cenario.BOTAO_REPETIR:

                        gs.playMusicaCenario();
                        iniciarObjetosCenario(tipoCenario, fase);
                        break;

                    case Cenario.BOTAO_MENU:

                        if (vitoria) {
                            gl.update(tipoCenario, qntEstrelas);
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
