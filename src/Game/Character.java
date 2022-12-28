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
    private double speed = 4;
    private double x, y;
    public double upTimer, downTimer, leftTimer, rightTimer;
    private double stepIncrement = 1;
    private String direction;
    private boolean direcitonChanged;
    private Image r0, r1, r2, r3, r4, r5;
    
    public Character (double xPos, double yPos, CanvasWindow canvas) {
        this.x = xPos;
        this.y = yPos;
        direcitonChanged = false;
        rightTimer = 0;
        upTimer = 0;
        downTimer = 0;
        leftTimer = 0;
        image = new Image(x, y, "runRight1.png");
        direction = "right";
        loadImages(canvas);
    } 
        
    private void loadImages(CanvasWindow canvas) {
        r0 = new Image(x, y, "runRight0.png");
        r1 = new Image(x, y, "runRight1.png");
        r2 = new Image(x, y, "runRight2.png");
        r3 = new Image(x, y, "runRight3.png");
        r4 = new Image(x, y, "runRight4.png");
        r5 = new Image(x, y, "runRight5.png");
    }

    public void updateRightTimer (double dt) {
        rightTimer += dt;
    }

    public void updateDirection (String direction) {
        this.direction = direction;
    }

    public boolean getDirectionChanged() {
        return direcitonChanged;
    }

    public void handleMovement(String direction, int mt) {
        direcitonChanged = false;
        if (direction.equals(this.direction)) {
            direcitonChanged = true;
        }
        updateImage(mt);
        updatePosiiton(direction);
    }

    public void updateImage (int mt) {
        int t = mt;
        while (t > 59) {
            t -= 60;
        }
        t /= 10;
        if(t ==0) {
            image = r0;
        }
        if(t ==1) {
            image = r1;
        }
        if(t ==2) {
            image = r2;
        }
        if(t ==3) {
            image = r3;
        }
        if(t ==4) {
            image = r4;
        }
        if(t ==5) {
            image = r5;
        }
    }
   
    public void updatePosiiton(String direction) {
        switch(direction) {
            case "right": image.moveBy(speed, 0);
            break;
            case "left": image.moveBy(-speed, 0);
            break;
            case "up": image.moveBy(0, -speed);
            break;
            case "down": image.moveBy(0, speed);
            break;
        }
        System.out.println(image.getCenter());
    }
        
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    
}
