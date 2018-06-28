import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameImgLoader {

    public Image getDPlayer(MPlayer player,int i) {
        return player.DPlayer[i];
    }
    public Image getUPlayer(MPlayer player,int i) {
        return player.UPlayer[i];
    }
    public Image getRPlayer(MPlayer player,int i) {
        return player.RPlayer[i];
    }
    public Image getLPlayer(MPlayer player,int i) {
        return player.LPlayer[i];
    }
    enum PlayerColor {GREEN}    //доступные цвета игроков


    //-------------------------------------------------загрузка спрайтов игрока--------------------------------------------
    public GameImgLoader (MPlayer player, PlayerColor PlayerColor){
        try {
            player.bulletImg = ImageIO.read(new File("lib/pic/shoot/bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.Corpse = ImageIO.read(new File("lib/pic/player/GREEN/Corpse.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.Boom = new Image[6];
        for (int i = 0; i < 6 ; i++) {
            try {
                player.Boom[i] = ImageIO.read(new File("lib/pic/shoot/boom_0" + String.valueOf(i+1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.ShootPlayer = new Image[6];
        for (int i = 0; i < 2 ; i++) {
            try {
                player.ShootPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/PGrShoot" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.DPlayer = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                player.DPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.Zombie = new Image[6];
        for (int i = 0; i < 6 ; i++) {
            try {
                player.Zombie[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/ZWalk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.UPlayer = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                player.UPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.testZombie = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                player.UPlayer[i] = ImageIO.read(new File("lib/pic/player/RED/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.RPlayer = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                player.RPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.LPlayer = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                player.LPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.DeathPlayer = new Image[8];
        for (int i = 0; i < 7 ; i++) {
            try {
                player.DeathPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/PGrDead" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            player.StandPlayer = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Stand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //------------------------------------------------Возвращаем картинки в зависимости от движения------------------------
    public Image getPlayerImgAction(MPlayer player) {

        switch (player.PAction) {
            case "UP":
                getUPlayer(player,player.spriteIndx);
                player.LastMovePlayer = "UP";
                return getUPlayer(player,player.spriteIndx);
            case "DOWN":
                getUPlayer(player,player.spriteIndx);
                player.LastMovePlayer = "DOWN";
                return getDPlayer(player,player.spriteIndx);
            case "RIGHT":
                getUPlayer(player,player.spriteIndx);
                player.LastMovePlayer = "RIGHT";
                return getRPlayer(player,player.spriteIndx);
            case "LEFT":
                getUPlayer(player,player.spriteIndx);
                player.LastMovePlayer = "LEFT";
                return getLPlayer(player,player.spriteIndx);
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
}