package com.dude.util;

import java.awt.Graphics;
import java.util.LinkedList;

import com.dude.objects.GameObject;

public class Handler {

  public LinkedList<GameObject> object = new LinkedList<GameObject>();

  public enum DIFF {
    Baby,
    Child,
    Adult
  };

  public DIFF difficulty = DIFF.Baby;

  public void tick(){
    for(int i = 0; i < object.size(); i++){
      GameObject tempObject = object.get(i);

      tempObject.tick();
    }
  }

  public void render(Graphics g){
    for(int i = 0; i < object.size(); i++){
      GameObject tempObject = object.get(i);

      tempObject.render(g);
    }
  }

  public void addObject(GameObject object){
    this.object.add(object);
  }

  public void removeObject(GameObject object){
    this.object.remove(object);
  }

  public void clearEverything(){
    object.clear();
  }

}
