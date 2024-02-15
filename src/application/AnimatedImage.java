// https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development/blob/master/AnimatedImage.java
package application;

import javafx.scene.image.Image;

public class AnimatedImage
{
    // assumes animation loops,
    //  each image displays for equal time
    protected Image[][] frames;
    protected double duration;
    
    public Image getFrame(double time, int status)
    {
        int index = (int)((time % (frames[status].length * duration)) / duration);
//        System.out.println("Setting frame "+index);
        return frames[status][index];
    }
}