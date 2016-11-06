package to.us.tyweb.firewars;

import to.us.tyweb.firewars.GameObjects.Coin;
import to.us.tyweb.firewars.GameObjects.Bullet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import sun.applet.Main;

public class Game extends Canvas implements Runnable {
    
   //fields
   static int width = 1024;
   static int height = 576;
   int updates;
   int frames;
   long timer;
   int timeTicks;
   int timeFrames;
   Player p;
   ImageLoader l;
   BufferedImage background = null;
   SpriteManager ss;
   Handler c;
   private boolean isShooting = false;
   Textures tex;
   private int health = 10;
   private int score = 0;
   public enum state{
       menu,
       game,
       paused,
       gameOver
   };
   public static state state;
   private GameTitleScreen m;
   private GameOverScreen o;
   private int round;
   private BufferedImage i = new BufferedImage(1024, 576, BufferedImage.TYPE_INT_RGB);
   private BufferedImage sheet = null;
   private boolean debug = false;
   private boolean isDebugPressed = false;
   private boolean pauseKeyPressed = false;
   GamePausedScreen ps;
   private int wave;
   Random r = new Random();
   private int invisiBulletDuration;
   private int difficulty = 2;
   private int speedUpDuration = 0;
   static Window w;
   
   public static void main(String args[]){
       new Game();
   }
   
   public Game(){
       new Thread(this).start();
   }
   
   public void run(){
l = new ImageLoader();
       try{
           sheet = l.load("res/img/spriteSheet.png");
           background = l.load("res/img/background.png");
       }catch(Exception e){
           e.printStackTrace();
       }
       ss = new SpriteManager(sheet);
       requestFocus();
       addKeyListener(new Key(this));
       addMouseListener(new Mouse(this));
       addMouseMotionListener(new Mouse(this));
       tex = new Textures(this);
       round = 24;
       p = new Player(512.0, 500.0, tex);
       c = new Handler(this);
       state = state.menu;
       m = new GameTitleScreen();
       o = new GameOverScreen(this);
       ps = new GamePausedScreen();
       wave = 0;
       invisiBulletDuration = 0;
       long lastTime = System.nanoTime();
       final double amountOfTicks = 60.0;
       double ns = 1000000000 / amountOfTicks;
       double delta = 0;
       updates = 0;
       frames = 0;
       timer = System.currentTimeMillis();
       w = new Window(this);
       while (true) {
       
           long now = System.nanoTime();
           delta += (now - lastTime) / ns;
           lastTime = now;
           if (delta >= 1){
               tick();
               updates++;
               delta--;
           }
           render();
           frames++;
           
           if (System.currentTimeMillis() - timer >= 1000){
               timeFrames = frames;
               timeTicks = updates;
               timer+=1000;
               frames = 0;
               updates = 0;
           }
           
       }
   }
   
   private void tick(){
       if(state == state.game){
            p.tick();
            c.tick();
            if(health <= 0){
                playSound("res/wav/gameOver.wav");
                state = state.gameOver;
            }
            if(r.nextInt(60 * 8) == 1) c.addInvisiBullet(new invisiBullet(tex, p));
            if(r.nextInt(60 * 5) == 1) c.addCoin(new Coin(tex, p));
            if(r.nextInt(60 * 8) == 1) c.addSpeedUp(new speedUp(tex));
            if(r.nextInt(60 * 8) == 1) c.addMedKit(new medKit(tex));
            if(invisiBulletDuration > 0) invisiBulletDuration--;
            if(speedUpDuration > 0) speedUpDuration--; 
       }
       
   }
   private void render(){
       BufferStrategy bs = getBufferStrategy();
       if(bs == null){
           createBufferStrategy(2);
           return;
       }
       Graphics g0 = bs.getDrawGraphics();
       Graphics g = i.getGraphics();
       
       //background
       g.drawImage(background, 0, 0, this);
       
       if(state == state.game){
       c.render(g);
       p.render(g);
       
       //healthbar
       g.setFont(new Font("arial", Font.PLAIN, 20));
       g.setColor(Color.gray);
       g.fillRect(5, 5, 205, 21);
       g.setColor(Color.red);
       g.fillRect(5, 5, (health*20)+5, 20);
       g.setColor(Color.white);
       g.drawRect(5, 5, (health*20)+5, 20);
       g.drawString("Health:  " + health + " / 10", 20, 22);
       g.setFont(new Font("arial", Font.PLAIN, 24));
       g.drawString("Score: " + score + " Wave: " + wave, 5, 55);
       
       //effects
       if(invisiBulletDuration > 0){
           g.drawImage(tex.invisiBullet, 225, 5, this);
           g.drawString("" + (int)invisiBulletDuration / 60, 260, 20);
       }
       if(speedUpDuration > 0){
           g.drawImage(tex.speedUp, 275, 5, this);
           g.drawString("" + (int)speedUpDuration / 60, 310, 20);
       }
       
       //debug
       if(debug){
       g.setFont(new Font("arial", Font.PLAIN, 12));
       g.setColor(Color.white);
       g.fillRect(0, 150, 150, 100);
       g.setColor(Color.black);
       g.drawString("FPS: " + timeFrames + ", TPS: " + timeTicks, 5, 165);
       g.drawString("Enemies: " + c.getEnemyArray().size() + " Bullets: " + c.getBulletArray().size(), 5, 180);
       g.drawString("ZigZags: " + c.getZigArray().size() + " MedKits: " + c.getMedKitArray().size(), 5, 195);
       g.drawString("InvisiBullets: " + c.getInvisiBulletArray().size(), 5, 210);
       g.drawString("SpeedUps: " + c.getSpeedUpArray().size(), 5, 225);
       g.drawString("X: " + p.getx() + " Y: " + p.gety(), 5, 240);
       }
       
       }else if(state == state.menu){
           m.render(g);
       }else if(state == state.gameOver){
           o.render(g);
       }else if(state == state.paused){
           //pause menu
           ps.render(g);
       }
       
       g0.drawImage(i, 0, 0, w.getWindow().getWidth(), w.getWindow().getHeight(), null);
       g.dispose();
       g0.dispose();
       bs.show();
   }
   
