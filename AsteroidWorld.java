import image.*;
import java.util.*;
import world.sound.*;
import world.sound.tunes.*;




/**VOIDWORLD*/
public class AsteroidWorld extends SoundWorld{
    public static void main(String[] args){
        new AsteroidWorld().bigBang();
    }
    Asteroids as;
    Bullets bs;
    Ship ship;
    int height;
    int width;
    int lives;
    int score;
    int volume;
    Music sounds;
    

    AsteroidWorld(){
        reset();
        as = as1;
        bs = bs1;
        ship = new Ship(new DPosn(250, 250), new DPosn(0, 0));
        this.height = 500;
        this.width = 500;
        this.lives = 5;
        this.score = 0;
        this.volume = 10000;
        this.sounds = new Music();
        this.sounds.setClip("dinosaur.wav", 1);
    }
    Asteroids as1;
    Bullets bs1;
    
    
    Ship startship = new Ship(new DPosn(250, 250), new DPosn(0,0));
    
    DPosn p1 = new DPosn(25, 50);
    DPosn p2 = new DPosn(400, 250);
    DPosn p3 = new DPosn(350, 200);
    DPosn p4 = new DPosn(92, 420);
    
    DPosn d1 = new DPosn(1, 1);
    DPosn d2 = new DPosn(-1, 1);
    DPosn d3 = new DPosn(-1, -1);
    DPosn d4 = new DPosn(1, -1);
    
    DPosn p5 = new DPosn(250, 400);
    DPosn p6 = new DPosn(62, 250);
    DPosn p7 = new DPosn(400, 100);
    DPosn p8 = new DPosn(86, 69);
    
    DPosn d5 = new DPosn(1, 1);
    DPosn d6 = new DPosn(-1, 0);
    DPosn d7 = new DPosn(-1, -1);
    DPosn d8 = new DPosn(0, 1);
    
    DPosn p9 = new DPosn(470, 360);
    DPosn p10 = new DPosn(350, 250);
    DPosn p11 = new DPosn(150, 270);
    DPosn p12 = new DPosn(92, 420);
    
    DPosn d9 = new DPosn(1, 1);
    DPosn d10 = new DPosn(1, -1);
    DPosn d11 = new DPosn(-1, -1);
    DPosn d12 = new DPosn(1, -1);
    
    Asteroid a1 = new Asteroid(p1, d1, 6);
    Asteroid a2 = new Asteroid(p2, d2, 9);
    Asteroid a3 = new Asteroid(p3, d3, 6);
    Asteroid a4 = new Asteroid(p4, d4, 9);
    Asteroid a5 = new Asteroid(p5, d5, 4);
    Asteroid a6 = new Asteroid(p6, d6, 6);
    Asteroid a7 = new Asteroid(p7, d7, 4);
    Asteroid a8 = new Asteroid(p8, d8, 9);
    Asteroid a9 = new Asteroid(p9, d9, 6);
    Asteroid a10 = new Asteroid(p10, d10, 12);
    Asteroid a11 = new Asteroid(p12, d11, 4);
    Asteroid a12 = new Asteroid(p11, d12, 6);
    
    ArrayList<Asteroid> alist = new ArrayList<Asteroid>();
    ArrayList<Bullet> blist = new ArrayList<Bullet>();


    public void reset(){
        as1 = new Asteroids(alist);
        bs1 = new Bullets(blist);
        as1.all.add(a1);
        as1.all.add(a2);
        as1.all.add(a3);
        as1.all.add(a4);
        as1.all.add(a5);
        as1.all.add(a6);
        as1.all.add(a7);
        as1.all.add(a8);
        as1.all.add(a9);
        as1.all.add(a10);
        as1.all.add(a11);
        as1.all.add(a12);
    }
    /** Removes all off-screen or destroyed asteroids */
    void remover(){
        for(Asteroid a : as.all){
            if(a.loc.x > width || a.loc.x < 0){
                a.dir.x = a.dir.x * -1;
            }
            if(a.loc.y > height || a.loc.y < 0){
                a.dir.y = a.dir.y * -1;
            }
        }
    }
    
    public double tickRate(){
        return 0.02
        ;
    }
    
    /**Removes all off-screen bullets*/
    void bulletRemove(){
        for(Bullet a : bs.bullets){
            if(a.loc.x > width || a.loc.x < 0
            || a.loc.y > height || a.loc.y < 0){
                bs.bullets.remove(a);
            }
        }
    }
    
    /**removes bullets that have hit an asteroid
     * recurses hasBullet through ArrayList of bullets*/
    void collision(){
        for (int i = 0; i < bs.bullets.size(); i++){
            Bullet b = bs.bullets.get(i);
            if(as.hasBullet(b)){
                this.score = this.score + 10;
                bs.bullets.remove(b);
            }
        }
    }

