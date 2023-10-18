package williamjss.view;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import williamjss.model.Cores;
import williamjss.model.Fontes;
import williamjss.model.Imagens;

public class Fases extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel botaoVoltar;
    private JLabel bolaDeSelecao;
    private JLabel titulo;

    private MiniCenario miniCenarioCampo;
    private MiniCenario miniCenarioMontanhas;
    private MiniCenario miniCenarioNeve;
    private MiniCenario miniCenarioPraia;
    private MiniCenario miniCenarioVulcao;
    private MiniCenario miniCenarioBasquete;
    private MiniCenario miniCenarioGolfe;
    private MiniCenario miniCenarioVolei;

    private int posicaoX, posicaoY;
    private int cenarioSelecionado;

    public Fases() {
        setSize(944, 601);
        setLayout(null);

        add(getBolaDeSelecao());
        add(getMiniCenarioCampo());
        add(getMiniCenarioMontanhas());
        add(getMiniCenarioNeve());
        add(getMiniCenarioPraia());
        add(getMiniCenarioVulcao());
        add(getMiniCenarioBasquete());
        add(getMiniCenarioVolei());
        add(getMiniCenarioGolfe());
        add(getBotaoVoltar());
        add(getTitulo());

        atualizarSelecao(Cenario.TIPO_CAMPO);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(Imagens.getCenarioMenu(), 0, 0, null);
    }

    public void atualizarSelecao(int codMiniCenario) {
        setCenarioSelecionado(codMiniCenario);

        int x = getMiniCenario(codMiniCenario).getX() - 10;
        int y = getMiniCenario(codMiniCenario).getY() + getMiniCenario(codMiniCenario).getHeight()
                - getBolaDeSelecao().getHeight() + 10;

        getBolaDeSelecao().setLocation(x, y);

        switch (codMiniCenario) {

            case Cenario.TIPO_CAMPO:
                getBolaDeSelecao().setIcon(Imagens.getSpriteBolaFutebol().get(0));
                break;

            case Cenario.TIPO_MONTANHAS:
                if (getMiniCenario(codMiniCenario).isBloqueado()) {
                    getBolaDeSelecao().setIcon(Imagens.getBolaPadrao());
                } else {
                    getBolaDeSelecao().setIcon(Imagens.getSpriteBolaEspinho().get(0));
                }
                break;

            case Cenario.TIPO_NEVE:
                if (getMiniCenario(codMiniCenario).isBloqueado()) {
                    getBolaDeSelecao().setIcon(Imagens.getBolaPadrao());
                } else {
                    getBolaDeSelecao().setIcon(Imagens.getSpriteBolaPedra().get(0));
                }
                break;

            case Cenario.TIPO_PRAIA:
                if (getMiniCenario(codMiniCenario).isBloqueado()) {
                    getBolaDeSelecao().setIcon(Imagens.getBolaPadrao());
                } else {
                    getBolaDeSelecao().setIcon(Imagens.getSpriteBolaPraia().get(0));
                }
                break;

            case Cenario.TIPO_VULCAO:
                if (getMiniCenario(codMiniCenario).isBloqueado()) {
                    getBolaDeSelecao().setIcon(Imagens.getBolaPadrao());
                } else {
                    getBolaDeSelecao().setIcon(Imagens.getSpriteBolaVulcao().get(0));
                }
                break;

            case Cenario.TIPO_VOLEI:
                if (getMiniCenario(codMiniCenario).isBloqueado()) {
                    getBolaDeSelecao().setIcon(Imagens.getBolaPadrao());
                } else {
                    getBolaDeSelecao().setIcon(Imagens.getSpriteBolaVolei().get(0));
                }
                break;

            case Cenario.TIPO_BASQUETE:
                if (getMiniCenario(codMiniCenario).isBloqueado()) {
                    getBolaDeSelecao().setIcon(Imagens.getBolaPadrao());
                } else {
                    getBolaDeSelecao().setIcon(Imagens.getSpriteBolaBasquete().get(0));
                }
                break;

            case Cenario.TIPO_GOLFE:
                if (getMiniCenario(codMiniCenario).isBloqueado()) {
                    getBolaDeSelecao().setIcon(Imagens.getBolaPadrao());
                } else {
                    getBolaDeSelecao().setIcon(Imagens.getSpriteBolaGolfe().get(0));
                }
                break;
        }

    }

    public MiniCenario getMiniCenario(int codBotao) {

        switch (codBotao) {

            case Cenario.TIPO_CAMPO:
                return getMiniCenarioCampo();

            case Cenario.TIPO_MONTANHAS:
                return getMiniCenarioMontanhas();

            case Cenario.TIPO_NEVE:
                return getMiniCenarioNeve();

            case Cenario.TIPO_PRAIA:
                return getMiniCenarioPraia();

            case Cenario.TIPO_VULCAO:
                return getMiniCenarioVulcao();

            case Cenario.TIPO_BASQUETE:
                return getMiniCenarioBasquete();

            case Cenario.TIPO_VOLEI:
                return getMiniCenarioVolei();

            case Cenario.TIPO_GOLFE:
                return getMiniCenarioGolfe();

            default:
                System.out.println("Erro ao tentar capturar o botao: o botao '" + codBotao + "' nao existe");
                return null;
        }
    }

    public int getCenarioSelecionado() {
        return cenarioSelecionado;
    }

    public void setCenarioSelecionado(int cenarioSelecionado) {
        this.cenarioSelecionado = cenarioSelecionado;
    }

    public MiniCenario getMiniCenarioCampo() {
        if (miniCenarioCampo == null) {
            posicaoX = 56;
            posicaoY = 140;
            miniCenarioCampo = new MiniCenario(posicaoX, posicaoY, Imagens.getMiniCenarioCampo().getImage());
            miniCenarioCampo.setEstrelasDesbloquear(0);
        }
        return miniCenarioCampo;
    }

    public MiniCenario getMiniCenarioMontanhas() {
        if (miniCenarioMontanhas == null) {
            posicaoX = getMiniCenarioCampo().getX() + getMiniCenarioCampo().getWidth() + 20;
            posicaoY = getMiniCenarioCampo().getY();
            miniCenarioMontanhas = new MiniCenario(posicaoX, posicaoY, Imagens.getMiniCenarioMontanhas().getImage());
            miniCenarioMontanhas.setEstrelasDesbloquear(2);
        }
        return miniCenarioMontanhas;
    }

    public MiniCenario getMiniCenarioNeve() {
        if (miniCenarioNeve == null) {
            posicaoX = getMiniCenarioMontanhas().getX() + getMiniCenarioMontanhas().getWidth() + 20;
            posicaoY = getMiniCenarioMontanhas().getY();
            miniCenarioNeve = new MiniCenario(posicaoX, posicaoY, Imagens.getMiniCenarioNeve().getImage());
            miniCenarioNeve.setEstrelasDesbloquear(5);
        }
        return miniCenarioNeve;
    }

    public MiniCenario getMiniCenarioBasquete() {
        if (miniCenarioBasquete == null) {
            posicaoX = getMiniCenarioNeve().getX() + getMiniCenarioNeve().getWidth() + 20;
            posicaoY = getMiniCenarioNeve().getY();
            miniCenarioBasquete = new MiniCenario(posicaoX, posicaoY, Imagens.getMiniCenarioBasquete().getImage());
            miniCenarioBasquete.setEstrelasDesbloquear(7);
        }
        return miniCenarioBasquete;
    }

    public MiniCenario getMiniCenarioPraia() {
        if (miniCenarioPraia == null) {
            posicaoX = getMiniCenarioCampo().getX();
            posicaoY = getMiniCenarioCampo().getY() + getMiniCenarioCampo().getHeight() + 20;
            miniCenarioPraia = new MiniCenario(posicaoX, posicaoY, Imagens.getMiniCenarioPraia().getImage());
            miniCenarioPraia.setEstrelasDesbloquear(10);
        }
        return miniCenarioPraia;
    }

    public MiniCenario getMiniCenarioVulcao() {
        if (miniCenarioVulcao == null) {
            posicaoX = getMiniCenarioPraia().getX() + getMiniCenarioPraia().getWidth() + 20;
            posicaoY = getMiniCenarioPraia().getY();
            miniCenarioVulcao = new MiniCenario(posicaoX, posicaoY, Imagens.getMiniCenarioVulcao().getImage());
            miniCenarioVulcao.setEstrelasDesbloquear(13);
        }
        return miniCenarioVulcao;
    }

    public MiniCenario getMiniCenarioVolei() {
        if (miniCenarioVolei == null) {
            posicaoX = getMiniCenarioVulcao().getX() + getMiniCenarioVulcao().getWidth() + 20;
            posicaoY = getMiniCenarioVulcao().getY();
            miniCenarioVolei = new MiniCenario(posicaoX, posicaoY, Imagens.getMiniCenarioVolei().getImage());
            miniCenarioVolei.setEstrelasDesbloquear(17);
        }
        return miniCenarioVolei;
    }

    public MiniCenario getMiniCenarioGolfe() {
        if (miniCenarioGolfe == null) {
            posicaoX = getMiniCenarioVolei().getX() + getMiniCenarioVolei().getWidth() + 20;
            posicaoY = getMiniCenarioVolei().getY();
            miniCenarioGolfe = new MiniCenario(posicaoX, posicaoY, Imagens.getMiniCenarioGolfe().getImage());
            miniCenarioGolfe.setEstrelasDesbloquear(20);
        }
        return miniCenarioGolfe;
    }

    public JLabel getBotaoVoltar() {
        if (botaoVoltar == null) {
            botaoVoltar = new JLabel("ESC");
            botaoVoltar.setFont(Fontes.getBaloo());
            botaoVoltar.setForeground(Cores.getAzulEscuro());
            botaoVoltar.setIcon(Imagens.getImgBotaoVoltar());
            botaoVoltar.setSize(Imagens.getImgBotaoVoltar().getIconWidth() + 200,
                    Imagens.getImgBotaoVoltar().getIconHeight());
            botaoVoltar.setLocation(20, 15);
        }
        return botaoVoltar;
    }

    public JLabel getBolaDeSelecao() {
        if (bolaDeSelecao == null) {
            bolaDeSelecao = new JLabel();
            bolaDeSelecao.setIcon(Imagens.getBolaPadrao());
            bolaDeSelecao.setSize(Imagens.getBolaPadrao().getIconWidth(), Imagens.getBolaPadrao().getIconHeight());
        }
        return bolaDeSelecao;
    }

    public JLabel getTitulo() {
        if (titulo == null) {
            titulo = new JLabel("FASES", SwingConstants.CENTER);
            titulo.setFont(Fontes.getTitulo());
            titulo.setForeground(Cores.getAzulEscuro());
            titulo.setSize(200, 100);
            titulo.setLocation(getWidth() / 2 - titulo.getWidth() / 2, 10);
        }
        return titulo;
    }

}
