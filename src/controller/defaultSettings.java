package controller;

import java.io.File;

import javax.print.attribute.standard.Media;

import javafx.scene.media.MediaPlayer;

public class defaultSettings {
	
    private boolean musicOff = false;
    private boolean sound = false;
    private MediaPlayer mediaPlay;

	defaultSettings(){
		String backgroundMusic = "src/assets/music.wav";
		Media sound = new Media(new File(backgroundMusic).toURI().toString());
		mediaPlay = new MediaPlayer(sound);
		mediaPlay.setCycleCount(MediaPlayer.INDEFINITE);
	}
	public void toggleMusic() {
		musicOff = !musicOff;
		if(musicOff) {
			this.mediaPlay.play();
		}
		else {
			this.mediaPlay.pause();
		}
	}

	public void toggleSound() {
		sound = !sound;
		
	} 	
	public boolean musicOn() {
		return musicOff;
	}
	
	public boolean soundOn() {
		return sound;
		
	}

}
