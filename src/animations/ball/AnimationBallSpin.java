package animations.ball;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import components.Ball;
import controller.GerenciadorSom;
import view.Cenario;

public class AnimationBallSpin extends Thread {
    private Ball bola;
    private GerenciadorSom gs;
    private Cenario cenario;
    private ArrayList<ImageIcon> spriteBola;

    public AnimationBallSpin(Ball bola, GerenciadorSom gs, Cenario cenario) {
        super("AnimationBallSpin");
        this.bola = bola;
        this.gs = gs;
        this.cenario = cenario;
        this.spriteBola = bola.getSprite();
    }

    @Override
    public void run() {

        // Enquanto ela estiver rodando, eh feita a "animacao" disso
        while (bola.isRodando()) {

            // Altera os sprites da bola
            for (int i = 0; i < spriteBola.size(); i++) {

                bola.setIcon(spriteBola.get(i));

                // Delay para alterar o icone da bola
                try {
                    sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Assim que a bola parar de rodar, ja encerra a animacao do sprite
                if (!bola.isRodando()) {
                    break;
                }

            }

        }

        // Para a bola quando o jogador cai da plataforma (bola.isDead) ou conclui o
        // jogo (!bola.isRodando)
        gs.stopMusicaCenario();
        cenario.setFimDeJogo(true);
        cenario.exibirTelaFinal(!bola.isDead(), bola.getQntMoedas());
        cenario.repaint();
    }
}
