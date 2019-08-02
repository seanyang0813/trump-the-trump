import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
/*************************************
* The GameDriver Class runs the game
* @author Jack Liu
* @Version 8.4.16
*****************************************/
public class GameDriver
{
   static JFrame frame;
   public static void main(String[] args) throws Exception
   {
      frame = new JFrame("Start");
      frame.setSize(1000, 1000);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new StartPanel());
      frame.setVisible(true);
      frame.setResizable(false);
   }
}