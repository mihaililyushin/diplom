import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MPlayer {
    private static MPlayer playerGREEN = null;
    private static MPlayer playerRED = null;
    private static MPlayer playerBLUE = null;
    private static MPlayer playerYELLOW = null;
    private static MPlayer playerBLACK = null;
    private static MPlayer playerWHITE = null;

    int mapY,mapX,moveX,moveY,speed,mouseX,mouseY,fireX,fireY,angle;
    int sizeX = 40;
    int sizeY = 40;
    int startX = 50;
    int startY = 50;
    final String Playercolor;
    Image[] WPlayer;    //      картинки движения игрока вниз
    Image StandPlayer;  //      картинка стоящего игрока
    Image[] DeathPlayer;
    Image DeadPlayer;

    String LastMovePlayer = "UP";        //последнее состояние движения
    int spriteIndx = 0;

    enum PlayerColor {GREEN,RED,BLUE,YELLOW,BLACK,WHITE}    //доступные цвета игроков

    enum PlayerAction {UP,DOWN,LEFT,RIGHT,NONE}                 //список разрешенных действий игрока
    String PAction = PlayerAction.NONE.toString();              //текущее действие

    //==========================================Доступные варианты игроков==============================================
    public static synchronized MPlayer getPlayer(PlayerColor Color) {
        MPlayer player = null;
        if (Color == PlayerColor.GREEN){
            if(playerGREEN == null){
                playerGREEN = new MPlayer(PlayerColor.GREEN,40,40);
            }
            player = playerGREEN;
        }
        if (Color == PlayerColor.RED){
            if(playerRED == null){
                playerRED = new MPlayer(PlayerColor.RED,40,40);
            }
            player = playerRED;
        }
        if (Color == PlayerColor.BLUE){
            if(playerBLUE == null){
                playerBLUE = new MPlayer(PlayerColor.BLUE,40,40);
            }
            player = playerBLUE;
        }
        if (Color == PlayerColor.YELLOW){
            if(playerYELLOW == null){
                playerYELLOW = new MPlayer(PlayerColor.YELLOW,40,40);
            }
            player = playerYELLOW;
        }
        if (Color == PlayerColor.BLACK){
            if(playerBLACK == null){
                playerBLACK = new MPlayer(PlayerColor.BLACK,40,40);
            }
            player = playerBLACK;
        }
        if (Color == PlayerColor.WHITE){
            if(playerWHITE == null){
                playerWHITE = new MPlayer(PlayerColor.WHITE,40,40);
            }
            player = playerWHITE;
        }
        return player;
    }
    //==========================================Конструктор игрока======================================================
    private MPlayer(PlayerColor Color,int startX,int startY){
        this.Playercolor = Color.toString();
        this.startX = Mmap.getInstance().RandomStart(startX,true);
        this.startY = Mmap.getInstance().RandomStart(startY,false);
        this.mapY = this.startX;
        this.mapX = this.startY;
        this.speed = 3;
    }
    //--------------------------------------------------Принцип движения---------------------------------------------------
    public MPlayer move(MPlayer player) {
        switch(PlayerAction.valueOf(PAction)) {
            case UP:
                player.playerMouseMoving(player,true);
                Mmap.getInstance().Check_X_and_Y(player);
                break;
            case DOWN:
                player.moveY = player.mapY + player.speed;
                Mmap.getInstance().Check_X_and_Y(player);
                break;
            case LEFT:
                player.moveX = player.mapX - player.speed;
                Mmap.getInstance().Check_X_and_Y(player);
                break;
            case RIGHT:
                player.moveX = player.mapX + player.speed;
                Mmap.getInstance().Check_X_and_Y(player);
                break;
            default:
                break;
        }
        return player;
    }

    //------------------------------------------------Возвращаем картинки в зависимости от движения------------------------
    public Image getPlayerImgAction(MPlayer player) {

        switch (player.PAction) {
            case "UP":
                Mmap.getInstance().getImagePlayerWalk(player,player.spriteIndx);
                player.LastMovePlayer = "UP";
                return Mmap.getInstance().getImagePlayerWalk(player,player.spriteIndx);
            case "DOWN":
                Mmap.getInstance().getImagePlayerWalk(player,player.spriteIndx);
                player.LastMovePlayer = "DOWN";
                return Mmap.getInstance().getImagePlayerWalk(player,player.spriteIndx);
            case "RIGHT":
                Mmap.getInstance().getImagePlayerWalk(player,player.spriteIndx);
                player.LastMovePlayer = "RIGHT";
                return Mmap.getInstance().getImagePlayerWalk(player,player.spriteIndx);
            case "LEFT":
                Mmap.getInstance().getImagePlayerWalk(player,player.spriteIndx);
                player.LastMovePlayer = "LEFT";
                return Mmap.getInstance().getImagePlayerWalk(player,player.spriteIndx);
            case "NONE":
                switch (player.LastMovePlayer) {
                    case "UP":
                        return player.StandPlayer;
                    case "DOWN":
                        return player.StandPlayer;
                    case "RIGHT":
                        return player.StandPlayer;
                    case "LEFT":
                        return player.StandPlayer;
                }
        }
        return player.StandPlayer;
    }
//-------------------------------------Реакция на нажатие клавиш--------------------------------------------------------

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
//---------------------------------------------Прекращение реакции при прекращении нажатия клавиш-----------------------

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
//---------------------------------------Реакция на нажатие кнопки мыши-------------------------------------------------

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
//----------------------------------------Получаем координы мыши если ей двигаем----------------------------------------

    public MPlayer mouseMoved(MouseEvent e, MPlayer player){
        player.mouseX = e.getX();
        player.mouseY = e.getY();
        return player;
    }
//-----------------------------------------Расчет дельты координат и угла направления движения при движении за мышью----

    public MPlayer playerMouseMoving (MPlayer player, boolean isMoving){
//        (y1-y2)x + (x2 - x1)y + (x1y2 - x2y1) = 0    Уравнение линии по двум точкам
        int A = player.mapY - player.mouseY;
        int B = player.mouseX - player.mapX;
        int C = player.mapX * player.mouseY - player.mouseX * player.mapY;
        int dopuskY = 0;
        int dopuskX = 0;
        int angle = 0;

            if (A != 0 & B != 0)      {
                int Yx = Math.abs(A/B); // Y растет быстрее чем X
                int Xy = Math.abs(B/A); // X растет быстрее чем Y
                if (0 < Yx & Yx < 3){
                    dopuskX = 2;
                    dopuskY = 2;
                    angle = 45; //поворот на 45
                }
                if (3 <= Yx & Yx < 9){
                    dopuskX = 1;
                    dopuskY = 3;
                    angle = 30; //поворот на 30
                }
                if (Yx >= 9){
                    dopuskX = 0;
                    dopuskY = 3;
                    angle = 0; //поворот на 0
                }
                if (0 < Xy & Xy < 3){
                    dopuskX = 2;
                    dopuskY = 2;
                    angle = 60;//поворот на 60
                }
                if (3 <= Xy & Xy < 9){
                    dopuskX = 3;
                    dopuskY = 1;
                    angle = 75;    //поворот на 75
                }
                if (Xy >= 9){
                    dopuskX = 3;
                    dopuskY = 0;
                    angle = 90;    //поворот на 90
                }
        }
        if (A == 0){
            dopuskX = 0;
            dopuskY = 3;
            angle = 90;    //поворот на 90
        }
        if (B == 0){
            dopuskX = 3;
            dopuskY = 0;
            angle = 0; //поворот на 0
        }
         if (A > 0 & B > 0) {
                if(isMoving) {
                    player.moveX += dopuskX;
                    player.moveY -= dopuskY;
                }
            player.angle = angle;
        }
        if (A < 0 & B > 0) {
                if(isMoving) {
                    player.moveX += dopuskX;
                    player.moveY += dopuskY;
                }
            player.angle = 180 - angle;
        }
        if (A > 0 & B < 0) {
                if(isMoving) {
                    player.moveX -= dopuskX;
                    player.moveY -= dopuskY;
                }
            player.angle = 360 - angle;
        }
        if (A < 0 & B < 0) {
                if(isMoving) {
                    player.moveX -= dopuskX;
                    player.moveY += dopuskY;
                }
            player.angle = angle + 180;
        }
        return player;
    }
}