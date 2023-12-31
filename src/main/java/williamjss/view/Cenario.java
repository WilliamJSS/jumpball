package williamjss.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import williamjss.components.Coin;
import williamjss.model.Fontes;
import williamjss.model.image.ImageButton;
import williamjss.model.image.ImageObject;
import williamjss.model.image.ImageScene;

public class Cenario extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final int TIPO_CAMPO = 0;
    public static final int TIPO_MONTANHAS = 1;
    public static final int TIPO_NEVE = 2;
    public static final int TIPO_BASQUETE = 3;
    public static final int TIPO_PRAIA = 4;
    public static final int TIPO_VULCAO = 5;
    public static final int TIPO_VOLEI = 6;
    public static final int TIPO_GOLFE = 7;

    public static final int BOTAO_REPETIR = 10;
    public static final int BOTAO_MENU = 11;
    public static final int BOTAO_AVANCAR = 12;

    private Image imgCenario;

    private int botaoSelecionado;
    private boolean fimDeJogo, inicioDeJogo, botaoAvancarBloqueado;

    private JLabel fundoTransparente;
    private JLabel titulo;
    private JLabel mensagem;
    private JLabel contagem;

    private JLabel botaoAvancar;
    private JLabel botaoMenu;
    private JLabel botaoRepetir;

    private ArrayList<JLabel> estrelasGrandes;

    public Cenario() {
        setSize(944, 601);
        setLayout(null);

        addComponentes();
    }

    public JLabel getBotao(int codBotao) {

        switch (codBotao) {

            case BOTAO_REPETIR:
                return getBotaoRepetir();

            case BOTAO_MENU:
                return getBotaoMenu();

            case BOTAO_AVANCAR:
                return getBotaoAvancar();

            default:
                System.out.println("Erro ao tentar capturar o botao: o botao '" + codBotao + "' nao existe");
                return null;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(getImgCenario(), 0, 0, null);
    }

    public void addComponentes() {
        add(getTitulo());
        add(getMensagem());
        add(getContagem());
        add(getBotaoAvancar());
        add(getBotaoMenu());
        add(getBotaoRepetir());

        for (int i = 0; i < getEstrelasGrandes().size(); i++) {
            add(getEstrelasGrandes().get(i));
        }

        add(getFundoTransparente());
    }

    public void setBotaoSelecionado(int botaoSelecionado) {
        this.botaoSelecionado = botaoSelecionado;

        getBotaoRepetir().setIcon(ImageButton.getImgBotaoRepetir());
        getBotaoMenu().setIcon(ImageButton.getImgBotaoMenu());

        if (!isBotaoAvancarBloqueado()) {
            getBotaoAvancar().setIcon(ImageButton.getImgBotaoAvancar());
        }

        if (botaoSelecionado == Cenario.BOTAO_REPETIR) {
            getBotaoRepetir().setIcon(ImageButton.getImgBotaoRepetirSelecionado());
        } else if (botaoSelecionado == Cenario.BOTAO_MENU) {
            getBotaoMenu().setIcon(ImageButton.getImgBotaoMenuSelecionado());
        } else if (botaoSelecionado == Cenario.BOTAO_AVANCAR) {
            getBotaoAvancar().setIcon(ImageButton.getImgBotaoAvancarSelecionado());
        }

    }

    public void exibirTelaFinal(boolean vitoria, int qntMoedas) {
        setBotaoAvancarBloqueado(!vitoria);

        getFundoTransparente().setVisible(true);
        getMensagem().setText("MOEDAS: " + qntMoedas + "/" + Coin.QNT_TOTAL);
        getMensagem().setVisible(true);
        setBotaoSelecionado(BOTAO_MENU);

        // Calcular pontuacao para determinar a quantidade de estrelas
        int pontuacao = qntMoedas * 100 / Coin.QNT_TOTAL;

        for (int i = 0; i < getEstrelasGrandes().size(); i++) {
            getEstrelasGrandes().get(i).setVisible(true);
            if ((i + 1) * 30 <= pontuacao) {
                if (vitoria) {
                    getEstrelasGrandes().get(i).setIcon(ImageObject.getImgEstrelaGrande());
                } else {
                    getEstrelasGrandes().get(i).setIcon(ImageObject.getImgEstrelaVermelha());
                }
            }
        }

        getBotaoRepetir().setVisible(true);
        getBotaoMenu().setVisible(true);

        if (vitoria) {
            getTitulo().setText("QUE CARA BOM");
            getBotaoAvancar().setIcon(ImageButton.getImgBotaoAvancar());
        } else {
            getTitulo().setText("NÃO CONSEGUE NÉ!?");
            getBotaoAvancar().setIcon(ImageButton.getImgBotaoAvancarBloqueado());
        }

        getBotaoAvancar().setVisible(true);
        getTitulo().setVisible(true);
    }

    public void limparTelaFinal() {
        getFundoTransparente().setVisible(false);
        getTitulo().setVisible(false);
        getMensagem().setVisible(false);
        getBotaoRepetir().setVisible(false);
        getBotaoMenu().setVisible(false);
        getBotaoAvancar().setVisible(false);

        for (int i = 0; i < getEstrelasGrandes().size(); i++) {
            getEstrelasGrandes().get(i).setVisible(false);
            getEstrelasGrandes().get(i).setIcon(ImageObject.getImgEstrelaCinzaGrande());
        }
    }

    public void exibirTelaInicial() {
        getContagem().setVisible(true);
        getContagem().setIcon(ImageObject.getContagemRegressiva().get(0));
        getFundoTransparente().setVisible(true);
        // getTitulo().setText("PRESS SPACE BUTTON TO START!");
        getTitulo().setText("PRESSIONE ESPAÇO PARA COMEÇAR!");
        getTitulo().setVisible(true);
    }

    public void limparTelaInicial() {
        getContagem().setVisible(false);
        getFundoTransparente().setVisible(false);
        getTitulo().setVisible(false);
    }

    public JLabel getBotaoAvancar() {
        if (botaoAvancar == null) {
            botaoAvancar = new JLabel();
            botaoAvancar.setIcon(ImageButton.getImgBotaoAvancar());
            botaoAvancar.setSize(ImageButton.getImgBotaoAvancar().getIconWidth(),
                    ImageButton.getImgBotaoAvancar().getIconHeight());
            botaoAvancar.setLocation(getBotaoMenu().getX() + botaoAvancar.getWidth() + 25, getBotaoMenu().getY());
            botaoAvancar.setVisible(false);
        }
        return botaoAvancar;
    }

    public JLabel getBotaoRepetir() {
        if (botaoRepetir == null) {
            botaoRepetir = new JLabel();
            botaoRepetir.setIcon(ImageButton.getImgBotaoRepetir());
            botaoRepetir.setSize(ImageButton.getImgBotaoRepetir().getIconWidth(),
                    ImageButton.getImgBotaoRepetir().getIconHeight());
            botaoRepetir.setLocation(getBotaoMenu().getX() - botaoRepetir.getWidth() - 25, getBotaoMenu().getY());
            botaoRepetir.setVisible(false);
        }
        return botaoRepetir;
    }

    public JLabel getBotaoMenu() {
        if (botaoMenu == null) {
            botaoMenu = new JLabel();
            botaoMenu.setIcon(ImageButton.getImgBotaoMenu());
            botaoMenu.setSize(ImageButton.getImgBotaoMenu().getIconWidth(), ImageButton.getImgBotaoMenu().getIconHeight());
            botaoMenu.setLocation(getWidth() / 2 - botaoMenu.getWidth() / 2, 400);
            botaoMenu.setVisible(false);
        }
        return botaoMenu;
    }

    public ArrayList<JLabel> getEstrelasGrandes() {
        if (estrelasGrandes == null) {
            estrelasGrandes = new ArrayList<JLabel>();
            JLabel estrelaInicial = gerarEstrelaGrande();
            estrelaInicial.setLocation(estrelaInicial.getX() + estrelaInicial.getWidth() + 30, estrelaInicial.getY());
            estrelasGrandes.add(estrelaInicial);
            for (int i = 1; i < 3; i++) {
                JLabel estrela = gerarEstrelaGrande();
                JLabel estrelaAnterior = estrelasGrandes.get(i - 1);
                estrela.setLocation(estrelaAnterior.getX() - estrela.getWidth() - 30, estrela.getY());
                estrelasGrandes.add(estrela);
            }
        }
        return estrelasGrandes;
    }

    public JLabel getContagem() {
        if (contagem == null) {
            contagem = new JLabel();
            contagem.setIcon(ImageObject.getContagemRegressiva().get(0));
            contagem.setSize(ImageObject.getContagemRegressiva().get(0).getIconWidth(),
                    ImageObject.getContagemRegressiva().get(0).getIconHeight());
            contagem.setLocation(getWidth() / 2 - contagem.getWidth() / 2, getHeight() / 2 - contagem.getHeight() / 2);
            contagem.setVisible(false);
        }
        return contagem;
    }

    public JLabel gerarEstrelaGrande() {
        JLabel estrelaGrande = new JLabel();
        estrelaGrande.setIcon(ImageObject.getImgEstrelaCinzaGrande());
        int x = ImageObject.getImgEstrelaCinzaGrande().getIconWidth();
        int y = ImageObject.getImgEstrelaCinzaGrande().getIconHeight();
        estrelaGrande.setSize(x, y);
        estrelaGrande.setLocation(getWidth() / 2 - estrelaGrande.getWidth() / 2,
                getTitulo().getY() + getTitulo().getHeight() + 30);
        estrelaGrande.setVisible(false);
        return estrelaGrande;
    }

    public JLabel getFundoTransparente() {
        if (fundoTransparente == null) {
            fundoTransparente = new JLabel();
            fundoTransparente.setIcon(ImageScene.getFundoCenario());
            fundoTransparente.setBounds(0, 0, getWidth(), getHeight());
            fundoTransparente.setVisible(false);
        }
        return fundoTransparente;
    }

    public JLabel getTitulo() {
        if (titulo == null) {
            titulo = new JLabel();
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
            titulo.setVerticalAlignment(SwingConstants.CENTER);
            titulo.setFont(Fontes.getTituloJogo());
            titulo.setForeground(Color.WHITE);
            titulo.setSize(getWidth() - 20, 60);
            titulo.setLocation(getWidth() / 2 - titulo.getWidth() / 2, 30);
            titulo.setVisible(false);
        }
        return titulo;
    }

    public JLabel getMensagem() {
        if (mensagem == null) {
            mensagem = new JLabel("MOEDAS: ", SwingConstants.CENTER);
            mensagem.setVerticalAlignment(SwingConstants.CENTER);
            mensagem.setFont(Fontes.getMensagemMoedas());
            mensagem.setForeground(Color.WHITE);
            mensagem.setSize(300, 100);
            mensagem.setLocation(getWidth() / 2 - mensagem.getWidth() / 2,
                    getEstrelasGrandes().get(1).getY() + getEstrelasGrandes().get(1).getHeight() + 20);
            mensagem.setVisible(false);
        }
        return mensagem;
    }

    public Image getImgCenario() {
        return imgCenario;
    }

    public void setImgCenario(Image imgCenario) {
        this.imgCenario = imgCenario;
    }

    public boolean isFimDeJogo() {
        return fimDeJogo;
    }

    public void setFimDeJogo(boolean fimDeJogo) {
        this.fimDeJogo = fimDeJogo;
    }

    public int getBotaoSelecionado() {
        return botaoSelecionado;
    }

    public boolean isInicioDeJogo() {
        return inicioDeJogo;
    }

    public void setInicioDeJogo(boolean inicioDeJogo) {
        this.inicioDeJogo = inicioDeJogo;
    }

    public boolean isBotaoAvancarBloqueado() {
        return botaoAvancarBloqueado;
    }

    public void setBotaoAvancarBloqueado(boolean botaoAvancarBloqueado) {
        this.botaoAvancarBloqueado = botaoAvancarBloqueado;
    }

}
