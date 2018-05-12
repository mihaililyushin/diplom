import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MMain extends JPanel implements ActionListener {

    JFrame frame;
    Image Space,Map;
    int Pindx = 0;

    Image[] DPlayer = new Image[7];
    Image[] UPlayer = new Image[7];
    Image[] RPlayer = new Image[7];
    Image[] LPlayer = new Image[7];
    String LastMovePlayer = "UP";        //последнее состояние движения

    Timer timer = new Timer(80,this);

    MPlayer player = new MPlayer();

    public MMain(JFrame frame){
        this.frame = frame;
        timer.start();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });
    }
//----------------------------------------------загружаем картинки-----------------------------------------------------
    private void ImageLoader(){
        try {
            Space = ImageIO.read(new File("lib/pic/backg/space.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Map = ImageIO.read(new File("lib/pic/backg/testBack.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 7 ; i++) {
            try {
                UPlayer[i] = ImageIO.read(new File("lib/pic/player/playU" + String.valueOf(i+1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 7 ; i++) {
            try {
                DPlayer[i] = ImageIO.read(new File("lib/pic/player/playD" + String.valueOf(i+1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 7 ; i++) {
            try {
                RPlayer[i] = ImageIO.read(new File("lib/pic/player/playR" + String.valueOf(i+1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 7 ; i++) {
            try {
                LPlayer[i] = ImageIO.read(new File("lib/pic/player/playL" + String.valueOf(i+1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//--------------------------------------------перерисовка графики в панеле---------------------------------------------
    public void paint (Graphics g){
        ImageLoader();
//        g.drawImage(Space, player.getMapX(), player.getMapY(),frame.getWidth(), frame.getHeight(), null);
        g.drawImage(Map, player.getMapX(), player.getMapY(),2048, 2048, null);
        //рисуем фон
        switch (player.PAction){
            case "UP":
                g.drawImage(UPlayer[Pindx],(frame.getWidth()/2),(frame.getHeight()/2),100,100,null);
                Pindx++;
                if (Pindx > 6) {
                    Pindx = 0;
                }
                LastMovePlayer = "UP";
                break;
            case "DOWN":
                g.drawImage(DPlayer[Pindx],(frame.getWidth()/2),(frame.getHeight()/2),100,100,null);
                Pindx++;
                if (Pindx > 6) {
                    Pindx = 0;
                }
                LastMovePlayer = "DOWN";
                break;
            case "RIGHT":
                g.drawImage(RPlayer[Pindx],(frame.getWidth()/2),(frame.getHeight()/2),100,100,null);
                Pindx++;
                if (Pindx > 6) {
                    Pindx = 0;
                }
                LastMovePlayer = "RIGHT";
                break;
            case "LEFT":
                g.drawImage(LPlayer[Pindx],(frame.getWidth()/2),(frame.getHeight()/2),100,100,null);
                Pindx++;
                if (Pindx > 6) {
                    Pindx = 0;
                }
                LastMovePlayer = "LEFT";
                break;
            case "NONE":
                switch (LastMovePlayer){
                    case "UP":
                        g.drawImage(UPlayer[0],(frame.getWidth()/2),(frame.getHeight()/2),100,100,null);
                        break;
                    case "DOWN":
                        g.drawImage(DPlayer[0],(frame.getWidth()/2),(frame.getHeight()/2),100,100,null);
                        break;
                    case "RIGHT":
                        g.drawImage(RPlayer[0],(frame.getWidth()/2),(frame.getHeight()/2),100,100,null);
                        break;
                    case "LEFT":
                        g.drawImage(LPlayer[0],(frame.getWidth()/2),(frame.getHeight()/2),100,100,null);
                        break;
                }
                break;
        }

    }
//-----------------------------------------------действия по таймеру---------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        player.move();
    }
}
