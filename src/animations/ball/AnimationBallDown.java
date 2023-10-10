package animations.ball;

import java.util.ArrayList;

import controle.GerenciadorCenario;
import controle.GerenciadorSom;
import objetosCenario.Bloco;
import objetosCenario.Bola;

public class AnimationBallDown extends Thread {

    private Bola bola;
    private ArrayList<Bloco> plataformas;
    private GerenciadorSom gs;

    public AnimationBallDown(Bola bola, GerenciadorCenario gc, GerenciadorSom gs) {
        super("AnimationBallDown");
        this.bola = bola;
        this.plataformas = gc.getPlataformas();
        this.gs = gs;
    }

    @Override
    public void run() {

        int delayQueda = 10;
        int posicaoInicial = bola.getY();
        int dy = Bloco.NIVEL_MEDIO_ALTO - Bloco.NIVEL_SUPERIOR;

        while (!bola.isEmCimaPlataforma()) {

            System.out.println("Caindo... " + bola.getY());

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
                Bloco bloco = plataformas.get(i);
                if (bola.verificaColisao(bloco) && bloco.isVisible()) {
                    bola.setEmCimaPlataforma(true);
                    break;
                }
            }

            // Verifica se a bola caiu na plataforma inferior
            if (bola.getY() + bola.getHeight() > Bloco.NIVEL_INFERIOR) {
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