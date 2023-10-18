package williamjss.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import williamjss.model.Fontes;
import williamjss.model.Imagens;

public class Menu extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel logo;
    private JLabel botaoJogar;
    private JLabel botaoAjuda;
    private JLabel botaoSair;
    private JLabel fundoMenu;

    private int botaoSelecionado;
    private ArrayList<JLabel> textoBotao;

    public static final int BOTAO_JOGAR = 0;
    public static final int BOTAO_AJUDA = 1;
    public static final int BOTAO_SAIR = 2;

    public Menu() {
        super();
        setSize(944, 601);
        setLayout(null);

        for (int i = 0; i < getTextoBotao().size(); i++) {
            add(getTextoBotao().get(i));
        }

        add(getBotaoJogar());
        add(getBotaoAjuda());
        add(getBotaoSair());
        add(getLogo());
        add(getFundoMenu());

        setBotaoSelecionado(BOTAO_JOGAR);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(Imagens.getCenarioMenu(), 0, 0, null);
    }

    public JLabel getBotao(int codBotao) {

        switch (codBotao) {

            case BOTAO_JOGAR:
                return getBotaoJogar();

            case BOTAO_AJUDA:
                return getBotaoAjuda();

            case BOTAO_SAIR:
                return getBotaoSair();

            default:
                System.out.println("Erro ao tentar capturar o botao: o botao '" + codBotao + "' nao existe");
                return null;
        }
    }

    public int getBotaoSelecionado() {
        return botaoSelecionado;
    }

    public void setBotaoSelecionado(int botaoSelecionado) {
        this.botaoSelecionado = botaoSelecionado;

        getBotao(BOTAO_JOGAR).setIcon(Imagens.getImgBotao2());
        getBotao(BOTAO_AJUDA).setIcon(Imagens.getImgBotao2());
        getBotao(BOTAO_SAIR).setIcon(Imagens.getImgBotao2());

        if (botaoSelecionado == BOTAO_JOGAR) {
            getBotao(botaoSelecionado).setIcon(Imagens.getImgBotao1Selecionado());
        } else if (botaoSelecionado == BOTAO_AJUDA) {
            getBotao(botaoSelecionado).setIcon(Imagens.getImgBotao1Selecionado());
        } else {
            getBotao(botaoSelecionado).setIcon(Imagens.getImgBotao1Selecionado());
        }
    }

    // Texto botoes
    public ArrayList<JLabel> getTextoBotao() {
        if (textoBotao == null) {
            textoBotao = new ArrayList<JLabel>();
            String[] txt = { "JOGAR", "AJUDA", "SAIR" };
            for (int i = 0; i < 3; i++) {
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

    // Botao jogar
    public JLabel getBotaoJogar() {
        if (botaoJogar == null) {
            botaoJogar = new JLabel();
            ;
            botaoJogar.setIcon(Imagens.getImgBotao1());
            botaoJogar.setSize(Imagens.getImgBotao1().getIconWidth(), Imagens.getImgBotao1().getIconHeight());
            int y = getFundoMenu().getY() + (getFundoMenu().getHeight() / 3 - botaoJogar.getHeight());
            botaoJogar.setLocation(getWidth() / 2 - botaoJogar.getWidth() / 2, y);
        }
        return botaoJogar;
    }

    // Botao ajuda
    public JLabel getBotaoAjuda() {
        if (botaoAjuda == null) {
            botaoAjuda = new JLabel();
            botaoAjuda.setIcon(Imagens.getImgBotao2());
            botaoAjuda.setSize(Imagens.getImgBotao2().getIconWidth(), Imagens.getImgBotao2().getIconHeight());
            int y = getBotaoJogar().getY() + getBotaoJogar().getHeight()
                    + (getFundoMenu().getHeight() / 3 - getBotaoJogar().getHeight()) / 2;
            botaoAjuda.setLocation(getWidth() / 2 - botaoAjuda.getWidth() / 2, y);
        }
        return botaoAjuda;
    }

    // Botao sair
    public JLabel getBotaoSair() {
        if (botaoSair == null) {
            botaoSair = new JLabel();
            botaoSair.setIcon(Imagens.getImgBotao1());
            botaoSair.setSize(Imagens.getImgBotao1().getIconWidth(), Imagens.getImgBotao1().getIconHeight());
            int y = getBotaoAjuda().getY() + getBotaoAjuda().getHeight()
                    + (getFundoMenu().getHeight() / 3 - getBotaoJogar().getHeight()) / 2;
            botaoSair.setLocation(getWidth() / 2 - botaoSair.getWidth() / 2, y);
        }
        return botaoSair;
    }

    // Logo
    public JLabel getLogo() {
        if (logo == null) {
            logo = new JLabel();
            logo.setIcon(Imagens.getLogoJumpBall());
            logo.setSize(Imagens.getLogoJumpBall().getIconWidth(), Imagens.getLogoJumpBall().getIconHeight());
            logo.setLocation(getWidth() / 2 - logo.getWidth() / 2, 10);
        }
        return logo;
    }

    public JLabel getFundoMenu() {
        if (fundoMenu == null) {
            fundoMenu = new JLabel();
            fundoMenu.setIcon(Imagens.getFundoMenu());
            fundoMenu.setSize(Imagens.getFundoMenu().getIconWidth(), Imagens.getFundoMenu().getIconHeight());
            fundoMenu.setLocation(getWidth() / 2 - fundoMenu.getWidth() / 2, 260);
        }
        return fundoMenu;
    }

}
