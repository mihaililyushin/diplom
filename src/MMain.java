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

    Timer timer = new Timer(8,this);

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
    GameImgLoader MapImg = new GameImgLoader();
    GameImgLoader playerImg = new GameImgLoader(player, GameImgLoader.PlayerColor.GREENP);
//--------------------------------------------перерисовка графики в панеле---------------------------------------------
    public void paint (Graphics g){

        g.setColor(new Color(0,0,0));

        g.fillRect(0, 0,800,600);



        System.out.println("позиция игрока X: " + player.getMapX()+ " Y: " + player.getMapY());

        g.drawImage(MapImg.getMap(), player.getMapX(), player.getMapY(),2048, 2048, null);
        //рисуем фон

        //============Движение спрайта персонажа========================================================================
        g.drawImage(playerImg.getPlayerImgAction(player),
                (frame.getWidth() / 2), (frame.getHeight() / 2), 40, 40, null);
        player.spriteIndx++;
        if (player.spriteIndx > 5) {
            player.spriteIndx = 0;
        }
        //=============================================================================================================
    }
//-----------------------------------------------действия по таймеру---------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        player.move(5);
    }
}
