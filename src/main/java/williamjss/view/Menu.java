package williamjss.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import williamjss.model.Fontes;
import williamjss.model.image.ImageButton;
import williamjss.model.image.ImageObject;
import williamjss.model.image.ImageScene;

public class Menu extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel logo;
    private JLabel buttonTop;
    private JLabel buttonMid;
    private JLabel buttonBot;
    private JLabel fundoMenu;

    private int botaoSelecionado;
    private ArrayList<JLabel> textoBotao;
    private ArrayList<JLabel> buttons;

    public static final int BOTAO_JOGAR = 0;
    public static final int BOTAO_AJUDA = 1;
    public static final int BOTAO_OPCOES = 2;
    public static final int BOTAO_SAIR = 3;

    public Menu() {
        super();
        setSize(944, 601);
        setLayout(null);

        for (int i = 0; i < getTextoBotao().size(); i++) {
            add(getTextoBotao().get(i));
            add(getButtons().get(i));
        }

        add(getLogo());
        add(getFundoMenu());

        setBotaoSelecionado(BOTAO_JOGAR);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(ImageScene.getCenarioMenu(), 0, 0, null);
    }

    public int getBotaoSelecionado() {
        return botaoSelecionado;
    }

    public void setBotaoSelecionado(int botaoSelecionado) {
        this.botaoSelecionado = botaoSelecionado;

        String[] txt = { "JOGAR", "AJUDA", "OPÇÕES", "SAIR" };

        int txtButtonTop = botaoSelecionado == BOTAO_JOGAR ? BOTAO_SAIR : botaoSelecionado - 1;
        int txtButtonBot = botaoSelecionado == BOTAO_SAIR ? BOTAO_JOGAR : botaoSelecionado + 1;

        getTextoBotao().get(0).setText(txt[txtButtonTop]);
        getTextoBotao().get(1).setText(txt[botaoSelecionado]);
        getTextoBotao().get(2).setText(txt[txtButtonBot]);

        Icon buttonMidIcon = getButtonMid().getIcon().equals(ImageButton.getImgBotao2Selecionado())
                ? ImageButton.getImgBotao1Selecionado()
                : ImageButton.getImgBotao2Selecionado();

        Icon buttonCorner = getButtonTop().getIcon().equals(ImageButton.getImgBotao2())
                ? ImageButton.getImgBotao1()
                : ImageButton.getImgBotao2();

        getButtonTop().setIcon(buttonCorner);
        getButtonMid().setIcon(buttonMidIcon);
        getButtonBot().setIcon(buttonCorner);
    }

    // Botoes
    public ArrayList<JLabel> getButtons() {
        if (buttons == null) {
            buttons = new ArrayList<JLabel>();
            buttons.add(getButtonTop());
            buttons.add(getButtonMid());
            buttons.add(getButtonBot());
        }
        return buttons;
    }

    // Texto botoes
    public ArrayList<JLabel> getTextoBotao() {
        if (textoBotao == null) {
            textoBotao = new ArrayList<JLabel>();
            String[] txt = { "SAIR", "JOGAR", "AJUDA" };
            for (int i = 0; i < 3; i++) {
                JLabel texto = new JLabel(txt[i]);
                texto.setHorizontalAlignment(SwingConstants.CENTER);
                texto.setForeground(Color.WHITE);
                texto.setFont(Fontes.getBotao());
                texto.setBounds(getButtons().get(i).getBounds());
                textoBotao.add(texto);
            }
        }
        return textoBotao;
    }

    // Botao de cima
    public JLabel getButtonTop() {
        if (buttonTop == null) {
            buttonTop = new JLabel();
            buttonTop.setIcon(ImageButton.getImgBotao2());
            buttonTop.setSize(ImageButton.getImgBotao2().getIconWidth(), ImageButton.getImgBotao2().getIconHeight());
            int y = getFundoMenu().getY() + (getFundoMenu().getHeight() / 3 - buttonTop.getHeight());
            buttonTop.setLocation(getWidth() / 2 - buttonTop.getWidth() / 2, y);
        }
        return buttonTop;
    }

    // Botao do meio
    public JLabel getButtonMid() {
        if (buttonMid == null) {
            buttonMid = new JLabel();
            buttonMid.setIcon(ImageButton.getImgBotao1Selecionado());
            buttonMid.setSize(ImageButton.getImgBotao1Selecionado().getIconWidth(),
                    ImageButton.getImgBotao1Selecionado().getIconHeight());
            int y = getButtonTop().getY() + getButtonTop().getHeight()
                    + (getFundoMenu().getHeight() / 3 - getButtonTop().getHeight()) / 2;
            buttonMid.setLocation(getWidth() / 2 - buttonMid.getWidth() / 2, y);
        }
        return buttonMid;
    }

    // Botao de baixo
    public JLabel getButtonBot() {
        if (buttonBot == null) {
            buttonBot = new JLabel();
            buttonBot.setIcon(ImageButton.getImgBotao2());
            buttonBot.setSize(ImageButton.getImgBotao2().getIconWidth(), ImageButton.getImgBotao2().getIconHeight());
            int y = getButtonMid().getY() + getButtonMid().getHeight()
                    + (getFundoMenu().getHeight() / 3 - getButtonTop().getHeight()) / 2;
            buttonBot.setLocation(getWidth() / 2 - buttonBot.getWidth() / 2, y);
        }
        return buttonBot;
    }

    // Logo
    public JLabel getLogo() {
        if (logo == null) {
            logo = new JLabel();
            logo.setIcon(ImageObject.getLogoJumpBall());
            logo.setSize(ImageObject.getLogoJumpBall().getIconWidth(), ImageObject.getLogoJumpBall().getIconHeight());
            logo.setLocation(getWidth() / 2 - logo.getWidth() / 2, 10);
        }
        return logo;
    }

    public JLabel getFundoMenu() {
        if (fundoMenu == null) {
            fundoMenu = new JLabel();
            fundoMenu.setIcon(ImageScene.getFundoMenu());
            fundoMenu.setSize(ImageScene.getFundoMenu().getIconWidth(), ImageScene.getFundoMenu().getIconHeight());
            fundoMenu.setLocation(getWidth() / 2 - fundoMenu.getWidth() / 2, 260);
        }
        return fundoMenu;
    }

}
