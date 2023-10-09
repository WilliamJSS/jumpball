package controle;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import modelo.Audios;

public class GerenciadorSom {

	private Clip clipMenu;
	private Clip clipCenario;
	private Clip clipVitoria;
	private Clip clipDerrota;
	private float[] volume;

	// Volumes
	public float[] getVolume() {
		if (volume == null) {
			volume = new float[]{

					-10,	// 0 - Musica do menu
					+05,	// 1 - Musica durante a partida
					-20,	// 2 - Toque de vitoria // -20
					-20,	// 3 - Toque de derrota
					-20,	// 4 - Navegar menu
					-10,	// 5 - Selecionar botao
					-15,	// 6 - Efeito pulo
					-20		// 7 - Efeito pegar moeda
			};
		}
		return volume;
	}

	// Musica menu
	public void playMusicaMenu() {
		clipMenu = playAudio(Audios.getMusicaMenu(), true, getVolume()[0]);
	}

	public void stopMusicaMenu() {
		if (clipMenu != null) {
			stopAudio(clipMenu);
		}
	}

	// Musica cenario
	public void playMusicaCenario() {
		clipCenario = playAudio(Audios.getMusicaCenario(), true, getVolume()[1]);
	}

	public void stopMusicaCenario() {
		if (clipCenario != null) {
			stopAudio(clipCenario);
		}
	}

	// Toque vitoria
	public void playToqueVitoria() {
		clipVitoria = playAudio(Audios.getToqueVitoria(), false, getVolume()[2]);
	}

	public void stopToqueVitoria() {
		if (clipVitoria != null) {
			stopAudio(clipVitoria);
		}
	}

	// Toque derrota
	public void playToqueDerrota() {
		clipDerrota = playAudio(Audios.getToqueDerrota(), false, getVolume()[3]);
	}

	public void stopToqueDerrota() {
		if (clipDerrota != null) {
			stopAudio(clipDerrota);
		}
	}

	// Toque navegar menu
	public void playToqueNavegarMenu() {
		new ThreadSom(Audios.getToqueNavegarMenu(), 600, getVolume()[4]).start();
	}

	// Toque selecionar botao
	public void playToqueSelecionarBotao() {
		new ThreadSom(Audios.getToqueSelecionarBotao(), 600, getVolume()[5]).start();
	}

	// Efeito pulo
	public void playEfeitoPulo() {
		new ThreadSom(Audios.getEfeitoPulo(), 600, getVolume()[6]).start();
	}

	// Efeito pegar moeda
	public void playEfeitoPegarMoeda() {
		new ThreadSom(Audios.getEfeitoPegarMoeda(), 600, getVolume()[7]).start();
	}

	// Tocar um audio, com ou sem loop
	public Clip playAudio(File arquivoWav, boolean loop, float dbVolume){

		Clip clip = null;

		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(arquivoWav);
			clip = AudioSystem.getClip();
			clip.open(audio);

			// Ajustar volume
			FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(dbVolume); // aumenta ou diminui o volume, na quantidade de db que for passada

			if (loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.loop(0);
			}

		} catch (LineUnavailableException e1) {
			e1.printStackTrace();	
		} catch (IOException e3) {
			e3.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		return clip;
	}

	// Parar um audio
	public void stopAudio(Clip clip) {
		clip.close();
	}

	// Thread para tocar audios curtos, inicia o clip e para depois de um tempo determinado
	public class ThreadSom extends Thread {

		private File arquivoWav;
		private long tempo;
		private float dbVolume;

		public ThreadSom(File arquivoWav, long tempo, float dbVolume) {
			super("ThreadSom");
			this.arquivoWav = arquivoWav;
			this.tempo = tempo;
			this.dbVolume = dbVolume;
		}

		@Override
		public void run() {

			Clip clip = playAudio(arquivoWav, false, dbVolume);

			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			stopAudio(clip);
		}
	}

}
