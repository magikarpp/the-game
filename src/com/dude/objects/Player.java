package com.dude.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.dude.main.Game;
import com.dude.util.HUD;
import com.dude.util.Handler;

public class Player extends GameObject {

  private Handler handler;

  public Player(float x, float y, ID id, Handler handler){
    super(x, y, id);
    this.handler = handler;

  }

  public Rectangle getBounds(){
    return new Rectangle((int)x, (int)y, 32, 32);
  }

  @Override
  public void tick(){
    x += velX;
    y += velY;

    x = Game.clamp(x, 0, Game.width - 32);
    y = Game.clamp(y, 0, Game.height - 32);

    collision();
  }

  private void collision(){
    for(int i = 0; i < handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);

    }
  }

  @Override
  public void render(Graphics g){
    g.setColor(Color.white);
    g.fillRect((int)x, (int)y, 32, 32);
  }



}
