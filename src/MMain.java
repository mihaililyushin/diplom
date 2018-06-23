import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class MMain extends JPanel implements ActionListener {

    JFrame frame;

    Timer timer = new Timer(24,this);

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
    GameImgLoader playerImg = new GameImgLoader(player, GameImgLoader.PlayerColor.GREEN);
    //--------------------------------------------перерисовка графики в панеле---------------------------------------------
    public void paint (Graphics g){

        g.drawImage(Mmap.getInstance().getMap(), 0, 0,Mmap.getInstance().sizeX, Mmap.getInstance().sizeY, null);
        //рисуем фон

        //============Движение спрайта персонажа========================================================================
       // g.drawLine(player.mapX,player.mapY,player.mouseX,player.mouseY);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old_affine = g2d.getTransform();
        AffineTransform affine = new AffineTransform();
        affine.rotate(Math.toRadians(player.angle + 90), player.mapX,
                player.mapY);           // поворачиваем спрайт игрока на угол

        g2d.setTransform(affine);

        g.drawImage(playerImg.getPlayerImgAction(player),
                player.mapX - 20, player.mapY - 20, 40, 40, null);
        player.spriteIndx++;
        if (player.spriteIndx > 5) {
            player.spriteIndx = 0;

        }

        //=================Анимация выстрела============================================================================
        if(player.bullet != null && player.bullet.spriteIndx != 0){
            g.drawImage(player.ShootPlayer[player.bullet.spriteIndx - 1],
                    player.mapX - 20, player.mapY - 20, 40, 40, null);
            player.bullet.spriteIndx--;
        }
        g2d.setTransform(old_affine);
        //==============Движение пули===================================================================================
        if(player.bullet != null) {
            g.drawImage(player.bulletImg,
                    player.bullet.mapX, player.bullet.mapY, 5, 5, null);
            player.bullet.livetime--;
            if(player.bullet.livetime == 0){
                player.bullet = null;
            }
        }
        //-------------Бум---------------------------
        if(player.boomIndx >= 0) {
            g.drawImage(player.Boom[5 - player.boomIndx], player.shootX-20, player.shootY-20, 50, 50, null);
        player.boomIndx--;
        }

    }
    //-----------------------------------------------действия по таймеру---------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        player.PlayerMoving(player,false);
        if(player.bullet != null) {
            player.bullet.bulletFly(player.bullet);
            Mmap.getInstance().Check_X_and_Y_bull(player.bullet);
            if(player.bullet.boom){
                player.shootX = player.bullet.moveX;
                player.shootY = player.bullet.moveY;
                player.boomIndx = 5;
                player.bullet = null;
            }
        }
        player.move(player);

    }
}