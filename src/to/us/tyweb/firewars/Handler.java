package to.us.tyweb.firewars;

import to.us.tyweb.firewars.GameObjects.Coin;
import to.us.tyweb.firewars.GameObjects.Bullet;
import to.us.tyweb.firewars.GameObjects.Alien;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;


public class Handler {
    
    private LinkedList<Bullet> b = new LinkedList<Bullet>();
    private LinkedList<Alien> e  =new LinkedList<Alien>();
    private LinkedList<medKit> m = new LinkedList<medKit>();
    private LinkedList<zigZagEnemy> z = new LinkedList<zigZagEnemy>();
    private LinkedList<invisiBullet> i = new LinkedList<invisiBullet>();
    private LinkedList<speedUp> su = new LinkedList<speedUp>();
    private LinkedList<Coin> co = new LinkedList<Coin>();
    private LinkedList<speedDown> sd = new LinkedList<speedDown>();
    
    Bullet tempBullet;
    Alien tempEnemy;
    medKit tempMedKit;
    zigZagEnemy tempZig;
    invisiBullet tempInvisiBullet;
    speedUp tempSpeedUp;
    speedDown tempSpeedDown;
    Game g;
    Player p;
    Textures tex;
    int round;
    Random r = new Random();
    
    public Handler(Game game){
        this.g = game;
        tex = g.tex;
        this.round = g.getRound();
        p = g.getPlayer();
    }
    
    public void tick(){
        //bullet tick
        for(int i = 0; i < b.size(); i++){
            tempBullet = b.get(i);
            for(int j = 0; j < e.size(); j++){
                tempEnemy = e.get(j);
                //enemy detection
                if((tempBullet.getx() - tempEnemy.getx() < 32) && (tempBullet.gety() - tempEnemy.gety() < 32)){
                    if((tempBullet.getx() - tempEnemy.getx() > -32) && (tempBullet.gety() - tempEnemy.gety() > -32)){
                        b.remove(tempBullet);
                        tempEnemy.kill();
                        g.scorePoint();
                        g.playSound("res/wav/impact.wav");
                    }
                }
            }
            for(int j = 0; j < z.size(); j++){
                tempZig = z.get(j);
                //zigzag detection
                if((tempBullet.getx() - tempZig.getx() < 32) && (tempBullet.gety() - tempZig.gety() < 32)){
                    if((tempBullet.getx() - tempZig.getx() > -32) && (tempBullet.gety() - tempZig.gety() > -32)){
                        b.remove(tempBullet);
                        z.remove(tempZig);
                        g.scorePoint();
                        g.scorePoint();
                        g.playSound("res/wav/impact.wav");
                    }
                }
            }
            tempBullet.tick();
            if(tempBullet.gety() < -34){
                removeBullet(tempBullet);
                //return to sender goes here
                
            }
        }
        
        
        //enemy tick
        for(int i = 0; i < e.size(); i++){
            tempEnemy = e.get(i);
            if(tempEnemy.gety() > Game.height*Game.scale){
                addEnemy(new Alien(tex, tempEnemy.getLevel() + 1, g));
                z.add(new zigZagEnemy(tex));
                removeEnemy(tempEnemy);
                g.setHealth(g.getHealth() - 1);
            }
            tempEnemy.tick();
        }
        
        
        //medkit tick
        for(int i = 0; i < m.size(); i++){
            tempMedKit = m.get(i);
            tempMedKit.tick();
            //player detection
            if((tempMedKit.getx() - p.getx() < 32) && (tempMedKit.gety() - p.gety() < 32)){
                if((tempMedKit.getx() - p.getx() > -32) && (tempMedKit.gety() - p.gety() > -32)){
                    m.remove(tempMedKit);
                    g.scorePoint();
                    g.playSound("res/wav/powerUp.wav");
                    if(g.getHealth() >= 7){
                        g.setHealth(10);
                    }else{
                        g.setHealth(g.getHealth() + 3);
                    }
                }
           }
           if(tempMedKit.gety() > Game.height*Game.scale) m.remove(tempMedKit);
        }
        
        
        //zigzag tick
        for(int i = 0; i < z.size(); i++){
            tempZig = z.get(i);
            tempZig.tick();
            if(tempZig.gety() > Game.height*Game.scale){
                z.remove(tempZig);
                g.setHealth(g.getHealth() - 1);
                z.add(new zigZagEnemy(tex));
                z.add(new zigZagEnemy(tex));
            }
        }
        
        
        //invisiBullet tick
        for(int ii = 0; ii < i.size(); ii++){
            tempInvisiBullet = i.get(ii);
            tempInvisiBullet.tick();
            //player detection
            if(tempInvisiBullet.getx() - p.getx() < 32 && tempInvisiBullet.gety() - p.gety() < 32){
                if(tempInvisiBullet.getx() - p.getx() > -32 && tempInvisiBullet.gety() - p.gety() > -32){
                    g.setInvisiBulletDuration(g.getInvisiBulletDuration() + 8*60);
                    removeInvisiBullet(tempInvisiBullet);
                    g.playSound("res/wav/gameOver.wav");
                }
            }
        }
        
        
        //speedUp tick
        for(int i = 0; i < su.size(); i++){
            tempSpeedUp = su.get(i);
            //player detection
            if(tempSpeedUp.getx() - p.getx() < 32 && tempSpeedUp.gety() - p.gety() < 32){
                if(tempSpeedUp.getx() - p.getx() > -32 && tempSpeedUp.gety() - p.gety() > -32){
                    g.setSpeedUpDuration(g.getSpeedUpDuration() + 15*60);
                    g.playSound("res/wav/powerUp.wav");
                    removeSpeedUp(tempSpeedUp);
                }
            }
            tempSpeedUp.tick();
        }
        
        //tick coins
        for(int i = 0; i < co.size(); i++){
            Coin tempCoin = co.get(i);
            tempCoin.tick();
            if(tempCoin.getx() - p.getx() < 32 && tempCoin.gety() - p.gety() < 32){
                if(tempCoin.getx() - p.getx() > -32 && tempCoin.gety() - p.gety() > -32){
                    removeCoin(tempCoin);
                    g.setScore(g.getScore()+5);
                    e.clear();
                    z.clear();
                }
            }
        }
        
        //cleared enemies, start new round
        if(e.size() <= 0 && z.size() <= 0){
            for(int i = 0; i < round; i++){
                if(r.nextInt(8) == 1){
                    addEnemy(new Alien(tex, 2, g));
                }else{
                    addEnemy(new Alien(tex, 1, g));
                }
            }
            g.setRound(g.getRound() + 5);
            g.setWave(g.getWave() + 1);
            if(round <= 1){
            m.add(new medKit(tex));
            g.playSound("res/wav/scoreUp.wav");
            }
        }
    }
    
    
    //render
    public void render(Graphics g){
        //render enemies
        for(int i = 0; i < e.size(); i++){
            tempEnemy = e.get(i);
            tempEnemy.render(g);
        }
        //render bullets
        if(this.g.getInvisiBulletDuration() <= 0){
        for(int i = 0; i < b.size(); i++){
            tempBullet = b.get(i);
            tempBullet.render(g);
        }
        }
        //render medkits
        for(int i = 0; i < m.size(); i++){
            tempMedKit = m.get(i);
            tempMedKit.render(g);
        }
        //render zigzags
        for(int i = 0; i < z.size(); i++){
            tempZig = z.get(i);
            tempZig.render(g);
        }
        //render invisiBullets
        for(int ii = 0; ii < i.size(); ii++){
            tempInvisiBullet = i.get(ii);
            tempInvisiBullet.render(g);
        }
        //render speedups
        for(int i = 0; i < su.size(); i++){
            tempSpeedUp = su.get(i);
            tempSpeedUp.render(g);
        }
        //render coins
        for(int i = 0; i < co.size(); i++){
            Coin tempCoin = co.get(i);
            tempCoin.render(g);
        }
    }
    
