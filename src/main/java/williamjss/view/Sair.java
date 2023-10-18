package williamjss.view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import williamjss.model.Fontes;
import williamjss.model.image.ImageButton;
import williamjss.model.image.ImageObject;
import williamjss.model.image.ImageScene;

public class Sair extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel logo;
    private JLabel botaoSim;
    private JLabel botaoNao;
    private JLabel fundoSair;
    private JLabel botaoFechar;
    private ArrayList<JLabel> textoBotao;
    private JTextPane mensagemSair;

    private int botaoSelecionado;

    public static final int BOTAO_SIM = 0;
    public static final int BOTAO_NAO = 1;

    public Sair() {
        setLayout(null);
        setSize(944, 601);

        for (int i = 0; i < getTextoBotao().size(); i++) {
            add(getTextoBotao().get(i));
        }

        add(getBotaoSim());
        add(getBotaoNao());
        add(getLogo());
        add(getMensagemSair());
        add(getBotaoFechar());
        add(getFundoSair());

        setBotaoSelecionado(BOTAO_NAO);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(ImageScene.getCenarioMenu(), 0, 0, null);
    }

    public JLabel getBotao(int codBotao) {
        if (codBotao == BOTAO_SIM) {
            return getBotaoSim();
        } else if (codBotao == BOTAO_NAO) {
            return getBotaoNao();
        }
        return null;
    }

    public int getBotaoSelecionado() {
        return botaoSelecionado;
    }

    public void setBotaoSelecionado(int botaoSelecionado) {
        this.botaoSelecionado = botaoSelecionado;

        getBotao(BOTAO_SIM).setIcon(ImageButton.getImgBotao1());
        getBotao(BOTAO_NAO).setIcon(ImageButton.getImgBotao2());

        if (botaoSelecionado == BOTAO_SIM) {
            getBotao(botaoSelecionado).setIcon(ImageButton.getImgBotao1Selecionado());
        } else {
            getBotao(botaoSelecionado).setIcon(ImageButton.getImgBotao2Selecionado());
        }
    }

    // Fundo Transparente
    public JLabel getFundoSair() {
        if (fundoSair == null) {
            fundoSair = new JLabel();
            fundoSair.setIcon(ImageScene.getFundoSair());
            fundoSair.setSize(ImageScene.getFundoSair().getIconWidth(), ImageScene.getFundoSair().getIconHeight());
            fundoSair.setLocation(getWidth() / 2 - fundoSair.getWidth() / 2,
                    getHeight() / 2 - fundoSair.getHeight() / 2 + 90);
        }
        return fundoSair;
    }

    // Mensagem sair
    public JTextPane getMensagemSair() {
        if (mensagemSair == null) {
            mensagemSair = new JTextPane();
            String txt = "Você tem certeza que já quer sair?";
            mensagemSair.setText(txt.toUpperCase());
            mensagemSair.setEditable(false);
            mensagemSair.setOpaque(false);
            mensagemSair.setForeground(Color.WHITE);
            mensagemSair.setFont(Fontes.getMensagemSair());
            mensagemSair.setSize(getFundoSair().getWidth() - 50, 100);
            mensagemSair.setLocation(getWidth() / 2 - mensagemSair.getWidth() / 2, getFundoSair().getY() + 30);

            // Centralizar
            StyledDocument doc = mensagemSair.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        }
        return mensagemSair;
    }

    // Texto botoes
    public ArrayList<JLabel> getTextoBotao() {
        if (textoBotao == null) {
            textoBotao = new ArrayList<JLabel>();
            String[] txt = { "SIM", "NÃO" };
            for (int i = 0; i < 2; i++) {
                JLabel texto = new JLabel(txt[i]);
                texto.setHorizontalAlignment(SwingConstants.CENTER);
                texto.setForeground(Color.WHITE);
                texto.setFont(Fontes.getBotao());
                texto.setBounds(getBotao(i).getBounds());
                textoBotao.add(texto);
            }
        }
        return textoBotao;
    }

    // Botao SIM
    public JLabel getBotaoSim() {
        if (botaoSim == null) {
            botaoSim = new JLabel();
            botaoSim.setIcon(ImageButton.getImgBotao1());
            botaoSim.setSize(ImageButton.getImgBotao1().getIconWidth(), ImageButton.getImgBotao1().getIconHeight());
            botaoSim.setLocation(getWidth() / 2 - botaoSim.getWidth() - 20,
                    getFundoSair().getY() + getFundoSair().getHeight() / 2 + 10);
        }
        return botaoSim;
    }

    // Botao NAO
    public JLabel getBotaoNao() {
        if (botaoNao == null) {
            botaoNao = new JLabel();
            botaoNao.setIcon(ImageButton.getImgBotao2());
            botaoNao.setSize(ImageButton.getImgBotao2().getIconWidth(), ImageButton.getImgBotao2().getIconHeight());
            botaoNao.setLocation(getWidth() / 2 + 20, getFundoSair().getY() + getFundoSair().getHeight() / 2 + 10);
        }
        return botaoNao;
    }

    // Botao fechar
    public JLabel getBotaoFechar() {
        if (botaoFechar == null) {
            botaoFechar = new JLabel();
            botaoFechar.setIcon(ImageButton.getImgBotaoFechar());
            botaoFechar.setSize(ImageButton.getImgBotaoFechar().getIconWidth(),
                    ImageButton.getImgBotaoFechar().getIconHeight());
            int x = getFundoSair().getX() + getFundoSair().getWidth() - botaoFechar.getWidth() / 2 - 10;
            int y = getFundoSair().getY() - botaoFechar.getHeight() / 2 + 10;
            botaoFechar.setLocation(x, y);
        }
        return botaoFechar;
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
}
