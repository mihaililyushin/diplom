public class Zombie {
    int mapX = 0;
    int mapY = 0;
    int speed = 0;
    int moveX = 0;
    int moveY = 0;
    int startX = 0;
    int startY = 0;
    int angle = 0;
    int sizeX = 40;
    int sizeY = 40;
    int spriteIndx = 0;
    int deathIndx = 0;
    boolean isAlife = true;
    boolean ismoving = true;


    public Zombie (MPlayer player){
        int random = (int)(Math.random() * 10);
        this.startX = ZombieCreate.respawn[random][0];
        this.startY = ZombieCreate.respawn[random][1];
        this.mapX = this.startX;
        this.mapY = this.startY;
        this.moveX = startX;
        this.moveY = startY;
        this.speed = 1;
    }

    public Zombie zombieWalk(MPlayer player,Zombie zombie){
        int speedY = 0; //приращение У при движении
        int speedX = 0; //приращение Х при движении
        int angle = 0;
        int speed = zombie.speed;
        int A = 0,B = 0;
        int y1 = zombie.mapY;
        int y2 = player.mapY;

        int x1 = zombie.mapX;
        int x2 = player.mapX;


        //        (y1-y2)x + (x2 - x1)y + (x1y2 - x2y1) = 0    Уравнение линии по двум точкам
        A = y1 - y2;
        B = x2 - x1;

// y = kx + b    уравнение с тангенсным коэфф. по точкам      k = (y2-y1)/(x2-x1);   b = y1 - k*x1  b1 = y2 - k*x1;
        if (y2 != y1 & x1 != x2) {
            double k = ((double) y2 - (double) y1) / ((double) x2 - (double) x1);
            if (A < 0 & B > 0) {
                angle = (int) Math.toDegrees(Math.atan(k));  // угол через k (y = kx + b)
            }
            if (A < 0 & B < 0) {
                angle = 180 + (int) Math.toDegrees(Math.atan(k));
            }
            if (A > 0 & B < 0) {
                angle = 180 + (int) Math.toDegrees(Math.atan(k));
            }
            if (A > 0 & B > 0) {
                angle = 360 + (int) Math.toDegrees(Math.atan(k));
            }
        }
        if (angle >= 0 & angle <= 7){
            speedX = 6 * speed;
            speedY = 0;
        }
        if (angle >= 180 & angle <= 180 + 7){
            speedX = 6 * speed;
            speedY = 0;
        }
        if (angle >=180-7 & angle < 180){
            speedX = 6 * speed;
            speedY = 0;
        }
        if (angle >=360 - 7 & angle <= 360){
            speedX = 6 * speed;
            speedY = 0;
        }//0-7
        if (angle > 7 & angle <= 22){
            speedX = 5 * speed;
            speedY = 1 * speed;
        }
        if (angle > 7 + 180 & angle <= 22 + 180){
            speedX = 5 * speed;
            speedY = 1 * speed;
        }
        if (angle >= 180-22 & angle < 180 - 7) {
            speedX = 5 * speed;
            speedY = 1 * speed;
        }
        if (angle >= 360 - 22 & angle < 360 - 7){
            speedX = 5 * speed;
            speedY = 1 * speed;
        }//7-22
        if (angle > 22 & angle <= 37){
            speedX = 4 * speed;
            speedY = 2 * speed;
        }
        if (angle > 22 + 180 & angle <= 37 + 180){
            speedX = 4 * speed;
            speedY = 2 * speed;
        }
        if (angle >= 180 - 37 & angle < 180 - 22){
            speedX = 4 * speed;
            speedY = 2 * speed;
        }
        if (angle >= 360 - 37 & angle < 360 - 22){
            speedX = 4 * speed;
            speedY = 2 * speed;
        }//22-37
        if (angle > 37 & angle <= 52){
            speedX = 3 * speed;
            speedY = 3 * speed;
        }
        if (angle > 37 + 180 & angle <= 52 + 180){
            speedX = 3 * speed;
            speedY = 3 * speed;
        }
        if (angle >= 180 - 52 & angle < 180 - 37){
            speedX = 3 * speed;
            speedY = 3 * speed;
        }
        if (angle >= 360 - 52 & angle < 360 - 37) {
            speedX = 3 * speed;
            speedY = 3 * speed;
        } //37-52
        if (angle > 52 & angle <= 68){
            speedX = 2 * speed;
            speedY = 4 * speed;
        }
        if (angle > 52 + 180 & angle <= 68 + 180){
            speedX = 2 * speed;
            speedY = 4 * speed;
        }
        if (angle >= 180 - 68 & angle < 180 - 52) {
            speedX = 2 * speed;
            speedY = 4 * speed;
        }
        if (angle >= 360 - 68 & angle < 360 - 52){
            speedX = 2 * speed;
            speedY = 4 * speed;
        }//52-68
        if (angle > 68 & angle <= 83){
            speedX = 1 * speed;
            speedY = 5 * speed;
        }
        if (angle > 68 + 180 & angle <= 83 + 180){
            speedX = 1 * speed;
            speedY = 5 * speed;
        }
        if (angle >= 180 - 83 & angle < 180 - 68){
            speedX = 1 * speed;
            speedY = 5 * speed;
        }
        if (angle >= 360 - 83 & angle < 360 - 68){
            speedX = 1 * speed;
            speedY = 5 * speed;
        }//68-83
        if (angle > 83 & angle <= 90){
            speedX = 0;
            speedY = 6 * speed;
        }
        if (angle > 83 + 180 & angle <= 90 + 180){
            speedX = 0;
            speedY = 6 * speed;
        }
        if (angle >= 180 - 90 & angle < 180 - 83){
            speedX = 0;
            speedY = 6 * speed;
        }
        if (angle >= 360 - 90 & angle < 360 - 83){
            speedX = 0;
            speedY = 6 * speed;
        }//83-90
//        if (player.PAction.equals("NONE")){
//            zombie.ismoving = true;
//        }
//        if (!player.PAction.equals("NONE")){
//            zombie.ismoving = false;
//        }
        if(zombie.ismoving){
        if (A > 0 & B > 0) {
            zombie.moveX += speedX;
            zombie.moveY -= speedY;
        }
        if (A < 0 & B > 0) {
            zombie.moveX += speedX;
            zombie.moveY += speedY;
        }
        if (A > 0 & B < 0) {
            zombie.moveX -= speedX;
            zombie.moveY -= speedY;
        }
        if (A < 0 & B < 0) {
            zombie.moveX -= speedX;
            zombie.moveY += speedY;
        }}
//        zombie.mapX = zombie.moveX;
//        zombie.mapY = zombie.moveY;
        //движение в зависимости от расположения в четверти координатных осей
        zombie.angle = angle;
        return zombie;
    }
}
