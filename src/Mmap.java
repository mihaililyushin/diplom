import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Mmap {
    //+++++++++++++++++++++++++++++++++++++Данные заполняются вручную один раз ,т.к. это параметры карты++++++++++++++++

    private int bordersCount = 8;               // количество препятствий
    static final int [][][] ObjectBorders = new int[][][]
            {
                    {{200,240,200,240},{0,0,248,248}},         //{X1,X2,X3,X4} и {Y1,Y2,Y3,Y4} вершин 1-ого препятствия карты
                    {{389,464,389,464},{170,170,246,246}},
                    {{800,1060,800,1060},{150,150,200,200}},
                    {{1000,1050,1000,1050},{300,300,470,470}},
                    {{630,745,630,745},{340,340,430,430}},
                    {{870,920,870,920},{600,600,760,760}},
                    {{375,425,375,425},{365,365,540,540}},
                    {{195,605,195,605},{540,540,585,585}}//и т.д.

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
        this.sizeX = 1200;
        this.sizeY = 760;
    }

    public static synchronized Mmap getInstance() {
        if (_bordersCheck == null)
            _bordersCheck = new Mmap();
        return _bordersCheck;
    }


    public MPlayer Check_X_and_Y (MPlayer player){

        boolean you_shall_not_pass = false;

        if (player.moveX < 0 | player.moveX > 1200){
            you_shall_not_pass = true;
    }
         if   (player.moveY < 0 | player.moveY > 760) {
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
        //===========================================Проверка попадания пули============================================
        return player;
    }

    public MBullet Check_X_and_Y_bull(MBullet bullet){

        boolean you_shall_not_pass = false;

            if (bullet.moveX < 0 || bullet.moveX > 1200){
                you_shall_not_pass = true;
                bullet.boom = true;
            }
            if   (bullet.moveY < 0 || bullet.moveY > 760) {
                you_shall_not_pass = true;
                bullet.boom = true;
            }
            for (int i = 0; i < this.bordersCount; i++) {
                if (bullet.moveX > ObjectBorders[i][0][0] & bullet.moveX < ObjectBorders[i][0][1] &&
                        bullet.moveY > ObjectBorders[i][1][0] & bullet.moveY < ObjectBorders[i][1][2]) {
                    you_shall_not_pass = true;
                    bullet.boom = true;
                }
            }
            if (you_shall_not_pass) {
                bullet.moveX = bullet.mapX;
                bullet.moveY = bullet.mapY;
            } else {
                bullet.mapX = bullet.moveX;
                bullet.mapY = bullet.moveY;
            }
            return bullet;
    }

    public MPlayer addCorpse(MPlayer player){
        if(player.corpseCount <10) {
            player.corpes.add(player.zombie.mapX);
            player.corpes.add(player.zombie.mapY);
            player.corpseCount++;
            player.zombie = null;
        }
        return player;
    }


}
