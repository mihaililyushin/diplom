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

}