    /** Did the ship crash into an asteroid */
    void crash(){
        Random q = new Random();
        int q1 = q.nextInt(500);
        int q2 = q.nextInt(500);
        for(Asteroid a : as.all){
            if(a.collide(ship))
            {if(a.collide(startship)){
                //separate methods (loop)
                ship.loc = new DPosn(q1, q2);
            }else{
                //explosion goes here
             ship.loc = new DPosn(250, 250);
             ship.dir = new DPosn(0, 0);
             this.lives --;
             this.score = this.score - 50;
             this.tickTunes.addNote(PERCUSSION, new Note(41, 8, this.volume));
            }
            }
        }
    }
    
    /**starts the scene, uses background*/
    Scene bgScene(Scene s){
        return s.placeImage(new FromFile("spacebg.jpg"), 250, 250);
    }
    
    /**Displays the player's score*/
    Scene scoreDraw(Scene s){
        return s.placeImage(new Text("Lives: "+this.lives+"    Score: "+this.score+"", 20, "gray"), 100, 20);
    }
    
    
    /**Checks the ship's respawn DPosn to avoid starting in an asteroid*/
    public boolean respawn(){
        for(Asteroid a : as.all){
            int hit = a.size + 15;
            if(a.loc.x - hit < ship.loc.x &&
                    ship.loc.x < a.loc.x + hit &&
                    a.loc.y - hit < ship.loc.y &&
                    ship.loc.y < a.loc.y + hit){
                return true;
            }
        } 
        return false;
    }
    
    /**Game Over once player loses all lives*/
    public boolean stopWhen(){
        return this.lives <= 0;
    }
    
    /**draws game over image*/
    public Scene lastScene(){
        return (new Text("GAME OVER. Score: "+this.score+"", 40, "black")).toScene();
    }
    
    /** Plays sound when bullet hits asteroid */
    
    
    /**wraps ship around scene*/
    void edger(){
        if(ship.loc.x > width){
            ship.loc.x = ship.loc.x - width;
            this.tickTunes.addNote(WOOD_BLOCK, new Note(50, 40, this.volume));
        }else{if(ship.loc.x < 0){
            ship.loc.x = ship.loc.x + width;
            this.tickTunes.addNote(WOOD_BLOCK, new Note(50, 40, this.volume));
        }else{if(ship.loc.y > height){
            ship.loc.y = ship.loc.y - height;
            this.tickTunes.addNote(WOOD_BLOCK, new Note(50, 40, this.volume));
        }else{if(ship.loc.y < 0){
            ship.loc.y = ship.loc.y + height;
            this.tickTunes.addNote(WOOD_BLOCK, new Note(50, 40, this.volume));
        }}}}
    }
    
    Bullet shoot(double x, double y){
        return new Bullet(ship.loc, new DPosn(ship.dir.x + x, ship.dir.y + y));
    }
    
    /**key handler:
     * moving and shooting*/
    public void onKey(String me){
        
        if(me.equals("s")) //move down
            ship.dir.dposnYUp();
        if(me.equals("w")) //move up
            ship.dir.dposnYDown();
        if(me.equals("a")) //move left
            ship.dir.dposnXDown();
        if(me.equals("d")) //move right
            ship.dir.dposnXUp();
        
        if(me.equals("8")){ //shoot up
            this.tickTunes.addNote(PERCUSSION, new Note(40, 50, this.volume));
            blist.add(shoot(0, -7));
        }
        if(me.equals("6")){ //shoot right
            blist.add(shoot(7, 0));
        this.tickTunes.addNote(PERCUSSION,  new Note(40, 50, this.volume));
        }
        if(me.equals("4")){ //shoot left
            blist.add(shoot(-7, 0));
        this.tickTunes.addNote(PERCUSSION,  new Note(40, 50, this.volume));
        }
        if(me.equals("2")){ //shoot down
            blist.add(shoot(0, 7));
        this.tickTunes.addNote(PERCUSSION,  new Note(40, 50, this.volume));
        }
        if(me.equals("9")){ //shoot up/right
            this.tickTunes.addNote(PERCUSSION, new Note(40, 50, this.volume));
            blist.add(shoot(Math.sqrt(34.5), - Math.sqrt(34.5) ));
        }
        if(me.equals("3")){ //shoot down/right
            blist.add(shoot(Math.sqrt(34.5),  Math.sqrt(34.5) ));
        this.tickTunes.addNote(PERCUSSION,  new Note(40, 50, this.volume));
        }
        if(me.equals("1")){ //shoot down/left
            blist.add(shoot(- Math.sqrt(34.5), Math.sqrt(34.5) ));
        this.tickTunes.addNote(PERCUSSION,  new Note(40, 50, this.volume));
        }
        if(me.equals("7")){ //shoot up/left
            blist.add(shoot(- Math.sqrt(34.5), - Math.sqrt(34.5) ));
        this.tickTunes.addNote(PERCUSSION,  new Note(40, 50, this.volume));
        }

        }
    
    /**ticker*/
    public void onTick(){
        remover();
        as.updateAll();
        ship.update();
        collision();
        crash();
        edger();
        bs.updateAll();
      
       }
    
    
    /**drawer*/
    public Scene onDraw(){
        return scoreDraw(bs.draw(ship.draw(as.draw(bgScene(new EmptyScene(width, height))))));
    }

} 