
public class ZombieCreate{
    final long start;
    int time;
    private static ZombieCreate _timer = null;

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
