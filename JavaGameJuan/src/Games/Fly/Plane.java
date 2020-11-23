package Games.Fly;

import javafx.stage.Screen;

public class Plane extends Thing {

    public Plane(int x, int y){

        this.x = x;
        this.y = y;
        this.speedX = 0;
        this.speedY = 0;
        this.mass = 1;
    }



    public void move(){
        this.x += speedX;
        this.y += speedY;
    }

    public void collideBorder(){
        if(this.x < 0)this.speedX = Math.abs(this.speedX/2);
        if(this.x > Screen.getPrimary().getBounds().getMaxX()-10)this.speedX = -Math.abs(this.speedX/2);
        if(this.y < 0)this.speedY = Math.abs(this.speedY/2);
        if(this.y > Screen.getPrimary().getBounds().getMaxY()-10)this.speedY = -Math.abs(this.speedX/2);
    }

}
