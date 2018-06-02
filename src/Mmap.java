import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Mmap {
    //+++++++++++++++++++++++++++++++++++++Данные заполняются вручную один раз ,т.к. это параметры карты++++++++++++++++

    private int bordersCount = 2;               // количество препятствий
    static final int [][][] ObjectBorders = new int[][][]
            {
                    {{200,240,200,240},{0,0,248,248}},         //{X1,X2,X3,X4} и {Y1,Y2,Y3,Y4} вершин 1-ого препятствия карты
                    {{389,464,389,464},{170,170,246,246}}      //и т.д.

};  // массив с координатами вершин "непроходимых" объектов
    // [количество объектов][2 типа координат X и Y][четыре координаты углов]

    int sizeX,sizeY;
    Image Map;          //      картинка фона карты
    Image [] Boom;      //      спрайты взрыва
    Image [] GREENPlayerWalk;
    Image [] REDPlayerWalk;
    Image [] BLUEPlayerWalk;
    Image [] YELLOWPlayerWalk;
    Image [] BLACKPlayerWalk;
    Image [] WHITEPlayerWalk;

    public Image getImagePlayerWalk(MPlayer player,int i) {
       Image img = null;
       switch (player.Playercolor){
           case "GREEN":
               img = GREENPlayerWalk [i];
               break;
           case "RED":
               img = REDPlayerWalk [i];
               break;
           case "BLUE":
               img = BLUEPlayerWalk [i];
               break;
           case "YELLOW":
               img = YELLOWPlayerWalk [i];
               break;
           case "BLACK":
               img = BLACKPlayerWalk [i];
               break;
           case "WHITE":
               img = WHITEPlayerWalk [i];
               break;
       }
       return img;
    }

    public Image getMap() {
        return Map;
    }

    private static Mmap _bordersCheck = null;

    private Mmap() {
            try {
                Map = ImageIO.read(new File("lib/pic/backg/testBack.gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
       GREENPlayerWalk = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                GREENPlayerWalk[i] = ImageIO.read(new File("lib/pic/player/GREENP/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } //загружаем Image [] GREENPlayerWalk
        REDPlayerWalk = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                REDPlayerWalk[i] = ImageIO.read(new File("lib/pic/player/RED/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//загружаем Image [] REDPlayerWalk
        BLUEPlayerWalk = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                BLUEPlayerWalk[i] = ImageIO.read(new File("lib/pic/player/BLUE/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//загружаем Image [] BLUEPlayerWalk
        YELLOWPlayerWalk = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                YELLOWPlayerWalk[i] = ImageIO.read(new File("lib/pic/player/YELLOW/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//загружаем Image [] YELLOWPlayerWalk
        BLACKPlayerWalk = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                BLACKPlayerWalk[i] = ImageIO.read(new File("lib/pic/player/BLACK/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//загружаем Image [] BLACKPlayerWalk
        WHITEPlayerWalk = new Image[7];
        for (int i = 0; i < 6 ; i++) {
            try {
                WHITEPlayerWalk[i] = ImageIO.read(new File("lib/pic/player/WHITE/Walk" + String.valueOf(i+1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//загружаем Image [] WHITEPlayerWalk
        this.sizeX = 1024;
        this.sizeY = 800;

    }

    public static synchronized Mmap getInstance() {
        if (_bordersCheck == null)
            _bordersCheck = new Mmap();
        return _bordersCheck;
    }


    public MPlayer Check_X_and_Y (MPlayer player){

        boolean you_shall_not_pass = false;

        if (player.moveX < 0 & player.moveX > this.sizeX && player.moveY < 0 & player.moveY > this.sizeY) {
            you_shall_not_pass = true;
        }

        for (int i = 0; i < this.bordersCount; i++) {
            if (player.moveX > ObjectBorders[i][0][0] & player.moveX < ObjectBorders[i][0][1] &&
                    player.moveY > ObjectBorders[i][1][0] & player.moveY < ObjectBorders[i][1][2]) {
                you_shall_not_pass = true;
            }
        }
        if (you_shall_not_pass) {
            player.moveX = player.mapX;
            player.moveY = player.mapY;
        } else {
            player.mapX = player.moveX;
            player.mapY = player.moveY;
        }
        return player;
    }

    public int RandomStart(int xy,boolean isX){
        int newxy = 0;
        if(isX){
            newxy = xy;
        } else  {
            newxy = xy;
        }
        return newxy;
    }

}
