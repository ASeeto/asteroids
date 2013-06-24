import image.Circle;
import image.Scene;


public class Asteroid{
    DPosn loc;
    int size;
    DPosn dir;
    int health;

    Asteroid(DPosn loc, DPosn dir, int health){
        this.loc = loc;
        this.size = health * 5;
        this.health = health;
        this.dir = dir;
    }    

    /**Draws one asteroid*/
    Scene draw(Scene s){    
        return s.placeImage(new Circle(size, "solid", "brown"), loc.x, loc.y)
                .placeImage(new Circle(size, "outline", "dark gray"), loc.x, loc.y);
    //}else return s.placeImage(new Circle(size * 2, "solid", "orange"), loc.x, loc.y)
        //.placeImage(new Circle(size, "solid", "yellow"), loc.x, loc.y);
        // this is the explosion graphic, needs to be activated by boolean in hasBullet
    }

    /**moves one asteroid*/
    public void update(){
        loc = new DPosn(loc.x + dir.x, loc.y + dir.y);
    }
    
    public boolean collide(Ship s){
        int hit = this.size + 7;
        if (this.loc.x - hit < s.loc.x &&
                s.loc.x < this.loc.x + hit &&
                this.loc.y - hit < s.loc.y &&
                s.loc.y < this.loc.y + hit){
            return true;
        }else{
            return false;
        }
    }
    
    /** Is this object "equal" to the given object? */
    public boolean equals(Object o){
        if(o instanceof Asteroid){
            Asteroid a1 = this;
            Asteroid a2 = (Asteroid) o;
            return a1.loc == a2.loc && 
            	   a1.size == a2.size &&
            	   a1.dir == a2.dir &&
            	   a1.health == a2.health;
        }else{ 
            return false;
            }
    }
    
}