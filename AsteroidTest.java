import static org.junit.Assert.*;
import image.*;

import java.util.ArrayList;
import org.junit.Test;


public class AsteroidTest {

    @Test
    public void testDposnXDown() {
        DPosn d1 = new DPosn(5.0, 5.0);
        DPosn d2 = new DPosn(4.0, 5.0);
        DPosn d3 = new DPosn(0.0, 0.0);
        d1.dposnXDown();
        assertTrue(d1.equals(d2));
        assertFalse(d1.equals(d3));
    }
    
    @Test
    public void testDposnXUp() {
        DPosn d1 = new DPosn(5.0, 5.0);
        DPosn d2 = new DPosn(4.0, 5.0);
        DPosn d3 = new DPosn(0.0, 0.0);
        d2.dposnXUp();
        assertTrue(d2.equals(d1));
        assertFalse(d1.equals(d3));
    }
    
    @Test
    public void testDposnYDown() {
        DPosn d1 = new DPosn(5.0, 5.0);
        DPosn d2 = new DPosn(5.0, 4.0);
        DPosn d3 = new DPosn(0.0, 0.0);
        d1.dposnYDown();
        assertTrue(d1.equals(d2));
        assertFalse(d1.equals(d3));
    }
    
    @Test
    public void testDposnYUp() {
        DPosn d1 = new DPosn(5.0, 5.0);
        DPosn d2 = new DPosn(5.0, 4.0);
        DPosn d3 = new DPosn(0.0, 0.0);
        d2.dposnYUp();
        assertTrue(d2.equals(d1));
        assertFalse(d1.equals(d3));
    }
    
    @Test
    public void testDPosnEquals() {
        DPosn d1 = new DPosn(5.0, 5.0);
        DPosn d2 = new DPosn(5.0, 5.0);
        DPosn d3 = new DPosn(0.0, 0.0);
        assertTrue(d1.equals(d2));
        assertFalse(d1.equals(d3));
    }
    
    @Test
    public void testAsteroidUpdate() {
        DPosn p1 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Asteroid a1 = new Asteroid(p1, d1, 6);
        a1.update();
        Asteroid a3 = new Asteroid(p1, d1, 6);
        assertFalse(a1.equals(a3));
    }
    
    @Test
    public void testCollide() {
        DPosn p1 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Ship s = new Ship(new DPosn(250, 250), new DPosn(0,0));
        Asteroid a1 = new Asteroid(p1, d1, 6);
        assertFalse(a1.collide(s));
    }
    
    @Test
    public void testAsteroidEquals() {
        DPosn p1 = new DPosn(25, 50);
        DPosn p2 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Asteroid a1 = new Asteroid(p1, d1, 6);
        Asteroid a2 = new Asteroid(p1, d1, 6);
        Asteroid a3 = new Asteroid(p2, d1, 6);
        assertTrue(a1.equals(a2));
        assertFalse(a1.equals(a3));
    }
    
    @Test
    public void testAsteriodsUpdate() {
        DPosn p1 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Asteroid a1 = new Asteroid(p1, d1, 6);
        Asteroid a2 = new Asteroid(p1, d1, 6);
        ArrayList<Asteroid> al1 = new ArrayList<Asteroid>();
        al1.add(a1);
        ArrayList<Asteroid> al2 = new ArrayList<Asteroid>();
        al2.add(a2);
        Asteroids a = new Asteroids(al1);
        Asteroids b = new Asteroids(al2);
        a.updateAll();
        assertFalse(a.equals(b));
    }
    
    @Test
    public void testAsteroidDraw() {
        DPosn p1 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Asteroid a1 = new Asteroid(p1, d1, 6);
        Scene s1 = new EmptyScene(500, 500);
        Scene s2 = new EmptyScene(500, 500);
        s2.placeImage(new Circle(a1.size, "solid", "brown"), a1.loc.x, a1.loc.y)
          .placeImage(new Circle(a1.size, "outline", "dark gray"), a1.loc.x, a1.loc.y);
        assertFalse(a1.draw(s1).equals(s2));
    }
    
    @Test
    public void testBulletEquals() {
        DPosn p1 = new DPosn(25, 50);
        DPosn p2 = new DPosn(20, 50);
        DPosn d1 = new DPosn(1, 1);
        Bullet b1 = new Bullet(p1, d1);
        Bullet b2 = new Bullet(p1, d1);
        Bullet b3 = new Bullet(p2, d1);
        assertTrue(b1.equals(b2));
        assertFalse(b1.equals(b3));
    }
    
