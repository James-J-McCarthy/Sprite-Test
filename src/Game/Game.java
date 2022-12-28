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
    private final int CANVAS_WIDTH = 500;
    private final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private int movementTimer;
    

    public Game () {
        canvas = new CanvasWindow(null, CANVAS_WIDTH, CANVAS_HEIGHT);
        char1 = new Character(canvas.getWidth()/2, canvas.getHeight()/2, canvas); 
        movementTimer = 0;
        
        char1.getImage().setCenter(canvas.getWidth()/2, canvas.getHeight()/2);
        canvas.animate((dt) -> {
            handleCharacterMovement(dt);
            canvas.add(char1.getImage());
        });
    }
 

    private void handleCharacterMovement(double dt) {
        Set<Key> keys = canvas.getKeysPressed();
        if (!char1.getDirectionChanged()) {
            movementTimer += (int) dt;
        } else {
            movementTimer = 0;
        }
        if (keys.contains(Key.LEFT_ARROW)) {
            char1.updatePosiiton("left");
            // char1.updateImage("left");
        } 
        if (keys.contains(Key.RIGHT_ARROW)) {
            char1.handleMovement("right", movementTimer);
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