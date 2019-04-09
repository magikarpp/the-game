package com.dude.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.dude.main.Game;
import com.dude.objects.ID;
import com.dude.objects.Player;

public class Menu extends MouseAdapter{

  private Game game;
  private Handler handler;
  private Random r = new Random();

  public Menu(Game game, Handler handler){
    this.game = game;
    this.handler = handler;
  }

  public void mousePressed(MouseEvent e){
    int mx = e.getX();
    int my = e.getY();

    if(game.gameState == Game.STATE.Menu){
      // Dodge Things
      if(mouseOver(mx, my, 320-100, 240-100, 200, 64)){
        game.gameState = Game.STATE.Game;

        handler.addObject(new Player(320-32, 240-32, ID.Player, handler));

      }
      // Shoot Things
      if(mouseOver(mx, my, 320-100, 240, 200, 64)){

      }
      // Options
      if(mouseOver(mx, my, 320-50, 240+100, 100, 32)){
        game.gameState = Game.STATE.Options;
      }

    } else if(game.gameState == Game.STATE.Options){

      if(mouseOver(mx, my, 75, 240, 150, 32)){
        handler.difficulty = Handler.DIFF.Baby;
        game.gameState = Game.STATE.Menu;
      }

      if(mouseOver(mx, my, 250, 240, 150, 32)){
        handler.difficulty = Handler.DIFF.Child;
        game.gameState = Game.STATE.Menu;
      }

      if(mouseOver(mx, my, 425, 240, 150, 32)){
        handler.difficulty = Handler.DIFF.Adult;
        game.gameState = Game.STATE.Menu;
      }

      if(mouseOver(mx, my, 250, 340, 150, 32)){
        game.gameState = Game.STATE.Menu;
      }

    } else if(game.gameState == Game.STATE.Dead){
      if(mouseOver(mx, my, 250, 340, 150, 32)){
        game.gameState = Game.STATE.Menu;
      }
    } else if(game.gameState == Game.STATE.Win){
      if(mouseOver(mx, my, 250, 340, 150, 32)){
        game.gameState = Game.STATE.Menu;
      }
    }

  }

  public void mouseReleased(MouseEvent e){

  }

  private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
    if(mx > x && mx < x + width){
      if(my > y && my < y + height){
        return true;
      }
    }
    return false;
  }

  public void tick(){

  }

  public void render(Graphics g){
    Font fnt = new Font("arial", 1, 50);
    Font fnt2 = new Font("arial", 1, 12);

    g.setColor(Color.white);

    if(game.gameState == Game.STATE.Menu){
      g.setFont(fnt);
      g.drawString("Menu", 320-80, 75);

      g.setFont(fnt2);
      g.drawRect(320-100, 240-100, 200,64);
      g.drawString("Dodge Things", 320-50, 240-65);

      g.drawRect(320-50, 240+100, 100,32);
      g.drawString("Options", 320-25, 240+120);

    } 

  }
}
