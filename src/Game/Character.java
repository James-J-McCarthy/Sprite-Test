package Game;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

// import edu.macalester.graphics.CanvasWindow;
// import edu.macalester.graphics.FontStyle;
// import edu.macalester.graphics.GraphicsGroup;
// import edu.macalester.graphics.GraphicsText;
// import edu.macalester.graphics.events.Key;


public class Character {
    private Image image;
    private double speed = 5;
    private double centerX, centerY;
    public double upTimer, downTimer, leftTimer, rightTimer;
    private double stepIncrement = 1;
    private String direction;
    private boolean direcitonChanged;
    private Image r0, r1, r2, r3, r4, r5, r6;
    
    public Character () {
        this.centerX = 250;
        this.centerY = 300;
        direcitonChanged = false;
        rightTimer = 0;
        upTimer = 0;
        downTimer = 0;
        leftTimer = 0;
        image = new Image("runRight0.png");
        image.setCenter(centerX, centerY);
        direction = "right";
        loadImages();
    } 
        
    private void loadImages() {
        r0 = new Image(centerX, centerY, "runRight0.png");
        r1 = new Image(centerX, centerY, "runRight1.png");
        r2 = new Image(centerX, centerY, "runRight2.png");
        r3 = new Image(centerX, centerY, "runRight3.png");
        r4 = new Image(centerX, centerY, "runRight4.png");
        r5 = new Image(centerX, centerY, "runRight5.png");
        r6 = new Image(centerX, centerY, "Owlet_Monster.png");
    }

    public void handleMovement(String direction, int ic) {
        updateImage(ic);
        updatePosiiton(direction);
    }

    private void updateImage(int ic) {
        image.setImagePath("runRight" + ic + ".png");
    }
   
    public void updatePosiiton(String direction) {     
        switch(direction) {
            case "right": if (this.centerX < Game.CANVAS_WIDTH - image.getWidth()/2 - speed) centerX += speed;
            break;
            case "left": if(this.centerX > image.getWidth()/2 - speed) centerX -= speed;
            break;
            case "up": if(this.centerY > image.getHeight()/2 - speed) centerY -= speed;
            break;
            case "down": if (this.centerY < Game.CANVAS_HEIGHT + image.getHeight()/2 + speed) centerY += speed;
            break;
        }
        image.setCenter(centerX, centerY);        
    }
        
    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public Image getImage() {
        return image;
    }
    
}
