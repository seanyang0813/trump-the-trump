import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import sun.audio.*;
import javax.sound.sampled.*;
/***************************************************
* The Gameboard controls most of the main program
* It has both players and creates guards. It knows
* the positions of all sprites, calculates distance
* and makes decisions based on them. It also plays
* sound when appropriate.
* @author Jack Liu
* @version 8.2.2016
****************************************************/
public class GamePanel extends JPanel
{
   private static JFrame frame;
   private static BufferedImage myImage;
   private static Graphics buffer;
   private static ImageIcon Board, Immigrant, Trump;
   private static Timer timer , Ltimer;
   private static Immigrants player;
   private static Trump trump;
   private static Clip audioClip;
   private boolean once;
   private static Guard[] guards = new Guard[10];
   private static Guard finalBoss;
   private static int Time, WTime, LTime, CD;
   private static FlashLight fl;
   private JFrame topFrame;
   private JButton close;
   private static AudioInputStream aS1, aS2;
   private static Clip aC1, aC2;
   /************************************************
   * Starts playing music that is continuously 
   * played while the game is run
   ************************************************/
   public GamePanel()
   {
      start();
      addKeyListener(new Key());
      setFocusable(true);
      timer = new Timer(3, new TListener());
      timer.start();
      Ltimer = new Timer(100, new LListener());
      Ltimer.start();
   }
   /************************************************
   *A listener continously running to run the moving 
   *objects in panel
   ************************************************/
   private class TListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         for(int x = 0; x < guards.length; x++)
         {
            guards[x].move();
            if(distance(player, guards[x])<50)
            {
               collide();
               }
         }
         finalBoss.move();
         if(distance(player, finalBoss)<50)
            collide();
         if(player.getX()==trump.getX() && player.getY()==trump.getY())
         {
            collide();
         }
         if(player.getX()==950 && !once)
         {
            immigrantWin();
            once=true;
            aC1.stop();
            aC1.close();
            try{aS1.close();}
            catch(Exception x){}
         }
         if(player.getY()==trump.getY()&&fl.isFiring())
         {
            collide();
         }
         if(player.getLife() == 0 && !once)
         {
            trumpWin();
            once=true;
            aC1.stop();
            aC1.close();
            try{aS1.close();}
            catch(Exception x){}
         }
         buffer.drawImage(Board.getImage(), 0,0, null);
         buffer.drawImage(Immigrant.getImage(), player.getX(), player.getY(), null);
         buffer.drawImage(Trump.getImage(), trump.getX(), trump.getY(), null);
         for(int x = 0; x < guards.length; x ++)
         {
            buffer.drawImage(guards[x].getImageIcon().getImage(), guards[x].getX(), guards[x].getY(), null);
         }
         repaint();
         buffer.drawImage(finalBoss.getImageIcon().getImage(), finalBoss.getX(), finalBoss.getY(), null);
         buffer.drawImage(fl.getImageIcon().getImage(), 0, trump.getY(), null);
         if(Time>WTime&&Time<LTime)
            fl.fire();
         if(Time>LTime)
            fl.blank();
      }
   }
   /************************************************
   *Timer controls timer of the laser
   ************************************************/
   private class LListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         Time++;
      }
   }
    /************************************************
   * Key Listener controls movement and limit
   *of immigrants and trump.    
   ************************************************/
   private static class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode() == KeyEvent.VK_W && player.getY()>175)
         {
            player.setY(player.getY()-50);
         }
         if(e.getKeyCode() == KeyEvent.VK_S && player.getY()<525-50)
         {
            player.setY(player.getY()+50);
         }
         if(e.getKeyCode() == KeyEvent.VK_A && player.getX()>0)
         {
            player.setX(player.getX()-50);
         }
         if(e.getKeyCode() == KeyEvent.VK_D && player.getX()<1000-50)
         {
            player.setX(player.getX()+50);
         }
         if(e.getKeyCode() == KeyEvent.VK_UP && trump.getY()>175 && !fl.isUsing())
         {
            trump.setY(trump.getY()-50);
         }
         if(e.getKeyCode() == KeyEvent.VK_DOWN && trump.getY()<525-50 && !fl.isUsing())
         {
            trump.setY(trump.getY()+50);
         }
         if(e.getKeyCode() == KeyEvent.VK_ENTER)
         {
            WTime=Time+7;
            LTime=Time+10;
            fl.prepare();
         }
      }
   }
   /************************************************
   * paint buffered image myImage
   ************************************************/
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   /************************************************
   * Starts playing music that is continuously 
   * played while the game is run
   ************************************************/
   public static void playMainTheme()
   {
      try{
         File audioFile = new File("main.wav");
         aS1 = AudioSystem.getAudioInputStream(audioFile);
         AudioFormat format = aS1.getFormat();
         DataLine.Info info = new DataLine.Info(Clip.class, format);
         aC1 = (Clip) AudioSystem.getLine(info);
         aC1.open(aS1);
         aC1.loop(Clip.LOOP_CONTINUOUSLY);
         aC1.start();
      }
      catch(Exception e){System.out.println("Oops");}
   }
   /************************************************
   * Starts up the game by creating guards, both
   * players, music, and background pictures
   ************************************************/
   public static void createStartingBoard()
   {
      myImage = new BufferedImage(1000, 521, BufferedImage.TYPE_INT_RGB);
      buffer = myImage.getGraphics();
      Board = new ImageIcon("Pictures/bgd.png");
      Immigrant = new ImageIcon("Pictures/Immigrants.png");
      Trump = new ImageIcon("Pictures/Trump.png");
      buffer.drawImage(Board.getImage(), 0,0, null);
      player = new Immigrants(0,175);
      buffer.drawImage(Immigrant.getImage(), player.getX(), player.getY(), null);
      trump = new Trump(900, 175);
      buffer.drawImage(Trump.getImage(), trump.getX(), trump.getY(), null);
      for(int x = 0; x < guards.length; x++)
      {
         guards[x]=createGuard();
      }
      finalBoss = new Guard(850, (int)(Math.random()*300+175));
      fl = new FlashLight();
      CD=999999;
      WTime=999999;
      LTime=999999;
      System.gc();
   }
   /************************************************
   * Plays sound file when a player is killed
   ************************************************/
   public static void playKillSound()
   {
      try{aC2.stop();
      aC2.close();
      aS2.close();}
      catch(Exception x){}
      try{
         File audioFile = new File("death.wav");
         aS2 = AudioSystem.getAudioInputStream(audioFile);
         AudioFormat format = aS2.getFormat();
         DataLine.Info info = new DataLine.Info(Clip.class, format);
         Clip aC2 = (Clip) AudioSystem.getLine(info);
         aC2.open(aS2);
         aC2.start();
      }
      catch(Exception e){System.out.println("Oops");}
   }
   /************************************************
   * returns a guard at specified x and y positions
   * @param x    x coordinate of guard
   * @param y    y coordinate of guard
   * @returns    guard
   ************************************************/
   public static Guard createGuard()
   {
      return new Guard((int)(Math.random()*16+1)*50,(int)(Math.random()*300+175));
   }
   /************************************************
   * Does everything needed at the start of the game
   ************************************************/
   public static void start()
   {
      playMainTheme();
      createStartingBoard();
   }
   /***********************************************
   * Calculates distance between different objects
   ***********************************************/
   public static int distance(Immigrants p, Guard g)
   {
      if(p.getX()==g.getX())
         return Math.abs(g.getY()-p.getY());
      else
         return 999;
   }
   /************************************************
   * What happenes when two sprites collide
   ************************************************/
   public static void collide()
   {
      playKillSound();
      reset(player);
      reset(trump);
      fl.blank();
      Time=0;
      WTime=999999;
      LTime=999999;
   }
   /************************************************
   * Resets position of the player
   ************************************************/
   public static void reset(Immigrants p)
   {
      p.respawn();
   }
   /************************************************
   * Resets position of Trump
   ************************************************/
   public static void reset(Trump t)
   {
      t.setX(900);
      t.setY(175);
   }
   public void immigrantWin()
   {
      try{
         frame = new JFrame("Immigrant Win");
         frame.setSize(1000, 1000);
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new ImmWin());
         frame.setVisible(true);
      }
      catch(Exception e){System.out.println("Oops");}
      topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
      topFrame.dispose();
   }
   public void trumpWin()
   {
      try{
         frame = new JFrame("Trump Win");
         frame.setSize(1000, 1000);
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new TrumpWin());
         frame.setVisible(true);
      }
      catch(Exception e){System.out.println("Oops");}
   
      topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
      topFrame.dispose();
   }
   private static class Closer implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         frame.dispose();
      }
   }
}