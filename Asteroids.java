import image.*;
import java.util.*;



/**an ArrayList of asteroids*/
public class Asteroids{
    ArrayList<Asteroid> all;
    Music sounds;

    Asteroids(ArrayList<Asteroid> all){
        this.all = all;
        this.sounds = new Music();
    }

    /**draws all asteroids*/
    public Scene draw(Scene s){
        Scene temp = s;
        for(Asteroid a : all){
            temp = a.draw(temp);
        }
        return temp;
    }
    
    /**moves all asteroids*/
    public void updateAll(){
        for(Asteroid a : all){
            a.update();
        }
    }
    
    /**creates a random new asteroid, adds it to the arraylist*/
    public Asteroid newA(){
        Random r = new Random();
        int r1 = r.nextInt(400); //screen location of new spawning asteroid
        int r2 = r.nextInt(4);   //which wall to spawn from
        int r3 = r.nextInt(2);   //X vector
        int r4 = r.nextInt(2);   //Y vector
        int r5 = r.nextInt(6);   //health of new asteroid
        r3 = (2 * r3) -1;        //sets to either -1 or 1
        r4 = (2 * r4) -1;        //sets to either -1 or 1
        r5 = r5 + 4;             //sets to size 4 through 9
        
        if(r2 < 2){
            return new Asteroid(new DPosn(r1 + 50, r2  * 500), 
                                new DPosn(r3, r4),
                                r5);
        }else{
            return new Asteroid(new DPosn((r2-2) * 500, r1  + 50),
                                new DPosn(r3, r4),
                                r5);
        }
    }
    
    /**detects if a bullet has hit any asteroid,
     * lowers asteroid health
     * or removes it and creates a new one*/
    public boolean hasBullet(Bullet b) {
        boolean hasBullet = false;
        for(int i = 0; i < all.size(); i++){
            Asteroid a = all.get(i);
            int hit = a.size;
            if(a.loc.x - hit < b.loc.x &&
                    b.loc.x < a.loc.x + hit &&
                    a.loc.y - hit < b.loc.y &&
                    b.loc.y < a.loc.y + hit){
                a.health --;
                if(a.health > 3){
                    a.size = a.health * 5;
                }
                if (a.health == 0){
                    all.remove(a);
                    all.add(newA());
                //  sounds.shootSound(0);
                }
                hasBullet = true;
            }
        }
        return hasBullet;
    }
    
    /** Is this object "equal" to the given object? */
    public boolean equals(Object o){
        if(o instanceof Asteroid){
            Asteroids a1 = this;
            Asteroids a2 = (Asteroids) o;
            return a1.all == a2.all;
        }else{ 
            return false;
            }
    }
}
    