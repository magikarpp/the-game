package com.dude.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.dude.main.Game;
import com.dude.util.Handler;
import com.dude.util.SpriteSheet;

public class Player extends GameObject {

  private Handler handler;
  
  private SpriteSheet ss;
  private BufferedImage player_image;

  public Player(float x, float y, ID id, BufferedImage player_image, Handler handler){
    super(x, y, id);
    this.handler = handler;
    
    this.ss = new SpriteSheet(player_image);
 
    this.player_image = ss.grabCharacterImage(0, 2, 100, 100);
  }

  public Rectangle getBounds(){
    return new Rectangle((int)x + 20, (int)y, 55, 100);
  }

  @Override
  public void tick(){
    x += velX;
    y += velY;

    x = Game.clamp(x, 0, Game.width - 96);
    y = Game.clamp(y, 0, Game.height - 96);

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
	g.fillRect((int)x + 20, (int)y, 55, 100);
	g.drawImage(player_image, (int)x, (int)y, null);
  }



}
