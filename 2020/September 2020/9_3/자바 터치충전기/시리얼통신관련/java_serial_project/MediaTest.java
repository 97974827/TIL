package oop;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MediaTest {
	public static String PROJECT_PATH = System.getProperty("user.dir"); // 프로젝트 경로
	
	public void abc() {
		  File bgm;
		  AudioInputStream stream;
		  AudioFormat format;
		  DataLine.Info info;
		  
		  bgm = new File(PROJECT_PATH + "/src/oop/msg001.wav");
		  System.out.println(bgm);
		  Clip clip;
		  
		  try {
		      stream = AudioSystem.getAudioInputStream(bgm);
		      format = stream.getFormat();
		      info = new DataLine.Info(Clip.class, format);
		      clip = (Clip)AudioSystem.getLine(info);
		      clip.open(stream);
		      clip.start();
		  } catch (Exception e) {
		     System.out.println("err : " + e);
		  }
          
   }
   public static void main(String[] args) {
      MediaTest test = new MediaTest();
//      test.abc();
//      while(true) {
         try {
    		test.abc();
    		Thread.sleep(5000); // 3초에 한번씩 재생하도록 설정
         } catch(Exception e) {
	           
         }
//      }
   }


}
