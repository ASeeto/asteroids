import image.Scene;

import java.util.ArrayList;

/**ArrayList of bullets*/
public class Bullets{
    ArrayList<Bullet> bullets;
    
    Bullets(ArrayList<Bullet> bullets){
        this.bullets = bullets;
    }
    
    /**draws all bullets*/
    public Scene draw(Scene s){
        Scene temp = s;
        for(Bullet a : bullets){
            temp = a.draw(temp);
        }
        return temp;
    }
    
    /**moves all bullets*/
    public void updateAll(){
        for(int i = 0; i < bullets.size(); i++){
            Bullet b = bullets.get(i);
            b.update();
        }
    }
    
    /** Is this object "equal" to the given object? */
    public boolean equals(Object o){
        if(o instanceof Asteroid){
            Bullets b1 = this;
            Bullets b2 = (Bullets) o;
            return b1.bullets == b2.bullets;
        }else{ 
            return false;
            }
    }
}