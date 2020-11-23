package Games.Fly;

import javafx.stage.Screen;

public class Asteroid extends Thing {
    protected double radius;

    public Asteroid(int x, int y, double speedX, double speedY, double radius){

        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.radius = radius;
        this.mass = radius/10;
    }

    public void move(){
        this.x += speedX;
        this.y += speedY;
    }

    public void collideBorder(){
        if(this.x < radius)this.speedX = Math.abs(this.speedX/2);
        if(this.x > Screen.getPrimary().getBounds().getMaxX()-radius)this.speedX = -Math.abs(this.speedX/1.01);
        if(this.y < radius)this.speedY = Math.abs(this.speedY/2);
        if(this.y > Screen.getPrimary().getBounds().getMaxY()-radius)this.speedY = -Math.abs(this.speedX/1.01);
    }

    public void magicInfluence(){
        double xInfluence = Math.random()*0.5-0.25; double yInfluence = Math.random()*0.5-0.25;
        this.speedX += xInfluence; this.speedY += yInfluence;
    }

}
