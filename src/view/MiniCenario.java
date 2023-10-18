package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Imagens;

public class MiniCenario extends JPanel {

    private static final long serialVersionUID = 1L;

    private Image img;
    private boolean bloqueado;
    private int estrelasDesbloquear; // Quantidade de estrelas necessarias para desbloquear esse cenario

    // MiniCenario desbloqueado
    private int qntEstrelas; // Quantidade de estrelas obtidas nesse cenario
    private ArrayList<JLabel> estrelas; // Estrelas obtidas nesse cenario (aparece 3 estrelas de cor cinza ou amarela)

    // MiniCenario bloqueado
    private int estrelasRestantes; // Quantidade de estrelas que faltam para desbloquear o cenario
    private JLabel estrelaCont; // Para exibir as estrelasRestantes
    private JLabel cadeado;
    private JLabel fundoMiniCenario;

    public MiniCenario(int x, int y, Image img) {
        setSize(Imagens.getMiniCenarioCampo().getIconWidth(), Imagens.getMiniCenarioCampo().getIconHeight());
        setLocation(x, y);
        setLayout(null);

        this.img = img;
        this.bloqueado = true;
        this.qntEstrelas = 0;
        this.estrelasRestantes = 0;

        adicionarComponentes();
        atualizarMiniCenario();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(getImg(), 0, 0, null);
    }

    public void adicionarComponentes() {
        add(getEstrelaCont());
        add(getIconeCadeado());
        add(getFundoMiniCenario());

        for (int i = 0; i < getEstrelas().size(); i++) {
            JLabel estrela = getEstrelas().get(i);
            add(estrela);
        }
    }

    public void atualizarMiniCenario() {

        // Componentes de tela
        getEstrelaCont().setVisible(isBloqueado());
        getIconeCadeado().setVisible(isBloqueado());
        getFundoMiniCenario().setVisible(isBloqueado());

        for (int i = 0; i < getEstrelas().size(); i++) {
            JLabel estrela = getEstrelas().get(i);
            estrela.setVisible(!isBloqueado());
        }

        // Estrelas restantes, caso esteja bloqueado o miniCenario
        getEstrelaCont().setText("" + getEstrelasRestantes());

        // Quantidade de estrelas obtidas, alterar entre estrela cinza e amarela
        for (int i = 0; i < getEstrelas().size(); i++) {
            JLabel estrela = getEstrelas().get(i);
            if (i < getQntEstrelas()) {
                estrela.setIcon(Imagens.getImgEstrela()); // Coloca estrela amarela
            } else {
                estrela.setIcon(Imagens.getImgEstrelaCinza()); // Coloca estrela cinza
            }
        }

    }

    public void desbloquearCenario() {
        setBloqueado(false);
        getEstrelaCont().setVisible(false);
        setEstrelasRestantes(0);
        getFundoMiniCenario().setVisible(false);
        getIconeCadeado().setVisible(false);

        for (int i = 0; i < getEstrelas().size(); i++) {
            JLabel estrela = getEstrelas().get(i);
            estrela.setVisible(true);
        }
    }

    public JLabel getFundoMiniCenario() {
        if (fundoMiniCenario == null) {
            fundoMiniCenario = new JLabel();
            fundoMiniCenario.setIcon(Imagens.getFundoMiniCenario());
            fundoMiniCenario.setBounds(0, 0, getWidth(), getHeight());
        }
        return fundoMiniCenario;
    }

    public JLabel getIconeCadeado() {
        if (cadeado == null) {
            cadeado = new JLabel();
            cadeado.setIcon(Imagens.getImgCadeado());
            cadeado.setSize(Imagens.getImgCadeado().getIconWidth(), Imagens.getImgCadeado().getIconHeight());
            cadeado.setLocation(getWidth() / 2 - cadeado.getWidth() / 2,
                    getHeight() / 2 - cadeado.getHeight() / 2 + 10);
        }
        return cadeado;
    }

    public JLabel getEstrelaCont() {
        if (estrelaCont == null) {
            estrelaCont = new JLabel("    " + getEstrelasRestantes());
            estrelaCont.setFont(new Font("Hemi Head Rg", Font.PLAIN, 36));
            estrelaCont.setForeground(Color.WHITE);
            estrelaCont.setIcon(Imagens.getImgEstrela());
            estrelaCont.setSize(Imagens.getImgEstrela().getIconWidth() * 2 + 25,
                    Imagens.getImgEstrela().getIconHeight());
            estrelaCont.setLocation(getWidth() - estrelaCont.getWidth(), 10);
        }
        return estrelaCont;
    }

    public ArrayList<JLabel> getEstrelas() {
        if (estrelas == null) {
            estrelas = new ArrayList<JLabel>();

            ImageIcon imgEstrela = Imagens.getImgEstrela();
            int largura = imgEstrela.getIconWidth();
            int altura = imgEstrela.getIconHeight();

            JLabel estrelaInicial = new JLabel();
            estrelaInicial.setIcon(imgEstrela);
            estrelaInicial.setBounds(getWidth() - largura - 10, 10, largura, altura);
            estrelas.add(estrelaInicial);

            for (int i = 1; i <= 2; i++) {
                JLabel novaEstrela = new JLabel();
                novaEstrela.setIcon(imgEstrela);
                novaEstrela.setBounds(estrelas.get(i - 1).getX() - largura - 10, 10, largura, altura);
                estrelas.add(novaEstrela);
            }
        }

        return estrelas;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getQntEstrelas() {
        return qntEstrelas;
    }

    public void setQntEstrelas(int qntEstrelas) {
        this.qntEstrelas = qntEstrelas;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getEstrelasRestantes() {
        return estrelasRestantes;
    }

    public void setEstrelasRestantes(int estrelasRestantes) {
        this.estrelasRestantes = estrelasRestantes;
    }

    public int getEstrelasDesbloquear() {
        return estrelasDesbloquear;
    }

    public void setEstrelasDesbloquear(int estrelasDesbloquear) {
        this.estrelasDesbloquear = estrelasDesbloquear;
    }

}
