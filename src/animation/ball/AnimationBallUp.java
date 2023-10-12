package animation.ball;

import components.Ball;

public class AnimationBallUp extends Thread {

    private Ball bola;
    private AnimationBallDown animationBallDown;

    public AnimationBallUp(Ball bola, AnimationBallDown animationBallDown) {
        super("AnimationBallUp");
        this.bola = bola;
        this.animationBallDown = animationBallDown;
    }

    @Override
    public void run() {
        
        int alturaPulo = bola.getAlturaPulo();
        
        while (alturaPulo > 0) {

            System.out.println("Pulando.. " + bola.getY());

            // Delay de pulo
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            bola.setLocation(bola.getX(), bola.getY() - 5);
            alturaPulo -= 5;
        }

        bola.setPulando(false);
        bola.setCaindo(true);

        try {
            sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        animationBallDown.start();
    }
}