    @Test
    public void testBulletsUpdate() {
        DPosn p1 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Bullet b1 = new Bullet(p1, d1);
        Bullet b2 = new Bullet(p1, d1);
        ArrayList<Bullet> bl1 = new ArrayList<Bullet>();
        bl1.add(b1);
        ArrayList<Bullet> bl2 = new ArrayList<Bullet>();
        bl2.add(b2);
        Bullets a = new Bullets(bl1);
        Bullets b = new Bullets(bl2);
        a.updateAll();
        assertFalse(a.equals(b));
    }
    
    @Test
    public void testBulletUpdate() {
        DPosn p1 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Bullet b1 = new Bullet(p1, d1);
        b1.update();
        Bullet b3 = new Bullet(p1, d1);
        assertFalse(b1.equals(b3));
    }
    
    @Test
    public void testBulletDraw() {
        DPosn p1 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Bullet b1 = new Bullet(p1, d1);
        Scene s1 = new EmptyScene(500, 500);
        Scene s2 = new EmptyScene(500, 500);
        s2.placeImage(new Circle(3, "solid", "orange"), b1.loc.x, b1.loc.y)
          .placeImage(new Circle(1, "solid", "yellow"), b1.loc.x, b1.loc.y);
        assertFalse(b1.draw(s1).equals(s2));
    }
    
    @Test
    public void testShipEquals() {
        DPosn p1 = new DPosn(25, 50);
        DPosn p2 = new DPosn(20, 50);
        DPosn d1 = new DPosn(1, 1);
        Ship s1 = new Ship(p1, d1);
        Ship s2 = new Ship(p1, d1);
        Ship s3 = new Ship(p2, d1);
        assertTrue(s1.equals(s2));
        assertFalse(s1.equals(s3));
    }
    
    @Test
    public void testShipUpdate() {
        DPosn p1 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Ship s1 = new Ship(p1, d1);
        s1.update();
        Ship s3 = new Ship(p1, d1);
        assertFalse(s1.equals(s3));
    }
    
    @Test
    public void testShipDraw() {
        DPosn p1 = new DPosn(25, 50);
        DPosn d1 = new DPosn(1, 1);
        Ship ship = new Ship(p1, d1);
        Scene s1 = new EmptyScene(500, 500);
        Scene s2 = new EmptyScene(500, 500);
        s2.placeImage(new Circle(15, "solid", "gray"), ship.loc.x, ship.loc.y)
          .placeImage(new Circle(7, "solid", "lime"), ship.loc.x, ship.loc.y)
          .placeImage(new Circle(15, "outline", "black"), ship.loc.x, ship.loc.y);
        assertFalse(ship.draw(s1).equals(s2));
    }
    
    @Test
    public void testRemover() {
        AsteroidWorld world1 = new AsteroidWorld();
        AsteroidWorld world2 = new AsteroidWorld();
        world1.remover();
        assertFalse(world1.equals(world2));
    }
    
    @Test
    public void testBulletRemove() {
        AsteroidWorld world1 = new AsteroidWorld();
        AsteroidWorld world2 = new AsteroidWorld();
        world1.bulletRemove();
        assertFalse(world1.equals(world2));
    }
    
    @Test
    public void testCollision() {
        AsteroidWorld world1 = new AsteroidWorld();
        AsteroidWorld world2 = new AsteroidWorld();
        world1.collision();
        assertFalse(world1.equals(world2));
    }
    
    @Test
    public void testCrash() {
        AsteroidWorld world1 = new AsteroidWorld();
        AsteroidWorld world2 = new AsteroidWorld();
        world1.crash();
        assertFalse(world1.equals(world2));
    }
    
    @Test
    public void testEdger() {
        AsteroidWorld world1 = new AsteroidWorld();
        AsteroidWorld world2 = new AsteroidWorld();
        world1.edger();
        assertFalse(world1.equals(world2));
    }
    
    @Test
    public void testbgScene() {
        AsteroidWorld world1 = new AsteroidWorld();
        Scene s1 = new EmptyScene(500, 500);
        Scene s2 = new EmptyScene(500, 500);
        s2.placeImage(new FromFile("spacebg.jpg"), 250, 250);
        assertFalse(world1.bgScene(s1).equals(s2));
    }
    
    @Test
    public void testscoreDraw() {
        AsteroidWorld world1 = new AsteroidWorld();
        Scene s1 = new EmptyScene(500, 500);
        Scene s2 = new EmptyScene(500, 500);
        s2.placeImage(new Text("Lives: "+world1.lives+"    Score: "+world1.score+"", 20, "gray"), 100, 20);
        assertFalse(world1.scoreDraw(s1).equals(s2));
    }
}
