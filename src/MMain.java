import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.TimerTask;

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

        ZombieCreate.getInstance();
        g.drawImage(Mmap.getInstance().getMap(), 0, 0,Mmap.getInstance().sizeX, Mmap.getInstance().sizeY, null);
        //рисуем фон
        //==================================Corpse======================================================================
        if (player.corpseCount > 0) {
            System.out.println(player.corpseCount);
            System.out.println(player.corpes.toString());
            player.corpseIndx = 1;
            for (int i = 0; i < player.corpseCount; i++) {
            g.drawImage(player.DeathPlayer[6],
                    player.corpes.get(i+i)-20, player.corpes.get(player.corpseIndx)-20, 40, 40, null);
            player.corpseIndx += 2;
        }}
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
        System.out.println(player.mouseX + " " + player.mouseY);
        //=================Анимация выстрела============================================================================
        if(player.bullet != null && player.bullet.spriteIndx != 0){
            g.drawImage(player.ShootPlayer[player.bullet.spriteIndx - 1],
                    player.mapX - 20, player.mapY - 20, 40, 40, null);
            player.bullet.spriteIndx--;
        }
        g2d.setTransform(old_affine);


        //=================Zombie=======================================================================================
        if (player.zombie == null){
        if (ZombieCreate.getInstance().getTime()%10 == 0){
            player.zombie = new Zombie(player);
        }}
        if (player.zombie != null && player.zombie.isAlife) {

            AffineTransform affine2 = new AffineTransform();
        affine2.rotate(Math.toRadians(player.zombie.angle + 90), player.zombie.mapX,
                player.zombie.mapY);           // поворачиваем спрайт игрока на угол

        g2d.setTransform(affine2);

            g.drawImage(player.UPlayer[player.zombie.spriteIndx],
                    player.zombie.mapX - 20, player.zombie.mapY - 20, 40, 40, null);
            player.zombie.spriteIndx++;
            if (player.zombie.spriteIndx > 5) {
                player.zombie.spriteIndx = 0;

            }
        g2d.setTransform(old_affine);
        }
        //===============================Zombie's death=================================================================
        if(player.zombie != null){
        if (!player.zombie.isAlife){
            g.drawImage(player.DeathPlayer[player.zombie.deathIndx],
                    player.zombie.mapX - 20, player.zombie.mapY - 20, 40, 40, null);
            player.zombie.deathIndx++;
            if (player.zombie.deathIndx > 7) {
                Mmap.getInstance().addCorpse(player);
//                player.zombie = null;
            }
        }}

        //==============Движение пули===================================================================================
        if(player.bullet != null) {
            g.drawImage(player.bulletImg,
                    player.bullet.mapX, player.bullet.mapY, 5, 5, null);
            player.bullet.livetime--;
            if(player.bullet.livetime == 0){
                player.bullet = null;
            }
            if(player.zombie != null){
                player.bullet.killZombie(player);
            }
        }
        //-------------Бум---------------------------
        if(player.boomIndx >= 0) {
            g.drawImage(player.Boom[5 - player.boomIndx], player.shootX-20, player.shootY-20, 50, 50, null);
        player.boomIndx--;
        }
        int fontSize = 20;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(Color.red);
        g.drawString("Убито зомби: " + player.corpseCount, 10, 20);
    }
    //-----------------------------------------------действия по таймеру---------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
//       ========================================игрок ходит===========================
        player.PlayerMoving(player,false);
        player.move(player);
//       ========================================пуля летит============================
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
//       ========================================зомби ходит===========================
        if(player.zombie != null && player.zombie.isAlife){
        player.zombie.zombieWalk(player,player.zombie);
        Mmap.getInstance().Check_X_and_Y(player.zombie);
        }

    }
}