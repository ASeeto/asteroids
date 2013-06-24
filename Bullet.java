import image.Circle;
import image.Scene;



/**A single bullet*/
public class Bullet{
    DPosn loc;
    DPosn dir;
    
    Bullet(DPosn loc, DPosn dir){
        this.loc = loc;
        this.dir = dir;
    }
    
    /**bullet move function, changes on tick*/
    public void update(){
        loc = new DPosn(loc.x + dir.x, loc.y + dir.y);
    }
    
    /**bullet draw function*/
    public Scene draw(Scene s){
        return s.placeImage(new Circle(3, "solid", "orange"), loc.x, loc.y)
                .placeImage(new Circle(1, "solid", "yellow"), loc.x, loc.y);
    }
    
    public boolean equals(Object o){
        if(o instanceof Bullet){
        	Bullet b1 = this;
        	Bullet b2 = (Bullet) o;
            return b1.loc == b2.loc && b1.dir == b2.dir;
        }else{ 
            return false;
            }
    }
}