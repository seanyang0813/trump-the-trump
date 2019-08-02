import java.awt.*;
import java.awt.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.awt.event.*;
import javax.swing.*;
/**************************************
* Guards are objects that move up and
* down in the game screen, They have 
* starting x and y positions and have 
* ImageIcons that are painted in the 
* GamePanel
* @author Sean Yang
* @version 8.3.2016
**************************************/   
public class Guard
{
   private int myX; //Location
   private int myY;
   private ImageIcon myImage;
   private ImageIcon WU = new ImageIcon("Pictures/WU.png");
   private ImageIcon WD = new ImageIcon("Pictures/WD.png");
   private boolean U,D;
   /************************************
  * Default constructor for Guard, he 
  * will spawn on the center of the 
  * right edge of the map with 0 
  * guards at his disposal.
  * @param x initial x coordinate
  * @param y initial y coordinate
   ************************************/      
   public Guard()     
   {
      myX = 0;
      myY = 400;
      myImage=WU;   
      U=false;
      D=true;  
   }
     /*********************************
   * Constrictor for Immigrants, x and y are
   * his location, life is the number of lives he has.
   * @param x initial x coordinate
  * @param y initial y coordinate
   **********************************/  
   public Guard(int x, int y)
   {
      myX = x;
      myY = y;
      if(y<=325)
      {   
         myImage=WD;
         U=false;
         D=true;
      }
      if(y>325)
      {
         myImage=WU;  
         U=true;
         D=false;  
      }       
   }
    /**********************************
   * Returns Guard's x coordinate
   * @return myX
   *********************************/
   public int getX() 
   { 
      return myX;
   }
    /**********************************
   * Returns Guard's y coordinate
   * @return myY
   *********************************/
   public int getY()      
   { 
      return myY;
   }
          /**********************************
   * Returns Guard's ImageIcon
   * @return ImageIcon
   *********************************/
   public ImageIcon getImageIcon()      
   { 
      return myImage;
   }
   /**************************************
  * Sets guard's x to the input value.
  * @param x      assigns x to myX
  ***************************************/
   public void setX(int x)
   {
      myX = x;
   } 
   /**************************************
  * Sets Guard's y to the input value.
  * @param y      assigns y to myY
  ***************************************/
   public void setY(int y)
   {
      myY=y;
   } 
   
   /***************************
   * Moves the guard up if 
   * boolean u is true, down if 
   * not. Upon reaching Y 
   * coordinate of 175 or 475,
   * switch directions.
   ****************************/
   public void move()
   {
      if(D)
      {
         myImage = WD;
         myY+=1;
         if(myY>=525-50)
         {
            D=false;
            U=true;
         }
      }
      if(U)
      {
         myImage = WU;
         myY-=1;
         if(myY<=175)
         {
            D=true;
            U=false;
         }
      }
      
   }
}