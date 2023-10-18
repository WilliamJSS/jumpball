package williamjss.animations.platform;

import java.util.ArrayList;

import williamjss.animations.ball.AnimationBallDown;
import williamjss.components.Block;
import williamjss.components.Ball;
import williamjss.controller.GerenciadorCenario;
import williamjss.controller.GerenciadorSom;
import williamjss.view.Cenario;

public class AnimationPlatformMain extends Thread {

    private Cenario cenario;
    private GerenciadorCenario gc;
    private Ball bola;
    private ArrayList<Block> plataformas;
    private GerenciadorSom gs;

    public AnimationPlatformMain(Cenario panelCenario, Ball bola, GerenciadorCenario gc, GerenciadorSom gs) {
        super("AnimationPlatformMain");
        this.cenario = panelCenario;
        this.gc = gc;
        this.bola = bola;
        this.plataformas = gc.getPlataformas();
        this.gs = gs;
    }

    @Override
    public void run() {

        while (bola.isRodando()) {

            boolean vaiCair;

            if (!bola.isCaindo() && !bola.isPulando() && !bola.isDead()) {

                // Supondo que a bola vai cair de uma plataforma, verifico se ela esta em cima
                // de alguma para que eu possa dizer o contrario
                vaiCair = true;

                // Subtrair por um porque a ultima posicao eh a bandeira, verificar se a bolanao esta caindo de proposito
                // pra tentar resolver um bug quando desce da plataforma e pula ao mesmo tempo, a bola trava no meio da plataforma
                for (int i = 0; i < plataformas.size() - 1; i++) {
                    if (bola.verificaColisao(plataformas.get(i)) && plataformas.get(i).isVisible()
                            && !bola.isCaindoDeProposito()) {
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
            if (bola.getBounds().intersects(plataformas.get(plataformas.size() - 1).getBounds())
                    && bola.isEmCimaPlataforma()) {
                bola.setRodando(false);
                gs.playToqueVitoria();
            }

            // Movimentar as plataformas
            for (int i = 0; i < plataformas.size(); i++) {
                Block bloco = plataformas.get(i);
                bloco.setLocation(bloco.getX() - 5, bloco.getY());

                // Caso o bloco va para fora do cenario, ele eh apagado da existencia
                if (bloco.getX() + bloco.getWidth() < 0) {
                    bloco.setVisible(false);
                    cenario.repaint();
                }
            }

            // Delay para movimentar as plataformas
            try {
                sleep(Block.SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
