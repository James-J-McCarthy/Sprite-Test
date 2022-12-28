package Game;

// import edu.macalester.graphics.Image;

import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
// import edu.macalester.graphics.FontStyle;
// import edu.macalester.graphics.GraphicsGroup;
// import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;

public class Game {
    
    // private Image background;
    
    private Character char1;
    public final static int CANVAS_WIDTH = 500;
    public final static int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private double movementTimer;
    private int imageCount;
    

    public Game () {
        canvas = new CanvasWindow(null, CANVAS_WIDTH, CANVAS_HEIGHT);
        char1 = new Character(); 
        imageCount = 0;
        movementTimer = 0;
        canvas.animate((dt) -> {
            handleCharacterMovement(dt);
            canvas.add(char1.getImage());
        });
    }

    private void handleCharacterMovement(double dt) {
        Set<Key> keys = canvas.getKeysPressed();
        movementTimer += dt;
        
        if(movementTimer > .2) {
            movementTimer = 0;
            imageCount ++;
            if (imageCount ==6) imageCount = 0;
        }
        System.out.println("imageCount = " + imageCount +" movemenTimer = "+ movementTimer);
        if (keys.contains(Key.LEFT_ARROW)) {
            char1.updatePosiiton("left");
            // char1.updateImage("left");
        } 
        if (keys.contains(Key.RIGHT_ARROW)) {
            char1.handleMovement("right", imageCount);
        }
        if (keys.contains(Key.DOWN_ARROW)) {
            char1.updatePosiiton("down");
            
        } 
        if (keys.contains(Key.UP_ARROW)) {
            char1.updatePosiiton("up");
           
        } 
    }

    public static void main(String[] args) {
        new Game();
    }
}