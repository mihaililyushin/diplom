import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MMain extends JPanel implements ActionListener {

    JFrame frame;

    Timer timer = new Timer(15,this);

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

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                player.mouseClicked(e, player);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mouseMoved(MouseEvent e) {
               player.mouseMoved(e, player);
            }
        });
    }
    //----------------------------------------------загружаем картинки-----------------------------------------------------
//    GameImgLoader MapImg = new GameImgLoader();
    GameImgLoader playerImg = new GameImgLoader(player, GameImgLoader.PlayerColor.GREENP);
    //--------------------------------------------перерисовка графики в панеле---------------------------------------------
    public void paint (Graphics g){

//        System.out.println("позиция игрока X: " + player.mapX+ " Y: " + player.mapY);

        g.drawImage(Mmap.getInstance().getMap(), 0, 0,Mmap.getInstance().sizeX, Mmap.getInstance().sizeY, null);
        //рисуем фон

        //============Движение спрайта персонажа========================================================================
        g.drawLine(player.mapX,player.mapY,player.mouseX,player.mouseY);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affine = new AffineTransform();
        affine.rotate(Math.toRadians(player.angle), player.mapX,
                player.mapY);           // поворачиваем спрайт игрока на угол

        g2d.setTransform(affine);

        g.drawImage(playerImg.getPlayerImgAction(player),
                player.mapX - 20, player.mapY - 20, 40, 40, null);
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
        player.playerMouseMoving(player,false);
        player.move(player);
    }
}