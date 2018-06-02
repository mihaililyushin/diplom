import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
public class test {
    public static void main(String args[]) throws Exception {
        try {
            JFrame frame = new JFrame("Rotation Test");
//            frame.setBounds(10,10,1008,756);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            BufferedImage bi = ImageIO.read(new File("lib/pic/player/GREENP/Stand.png"));
            Graphics2D g = (Graphics2D)bi.getGraphics();
            g.rotate(Math.toRadians(70),26,26);
            g.drawImage(bi, 0, 0, null);
            JLabel player = new JLabel(new ImageIcon(bi));
            frame.getContentPane().add(player);
//            g.drawRect();


            player.setBounds(0,0,100,100);
            frame.setVisible(true);
        } catch (IOException ex) {
            System.out.println("Exception");
        }
    }
}