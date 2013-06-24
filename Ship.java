import image.Circle;
import image.Scene;


public class Ship{
    DPosn loc;
    DPosn dir;

    Ship(DPosn loc, DPosn dir){
        this.loc = loc;
        this.dir = dir;
    }

    /**draws Ship on Scene*/
    Scene draw(Scene s){
        return s.placeImage(new Circle(15, "solid", "gray"), loc.x, loc.y)
        .placeImage(new Circle(7, "solid", "lime"), loc.x, loc.y)
        .placeImage(new Circle(15, "outline", "black"), loc.x, loc.y);
    }
    
    /**Moves Ship, de-acceleration built in*/
    public void update(){
        loc = new DPosn(loc.x + .98 * dir.x, loc.y + .98 * dir.y);
    }
    
    /** Is this object "equal" to the given object? */
    public boolean equals(Object o){
        if(o instanceof Ship){
            Ship s1 = this;
            Ship s2 = (Ship) o;
            return s1.loc == s2.loc && s1.dir == s2.dir;
        }else{ 
            return false;
            }
    }
}
