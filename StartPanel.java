import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
/***************************************************
* The StartPanel is the panel that is shown when the
* driver is run. It contains three buttons that show
* the high scores, instructions, and credits. It is 
* also the thing that runs the main game.
* @author Jack Liu
* @version 7.31.2016
****************************************************/
public class StartPanel extends JPanel
{
   
   private BufferedImage myImage;
   private static Graphics buffer;
   static ImageIcon Start, Instructions, Play, Restore, Credits, InsText, CreText, Back;
   JButton highScores, instructions, credits, game;
   JPanel ButtonPanel, BPT, BPB;
   static JButton A, B, C, D, E, Main;
   javax.swing.Timer timer;
   static int x, IW, TW;
   static JFrame frame, topFrame;
   public StartPanel()throws Exception
   {
      File f = new File("data.txt");
      timer = new javax.swing.Timer(0, new TListener());
      timer.start();
      ButtonPanel = new JPanel();
      ButtonPanel.setLayout(new GridLayout(2, 1));
      BPT = new JPanel();
      BPB = new JPanel();
      BPT.setLayout(new GridLayout(1,3));
      BPB.setLayout(new GridLayout(1,2));
      setLayout(new BorderLayout());
      myImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
      buffer = myImage.getGraphics();	
      Start = new ImageIcon("Pictures/Start.jpeg");
      Instructions = new ImageIcon("Pictures/Instructions.png");
      Play = new ImageIcon("Pictures/Play.png");
      Restore = new ImageIcon("Pictures/Restore.png");
      Credits = new ImageIcon("Pictures/Credits.png");
      InsText = new ImageIcon("Pictures/InsText.png");
      CreText = new ImageIcon("Pictures/CreText.png");
      Back = new ImageIcon("Pictures/Back.png");
      A=buttonMaker(A, "Play", new Game());
      Main=buttonMaker(Main, "Back", new Back());
      B=buttonMaker(B, "Stats", new Stats());
      C=buttonMaker(C, "Instructions", new Instructions());
      E=buttonMaker(E, "Credits", new Credits());
      BPT.add(A);
      BPT.add(Main);
      BPT.add(B);
      BPB.add(C);
      BPB.add(E);
      if(f.exists())
         B.setEnabled(true);
      else
         B.setEnabled(false);
      Main.setEnabled(false);
      ButtonPanel.add(BPT);
      ButtonPanel.add(BPB);
      add(ButtonPanel, BorderLayout.SOUTH);
      buffer.drawImage(Start.getImage(), 0,0, null);
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   public class TListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         repaint();
      }
   }
   public class Game implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         startGame();
      }
   }
   public static class Stats implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         showStats();
      }
   }
   public static class Back implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         buffer.drawImage(Start.getImage(), 0,0,null);
         Main.setEnabled(false);
         C.setEnabled(true);
         E.setEnabled(true);
      }
   }
   public static class Instructions implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         showInstructions();
      }
   }
   public static class Credits implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         showCredits();
      }
   }
   public static JButton buttonMaker(JButton button, String icon, ActionListener l)
   {
      ImageIcon Picture = new ImageIcon("Pictures/"+icon+".png");
      button = new JButton();
      button.setIcon(Picture);
      button.setBorderPainted(false);
      button.setFocusPainted(false);
      button.setContentAreaFilled(false);
      button.setOpaque(true);
      button.setBackground(Color.BLACK);
      button.addActionListener(l);
      return button;
   }
   /************************************************
   * Displays instructions of the game
   ************************************************/
   public static void showInstructions()
   {
      buffer.drawImage(InsText.getImage(), 0,0, null);
      Main.setEnabled(true);
      C.setEnabled(false);
      E.setEnabled(true);
   }
   /************************************************
   * Displays credits of the game
   ************************************************/
   public static void showCredits()
   {
      buffer.drawImage(CreText.getImage(), 0,0, null);
      Main.setEnabled(true);
      E.setEnabled(false);
      C.setEnabled(true);
   }
   /************************************************
   * Displays Stats of the game
   ************************************************/
   public static void showStats()
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
   /************************************************
   * Opens new JFrame to start the game, GamePanel
   * has all the methods needed for gameplay
   ************************************************/
   public void startGame()
   {
      frame = new JFrame("Game");
      frame.setSize(1000, 521);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GamePanel());
      frame.setVisible(true);
      topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
      topFrame.dispose();
   }
}
