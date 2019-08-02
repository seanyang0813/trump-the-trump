import java.awt.*;
import java.awt.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**************************************
* The Immigrants class is the class
* of the player in the game. It knows
* its x and y positions and can reset
* or respawn itself
* @author Sean Yang
* @version 8.3.2016
**************************************/   
public class Immigrants
{
   static private int myX;   // x and y coordinates of center, current time and life
   static private int myY;
   static private int myLife; //number of lifes
   static private int myTime; // time left
     /************************************
  * Default constructor for Immigrants, he 
  * will spawn on the left bottom corner of the 
  * map with 3 lifes 60 senconds.
  * @param x initial x coordinate
  * @param y initial y coordinate
  * @param myLife initial number of livess;
  * @param seconds initial seconds start with.
  ************************************/

   public Immigrants()     //default constructor
   {
      myX = 0;
      myY = 400;
      myLife=3;
      myTime=60;
   }
   /*********************************
   * Constrictor for Immigrants, x and y are
   * his location
   * @param x initial x coordinate
   * @param y initial y coordinate
   **********************************/    
   
   public Immigrants(int x, int y)
   {
      myX = x;
      myY = y;
      myLife=3;
      myTime=60;
        
   }
   /*********************************
   * Constrictor for Immigrants, x and y are
   * his location, life is the number of lives he has.
   * @param x initial x coordinate
  * @param y initial y coordinate
  * @param z initial number of lives
 

   **********************************/    
   
   public Immigrants(int x, int y, int z, int a)
   {
      myX = x;
      myY = y;
      myLife=z;
      myTime=a;
        
   }
    /**********************************
   * Returns Immigrant's x coordinate
   * @return myX
   *********************************/
   public static int getX() 
   { 
      return myX;
   }
    /**********************************
   * Returns Immigrant's y coordinate
   * @return myY
   *********************************/
   public static int getY()      
   { 
      return myY;
   }
    /**********************************
   * Returns Immigrant's life
   * @return myLife
   *********************************/
   public static int getLife()
   {
      return myLife;
   }
    /**********************************
   * Returns Immigrant's time
   * @return myTime
   *********************************/
   public static int getTime()
   {
      return myTime;
   }
     /**************************************
  * Sets Immigrant's x to the input value.
  * @param x      assigns x to myX
  ***************************************/
   public static void setX(int x)
   {
      myX = x;
   } 
   /**************************************
  * Sets Immigrant's y to the input value.
  * @param y      assigns y to myY
  ***************************************/
   public static void setY(int y)
   {
      myY=y;
   } 
    /**************************************
  * Change Immigrant's life to the input value
  * @param life assigns life to myLife
  ***************************************/
   public static void setLife(int life)
   {
      myLife = life;
   }
    /**************************************
  * Change Immigrant's time to input value
  * @param life assigns time to myTime
  ***************************************/
   public static void setTime(int time)
   {
      myTime=time;
   }
  /**************************************
   * just like Anivia immigrants respawns
   * @param Immigrant lose 1 life and respawn
   ***********************************/

   public static void respawn()
   {
      myX=0;
      myY=175;
      myTime=60;
      myLife--;
   }
}
    
    