    public void addBullet(Bullet bull){
        b.add(bull);
    }
    public void removeBullet(Bullet bull){
        b.remove(bull);
    }
    public void addEnemy(Alien en){
        e.add(en);
    }
    public void removeEnemy(Alien en){
        e.remove(en);
    }
    public void addMedKit(medKit m){
        this.m.add(m);
    }
    public void removeMedKit(medKit m){
        this.m.remove(m);
    }
    public LinkedList<Alien> getEnemyArray(){
        return e;
    }
    public LinkedList<Bullet> getBulletArray(){
        return b;
    }
    public LinkedList<medKit> getMedKitArray(){
        return m;
    }
    public void addZig(zigZagEnemy z){
        this.z.add(z);
    }
    public void removeZig(zigZagEnemy z){
        this.z.remove(z);
    }
    public LinkedList<zigZagEnemy> getZigArray(){
        return z;
    }
    public void addInvisiBullet(invisiBullet i){
        this.i.add(i);
    }
    public void removeInvisiBullet(invisiBullet i){
        this.i.remove(i);
    }
    public LinkedList<invisiBullet> getInvisiBulletArray(){
        return i;
    }
    public void addSpeedUp(speedUp s){
        su.add(s);
    }
    public void removeSpeedUp(speedUp s){
        su.remove(s);
    }
    public LinkedList<speedUp> getSpeedUpArray(){
        return su;
    }
    public void addCoin(Coin c){
        co.add(c);
    }
    public void removeCoin(Coin c){
        co.remove(c);
    }
}
