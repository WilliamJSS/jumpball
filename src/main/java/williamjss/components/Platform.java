package williamjss.components;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import williamjss.model.Imagens;

public class Platform extends ArrayList<Block> {

	private ArrayList<int[]> padroes;

	// Generate main platforms
	public Platform(int type, int[] stage) {
		super();
		this.padroes = getPadroes();

		int dx = 12 * 30; // 12 blocos de distancia
		int tamanho;

		for (int i = 0; i < stage.length; i++) {

			// Adiciona um padrao de plataformas ao arrayList
			tamanho = this.size();
			for (int j = 0; j < padroes.get(stage[i]).length; j++) {
				this.addAll(setPosition(padroes.get(stage[i])[j], type));
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

	// The structures are defined based by scene nivels (TOP, MID, BOTTOM)
	// TOP (1, 4, 7) - representing LEFT, CENTER and RIGHT, respectively
	// MID (2, 5, 8) - representing LEFT, CENTER and RIGHT, respectively
	// BOTTOM (3, 6, 9) - representing LEFT, CENTER and RIGHT, respectively
	public ArrayList<int[]> getPadroes() {
		if (padroes == null) {
			padroes = new ArrayList<int[]>();

			padroes.add(new int[] { 1, 4, 7 }); // 0 - toda parte de cima
			padroes.add(new int[] { 2, 5, 8 }); // 1 - toda parte do meio
			padroes.add(new int[] { 3, 6, 9 }); // 2 - toda parte de baixo

			padroes.add(new int[] { 1, 5, 9 }); // 3 - escadinha descendo
			padroes.add(new int[] { 1, 2, 5, 8, 9 }); // 4 - escadinha descendo + toda parte do meio
			padroes.add(new int[] { 1, 4, 5, 7, 9 }); // 5 - escadinha descendo + toda parte de cima
			padroes.add(new int[] { 1, 3, 5, 6, 9 }); // 6 - escadinha descendo + toda parte de baixo

			padroes.add(new int[] { 3, 5, 7 }); // 7 - escadinha subindo
			padroes.add(new int[] { 1, 3, 4, 5, 7 }); // 8 - escadinha subindo + toda parte de cima
			padroes.add(new int[] { 3, 5, 6, 7, 9 }); // 9 - escadinha subindo + toda parte de baixo
			padroes.add(new int[] { 2, 3, 5, 7, 8 }); // 10 - escadinha subindo + toda parte do meio

			padroes.add(new int[] { 1, 5, 7 }); // 11 - no meio e em cima -_-
			padroes.add(new int[] { 2, 6, 8 }); // 12 - em baixo e no meio -_-
			padroes.add(new int[] { 2, 4, 8 }); // 13 - no meio e em cima _-_
			padroes.add(new int[] { 3, 5, 9 }); // 14 - em baixo e no meio _-_

			// Outros padroes
			padroes.add(new int[] { 2, 6, 9 }); // 15
			padroes.add(new int[] { 1, 3, 4, 6, 8 }); // 16
			padroes.add(new int[] { 2, 4, 6, 9 }); // 17
			padroes.add(new int[] { 2, 3, 4, 6, 7, 9 });// 18
			padroes.add(new int[] { 3, 6, 8 }); // 19
			padroes.add(new int[] { 2, 4, 7, 9 }); // 20
			padroes.add(new int[] { 2, 4, 6, 7, 9 }); // 21
			padroes.add(new int[] { 2, 3, 5, 6, 7, 8 });// 22
			padroes.add(new int[] { 2, 5, 6, 8 }); // 23
			padroes.add(new int[] { 3, 6, 8, 9 }); // 24
			padroes.add(new int[] { 2, 4, 5, 9 }); // 25
			padroes.add(new int[] { 3, 4, 5, 7 }); // 26
			padroes.add(new int[] { 1, 3, 5, 7, 9 }); // 27
			padroes.add(new int[] { 1, 3, 5, 8 }); // 28
			padroes.add(new int[] { 2, 8, 9 }); // 29
			padroes.add(new int[] { 2, 3, 4, 5, 7, 9 });// 30
			padroes.add(new int[] { 1, 5, 8, 9 }); // 31
			padroes.add(new int[] { 1, 2, 5, 6, 8 }); // 32
		}

		return padroes;
	}

	// Gera uma plataforma com a quantidade de blocos e tipo especificado
	public ArrayList<Block> generate(int length, int type) {

		ArrayList<Block> plataforma = new ArrayList<Block>();

		ImageIcon imgBloco1, imgBloco2;

		switch (type) {

			case Block.TIPO_AGUA:
				imgBloco1 = Imagens.getBlocoAgua();
				imgBloco2 = Imagens.getBlocoAgua();
				break;

			case Block.TIPO_GELO:
				imgBloco1 = Imagens.getBlocoGelo();
				imgBloco2 = Imagens.getBlocoGelo();
				break;

			case Block.TIPO_GRAMA:
				imgBloco1 = Imagens.getBlocoGrama1();
				imgBloco2 = Imagens.getBlocoGrama2();
				break;

			case Block.TIPO_GRAMADO:
				imgBloco1 = Imagens.getBlocoGrama();
				imgBloco2 = Imagens.getBlocoGrama();
				break;

			case Block.TIPO_LAVA:
				imgBloco1 = Imagens.getBlocoLava();
				imgBloco2 = Imagens.getBlocoLava();
				break;

			case Block.TIPO_PEDRA:
				imgBloco1 = Imagens.getBlocoPedra();
				imgBloco2 = Imagens.getBlocoPedra();
				break;

			case Block.TIPO_AREIA:
				imgBloco1 = Imagens.getBlocoAreia();
				imgBloco2 = Imagens.getBlocoAreia();
				break;

			case Block.TIPO_ESPINHO:
				imgBloco1 = Imagens.getBlocoEspinho();
				imgBloco2 = Imagens.getBlocoEspinho();
				break;

			case Block.TIPO_NEVE:
				imgBloco1 = Imagens.getBlocoNeve();
				imgBloco2 = Imagens.getBlocoNeve();
				break;

			case Block.TIPO_BASQUETE:
				imgBloco1 = Imagens.getBlocoBasquete();
				imgBloco2 = Imagens.getBlocoBasquete();
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

		int dx;
		ArrayList<Block> platform = new ArrayList<Block>();
		platform.addAll(generate(4, type));

		switch (position) {

			case 1: // Platform nivel TOP / LEFT

				for (int i = 0; i < platform.size(); i++) {
					Block bloco = platform.get(i);
					bloco.setLocation(bloco.getX(), Block.NIVEL_TOP);
				}

				break;

			case 2: // Platform nivel MID / LEFT

				for (int i = 0; i < platform.size(); i++) {
					Block bloco = platform.get(i);
					bloco.setLocation(bloco.getX(), Block.NIVEL_MID);
				}

				break;

			case 3: // Platform nivel BOTTOM / LEFT

				for (int i = 0; i < platform.size(); i++) {
					Block bloco = platform.get(i);
					bloco.setLocation(bloco.getX(), Block.NIVEL_BOTTOM);
				}

				break;

			case 4: // Platform nivel TOP / CENTER
				platform.addAll(generate(4, type));
				dx = 4 * platform.get(0).getHeight(); // 4 blocos de distancia do ponto inicial

				for (int i = 0; i < platform.size(); i++) {
					Block bloco = platform.get(i);
					bloco.setLocation(bloco.getX() + dx, Block.NIVEL_TOP);
				}

				break;

			case 5: // Platform nivel MID / CENTER
				dx = 4 * platform.get(0).getHeight(); // 4 blocos de distancia do ponto inicial

				for (int i = 0; i < platform.size(); i++) {
					Block bloco = platform.get(i);
					bloco.setLocation(bloco.getX() + dx, Block.NIVEL_MID);
				}

				break;

			case 6: // Platform nivel BOTTOM / CENTER
				dx = 4 * platform.get(0).getHeight(); // 4 blocos de distancia do ponto inicial

				for (int i = 0; i < platform.size(); i++) {
					Block bloco = platform.get(i);
					bloco.setLocation(bloco.getX() + dx, Block.NIVEL_BOTTOM);
				}

				break;

			case 7: // Platform nivel TOP / RIGHT
				platform.addAll(generate(4, type));
				dx = 8 * platform.get(0).getHeight(); // 8 blocos de distancia do ponto inicial

				for (int i = 0; i < platform.size(); i++) {
					Block bloco = platform.get(i);
					bloco.setLocation(bloco.getX() + dx, Block.NIVEL_TOP);
				}

				break;

			case 8: // Platform nivel MID / RIGHT
				dx = 8 * platform.get(0).getHeight(); // 8 blocos de distancia do ponto inicial

				for (int i = 0; i < platform.size(); i++) {
					Block bloco = platform.get(i);
					bloco.setLocation(bloco.getX() + dx, Block.NIVEL_MID);
				}

				break;

			case 9: // Platform nivel BOTTOM / RIGHT
				dx = 8 * platform.get(0).getHeight(); // 8 blocos de distancia do ponto inicial

				for (int i = 0; i < platform.size(); i++) {
					Block bloco = platform.get(i);
					bloco.setLocation(bloco.getX() + dx, Block.NIVEL_BOTTOM);
				}

		}

		return platform;
	}

}
