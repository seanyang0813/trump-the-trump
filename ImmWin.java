import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;    //the Scanner class
import java.awt.image.BufferedImage;
import java.io.*; 
import javax.sound.sampled.*;
/*********************************************
* ImmWin (Immigrant Win) is a panel that is 
* run when the immigrant wins the game.
* It plays music, updates the data file, and
* has two buttons, exit, or play again
*********************************************/
public class ImmWin extends JPanel
{
   private BufferedImage myImage;
   private JPanel ButtonPanel;
   private JButton button1, button2;
   private Graphics buffer;
   private ImageIcon TWin, PlayAgain, Exit;
   private JFrame frame, topFrame;
   private static Clip aC2;
   private static AudioInputStream aS2;
   public ImmWin()throws Exception
   {
      playWinSound();
      Scanner infile = new Scanner(new File("data.txt"));
      int x=infile.nextInt();
      int y = infile.nextInt();
      infile.close();
      x++;
      PrintStream outfile = new PrintStream(new FileOutputStream("data.txt"));
      System.setOut(outfile);
      System.out.println(""+x);
      System.out.println(""+y);
      outfile.close();
      setLayout(new BorderLayout());
      myImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
      buffer = myImage.getGraphics();
      TWin = new ImageIcon("Pictures/ImmWin.png");
      buffer.drawImage(TWin.getImage(), 0,0, null);
      PlayAgain = new ImageIcon("Pictures/PlayAgain.png");
      Exit = new ImageIcon("Pictures/Exit.png");
      ButtonPanel = new JPanel();
      ButtonPanel.setLayout(new GridLayout(1,1));
      button1 = buttonMaker(button1, PlayAgain);
      button1.addActionListener(new Game());
      ButtonPanel.add(button1);
      button2 = buttonMaker(button2, Exit);
      button2.addActionListener(new exit());
      ButtonPanel.add(button2);
      add(ButtonPanel, BorderLayout.SOUTH);
      //aC2.close();
      //aC2.stop();
      //aS2.close();
   }
   /********************************************************
   * Draws a buffered image onto the panel
   ********************************************************/
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   /********************************************************
   * Method that makes making multiple buttons easier and
   * faster, and making them all look alike
   * @param button   JButton you want to change appearance
   * @param icon     ImageIcon that is shown on the button
   * @return JButton modified JButton 
   ********************************************************/
   public JButton buttonMaker(JButton button, ImageIcon icon)
   {
      button=new JButton();
      button.setIcon(icon);
      button.setBorderPainted(false);
      button.setFocusPainted(false);
      button.setContentAreaFilled(false);
      button.setOpaque(true);
      button.setBackground(new Color(230,255,5));
      return button;
   }
   /************************************************
   * Opens a new JFrame with the GamePanel when triggered
   ************************************************/
   private class Game implements ActionListener 
   {
      public void actionPerformed(ActionEvent e)
      {
         frame = new JFrame("Game");
         frame.setSize(1000, 525);
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new GamePanel());
         frame.setVisible(true);
         close();
      } 
   }
   /************************************************
   * Closes the program when action is triggered
   ************************************************/
   private class exit implements ActionListener 
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      } 
   }
   /************************************************
   * Closes JFrame the panel is in
   ************************************************/
   public void close()
   {
      topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
      topFrame.dispose();
   }
   /************************************************
   * Plays sound file when a player wins
   ************************************************/
   public static void playWinSound()
   {
      try{
         File audioFile = new File("win.wav");
         aS2 = AudioSystem.getAudioInputStream(audioFile);
         AudioFormat format = aS2.getFormat();
         DataLine.Info info = new DataLine.Info(Clip.class, format);
         Clip aC2 = (Clip) AudioSystem.getLine(info);
         aC2.open(aS2);
         aC2.start();
      }
      catch(Exception e){System.out.println("Oops");}
   }
}
