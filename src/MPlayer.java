import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MPlayer {
    int mapY,mapX,moveX,moveY,speed,mouseX,mouseY,fireX,fireY,angle;
    int sizeX = 40;
    int sizeY = 40;
    int startX = 50;
    int startY = 50;
    Image[] DPlayer;    //      картинки движения игрока вниз
    Image[] UPlayer;    //      картинки движения игрока вверх
    Image[] RPlayer;    //      картинки движения игрока вправо
    Image[] LPlayer;
    Image bulletImg;//      пуля
    Image StandPlayer;  //      картинка стоящего игрока
    Image[] DeathPlayer;
    Image DeadPlayer;
    MBullet bullet = null;

    String LastMovePlayer = "UP";        //последнее состояние движения
    int spriteIndx = 0;

    String PAction = "NONE";              //текущее действие

    public MPlayer(){
        this.mapY = this.startX;
        this.mapX = this.startY;
        this.speed = 1;                 //множитель скорости speed*6
    }
    //--------------------------------------------------Принцип движения---------------------------------------------------
    public MPlayer move(MPlayer player) {
        if (!player.PAction.equals("NONE")){
            player.playerMouseMoving(player,true);
            Mmap.getInstance().Check_X_and_Y(player);
        }
        return player;
    }
//-------------------------------------Реакция на нажатие клавиш--------------------------------------------------------

    public void keyPressed(KeyEvent e) {            //определяем разрешенное действие по нажатию клавиши
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            PAction = "UP";
        }
        if(key == KeyEvent.VK_S) {
            PAction = "DOWN";
        }
        if(key == KeyEvent.VK_A) {
            PAction = "LEFT";
        }
        if(key == KeyEvent.VK_D) {
            PAction = "RIGHT";
        }
    }
//---------------------------------------------Прекращение реакции при прекращении нажатия клавиш-----------------------

    public void keyReleased(KeyEvent e) {           //прекратить движение если клавишу отпустили
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W | key == KeyEvent.VK_S | key == KeyEvent.VK_A | key == KeyEvent.VK_D ) {
            PAction = "NONE";
        }
    }
//---------------------------------------Реакция на нажатие кнопки мыши-------------------------------------------------

    public void mouseClicked(MouseEvent e, MPlayer player){
        int click = e.getButton();

        if (click == 1){
            player.fireX = e.getX();
            player.fireY = e.getY();
            System.out.println("BOOOOOOOOM " + player.fireX + " : " + player.fireY);
            if (this.bullet == null){
                this.bullet = new MBullet(player);
            }
        }
        if (click == 3){
            System.out.println("button 2 pressed");
        }
    }
//----------------------------------------Получаем координы мыши если ей двигаем----------------------------------------

    public MPlayer mouseMoved(MouseEvent e, MPlayer player){
        player.mouseX = e.getX();
        player.mouseY = e.getY();
        return player;
    }
