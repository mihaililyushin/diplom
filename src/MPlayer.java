import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MPlayer {
    int mapY,mapX,moveX,moveY,speed,mouseX,mouseY,fireX,fireY;
    Image[] DPlayer;    //      картинки движения игрока вниз
    Image[] UPlayer;    //      картинки движения игрока вверх
    Image[] RPlayer;    //      картинки движения игрока вправо
    Image[] LPlayer;    //      картинки движения игрока влево
    Image StandPlayer;  //      картинка стоящего игрока
    Image[] DeathPlayer;
    Image DeadPlayer;

    String LastMovePlayer = "UP";        //последнее состояние движения
    int spriteIndx = 0;

    enum PlayerAction {UP,DOWN,LEFT,RIGHT,NONE}                 //список разрешенных действий игрока
    String PAction = PlayerAction.NONE.toString();              //текущее действие


    public MPlayer(int startX, int startY){
        this.mapY = startY;
        this.mapX = startX;
        this.speed = 3;
    }

    //--------------------------------------------------Принцип движения---------------------------------------------------
    public MPlayer move(MPlayer player) {
        switch(PlayerAction.valueOf(PAction)) {
            case UP:
                player.playerMouseMoving(player);
                BordersCheck.getInstance().Check_X_and_Y(player);
                break;
            case DOWN:
                player.moveY = player.mapY + player.speed;
                BordersCheck.getInstance().Check_X_and_Y(player);
                break;
            case LEFT:
                player.moveX = player.mapX - player.speed;
                BordersCheck.getInstance().Check_X_and_Y(player);
                break;
            case RIGHT:
                player.moveX = player.mapX + player.speed;
                BordersCheck.getInstance().Check_X_and_Y(player);
                break;
            default:
                break;
        }
        return player;
    }
    //-------------------------------------Реакция на нажатие клавиш-------------------------------------------------------
    public void keyPressed(KeyEvent e) {            //определяем разрешенное действие по нажатию клавиши
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            PAction = PlayerAction.UP.toString();
        }
        if(key == KeyEvent.VK_S) {
            PAction = PlayerAction.DOWN.toString();
        }
        if(key == KeyEvent.VK_A) {
            PAction = PlayerAction.LEFT.toString();
        }
        if(key == KeyEvent.VK_D) {
            PAction = PlayerAction.RIGHT.toString();
        }
    }
    //---------------------------------------------Прекращение реакции при прекращении нажатия клавиш----------------------
    public void keyReleased(KeyEvent e) {           //прекратить движение если клавишу отпустили
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            PAction = PlayerAction.NONE.toString();
        }
        if(key == KeyEvent.VK_S) {
            PAction = PlayerAction.NONE.toString();
        }
        if(key == KeyEvent.VK_A) {
            PAction = PlayerAction.NONE.toString();
        }
        if(key == KeyEvent.VK_D) {
            PAction = PlayerAction.NONE.toString();
        }
    }
    public void mouseClicked(MouseEvent e, MPlayer player){
      int click = e.getButton();

        if (click == 1){
            player.fireX = e.getX();
            player.fireY = e.getY();
            System.out.println("BOOOOOOOOM " + player.fireX + " : " + player.fireY);
        }
        if (click == 3){
            System.out.println("button 2 pressed");
        }
    }
    public MPlayer mouseMoved(MouseEvent e, MPlayer player){
        player.mouseX = e.getX();
        player.mouseY = e.getY();
        return player;
    }

    public MPlayer playerMouseMoving (MPlayer player){
//        (y1-y2)x + (x2 - x1)y + (x1y2 - x2y1) = 0    Уравнение линии по двум точкам
        int A = player.mapY - player.mouseY;
        int B = player.mouseX - player.mapX;
        int C = player.mapX * player.mouseY - player.mouseX * player.mapY;
        int dopuskY = 0;
        int dopuskX = 0;

            if (A != 0 & B != 0)      {
                int Yx = Math.abs(A/B); // Y растет быстрее чем X
                int Xy = Math.abs(B/A); // X растет быстрее чем Y
                if (0 < Yx & Yx < 3){
                    dopuskX = 2;
                    dopuskY = 2;
                }
                if (3 <= Yx & Yx < 9){
                    dopuskX = 1;
                    dopuskY = 3;
                }
                if (Yx >= 9){
                    dopuskX = 0;
                    dopuskY = 3;
                }
                if (0 < Xy & Xy < 3){
                    dopuskX = 2;
                    dopuskY = 2;
                }
                if (3 <= Xy & Xy < 9){
                    dopuskX = 3;
                    dopuskY = 1;
                }
                if (Xy >= 9){
                    dopuskX = 3;
                    dopuskY = 0;
                }
        }
        if (A == 0){
            dopuskX = 0;
            dopuskY = 3;
        }
        if (B == 0){
            dopuskX = 3;
            dopuskY = 0;
        }
         if (A > 0 & B > 0) {
            player.moveX += dopuskX;
            player.moveY -= dopuskY;
        }
        if (A < 0 & B > 0) {
            player.moveX += dopuskX;
            player.moveY += dopuskY;
        }
        if (A > 0 & B < 0) {
            player.moveX -= dopuskX;
            player.moveY -= dopuskY;
        }
        if (A < 0 & B < 0) {
            player.moveX -= dopuskX;
            player.moveY += dopuskY;
        }
        return player;
    }

}