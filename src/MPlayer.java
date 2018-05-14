import java.awt.event.KeyEvent;

public class MPlayer extends GameObjects{

    String LastMovePlayer = "UP";        //последнее состояние движения
    int spriteIndx = 0;

    enum PlayerAction {UP,DOWN,LEFT,RIGHT,NONE}                 //список разрешенных действий игрока
    String PAction = PlayerAction.NONE.toString();              //текущее действие
//--------------------------------------------------Принцип движения---------------------------------------------------
    public void move(int i) {
        this.speed = i;
        switch(PlayerAction.valueOf(PAction)) {
            case UP:
                mapY+=speed;
                break;
            case DOWN:
                mapY-=speed;
                break;
            case LEFT:
                mapX+=speed;
                break;
            case RIGHT:
                mapX-=speed;
                break;
            default:
                break;
        }
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
//---------------------------------------------Изменение картинок самого игрока при движении игрока---------------------

}