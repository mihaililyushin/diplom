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

    public MMain(JFrame frame){
        this.frame = frame;
        timer.start();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).keyReleased(e);
            }
        });

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).mouseClicked(e, MPlayer.getPlayer(MPlayer.PlayerColor.GREEN));
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
                MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).mouseMoved(e, MPlayer.getPlayer(MPlayer.PlayerColor.GREEN));
            }
        });
    }
    //----------------------------------------------загружаем картинки-----------------------------------------------------
//    GameImgLoader MapImg = new GameImgLoader();
//    GameImgLoader playerImg = new GameImgLoader(player, GameImgLoader.PlayerColor.GREENP);
    //--------------------------------------------перерисовка графики в панеле---------------------------------------------
    public void paint (Graphics g){

//        System.out.println("позиция игрока X: " + player.mapX+ " Y: " + player.mapY);

        g.drawImage(Mmap.getInstance().getMap(), 0, 0,Mmap.getInstance().sizeX, Mmap.getInstance().sizeY, null);
        //рисуем фон

//============Движение спрайта персонажа================================================================================
//        g.drawLine(MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).mapX,MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).mapY,
//                MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).fireX,MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).fireY);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform affine = new AffineTransform();
        affine.rotate(Math.toRadians(MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).angle), MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).mapX,
                MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).mapY);           // поворачиваем спрайт игрока на угол

        g2d.setTransform(affine);
        g.drawImage(MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).getPlayerImgAction(MPlayer.getPlayer(MPlayer.PlayerColor.GREEN)),
                MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).mapX - 20,
                MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).mapY - 20, 40, 40, null);
        MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).spriteIndx++;

        if (MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).spriteIndx > 5) {
            MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).spriteIndx = 0;
        }

        //=============================================================================================================
    }
//-----------------------------------------------действия по таймеру---------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).playerMouseMoving(MPlayer.getPlayer(MPlayer.PlayerColor.GREEN),false);
        MPlayer.getPlayer(MPlayer.PlayerColor.GREEN).move(MPlayer.getPlayer(MPlayer.PlayerColor.GREEN));


    }
}