import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;      //the File class
import java.util.*;    //the Scanner class
/*********************************************
* TrumpWin is a panel that is run when 
* the immigrant wins the game.
* It plays music, updates the data file, and
* has two buttons, exit, or play again
* @author Yuhei Hattori
* @verson 8.4.2016
*********************************************/
public class TrumpWin extends JPanel
{
   private BufferedImage myImage;
   private JPanel ButtonPanel;
   private JButton button1, button2;
   private Graphics buffer;
   private ImageIcon TWin, PlayAgain, Exit, stats;
   private JFrame frame, topFrame;
   private int IW, TW;
   public TrumpWin()throws Exception
   {
      Scanner infile = new Scanner(new File("data.txt"));
      int x=infile.nextInt();
      int y = infile.nextInt();
      infile.close();
      y++;
      PrintStream outfile = new PrintStream(new FileOutputStream("data.txt"));
      System.setOut(outfile);
      System.out.println(""+x);
      System.out.println(""+y);
      outfile.close();
      setLayout(new BorderLayout());
      myImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
      buffer = myImage.getGraphics();
      TWin = new ImageIcon("Pictures/TWin.png");
      buffer.drawImage(TWin.getImage(), 0,0, null);
      stats = new ImageIcon("Pictures/Stats.png");
      Exit = new ImageIcon("Pictures/Exit.png");
      ButtonPanel = new JPanel();
      ButtonPanel.setLayout(new GridLayout(1,1));
      button1=buttonMaker(button1, stats);
      button1.addActionListener(new Stats());
      ButtonPanel.add(button1);
      button2 = buttonMaker(button2, Exit);
      button2.addActionListener(new exit());
      ButtonPanel.add(button2);
      add(ButtonPanel, BorderLayout.SOUTH);
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   public JButton buttonMaker(JButton button, ImageIcon icon)
   {
      button=new JButton();
      button.setIcon(icon);
      button.setBorderPainted(false);
      button.setFocusPainted(false);
      button.setContentAreaFilled(false);
      button.setOpaque(true);
      button.setBackground(new Color(254,242,0));
      return button;
   }
      public class Stats implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         showStats();
      }
   }   private class exit implements ActionListener 
   {
      public void actionPerformed(ActionEvent e)
      {
      System.exit(0);
      } 
   }
       /************************************************
   * Displays Stats of the game
   ************************************************/
   public void showStats()
   {
      try{
         Scanner infile = new Scanner(new File("data.txt"));
         IW =infile.nextInt();
         TW = infile.nextInt();
         infile.close();
         Object[] options = {"Reset Stats","OK"};
         int n = JOptionPane.showOptionDialog(frame,
            "Immigrant Wins: "+IW+"\n Trump Wins: "+TW+"",
            "Game Statistics",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
         if(n==0)
         {
            PrintStream outfile = new PrintStream(new FileOutputStream("data.txt"));
      System.setOut(outfile);
      System.out.println(""+0);
      System.out.println(""+0);
      outfile.close();
         }
      }
      catch(Exception e){System.out.println("Oops");}
   }


}
