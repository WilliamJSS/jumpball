package williamjss.components;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import williamjss.model.image.ImageBlock;

public class Platform extends ArrayList<Block> {

    private ArrayList<int[]> padroes;

    // Generate main platforms
    public Platform(int type, int[] stage) {
        super();

        this.padroes = getPadroes();

        // 4 blocos (1 plataforma) de distancia entre cada coluna
        int dx = 4 * Block.SIZE;
        int tamanho;

        for (int i = 0; i < stage.length; i++) {

            // Adiciona um padrao de plataformas ao arrayList
            tamanho = this.size();
            for (int position : padroes.get(stage[i])) {
                this.addAll(setPosition(position, type));
            }

            // Atualiza a posicao X dos blocos do padrao recem adicionados
            for (int k = tamanho; k < this.size(); k++) {
                Block bloco = this.get(k);
                bloco.setLocation(bloco.getX() + i * dx, bloco.getY());
            }
        }
    }

    // Generate bottom platform
    public Platform(int type) {
        super();

        this.addAll(generate(35, type));

        Block blocoInicial = this.get(0);
        blocoInicial.setLocation(0, Block.NIVEL_FLOOR);

        for (int i = 1; i < this.size(); i++) {
            Block blocoAnterior = this.get(i - 1);
            Block blocoAtual = this.get(i);

            // Adicionando os blocos restantes subsequentemente ao primeiro
            blocoAtual.setLocation(blocoAnterior.getX() + blocoAnterior.getWidth(), blocoInicial.getY());
        }
    }

    // The structures are defined based by scene column nivels
    // 0 - void, 1 - TOP, 2 - MID, 3 - BOTTOM
    public ArrayList<int[]> getPadroes() {
        if (padroes == null) {
            padroes = new ArrayList<int[]>();
            padroes.add(new int[] { 0 }); // 0 - zero
            padroes.add(new int[] { 1 }); // 1 - top
            padroes.add(new int[] { 2 }); // 2 - mid
            padroes.add(new int[] { 3 }); // 3 - bottom
            padroes.add(new int[] { 1, 2 }); // 4 - top, mid
            padroes.add(new int[] { 1, 3 }); // 5 - top, bot
            padroes.add(new int[] { 2, 3 }); // 6 - mid, bot
            padroes.add(new int[] { 1, 2, 3 }); // 7 - all
        }
        return padroes;
    }

    // Gera uma plataforma com a quantidade de blocos e tipo especificado
    public ArrayList<Block> generate(int length, int type) {

        ArrayList<Block> plataforma = new ArrayList<Block>();

        ImageIcon imgBloco1, imgBloco2;

        switch (type) {

            case Block.TIPO_AGUA:
                imgBloco1 = ImageBlock.getBlocoAgua();
                imgBloco2 = ImageBlock.getBlocoAgua();
                break;

            case Block.TIPO_GELO:
                imgBloco1 = ImageBlock.getBlocoGelo();
                imgBloco2 = ImageBlock.getBlocoGelo();
                break;

            case Block.TIPO_GRAMA:
                imgBloco1 = ImageBlock.getBlocoGrama1();
                imgBloco2 = ImageBlock.getBlocoGrama2();
                break;

            case Block.TIPO_GRAMADO:
                imgBloco1 = ImageBlock.getBlocoGrama();
                imgBloco2 = ImageBlock.getBlocoGrama();
                break;

            case Block.TIPO_LAVA:
                imgBloco1 = ImageBlock.getBlocoLava();
                imgBloco2 = ImageBlock.getBlocoLava();
                break;

            case Block.TIPO_PEDRA:
                imgBloco1 = ImageBlock.getBlocoPedra();
                imgBloco2 = ImageBlock.getBlocoPedra();
                break;

            case Block.TIPO_AREIA:
                imgBloco1 = ImageBlock.getBlocoAreia();
                imgBloco2 = ImageBlock.getBlocoAreia();
                break;

            case Block.TIPO_ESPINHO:
                imgBloco1 = ImageBlock.getBlocoEspinho();
                imgBloco2 = ImageBlock.getBlocoEspinho();
                break;

            case Block.TIPO_NEVE:
                imgBloco1 = ImageBlock.getBlocoNeve();
                imgBloco2 = ImageBlock.getBlocoNeve();
                break;

            case Block.TIPO_BASQUETE:
                imgBloco1 = ImageBlock.getBlocoBasquete();
                imgBloco2 = ImageBlock.getBlocoBasquete();
                break;

            default:
                System.out.println("Erro ao gerar plataforma: o tipo de bloco '" + type + "' nao existe.");
                return null;
        }

        // Cria um bloco inicial da plataforma, antes de adicionar os demais
        Block blocoInicial = new Block();
        blocoInicial.atualizarBloco(type, imgBloco1);
        plataforma.add(blocoInicial);

        for (int i = 1; i < length; i++) {
            Block blocoAnterior = plataforma.get(plataforma.size() - 1);
            Block blocoAtual = new Block();

            // Verificar se vai adicionar o lado esquerdo ou direito do bloco
            blocoAtual.atualizarBloco(type, i % 2 == 0 ? imgBloco1 : imgBloco2);

            // Adiciona o bloco atual ao lado do bloco anterior
            blocoAtual.setLocation(blocoAnterior.getX() + blocoAnterior.getWidth(), blocoInicial.getY());

            plataforma.add(blocoAtual);
        }

        return plataforma;
    }

    // Gera uma plataforma com 4 blocos do tipo especificado na posicao determinada
    public ArrayList<Block> setPosition(int position, int type) {

        ArrayList<Block> platform = new ArrayList<Block>();
        platform.addAll(generate(4, type));

        switch (position) {

            case 1: // Platform nivel TOP
                for (Block bloco : platform) {
                    bloco.setLocation(bloco.getX(), Block.NIVEL_TOP);
                }
                break;

            case 2: // Platform nivel MID
                for (Block bloco : platform) {
                    bloco.setLocation(bloco.getX(), Block.NIVEL_MID);
                }
                break;

            case 3: // Platform nivel BOTTOM
                for (Block bloco : platform) {
                    bloco.setLocation(bloco.getX(), Block.NIVEL_BOTTOM);
                }

        }

        return platform;
    }

}
