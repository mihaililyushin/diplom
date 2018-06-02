//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.io.File;
//import java.io.IOException;
//
//public class GameImgLoader {
//
//    public Image getWPlayer(MPlayer player,int i) {
//        return player.WPlayer[i];
//    }
//
//
//
//
//    //-------------------------------------------------загрузка спрайтов игрока--------------------------------------------
//    public GameImgLoader (MPlayer player, PlayerColor PlayerColor){
//        player.WPlayer = new Image[7];
//        for (int i = 0; i < 6 ; i++) {
//            try {
//                player.WPlayer[i] = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Walk" + String.valueOf(i+1) + ".png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try {
//            player.StandPlayer = ImageIO.read(new File("lib/pic/player/" + PlayerColor.toString() + "/Stand.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    //------------------------------------------------Возвращаем картинки в зависимости от движения------------------------
//    public Image getPlayerImgAction(MPlayer player) {
//
//        switch (player.PAction) {
//            case "UP":
//                getWPlayer(player,player.spriteIndx);
//                player.LastMovePlayer = "UP";
//                return getWPlayer(player,player.spriteIndx);
//            case "DOWN":
//                getWPlayer(player,player.spriteIndx);
//                player.LastMovePlayer = "DOWN";
//                return getWPlayer(player,player.spriteIndx);
//            case "RIGHT":
//                getWPlayer(player,player.spriteIndx);
//                player.LastMovePlayer = "RIGHT";
//                return getWPlayer(player,player.spriteIndx);
//            case "LEFT":
//                getWPlayer(player,player.spriteIndx);
//                player.LastMovePlayer = "LEFT";
//                return getWPlayer(player,player.spriteIndx);
//            case "NONE":
//                switch (player.LastMovePlayer) {
//                    case "UP":
//                        return player.StandPlayer;
//                    case "DOWN":
//                        return player.StandPlayer;
//                    case "RIGHT":
//                        return player.StandPlayer;
//                    case "LEFT":
//                        return player.StandPlayer;
//                }
//        }
//        return player.StandPlayer;
//    }
////}