//-----------------------------------------Расчет дельты координат и угла направления движения при движении за мышью----

    public Object playerMouseMoving (Object object, boolean isMoving){

        MPlayer player = null;
        MBullet bullet = null;
        Object returnObj = null;

        int x1 =0, x2 = 0,y1 = 0, y2 = 0, result_angle = 0, moveX = 0, moveY = 0, speed = 0;

        if (object instanceof MPlayer){
        player = (MPlayer) object;
        x1 = player.mapX;
        x2 = player.mouseX;
        y1 = player.mapY;
        y2 = player.mouseY;
        speed = player.speed;
        result_angle = player.angle;
        }
        if (object instanceof MBullet){
            bullet = (MBullet) object;
            x1 = bullet.startX;
            x2 = bullet.startX2;
            y1 = bullet.startY;
            y2 = bullet.startY2;
            speed = bullet.speed;
            result_angle = bullet.angle;
        }

//        (y1-y2)x + (x2 - x1)y + (x1y2 - x2y1) = 0    Уравнение линии по двум точкам
        int A = y1 - y2;
        int B = x2 - x1;
        int C = x1 * y2 - x2 * y1;
        int speedY = 0; //приращение У при движении
        int speedX = 0; //приращение Х при движении
        int angle = 0;  //угол поворота

// y = kx + b    уравнение с тангенсным коэфф. по точкам      k = (y2-y1)/(x2-x1);   b = y1 - k*x1  b1 = y2 - k*x1;
        if (y2 != y1 & x1 != x2) {
            double k = ((double) y2 - (double) y1) / ((double) x2 - (double) x1);
            double b = y1 - k * x1;
            angle = (int)Math.toDegrees(Math.atan(k));   // угол через k (y = kx + b)
            if (A < 0 & B > 0) {
                result_angle = angle;
            }
            if (A <0 & B < 0){
                result_angle = 180 + angle;
            }
            if (A > 0 & B < 0) {
                result_angle = 180 + angle;
            }
            if (A > 0 & B > 0){
                result_angle = 360 + angle;
            }

        }
//============================Приращение скорости по Х и У в зависимости от угла=======================================
       if (result_angle >= 0 & result_angle <= 7){
           System.out.println("from 0-7");
           speedX = 6 * speed;
           speedY = 0;
       }
       if (result_angle >= 180 & result_angle <= 180 + 7){
           System.out.println("from 0-7");
           speedX = 6 * speed;
           speedY = 0;
       }
       if (result_angle >=180-7 & result_angle < 180){
           System.out.println("from 0-7");
           speedX = 6 * speed;
           speedY = 0;
       }
       if (result_angle >=360 - 7 & result_angle <= 360){
           System.out.println("from 0-7");
           speedX = 6 * speed;
           speedY = 0;
       }//0-7
        if (result_angle > 7 & result_angle <= 22){
            System.out.println("from 7-22");
            speedX = 5 * speed;
            speedY = 1 * speed;
        }
        if (result_angle > 7 + 180 & result_angle <= 22 + 180){
            System.out.println("from 7-22");
            speedX = 5 * speed;
            speedY = 1 * speed;
        }
        if (result_angle >= 180-22 & result_angle < 180 - 7) {
            System.out.println("from 7-22");
            speedX = 5 * speed;
            speedY = 1 * speed;
        }
        if (result_angle >= 360 - 22 & result_angle < 360 - 7){
            System.out.println("from 7-22");
            speedX = 5 * speed;
            speedY = 1 * speed;
        }//7-22
        if (result_angle > 22 & result_angle <= 37){
            System.out.println("from 22-37");
            speedX = 4 * speed;
            speedY = 2 * speed;
        }
        if (result_angle > 22 + 180 & result_angle <= 37 + 180){
            System.out.println("from 22-37");
            speedX = 4 * speed;
            speedY = 2 * speed;
        }
        if (result_angle >= 180 - 37 & result_angle < 180 - 22){
            System.out.println("from 22-37");
            speedX = 4 * speed;
            speedY = 2 * speed;
        }
        if (result_angle >= 360 - 37 & result_angle < 360 - 22){
            System.out.println("from 22-37");
            speedX = 4 * speed;
            speedY = 2 * speed;
        }//22-37
        if (result_angle > 37 & result_angle <= 52){
            System.out.println("from 37-52");
            speedX = 3 * speed;
            speedY = 3 * speed;
        }
        if (result_angle > 37 + 180 & result_angle <= 52 + 180){
            System.out.println("from 37-52");
            speedX = 3 * speed;
            speedY = 3 * speed;
        }
        if (result_angle >= 180 - 52 & result_angle < 180 - 37){
            System.out.println("from 37-52");
            speedX = 3 * speed;
            speedY = 3 * speed;
        }
        if (result_angle >= 360 - 52 & result_angle < 360 - 37) {
            System.out.println("from 37-52");
            speedX = 3 * speed;
            speedY = 3 * speed;
        } //37-52
        if (result_angle > 52 & result_angle <= 68){
            System.out.println("from 52-68");
            speedX = 2 * speed;
            speedY = 4 * speed;
        }
        if (result_angle > 52 + 180 & result_angle <= 68 + 180){
            System.out.println("from 52-68");
            speedX = 2 * speed;
            speedY = 4 * speed;
        }
        if (result_angle >= 180 - 68 & result_angle < 180 - 52) {
            System.out.println("from 52-68");
            speedX = 2 * speed;
            speedY = 4 * speed;
        }
        if (result_angle >= 360 - 68 & result_angle < 360 - 52){
            System.out.println("from 52-68");
            speedX = 2 * speed;
            speedY = 4 * speed;
        }//52-68
        if (result_angle > 68 & result_angle <= 83){
            System.out.println("from 68-83");
            speedX = 1 * speed;
            speedY = 5 * speed;
        }
        if (result_angle > 68 + 180 & result_angle <= 83 + 180){
            System.out.println("from 68-83");
            speedX = 1 * speed;
            speedY = 5 * speed;
        }
        if (result_angle >= 180 - 83 & result_angle < 180 - 68){
            System.out.println("from 68-83");
            speedX = 1 * speed;
            speedY = 5 * speed;
        }
        if (result_angle >= 360 - 83 & result_angle < 360 - 68){
            System.out.println("from 68-83");
            speedX = 1 * speed;
            speedY = 5 * speed;
        }//68-83
        if (result_angle > 83 & result_angle <= 90){
            System.out.println("from 68-83");
            speedX = 0;
            speedY = 6 * speed;
        }
        if (result_angle > 83 + 180 & result_angle <= 90 + 180){
            System.out.println("from 68-83");
            speedX = 0;
            speedY = 6 * speed;
        }
        if (result_angle >= 180 - 90 & result_angle < 180 - 83){
            System.out.println("from 68-83");
            speedX = 0;
            speedY = 6 * speed;
        }
        if (result_angle >= 360 - 90 & result_angle < 360 - 83){
            System.out.println("from 68-83");
            speedX = 0;
            speedY = 6 * speed;
        }//83-90

//======================== Правила изменения координат при движении только игрок========================================
        if(object instanceof MPlayer) {
            if (A > 0 & B > 0) {
                if (isMoving) {
                    switch (player.PAction) {
                        case "UP": {
                            player.moveX += speedX;
                            player.moveY -= speedY;
                            break;
                        }
                        case "DOWN": {
                            player.moveX -= speedX;
                            player.moveY += speedY;
                            break;
                        }
                        case "LEFT": {
                            player.moveX -= speedX;
                            player.moveY -= speedY;
                            break;
                        }
                        case "RIGHT": {
                            player.moveX += speedX;
                            player.moveY += speedY;
                            break;
                        }
                    }
                }
            }
            if (A < 0 & B > 0) {
                if (isMoving) {
                    switch (player.PAction) {
                        case "UP": {
                            player.moveX += speedX;
                            player.moveY += speedY;
                            break;
                        }
                        case "DOWN": {
                            player.moveX -= speedX;
                            player.moveY -= speedY;
                            break;
                        }
                        case "LEFT": {
                            player.moveX += speedX;
                            player.moveY -= speedY;
                            break;
                        }
                        case "RIGHT": {
                            player.moveX -= speedX;
                            player.moveY += speedY;
                            break;
                        }
                    }
                }
            }
            if (A > 0 & B < 0) {
                if (isMoving) {
                    switch (player.PAction) {
                        case "UP": {
                            player.moveX -= speedX;
                            player.moveY -= speedY;
                            break;
                        }
                        case "DOWN": {
                            player.moveX += speedX;
                            player.moveY += speedY;
                            break;
                        }
                        case "LEFT": {
                            player.moveX -= speedX;
                            player.moveY += speedY;
                            break;
                        }
                        case "RIGHT": {
                            player.moveX += speedX;
                            player.moveY -= speedY;
                            break;
                        }
                    }
                }
            }
            if (A < 0 & B < 0) {
                if (isMoving) {
                    switch (player.PAction) {
                        case "UP": {
                            player.moveX -= speedX;
                            player.moveY += speedY;
                            break;
                        }
                        case "DOWN": {
                            player.moveX += speedX;
                            player.moveY -= speedY;
                            break;
                        }
                        case "LEFT": {
                            player.moveX += speedX;
                            player.moveY += speedY;
                            break;
                        }
                        case "RIGHT": {
                            player.moveX -= speedX;
                            player.moveY -= speedY;
                            break;
                        }
                    }
                }
            }
            player.angle = result_angle;
            returnObj = player;
        }
//================================Пуля летит сама==================================================================
        if (object instanceof MBullet){
            bullet.moveX += speedX;
            bullet.moveY += speedY;
//            if (A > 0 & B > 0) {
//                bullet.moveX += speedX;
//                bullet.moveY -= speedY;
//            }
//            if (A < 0 & B > 0) {
//                bullet.moveX += speedX;
//                bullet.moveY += speedY;
//            }
//            if (A > 0 & B < 0) {
//                player.moveX -= speedX;
//                player.moveY -= speedY;
//            }
//            if (A < 0 & B < 0) {
//                bullet.moveX -= speedX;
//                bullet.moveY += speedY;
//            }
            bullet.angle = result_angle;
            returnObj = bullet;
        }
        return returnObj;
    }


}