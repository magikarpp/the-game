package com.dude.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.dude.objects.GameObject;
import com.dude.objects.ID;

public class KeyInput extends KeyAdapter {

  private Handler handler;
  
  private boolean uP = false;
  private boolean dP = false;
  private boolean lP = false;
  private boolean rP = false;
  

  public KeyInput(Handler handler){
    this.handler = handler;

  }

  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();

    for(int i = 0; i < handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);

      if(tempObject.getId() == ID.Player){
          if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
        	  uP = true;
        	  tempObject.setVelY(-5);
          }
          if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
        	  dP = true;
        	  tempObject.setVelY(5);
          }
          if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
        	  lP = true;
        	  tempObject.setVelX(-5);
          }
          if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
        	  rP = true;
        	  tempObject.setVelX(5);
          }
      }

    }
  }

  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();

    for(int i = 0; i < handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);

      if(tempObject.getId() == ID.Player){
          if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
        	  uP = false;
        	  if(dP) {
        		  tempObject.setVelY(5);
        	  } else {
        		  tempObject.setVelY(0);
        	  }
          }
          if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
        	  dP = false;
        	  if(uP) {
        		  tempObject.setVelY(-5);
        	  } else {
        		  tempObject.setVelY(0);
        	  }
          }
          if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
        	  lP = false;
        	  if(rP) {
        		  tempObject.setVelX(5);
        	  } else {
        		  tempObject.setVelX(0);
        	  }
          }
          if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
        	  rP = false;
        	  if(lP) {
        		  tempObject.setVelX(-5);
        	  } else {
        		  tempObject.setVelX(0);
        	  }
          }
      }

    }
  }

}
