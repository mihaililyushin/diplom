import java.util.Random;

public class ZombieCreate{
    final long start;
    int time;
    private static ZombieCreate _timer = null;
    public static int [][] respawn = new int [][] {
            {650,200},{420,100},{300,500},{100,500},{100,650},{450,670},{750,650},{520,500},{1050,600},{900,520}};
//  точки появления

    public static synchronized ZombieCreate getInstance() {
        if (_timer == null)
            _timer = new ZombieCreate();
        return _timer;
    }

    private ZombieCreate(){
        start = System.currentTimeMillis();
    }

    public int getTime(){
       long curret = System.currentTimeMillis() - start;
       time = (int)curret/1000;
       return time;
    }



}
