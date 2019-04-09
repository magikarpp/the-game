package com.dude.util;

import java.util.Random;

import com.dude.main.Game;

public class Spawn {

  private Handler handler;
  private HUD hud;
  private Game game;
  private Random r = new Random();
  
  public Spawn(Handler handler, HUD hud, Game game){
    this.handler = handler;
    this.hud = hud;
    this.game = game;
  }

  public void tick(){
    
  }


}
