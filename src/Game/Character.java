package Game;


import edu.macalester.graphics.Image;

public class Character {
    private Image image;
    private double speed = 5;
    private double centerX, centerY;
    public double upTimer, downTimer, leftTimer, rightTimer;
    private Image r0, r1, r2, r3, r4, r5, o0,
    l0, l1, l2, l3, l4, l5;
    private String direction;
    
    public Character (double charX, double charY) {
        this.centerX = charX;
        this.centerY = charY;
        rightTimer = 0;
        upTimer = 0;
        downTimer = 0;
        leftTimer = 0;
        image = new Image("runRight0.png");
        image.setCenter(centerX, centerY);
        direction = "none";
        loadImages();
    } 
        
    private void loadImages() {
        r0 = new Image(centerX, centerY, "runRight0.png");
        r1 = new Image(centerX, centerY, "runRight1.png");
        r2 = new Image(centerX, centerY, "runRight2.png");
        r3 = new Image(centerX, centerY, "runRight3.png");
        r4 = new Image(centerX, centerY, "runRight4.png");
        r5 = new Image(centerX, centerY, "runLeft0.png");
        o0 = new Image(centerX, centerY, "Owlet_Monster.png");
    }

    public void handleMovement(int ic) {
        updateRunImage(ic);
        updateRunPosiiton();
    }

    private void updateRunImage(int ic) {
        if(this.direction.equals("right")){
            image.setImagePath("run" + "Right" + ic + ".png");
        }
        if(this.direction.equals("left")){
            image.setImagePath("run" + "Left" + ic + ".png");
        }

    }

    public void resetCharacterImage() {
        image.setImagePath("PinkStand.png");
    }
   
    public void updateRunPosiiton() {     
        switch(this.direction) {
            case "right": if (this.centerX < Game.CANVAS_WIDTH - image.getWidth()/2 - speed) centerX += speed;
            break;
            case "left": if(this.centerX > image.getWidth()/2 - speed) centerX -= speed;
            break;
            case "up": if(this.centerY > image.getHeight()/2 - speed) centerY -= speed;
            break;
            case "down": if (this.centerY < Game.CANVAS_HEIGHT + image.getHeight()/2 + speed) centerY += speed;
            break;
            case "none": break;
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

    public void setDirection(String direction) {
        this.direction = direction;
    }
    
}
