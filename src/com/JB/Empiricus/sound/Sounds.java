package com.JB.Empiricus.sound;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/*http://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html

This enum encapsulates all the sound effects of the game.
An enum is an ordinary class with a predefined and enumerable set of instances

HOW IT WORKS:
1. Define all of your sound effect names and the asociated file.
2. To play a specific sound, simply invoke  Sounds.SOUND_NAME.play();
3. Optionally invoke the static method Sounds.init(); to pre-load all the sound files, so that
   the play is not paused while loading the file for the first time.
4. You can use the static variable Sounds.volume to mute the sound. (mute button) 

*/
public enum Sounds {

SHOOT ("/res/music/soundeffects/shoot.wav"),
WALK ("/music/soundeffects/walk.wav"),
HIT ("/music/soundeffects/hit.wav"),
RELOAD ("/music/soundeffects/reload.wav");
        
//Nested class for specifying volume
public enum Volume {
        MUTE,LOW,MEDIUM,HIGH
}

public static Volume volume = Volume.LOW;

//Each sound effect has its own clip, loaded with its own sound file
private Clip clip;

Sounds (String soundFileName){
          try {
                 // Use URL (instead of File) to read from disk and JAR.
                 URL url = this.getClass().getClassLoader().getResource(soundFileName);
                 // Set up an audio input stream piped from the sound file.
                 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                 // Get a clip resource.
                 clip = AudioSystem.getClip();
                 // Open audio clip and load samples from the audio input stream.
                 clip.open(audioInputStream);
              } catch (UnsupportedAudioFileException e) {
                 e.printStackTrace();
              } catch (IOException e) {
                 e.printStackTrace();
              } catch (LineUnavailableException e) {
                 e.printStackTrace();
              }
}

// Play or re-wind the sound effect from the beginning, by rewinding
public void play (){
        if (volume != Volume.MUTE){
                if (clip.isRunning()) clip.stop (); // stop the player if it is still running
                clip.setFramePosition (0);
                clip.start ();
        }
}

//Optional static method to pre-load all the files
public static void init (){
        values (); // calls the constructor for all elements
}


}
