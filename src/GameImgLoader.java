import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameImgLoader {

    public Image getDPlayer(int i) {
        return DPlayer[i];
    }
    public Image getUPlayer(int i) {
        return UPlayer[i];
    }
    public Image getRPlayer(int i) {
        return RPlayer[i];
    }
    public Image getLPlayer(int i) {
        return LPlayer[i];
    }
    public Image getMap() {
        return Map;
    }
    enum PlayerColor {GREENP}    //доступные цвета игроков
    Image[] DPlayer;    //      картинки движения игрока вниз
    Image[] UPlayer;    //      картинки движения игрока вверх
    Image[] RPlayer;    //      картинки движения игрока вправо
    Image[] LPlayer;    //      картинки движения игрока влево
    Image StandPlayer;  //      картинка стоящего игрока
    Image[] DeathPlayer;
    Image DeadPlayer;
    Image Map;          //      картинка фона карты

//-------------------------------------------------загрузка спрайтов игрока--------------------------------------------
    public GameImgLoader (MPlayer player, PlayerColor PlayerColor){
        DPlayer = new Image[7];
        for (int i = 0; i < 7 ; i++) {
            try {
                DPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UPlayer = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                UPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RPlayer = new Image[7];
        for (int i = 0; i < 7 ; i++) {
            try {
                RPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LPlayer = new Image[7];
        for (int i = 0; i < 7 ; i++) {
            try {
                LPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            StandPlayer = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Stand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//------------------------------------------------Загрузка фона карты---------------------------------------------------
    public GameImgLoader(){
        try {
            Map = ImageIO.read(new File("lib/pic/backg/testBack.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//------------------------------------------------Возвращаем картинки в зависимости от движения------------------------
    public Image getPlayerImgAction(MPlayer player) {

        switch (player.PAction) {
            case "UP":
                getUPlayer(player.spriteIndx);
                player.LastMovePlayer = "UP";
                return getUPlayer(player.spriteIndx);
            case "DOWN":
                getUPlayer(player.spriteIndx);
                player.LastMovePlayer = "DOWN";
                return getDPlayer(player.spriteIndx);
            case "RIGHT":
                getUPlayer(player.spriteIndx);
                player.LastMovePlayer = "RIGHT";
                return getRPlayer(player.spriteIndx);
            case "LEFT":
                getUPlayer(player.spriteIndx);
                player.LastMovePlayer = "LEFT";
                return getLPlayer(player.spriteIndx);
            case "NONE":
                switch (player.LastMovePlayer) {
                    case "UP":
                        return StandPlayer;
                    case "DOWN":
                        return StandPlayer;
                    case "RIGHT":
                        return StandPlayer;
                    case "LEFT":
                        return StandPlayer;
                }
        }
        return StandPlayer;
    }
}
