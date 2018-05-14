public class GameObjects {
    public int mapX, mapY;
    public int sizeX,sizeY;
    public int speed;
    boolean isPlayer;
    boolean isGoods;
    boolean isWalls;
    boolean isBullet;

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public void setGoods(boolean goods) {
        isGoods = goods;
    }

    public void setWalls(boolean walls) {
        isWalls = walls;
    }

    public void setBullet(boolean bullet) {
        isBullet = bullet;
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public boolean isGoods() {
        return isGoods;
    }

    public boolean isWalls() {
        return isWalls;
    }

    public boolean isBullet() {
        return isBullet;
    }
}
