package application;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public abstract class Sprite
{
    private Image image;
    private double positionX;
    private double positionY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    protected int flip;
    
    public Sprite()
    {
    	flip = 1;
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
    }
    

    public void setImage(Image i)
    {
        image = i;
        width = image.getWidth();
        height = image.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }
    
    public void setImage(String filename, double width, double height, boolean preserveRatio, boolean smooth)
    {
        Image i = new Image(filename, width, height, preserveRatio, smooth);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
    	gc.drawImage(image, positionX, positionY, flip*image.getWidth(), image.getHeight());

    	// show hitbox
//    	if(flip == 1) {
//    		gc.strokeRect(positionX, positionY, image.getWidth(), image.getHeight());    		
//    	}
//    	if(flip == -1) {
//    		gc.strokeRect(positionX-image.getWidth(), positionY, image.getWidth(), image.getHeight());
//    	}
    }

    public Rectangle2D getBoundary()
    {
    	if(flip == 1) {
    		return new Rectangle2D(positionX, positionY, image.getWidth(), image.getHeight());
    	} else {
    		return new Rectangle2D(positionX-image.getWidth(), positionY, image.getWidth(), image.getHeight());    		
    	}
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public int getFlip() {
		return flip;
	}

	public void setFlip(int flip) {
		this.flip = flip;
	}


	public double getWidth() {
		return width;
	}


	public double getHeight() {
		return height;
	}
}