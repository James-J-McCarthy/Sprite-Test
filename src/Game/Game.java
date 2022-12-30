package Game;

import java.util.ArrayList;

// import edu.macalester.graphics.Image;

import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.events.Key;

public class Game {
    
    // private Image background;
    
    private Character char1;
    public final static int CANVAS_WIDTH = 600;
    public final static int CANVAS_HEIGHT = 300;
    private CanvasWindow canvas;
    private double movementTimer;
    private int imageCount;
    private Image blackCanvas, whiteStripeBackground, bigTiles, tree;
    private GraphicsGroup backgroup;
    private static GraphicsGroup characterlayer;
    private static GraphicsGroup groundGroup;
    private GraphicsGroup frontGroup;

    public Game () {
        canvas = new CanvasWindow(null, CANVAS_WIDTH, CANVAS_HEIGHT);

        backgroup = new GraphicsGroup();
        characterlayer = new GraphicsGroup();
        frontGroup = new GraphicsGroup();
        groundGroup = new GraphicsGroup();

        char1 = new Character(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        setUpGraphics();
        
        imageCount = 0;
        movementTimer = 0;
        canvas.animate((dt) -> {
            handleCharacterMovement(dt);
        });
    }

    private void setUpGraphics() {
        backgroup = new GraphicsGroup();
        characterlayer = new GraphicsGroup();
        frontGroup = new GraphicsGroup();
        
        blackCanvas = new Image("Layer0.png");
        whiteStripeBackground = new Image(0, CANVAS_HEIGHT/4, "Layer01.png");
        
        blackCanvas.setScale(3);
        blackCanvas.setMaxWidth(CANVAS_WIDTH);

        whiteStripeBackground.setMaxWidth(CANVAS_WIDTH/2);

        Image whiteStripe2 = new Image(CANVAS_WIDTH/2, CANVAS_HEIGHT/4, "Layer01.png");
        whiteStripe2.setMaxWidth(CANVAS_WIDTH/2);

        
        tree = new Image("Layer04.png");
        tree.setPosition(CANVAS_WIDTH/2, CANVAS_HEIGHT*6/7 - tree.getHeight());
        tree.setScale(1.9);
        tree.setMaxHeight(CANVAS_HEIGHT);
        frontGroup.add(tree);

        characterlayer.add(char1.getImage());

        backgroup.add(blackCanvas);
        backgroup.add(whiteStripe2);
        backgroup.add(whiteStripeBackground);
        for (double x = 0; x <= 30; x++){
            Image groundTile = new Image(CANVAS_WIDTH* x/30, CANVAS_HEIGHT *7/10, "groundTile.png");

            groundTile.setAnchor(CANVAS_WIDTH* x/30, CANVAS_HEIGHT *7/10);
            groundTile.setMaxWidth(CANVAS_WIDTH/30);

            groundGroup.add(groundTile);
        }
        canvas.add(backgroup);
        canvas.add(groundGroup);
        canvas.add(characterlayer);
        canvas.add(frontGroup);
    }

    private void handleCharacterMovement(double dt) {
        Set<Key> keys = canvas.getKeysPressed();
        movementTimer += dt;
        
        if(movementTimer > .12) {
            movementTimer = 0;
            imageCount ++;
            if (imageCount ==5) imageCount = 0;
        }
        if (keys.contains(Key.LEFT_ARROW)) {
            char1.setDirection("left");
        } 
        if (keys.contains(Key.RIGHT_ARROW)) {
            char1.setDirection("right");
        }
        if (keys.contains(Key.DOWN_ARROW)) {
            char1.setDirection("down");
        } 
        if (keys.contains(Key.UP_ARROW)) {
            char1.setDirection("up");
        } 
        char1.handleMovement(imageCount, dt);
        if (!keys.contains(Key.LEFT_ARROW) && !keys.contains(Key.RIGHT_ARROW)
        && !keys.contains(Key.DOWN_ARROW) && !keys.contains(Key.UP_ARROW)) {
            char1.setDirection("none");
            char1.resetCharacterImage();
        }
    }

    public static void setCharOnGround (Character character, GraphicsObject groundTile) {
        character.setCenterY(groundTile.getPosition().getY() -  character.getHeight()*3/8);
    }
    
    public static GraphicsObject getGroundUnderACharacter(Character character) {
        GraphicsObject obj = groundGroup.getElementAt(character.getCenterX(),
        character.getCenterY() + character.getHeight() * 3/8 );
        return obj;
    }  
   
    public static void main(String[] args) {
        new Game();
    }

}