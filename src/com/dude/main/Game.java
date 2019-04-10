package com.dude.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.dude.util.BufferedImageLoader;
import com.dude.util.HUD;
import com.dude.util.Handler;
import com.dude.util.KeyInput;
import com.dude.util.Menu;
import com.dude.util.Spawn;

public class Game extends Canvas implements Runnable{

  private static final long serialVersionUID = 00002L;

  public static final int width = 960, height = width / 12 * 9;

  private Thread thread;
  private boolean running = false;

  private Handler handler;
  private HUD hud;
  private Spawn spawn;// TODO Auto-generated method stub
  private Menu menu;

  public enum STATE {
    Menu,
    Options,
    Dead,
    Win,
    Game
  };

  public STATE gameState = STATE.Menu;
  
  public static BufferedImage sprite_archor;
  public static BufferedImage sprite_priest;
  public static BufferedImage sprite_warrior;
  public static BufferedImage sprite_enemy_blue;
  public static BufferedImage sprite_enemy_green;
  public static BufferedImage sprite_enemy_red;

  public Game(){
    BufferedImageLoader loader = new BufferedImageLoader();
    sprite_archor = loader.loadImage("/Characters/Character_Hero_Archor.png");
    sprite_priest = loader.loadImage("/Characters/Character_Hero_Priest.png");
    sprite_warrior = loader.loadImage("/Characters/Character_Hero_Warrior.png");
    
    sprite_enemy_blue = loader.loadImage("/Enemies/Character_Monster_Slime_Blue.png");
    sprite_enemy_green = loader.loadImage("/Enemies/Character_Monster_Slime_Green.png");
    sprite_enemy_red = loader.loadImage("/Enemies/Character_Monster_Slime_Red.png");
    
    handler = new Handler();
    menu = new Menu(this, handler);
    this.addKeyListener(new KeyInput(handler));
    this.addMouseListener(menu);

    new Window(width, height, "A New Game!", this);

    hud = new HUD(this, handler);
    spawn = new Spawn(handler, hud, this);

  }

  public synchronized void start(){
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop(){
    try{
      thread.join(); // killing off the thread
      running = false;
    } catch(Exception e){
      e.printStackTrace();
    }

  }

  @Override
  public void run(){
    // window focused on JFrame on run()
    this.requestFocus();
    // frame ticks gotten online
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while(running){
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while(delta >= 1){
        tick();
        delta--;
      }
      if(running){
        render();
      }
      frames++;

      if(System.currentTimeMillis() - timer > 1000){
        timer += 1000;
        // System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();
  }

  private void tick(){
    handler.tick();
    if(gameState == STATE.Game){
      hud.tick();
      spawn.tick();
    } else if(gameState == STATE.Menu || gameState == STATE.Options || gameState == STATE.Dead || gameState == STATE.Win){
      menu.tick();
    }
  }

  private void render(){
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null){
      this.createBufferStrategy(3);
      return;
    }

    Graphics2D g = (Graphics2D) bs.getDrawGraphics();

    g.setColor(Color.black);
    g.fillRect(0, 0, width, height);

    handler.render(g);
    if(gameState == STATE.Game){
      hud.render(g);

    } else if(gameState == STATE.Menu || gameState == STATE.Options || gameState == STATE.Dead || gameState == STATE.Win){
      menu.render(g);
    }

    g.dispose();
    bs.show();
  }

  public static float clamp(float var, float min, float max){
    if(var >= max){
      return var = max;
    } else if(var <= min){
      return var = min;
    }
    return var;
  }

  public static void main(String[] args){
    new Game();
  }
}