   //key event handler
   public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A){
            if(speedUpDuration > 0){
                p.setvalx(-9);
            }else{
            p.setvalx(-5);
            }
        }else if (key == KeyEvent.VK_D){
            if(speedUpDuration > 0){
                p.setvalx(9);
            }else{
            p.setvalx(5);
            }
        }else if (key == KeyEvent.VK_W){
            if(speedUpDuration > 0){
                p.setvaly(-9);
            }else{
            p.setvaly(-5);
            }
        }else if (key == KeyEvent.VK_S){
            if(speedUpDuration > 0){
                p.setvaly(9);
            }else{
            p.setvaly(5);
            }
        }else if (key == KeyEvent.VK_SPACE){
            if(state == state.game){
                if(!isShooting){
                    c.addBullet(new Bullet(p.getx(), p.gety() - 24, tex, c));
                    playSound("res/wav/peew.wav");
                }
                isShooting = true;
            }
        }else if (key == KeyEvent.VK_F3){
            if(!isDebugPressed){
                if(debug){
                    debug = false;
                }else{
                    debug = true;
                }
                
            }
            isDebugPressed = true;
        }else if(key == KeyEvent.VK_ESCAPE){
            if(!pauseKeyPressed){
            if(state != state.gameOver){
            if(state == state.game){
                state = state.paused;
            }else state = state.game;
            }
            }
            pauseKeyPressed = true;
        }
   } 
   public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A){
            p.setvalx(0);
        }else if (key == KeyEvent.VK_D){
            p.setvalx(0);
        }else if (key == KeyEvent.VK_W){
            p.setvaly(0);
        }else if (key == KeyEvent.VK_S){
            p.setvaly(0);
        }else if (key == KeyEvent.VK_SPACE){
            isShooting = false;
        }else if (key == KeyEvent.VK_F3){
            isDebugPressed = false;
        }else if (key == KeyEvent.VK_ESCAPE){
            pauseKeyPressed = false;
        }
   }
      
   public void reset(){
       //reset the game
       p.setx(width);
       p.sety(height*scale);
       health = 10;
       score = 0;
       round = 24;
       c.getEnemyArray().clear();
       c.getBulletArray().clear();
       c.getMedKitArray().clear();
       c.getZigArray().clear();
       c.getInvisiBulletArray().clear();
       c.getSpeedUpArray().clear();
       wave = 0;
       speedUpDuration = 0;
       invisiBulletDuration = 0;
   }
   
   public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Clip clip = AudioSystem.getClip();
                InputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream(url));
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                clip.open(inputStream);
                clip.start(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     }).start();
   }
   
   public void setHealth(int h){
       this.health = h;
   }
   public int getHealth(){
       return health;
   }
   public BufferedImage getSpriteSheet(){
       return sheet;
   }
   public void scorePoint(){
       score++;
   }
   public void takeDammage(){
       health--;
   }
   public int getScore(){
       return score;
   }
   public Player getPlayer(){
       return p;
   }
   public int getRound(){
       return round;
   }
   public void setRound(int r){
       round = r;
   }
   public void setScore(int s){
       score = s;
   }
   public void setWave(int w){
       wave = w;
   }
   public int getWave(){
       return wave;
   }
   public int getInvisiBulletDuration(){
       return invisiBulletDuration;
   }
   public void setInvisiBulletDuration(int i){
       this.invisiBulletDuration = i;
   }
   public void setSpeedUpDuration(int i){
       this.speedUpDuration = i;
   }
   public int getSpeedUpDuration(){
       return speedUpDuration;
   }
   public Handler getController(){
       return c;
   }
   public Textures getTexture(){
       return tex;
   }
}
