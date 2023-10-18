package williamjss.animations.ball;

import java.util.ArrayList;

import williamjss.components.Ball;
import williamjss.components.Block;
import williamjss.controller.GerenciadorCenario;
import williamjss.controller.GerenciadorSom;

public class AnimationBallDown extends Thread {

    private Ball bola;
    private ArrayList<Block> plataformas;
    private GerenciadorSom gs;

    public AnimationBallDown(Ball bola, GerenciadorCenario gc, GerenciadorSom gs) {
        super("AnimationBallDown");
        this.bola = bola;
        this.plataformas = gc.getPlataformas();
        this.gs = gs;
    }

    @Override
    public void run() {

        int delayQueda = 10;
        int posicaoInicial = bola.getY();
        int dy = Block.NIVEL_MID - Block.NIVEL_TOP;

        while (!bola.isEmCimaPlataforma()) {

            // Diminui o delay de queda da bola, depois de percorrida determinadas
            // distancias
            if (bola.getY() > posicaoInicial + 2 * dy) {
                delayQueda = 6;
            } else if (bola.getY() > posicaoInicial + 3 * dy / 2) {
                delayQueda = 7;
            } else if (bola.getY() > posicaoInicial + dy) {
                delayQueda = 8;
            } else if (bola.getY() > posicaoInicial + dy / 2) {
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
                Block bloco = plataformas.get(i);
                if (bola.verificaColisao(bloco) && bloco.isVisible()) {
                    bola.setEmCimaPlataforma(true);
                    break;
                }
            }

            // Verifica se a bola caiu na plataforma inferior
            if (bola.getY() + bola.getHeight() > Block.NIVEL_FLOOR) {
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
