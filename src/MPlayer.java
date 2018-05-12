import java.awt.event.KeyEvent;

public class MPlayer {

    private int x = 0;
    private int y = 0;
    private int speed = 20;
    private int mapX = 0;
    private int mapY = 0;

    enum PlayerAction {UP,DOWN,LEFT,RIGHT,NONE}  //список разрешенных действий игрока
    String PAction = PlayerAction.NONE.toString();                              //текущее действие

    public void move() {
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

    public void keyPressed(KeyEvent e) {            //определяем разрешенное действие по нажатию клавиши
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            PAction = PlayerAction.UP.toString();
            System.out.println("moving " + PlayerAction.UP);
        }
        if(key == KeyEvent.VK_S) {
            PAction = PlayerAction.DOWN.toString();
            System.out.println("moving " + PlayerAction.DOWN);
        }
        if(key == KeyEvent.VK_A) {
            PAction = PlayerAction.LEFT.toString();
            System.out.println("moving " + PlayerAction.LEFT);
        }
        if(key == KeyEvent.VK_D) {
            PAction = PlayerAction.RIGHT.toString();
            System.out.println("moving " + PlayerAction.RIGHT);
        }
    }

    public void keyReleased(KeyEvent e) {           //прекратить движение если клавишу отпустили
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            PAction = PlayerAction.NONE.toString();
            System.out.println("stop moving " + PlayerAction.UP);
        }
        if(key == KeyEvent.VK_S) {
            PAction = PlayerAction.NONE.toString();
            System.out.println("stop moving " + PlayerAction.DOWN);
        }
        if(key == KeyEvent.VK_A) {
            PAction = PlayerAction.NONE.toString();
            System.out.println("stop moving " + PlayerAction.LEFT);
        }
        if(key == KeyEvent.VK_D) {
            PAction = PlayerAction.NONE.toString();
            System.out.println("stop moving " + PlayerAction.RIGHT);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }
}