import java.util.Arrays;

public class BordersCheck {
    //+++++++++++++++++++++++++++++++++++++Данные заполняются вручную один раз ,т.к. это параметры карты++++++++++++++++

    private int bordersCount = 2;               // количество препятствий
    static final int [][][] ObjectBorders = new int[][][]
            {
                    {{160,240,160,240},{-40,-40,248,248}},         //{X1,X2,X3,X4} и {Y1,Y2,Y3,Y4} вершин 1-ого препятствия карты
                    {{349,464,349,464},{130,130,246,246}}      //и т.д.

};  // массив с координатами вершин "непроходимых" объектов
    // [количество объектов][2 типа координат X и Y][четыре координаты углов]

    private static BordersCheck _bordersCheck = null;

    private BordersCheck() {
    }

    public static synchronized BordersCheck getInstance() {
        if (_bordersCheck == null)
            _bordersCheck = new BordersCheck();
        return _bordersCheck;
    }


    public MPlayer Check_X_and_Y (MPlayer player){

        boolean you_shall_not_pass = false;

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
