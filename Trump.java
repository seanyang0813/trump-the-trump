
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
/*************************************
* The Trump class is the class that 
* controls the sprite trump in the 
* GamePanel, it contains its x and y
* positions
*************************************/
public class Trump
{
   static private int myX; //Location
   static private int myY;
   static private int guards; //number of guards at his disposal
   
  /************************************
  * Default constructor for Trump, he 
  * will spawn on the center of the 
  * right edge of the map with 0 
  * guards at his disposal.
  * @param x initial x coordinate
  * @param y initial y coordinate
  * @param z initial number of guards
  ************************************/
   public Trump()
   {
      myX = 0; //Right edge
      myY = 0; //Center
      guards = 0;
   }
      /*********************************
   * Constrictor for Trump, x and y are
   * his location
   * @param x initial x coordinate
   * @param y initial y coordinate
   **********************************/    
   
   public Trump(int x, int y)
   {
      myX = x;
      myY = y;
      guards = 0;
        
   }
   /*********************************
   * Constrictor for Trump, x and y are
   * his location, z is the number of
   * guards he has.
   * @param x initial x coordinate
  * @param y initial y coordinate
  * @param z initial number of guards
   **********************************/
   public Trump(int x, int y, int z)
   {
     myX = x;
     myY = y;
     guards = z;
   }
   /**********************************
   * Returns Trump's x coordinate
   * @return myX
   *********************************/
   public static int getX() 
   { 
      return myX;
   }
   /**********************************
   * Returns Trump's y coordinate
   * @return myY
   *********************************/
   public static int getY()      
   { 
      return myY;
   }
      /**********************************
   * Returns number of Trump's guards
   * @return guards
   *********************************/
   public static int getGuards()
   {
      return guards;
   }
  /**************************************
  * Sets Trump's x to the input value.
  * @param x      assigns x to myX
  ***************************************/
  
  public static void setX(int x)
   {
      myX = x;
   } 
   /**************************************
  * Sets Trump's y to the input value.
  * @param y      assigns y to myX
  ***************************************/
   public static void setY(int y)
   {
      myY = y;
   } 
   /**************************************
  * Adds input value to the number of guards trump has
  * @param z   adds z to guards
  ***************************************/
  public static void addGuards(int z)
  {
   guards+=z;
  }
  /**************************************
   * moves Trump to the new xy coordinate
   * @param moves trump to input location
   ***********************************/
   public static void move(int x, int y) //move command, x spaces left/right and y spaces up/down, add boundaries in the main game with if statements
                                  //if we want a moving animation we will have to make it ourselves, just change the angle of his feet
                                  //collison should go into the main game, not here
   {
      myX += x;
      myY += y;
      //repaint();                 //idk how we are going to show trump chainging locations, whatever command youre using in the main program
   }     
  //in the game program, copy and paste later
   // public void placeGuard(int x)
   // {
      // guard a = new Guard (x+5, 1);  //whatever the command to make a new guard is, 
   //                                   //dont forget listener and set x to whatever number key was pressed
   // }
   // public void flashLight(int x, int y, int dir)  //x and y are trump's positions, dir is direction he is facing
//                                                   //and the direction he will shine, 1 being up, 2 being right, 
//                                                   //3 being down and 4 being right
//    {
//       switch(dir)
//       {
//          case 1: for(int z = y; z < y+3; z++) //assuming light will shine 3 spaces, we can change that
//          {  
//             //paintRedToShowDanger            //whatever we are doing
//             //java.util.concurrent.TimeUnit   //dont forget to import java.util.concurrent.TimeUnit
               //paintYellowAndKillImmigrant
//          }
//          break;
//          case 2: for(int z = x; z < x+5; z++)
 //{  
//             //paintRedToShowDanger            //whatever we are doing
//             //java.util.concurrent.TimeUnit   //dont forget to import java.util.concurrent.TimeUnit
               //paintYellowAndKillImmigrant
//          }
//          break;
//          case 3: for(int z = y; z < y-3; z++) //assuming light will shine 3 spaces, we can change that
//          {  
//             //paintRedToShowDanger            //whatever we are doing
//             //java.util.concurrent.TimeUnit   //dont forget to import java.util.concurrent.TimeUnit
               //paintYellowAndKillImmigrant
//          }
//          break;
//          case 4: for(int z = x; z < x-5; z++)
 //{  
//             //paintRedToShowDanger            //whatever we are doing
//             //java.util.concurrent.TimeUnit   //dont forget to import java.util.concurrent.TimeUnit
               //paintYellowAndKillImmigrant
//          }
//          break;
 

}
