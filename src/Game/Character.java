package Game;


import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;

public class Character {
    private Image image;
    private double runSpeed = 100;
    private double centerX, centerY;
    private Image r0, r1, r2, r3, r4, r5, o0,
    l0, l1, l2, l3, l4, l5;
    private String direction;
    private double fallSpeed;
    private double GRAVITY = 18;
    private double dx, dy;
    private double MAX_DX = 4;
    private double MAX_DY = 8;
    public Character (double charX, double charY) {
        this.centerX = charX;
        this.centerY = charY;
        dx = 0;
        dy = 0;
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

    public void handleMovement(int ic, double dt) {
        updateRunImage(ic);
        updateRunPosiiton(dt);
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
   
    public void updateRunPosiiton(double dt) {     
        switch(this.direction) {
            case "right": if (this.centerX < Game.CANVAS_WIDTH - image.getWidth()/2 - runSpeed * dt) {
                if(!dxIsOverMax(dx + runSpeed*dt)){
                    dx += runSpeed*dt;
                }
            }
            break;
            case "left": if(this.centerX > image.getWidth()/2 - runSpeed * dt) {
                if(!dxIsUnderMin (dx - runSpeed*dt)){
                    dx -= runSpeed*dt;
                }
            }
            break;
            case "up": if(this.centerY > image.getHeight()/2 - runSpeed * dt) {
                if (Game.getGroundUnderACharacter(this) != null) {
                    dy = -MAX_DY;
                }
                centerY -= 2;
            }
            break;
            case "down": if (this.centerY < Game.CANVAS_HEIGHT + image.getHeight()/2 + runSpeed * dt && Game.getGroundUnderACharacter(this) == null) {
            }
            break;
            case "none": dx = 0;
            break;
        }
        if(Game.getGroundUnderACharacter(this) == null){
            dy +=  GRAVITY * dt;
            centerY += dy;
            if(Game.getGroundUnderACharacter(this) != null) {
                Game.setCharOnGround(this, Game.getGroundUnderACharacter(this));
            }
        } else {
            dy = 0;
        }
        centerX += dx;
        image.setCenter(centerX, centerY);        
    }

    private boolean dxIsOverMax(double newDx) {
        if (newDx > MAX_DX) {
            return true;
        } else {
            return false;
        }
    }

    private boolean dxIsUnderMin(double newDx) {
        if (newDx < -MAX_DX) {
            return true;
        } else {
            return false;
        }
    }

    private boolean dyIsOverMax(double newDy) {
        if (newDy > MAX_DY) {
            return true;
        } else {
            return false;
        }
    }

    private boolean dyIsUnderMin(double newDy) {
        if (newDy < -MAX_DY) {
            return true;
        } else {
            return false;
        }
    }

    public double getCenterX() {
        return centerX;
    }

    public double getWidth() {
        return image.getWidth();
    }

    public double getHeight() {
        return image.getHeight();
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

    public void setCenterY(double y) {
        centerY = y;
    }
    
}
