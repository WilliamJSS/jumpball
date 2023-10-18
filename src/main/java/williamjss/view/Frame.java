package williamjss.view;

import javax.swing.JFrame;

import williamjss.model.image.ImageObject;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;

    public Frame() {
        setTitle("JumpBall 2D");
        setIconImage(ImageObject.getIconeJumpBall());
        setSize(960, 640); // (-16, -39)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
