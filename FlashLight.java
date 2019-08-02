import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*************************************
* The FlashLight Class is used to show
* Trump's flashlight beam that can 
* "kill" immigrants. 
* @author Yuhei Hattori
* @Version 8.3.16
*****************************************/
public class FlashLight
{
   private static int myY;
   private static int myX = 0;
   private static boolean firing;
   private static boolean using;
   private static ImageIcon myImage;
   private static ImageIcon warning = new ImageIcon("Pictures/Warning.png");
   private static ImageIcon fire = new ImageIcon("Pictures/Laser.png");
   private static ImageIcon blank = new ImageIcon("Pictures/50.png");
   /*******************************
   * Default Constructor for Flashlight.
   * It spawns at location 0,0, and it 
   * is not firing or being used, ,and 
   * the image is blank.
   * @param myX initial x coordinate
   * @param myY initial y coordinate
   ***********************************/
   public FlashLight()
   {
      myX=0;
      myY = 0;
      firing=false;
      using=false;
      myImage=blank;
   }
  /*************************************
  * Constructor for FlashLight. x will 
  * always be 0, but you can 
  * specify the y location of the 
  * flashlight.
  * @param myX initial x coordinate
  * @param myY initial y coordinate
  *************************************/
   public FlashLight(int y)
   {
      myX=0;
      myY = y;
      firing=false;
      using=false;
   }
   
  /*******************************
  * Returns the correct ImageIcon
  * for the flashlight.
  * @return myImage
  *****************************/
   public ImageIcon getImageIcon()
   {
      return myImage;
   }
   /***************************
   * Returns wether or not 
   * the flashlight is firing.
   * @return isFiring
   *****************************/
   public boolean isFiring()
   {
      return firing;
   }
   /******************************
   * Returns wether or not the
   * flaslight is being used.
   * @return isUsing
   **************************/
   public boolean isUsing()
   {
      return using;
   }   
  /******************************
  * Sets the image to a blank image,
  * and sets isFiring and 
  * isUsing to false.
  * @param isFiring     sets False
  * @param isUsing      sets False
  * @param myImage      sets blank
  *******************************/
  
   public void blank()
   {
      myImage=blank;
      firing=false;
      using=false;
   }
   /**************************
   Sets the image to the warning
   * image, and sets isFiring
   * to false and isUsing to
   * true.
   * @param isFiring    sets False
   * @param isUsing     sets True
   * @param myImage     sets warning
   *****************************/
   public void prepare()
   {
      myImage=warning;
      firing=false;
      using=true;
   }
  /**************************
   Sets the image to the firing
   * image, and sets isFiring
   * and isUsing to
   * true.
   * @param isFiring    sets True
   * @param isUsing     sets True
   * @param myImage     sets fire
   *****************************/
   public void fire()
   {
      firing=true;
      using=true;
      myImage = fire;
   }
}
   




