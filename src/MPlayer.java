import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MPlayer {
    int mapY,mapX,moveX,moveY,speed,mouseX,mouseY,fireX,fireY,angle,shootX,shootY;
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
    Image[] ShootPlayer;
    Image[] Boom;
    Image[] DeathPlayer;
    Image DeadPlayer;
    MBullet bullet = null;

    String LastMovePlayer = "UP";        //последнее состояние движения
    int spriteIndx = 0;
    int boomIndx = 0;


    String PAction = "NONE";              //текущее действие

    public MPlayer(){
        this.mapY = this.startX;
        this.mapX = this.startY;
        this.speed = 1;                 //множитель скорости speed*6
    }
    //--------------------------------------------------Принцип движения---------------------------------------------------
    public MPlayer move(MPlayer player) {
        if (!player.PAction.equals("NONE")){
            player.PlayerMoving(player,true);
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

    public MPlayer mouseClicked(MouseEvent e, MPlayer player){
        int click = e.getButton();

        if (click == 1){
            player.fireX = e.getX();
            player.fireY = e.getY();
            if (this.bullet == null){
            this.bullet = new MBullet(player);
            }
        }
        if (click == 3){
            System.out.println("button 2 pressed");
        }
        return player;
    }
//----------------------------------------Получаем координы мыши если ей двигаем----------------------------------------

    public MPlayer mouseMoved(MouseEvent e, MPlayer player){
        player.mouseX = e.getX();
        player.mouseY = e.getY();
        return player;
    }
//-----------------------------------------Расчет дельты координат и угла направления движения при движении за мышью----

    public MPlayer PlayerMoving(MPlayer player, boolean isMoving){

        int x1 =0, x2 = 0,y1 = 0, y2 = 0, angle = 0, moveX = 0, moveY = 0, speed = 0, A = 0, B = 0, C = 0;
        int speedY = 0; //приращение У при движении
        int speedX = 0; //приращение Х при движении
            x1 = player.mapX;
            x2 = player.mouseX;
            y1 = player.mapY;
            y2 = player.mouseY;
            speed = player.speed;

//        (y1-y2)x + (x2 - x1)y + (x1y2 - x2y1) = 0    Уравнение линии по двум точкам
            A = y1 - y2;
            B = x2 - x1;

// y = kx + b    уравнение с тангенсным коэфф. по точкам      k = (y2-y1)/(x2-x1);   b = y1 - k*x1  b1 = y2 - k*x1;
            if (y2 != y1 & x1 != x2) {
                double k = ((double) y2 - (double) y1) / ((double) x2 - (double) x1);
                if (A < 0 & B > 0) {
                    angle = (int) Math.toDegrees(Math.atan(k));  // угол через k (y = kx + b)
                }
                if (A < 0 & B < 0) {
                    angle = 180 + (int) Math.toDegrees(Math.atan(k));
                }
                if (A > 0 & B < 0) {
                    angle = 180 + (int) Math.toDegrees(Math.atan(k));
                }
                if (A > 0 & B > 0) {
                    angle = 360 + (int) Math.toDegrees(Math.atan(k));
                }
            }
//============================Приращение скорости по Х и У в зависимости от угла общие==================================
       if (angle >= 0 & angle <= 7){
           speedX = 6 * speed;
           speedY = 0;
       }
       if (angle >= 180 & angle <= 180 + 7){
           speedX = 6 * speed;
           speedY = 0;
       }
       if (angle >=180-7 & angle < 180){
           speedX = 6 * speed;
           speedY = 0;
       }
       if (angle >=360 - 7 & angle <= 360){
           speedX = 6 * speed;
           speedY = 0;
       }//0-7
        if (angle > 7 & angle <= 22){
            speedX = 5 * speed;
            speedY = 1 * speed;
        }
        if (angle > 7 + 180 & angle <= 22 + 180){
            speedX = 5 * speed;
            speedY = 1 * speed;
        }
        if (angle >= 180-22 & angle < 180 - 7) {
            speedX = 5 * speed;
            speedY = 1 * speed;
        }
        if (angle >= 360 - 22 & angle < 360 - 7){
            speedX = 5 * speed;
            speedY = 1 * speed;
        }//7-22
        if (angle > 22 & angle <= 37){
            speedX = 4 * speed;
            speedY = 2 * speed;
        }
        if (angle > 22 + 180 & angle <= 37 + 180){
            speedX = 4 * speed;
            speedY = 2 * speed;
        }
        if (angle >= 180 - 37 & angle < 180 - 22){
            speedX = 4 * speed;
            speedY = 2 * speed;
        }
        if (angle >= 360 - 37 & angle < 360 - 22){
            speedX = 4 * speed;
            speedY = 2 * speed;
        }//22-37
        if (angle > 37 & angle <= 52){
            speedX = 3 * speed;
            speedY = 3 * speed;
        }
        if (angle > 37 + 180 & angle <= 52 + 180){
            speedX = 3 * speed;
            speedY = 3 * speed;
        }
        if (angle >= 180 - 52 & angle < 180 - 37){
            speedX = 3 * speed;
            speedY = 3 * speed;
        }
        if (angle >= 360 - 52 & angle < 360 - 37) {
            speedX = 3 * speed;
            speedY = 3 * speed;
        } //37-52
        if (angle > 52 & angle <= 68){
            speedX = 2 * speed;
            speedY = 4 * speed;
        }
        if (angle > 52 + 180 & angle <= 68 + 180){
            speedX = 2 * speed;
            speedY = 4 * speed;
        }
        if (angle >= 180 - 68 & angle < 180 - 52) {
            speedX = 2 * speed;
            speedY = 4 * speed;
        }
        if (angle >= 360 - 68 & angle < 360 - 52){
            speedX = 2 * speed;
            speedY = 4 * speed;
        }//52-68
        if (angle > 68 & angle <= 83){
            speedX = 1 * speed;
            speedY = 5 * speed;
        }
        if (angle > 68 + 180 & angle <= 83 + 180){
            speedX = 1 * speed;
            speedY = 5 * speed;
        }
        if (angle >= 180 - 83 & angle < 180 - 68){
            speedX = 1 * speed;
            speedY = 5 * speed;
        }
        if (angle >= 360 - 83 & angle < 360 - 68){
            speedX = 1 * speed;
            speedY = 5 * speed;
        }//68-83
        if (angle > 83 & angle <= 90){
            speedX = 0;
            speedY = 6 * speed;
        }
        if (angle > 83 + 180 & angle <= 90 + 180){
            speedX = 0;
            speedY = 6 * speed;
        }
        if (angle >= 180 - 90 & angle < 180 - 83){
            speedX = 0;
            speedY = 6 * speed;
        }
        if (angle >= 360 - 90 & angle < 360 - 83){
            speedX = 0;
            speedY = 6 * speed;
        }//83-90

//======================== Правила изменения координат при движении только игрок========================================

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
            player.angle = angle;
        return player;
    }


}