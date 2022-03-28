public class player {
    String name;
    boolean zocker;
    int gewinn;
    int level;
    boolean joker5050used;
    private int _level;

    private Integer[] levels = {100, 200, 300, 500, 1000};

    public player(){
        gewinn = 0;
        level = levels[0];
        _level = 0;
        joker5050used=false;
    }
    public void reset(){
        gewinn = 0;
        level = levels[0];
        _level = 0;
        joker5050used=false;
    }

    public Integer getLevel(){
        return level;
    }
    public void setNextLevel(){
        if (_level < levels.length-1){
            level = levels[_level+1];
            _level++;
        }else{
            level = -1;
        }
    }
}
