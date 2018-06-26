public class MBullet {
    int mapX = 0;       //текущие координаты нахождения пули
    int mapY = 0;
    int speed = 0;
    int moveX = 0;
    int moveY = 0;
    int angle = 0;
    int livetime = 0;
    int spriteIndx = 0;
    int A,B;
    boolean boom = false;
    double k;

    public MBullet(MPlayer player) {
        this.mapX = player.mapX;
        this.mapY = player.mapY;
        this.moveX = player.moveX;
        this.moveY = player.moveY;
        this.livetime = 100;
        this.speed = 5;
        this.spriteIndx = 6;
        //        (y1-y2)x + (x2 - x1)y + (x1y2 - x2y1) = 0    Уравнение линии по двум точкам
        final int A = player.mapY - player.fireY;
        this.A = A;
        final int B = player.fireX - player.mapX;
        this.B = B;
        // y = kx + b    уравнение с тангенсным коэфф. по точкам      k = (y2-y1)/(x2-x1);   b = y1 - k*x1  b1 = y2 - k*x1;
        if (player.fireY != player.mapY & player.mapX != player.fireX) {
            final double k = ((double) player.fireY - (double) player.mapY) / ((double) player.fireX - (double) player.mapX);
            this.k = k;
            if (this.A < 0 & this.B > 0) {
                this.angle = (int) Math.toDegrees(Math.atan(this.k));  // угол через k (y = kx + b)
            }
            if (this.A < 0 & this.B < 0) {
                this.angle = 180 + (int) Math.toDegrees(Math.atan(this.k));
            }
            if (this.A > 0 & this.B < 0) {
                this.angle = 180 + (int) Math.toDegrees(Math.atan(this.k));
            }
            if (this.A > 0 & this.B > 0) {
                this.angle = 360 + (int) Math.toDegrees(Math.atan(this.k));
            }

        }
    }
    public MBullet bulletFly(MBullet bullet){
        int speedX = 0, speedY = 0;
        if (bullet.angle >= 0 & bullet.angle <= 7){
            speedX = 6 * bullet.speed;
            speedY = 0;
        }
        if (bullet.angle >= 180 & bullet.angle <= 180 + 7){
            speedX = 6 * bullet.speed;
            speedY = 0;
        }
        if (bullet.angle >=180-7 & bullet.angle < 180){
            speedX = 6 * bullet.speed;
            speedY = 0;
        }
        if (bullet.angle >=360 - 7 & bullet.angle <= 360){
            speedX = 6 * bullet.speed;
            speedY = 0;
        }
        if (bullet.angle > 7 & bullet.angle <= 22){
            speedX = 5 * bullet.speed;
            speedY = 1 * bullet.speed;
        }
        if (bullet.angle > 7 + 180 & bullet.angle <= 22 + 180){
            speedX = 5 * bullet.speed;
            speedY = 1 * bullet.speed;
        }
        if (bullet.angle >= 180-22 & bullet.angle < 180 - 7) {
            speedX = 5 * bullet.speed;
            speedY = 1 * bullet.speed;
        }
        if (bullet.angle >= 360 - 22 & bullet.angle < 360 - 7){
            speedX = 5 * bullet.speed;
            speedY = 1 * bullet.speed;
        }
        if (bullet.angle > 22 & bullet.angle <= 37){
            speedX = 4 * bullet.speed;
            speedY = 2 * bullet.speed;
        }
        if (bullet.angle > 22 + 180 & bullet.angle <= 37 + 180){
            speedX = 4 * bullet.speed;
            speedY = 2 * bullet.speed;
        }
        if (bullet.angle >= 180 - 37 & bullet.angle < 180 - 22){
            speedX = 4 * bullet.speed;
            speedY = 2 * bullet.speed;
        }
        if (bullet.angle >= 360 - 37 & bullet.angle < 360 - 22){
            speedX = 4 * bullet.speed;
            speedY = 2 * bullet.speed;
        }
        if (bullet.angle > 37 & bullet.angle <= 52){
            speedX = 3 * bullet.speed;
            speedY = 3 * bullet.speed;
        }
        if (bullet.angle > 37 + 180 & bullet.angle <= 52 + 180){
            speedX = 3 * bullet.speed;
            speedY = 3 * bullet.speed;
        }
        if (bullet.angle >= 180 - 52 & bullet.angle < 180 - 37){
            speedX = 3 * bullet.speed;
            speedY = 3 * bullet.speed;
        }
        if (bullet.angle >= 360 - 52 & bullet.angle < 360 - 37) {
            speedX = 3 * bullet.speed;
            speedY = 3 * bullet.speed;
        }
        if (bullet.angle > 52 & bullet.angle <= 68){
            speedX = 2 * bullet.speed;
            speedY = 4 * bullet.speed;
        }
        if (bullet.angle > 52 + 180 & bullet.angle <= 68 + 180){
            speedX = 2 * bullet.speed;
            speedY = 4 * bullet.speed;
        }
        if (bullet.angle >= 180 - 68 & bullet.angle < 180 - 52) {
            speedX = 2 * bullet.speed;
            speedY = 4 * bullet.speed;
        }
        if (bullet.angle >= 360 - 68 & bullet.angle < 360 - 52){
            speedX = 2 * bullet.speed;
            speedY = 4 * bullet.speed;
        }
        if (bullet.angle > 68 & bullet.angle <= 83){
            speedX = 1 * bullet.speed;
            speedY = 5 * bullet.speed;
        }
        if (bullet.angle > 68 + 180 & bullet.angle <= 83 + 180){
            speedX = 1 * bullet.speed;
            speedY = 5 * bullet.speed;
        }
        if (bullet.angle >= 180 - 83 & bullet.angle < 180 - 68){
            speedX = 1 * bullet.speed;
            speedY = 5 * bullet.speed;
        }
        if (bullet.angle >= 360 - 83 & bullet.angle < 360 - 68){
            speedX = 1 * bullet.speed;
            speedY = 5 * bullet.speed;
        }
        if (bullet.angle > 83 & bullet.angle <= 90){
            speedX = 0;
            speedY = 6 * bullet.speed;
        }
        if (bullet.angle > 83 + 180 & bullet.angle <= 90 + 180){
            speedX = 0;
            speedY = 6 * bullet.speed;
        }
        if (bullet.angle >= 180 - 90 & bullet.angle < 180 - 83){
            speedX = 0;
            speedY = 6 * bullet.speed;
        }
        if (bullet.angle >= 360 - 90 & bullet.angle < 360 - 83){
            speedX = 0;
            speedY = 6 * bullet.speed;
        } //приращение координат

        if (A > 0 & B > 0) {
            bullet.moveX += speedX;
            bullet.moveY -= speedY;
        }
        if (A < 0 & B > 0) {
            bullet.moveX += speedX;
            bullet.moveY += speedY;
        }
        if (A > 0 & B < 0) {
            bullet.moveX -= speedX;
            bullet.moveY -= speedY;
        }
        if (A < 0 & B < 0) {
            bullet.moveX -= speedX;
            bullet.moveY += speedY;
        } //движение в зависимости от расположения в четверти координатных осей

        return bullet;
    }

    public MPlayer killZombie(MPlayer player){
        if (player.bullet.mapX > player.zombie.mapX-30 && player.bullet.mapX < player.zombie.mapX+30 &&
                player.bullet.mapY > player.zombie.mapY-30 && player.bullet.mapY < player.zombie.mapY+30){
            player.zombie.isAlife = false;
        }
        return player;
    }
}