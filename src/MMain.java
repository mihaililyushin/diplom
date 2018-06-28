import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.TimerTask;

public class MMain extends JPanel implements ActionListener {

    JFrame frame;

    Timer timer = new Timer(30,this);

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
            player.corpseIndx = 1;
            for (int i = 0; i < player.corpseCount; i++) {
            g.drawImage(player.Corpse,
                    player.corpes.get(i+i)-20, player.corpes.get(player.corpseIndx)-20, 40, 40, null);
            player.corpseIndx += 2;
        }}
        if (player.isAlive){
            // тут может наступить конец игры

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

            g.drawImage(player.Zombie[player.zombie.spriteIndx],
                    player.zombie.mapX - 20, player.zombie.mapY - 20, 40, 40, null);
            player.zombie.spriteIndx++;
            if (player.zombie.spriteIndx > 5) {
                player.zombie.spriteIndx = 0;

            }
        g2d.setTransform(old_affine);
            //-----------player killed------------------------------------------------------------
            player.killerZombie(player,player.zombie);
        } //zombie
        if (player.zombie2 == null){
            if (ZombieCreate.getInstance().getTime()%10 == 0){
                player.zombie2 = new Zombie(player);
            }}
        if (player.zombie2 != null && player.zombie2.isAlife) {

            AffineTransform affine3 = new AffineTransform();
            affine3.rotate(Math.toRadians(player.zombie2.angle + 90), player.zombie2.mapX,
                    player.zombie2.mapY);           // поворачиваем спрайт игрока на угол

            g2d.setTransform(affine3);

            g.drawImage(player.Zombie[player.zombie2.spriteIndx],
                    player.zombie2.mapX - 20, player.zombie2.mapY - 20, 40, 40, null);
            player.zombie2.spriteIndx++;
            if (player.zombie2.spriteIndx > 5) {
                player.zombie2.spriteIndx = 0;

            }
            g2d.setTransform(old_affine);
            //-----------player killed------------------------------------------------------------
            player.killerZombie(player,player.zombie2);
        } //zombie2
        if (player.zombie3 == null){
            if (ZombieCreate.getInstance().getTime()%10 == 0){
                player.zombie3 = new Zombie(player);
            }}
        if (player.zombie3 != null && player.zombie3.isAlife) {

            AffineTransform affine4 = new AffineTransform();
            affine4.rotate(Math.toRadians(player.zombie3.angle + 90), player.zombie3.mapX,
                    player.zombie3.mapY);           // поворачиваем спрайт игрока на угол

            g2d.setTransform(affine4);

            g.drawImage(player.Zombie[player.zombie3.spriteIndx],
                    player.zombie3.mapX - 20, player.zombie3.mapY - 20, 40, 40, null);
            player.zombie3.spriteIndx++;
            if (player.zombie3.spriteIndx > 5) {
                player.zombie3.spriteIndx = 0;

            }
            g2d.setTransform(old_affine);
            //-----------player killed------------------------------------------------------------
            player.killerZombie(player,player.zombie3);
        } //zombie3
            if (player.zombie4 == null){
                if (ZombieCreate.getInstance().getTime()%10 == 0){
                    player.zombie4 = new Zombie(player);
                }}
            if (player.zombie4 != null && player.zombie4.isAlife) {

                AffineTransform affine5 = new AffineTransform();
                affine5.rotate(Math.toRadians(player.zombie4.angle + 90), player.zombie4.mapX,
                        player.zombie4.mapY);           // поворачиваем спрайт игрока на угол

                g2d.setTransform(affine5);

                g.drawImage(player.Zombie[player.zombie4.spriteIndx],
                        player.zombie4.mapX - 20, player.zombie4.mapY - 20, 40, 40, null);
                player.zombie4.spriteIndx++;
                if (player.zombie4.spriteIndx > 5) {
                    player.zombie4.spriteIndx = 0;

                }
                g2d.setTransform(old_affine);
                //-----------player killed------------------------------------------------------------
                player.killerZombie(player,player.zombie4);
            }  //zombie4
        if (player.zombie5 == null){
                if (ZombieCreate.getInstance().getTime()%10 == 0){
                    player.zombie5 = new Zombie(player);
                }}
            if (player.zombie5 != null && player.zombie5.isAlife) {

                AffineTransform affine6 = new AffineTransform();
                affine6.rotate(Math.toRadians(player.zombie5.angle + 90), player.zombie5.mapX,
                        player.zombie5.mapY);           // поворачиваем спрайт игрока на угол

                g2d.setTransform(affine6);

                g.drawImage(player.Zombie[player.zombie5.spriteIndx],
                        player.zombie5.mapX - 20, player.zombie5.mapY - 20, 40, 40, null);
                player.zombie5.spriteIndx++;
                if (player.zombie5.spriteIndx > 5) {
                    player.zombie5.spriteIndx = 0;

                }
                g2d.setTransform(old_affine);
                //-----------player killed------------------------------------------------------------
                player.killerZombie(player,player.zombie5);
            } //zombie5
        //===============================Zombie's death=================================================================
        if(player.zombie != null){
        if (!player.zombie.isAlife){
            g.drawImage(player.DeathPlayer[player.zombie.deathIndx],
                    player.zombie.mapX - 20, player.zombie.mapY - 20, 40, 40, null);
            player.zombie.deathIndx++;
            if (player.zombie.deathIndx > 7) {
                Mmap.getInstance().addCorpse(player,player.zombie);
            }
        }} //zombie
        if(player.zombie2 != null){
            if (!player.zombie2.isAlife){
                g.drawImage(player.DeathPlayer[player.zombie2.deathIndx],
                        player.zombie2.mapX - 20, player.zombie2.mapY - 20, 40, 40, null);
                player.zombie2.deathIndx++;
                if (player.zombie2.deathIndx > 7) {
                    Mmap.getInstance().addCorpse(player,player.zombie2);
                }
            }} //zombie2
        if(player.zombie3 != null){
            if (!player.zombie3.isAlife){
                g.drawImage(player.DeathPlayer[player.zombie3.deathIndx],
                        player.zombie3.mapX - 20, player.zombie3.mapY - 20, 40, 40, null);
                player.zombie3.deathIndx++;
                if (player.zombie3.deathIndx > 7) {
                    Mmap.getInstance().addCorpse(player,player.zombie3);
                }
            }} //zombie3
            if(player.zombie4 != null){
                if (!player.zombie4.isAlife){
                    g.drawImage(player.DeathPlayer[player.zombie4.deathIndx],
                            player.zombie4.mapX - 20, player.zombie4.mapY - 20, 40, 40, null);
                    player.zombie4.deathIndx++;
                    if (player.zombie4.deathIndx > 7) {
                        Mmap.getInstance().addCorpse(player,player.zombie4);
                    }
                }} //zombie4
            if(player.zombie5 != null){
                if (!player.zombie5.isAlife){
                    g.drawImage(player.DeathPlayer[player.zombie5.deathIndx],
                            player.zombie5.mapX - 20, player.zombie5.mapY - 20, 40, 40, null);
                    player.zombie5.deathIndx++;
                    if (player.zombie5.deathIndx > 7) {
                        Mmap.getInstance().addCorpse(player,player.zombie5);
                    }
                }} //zombie5

        //==============Движение пули===================================================================================
        if(player.bullet != null) {
            g.drawImage(player.bulletImg,
                    player.bullet.mapX, player.bullet.mapY, 5, 5, null);
            player.bullet.livetime--;
            if(player.bullet.livetime == 0){
                player.bullet = null;
            }
            if(player.zombie != null){
                player.bullet.killZombie(player, player.zombie);
            } // zombie
            if(player.zombie2 != null){
            player.bullet.killZombie(player, player.zombie2);
        } // zombie 2
            if(player.zombie3 != null){
                player.bullet.killZombie(player, player.zombie3);
            } // zombie 3
            if(player.zombie4 != null){
                player.bullet.killZombie(player, player.zombie4);
            } // zombie 4
            if(player.zombie5 != null){
                player.bullet.killZombie(player, player.zombie5);
            } // zombie 5
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
    } else {
            int fontSize = 80;
            g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
            g.setColor(Color.red);
            g.drawString("Убито зомби: " + player.corpseCount, 10, 300);
            if (player.corpseCount == 0){
                g.drawString("Тебя съели,ты был не против", 10, 200);
            }
            if (player.corpseCount > 0 && player.corpseCount <= 10){
                g.drawString("Ты вкусный,но ты пытался", 10, 200);
            }
            if (player.corpseCount > 10 && player.corpseCount <= 20){
                g.drawString("Тут нельзя выиграть!", 10, 200);
            }
            if (player.corpseCount > 20){
                g.drawString("И тебе не надоело?)))", 10, 200);
            }

    }}
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
        } // zombie
        if(player.zombie2 != null && player.zombie2.isAlife){
            player.zombie2.zombieWalk(player,player.zombie2);
            Mmap.getInstance().Check_X_and_Y(player.zombie2);
        } // zombie 2
        if(player.zombie3 != null && player.zombie3.isAlife){
            player.zombie3.zombieWalk(player,player.zombie3);
            Mmap.getInstance().Check_X_and_Y(player.zombie3);
        } // zombie 3
        if(player.zombie4 != null && player.zombie4.isAlife){
            player.zombie4.zombieWalk(player,player.zombie4);
            Mmap.getInstance().Check_X_and_Y(player.zombie4);
        } // zombie 4
        if(player.zombie5 != null && player.zombie5.isAlife){
            player.zombie5.zombieWalk(player,player.zombie5);
            Mmap.getInstance().Check_X_and_Y(player.zombie5);
        } // zombie 5

    }
}