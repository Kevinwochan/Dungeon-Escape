package controller;
import java.applet.AudioClip;

import javafx.concurrent.Task;

final class BackgroundAudio extends Task<Object>{
	AudioClip audio;
	
	public BackgroundAudio() {
		audio = new AudioClip(getClass().getResource("../assets/music.wav").toExternalForm());
	}
	

	@Override
	protected Object call() throws Exception {
		audio.setVolume(0.5f);
		audio.setCycleCount(AudioClip.INDEFINITE); 
		audio.play();
		return null;
	}
}
