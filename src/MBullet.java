public class MBullet {
    int lifetime = 100; //дальность полета(сколько времени существует)
    int mapX = 0;       //текущие координаты нахождения пули
    int mapY = 0;
    int speed = 0;
    int startX = 0;     //по этим координатам строим уравнение движения
    int startX2 = 0;
    int startY = 0;
    int startY2 = 0;
    int moveX = 0;
    int moveY = 0;
    int angle = 0;
    int livetime;

//    public static MBullet _bullet = null;
//
//    public static synchronized MBullet getInstance(MPlayer player) {
//        if (_bullet == null)
//            _bullet = new MBullet(player);
//        return _bullet;
//    }

    public  MBullet(MPlayer player){
        this.startX = player.mapX;
        this.startY = player.mapY;
        this.startX2 = player.fireX;
        this.startY2 = player.fireY;
        this.mapX = player.mapX;
        this.mapY = player.mapY;
        this.moveX = 0;
        this.moveY = 0;
        this.livetime = 30;
        this.speed = 2;
        this.angle = 0;
    }
//
//    public MBullet bulletFly(MBullet bullet){
//
//        //        (y1-y2)x + (x2 - x1)y + (x1y2 - x2y1) = 0    Уравнение линии по двум точкам
//        //      Ax + By + C = 0
//        int A = bullet.startY - bullet.startY2;
//        int B = bullet.startX2 - bullet.startX;
//        int C = bullet.startX * bullet.startY2 - bullet.startX2 * bullet.startY;
//        bullet.mapX =
//
//
//
//        return bullet;
//    }
//
}
