import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fragen {
    List<Frage> _Fragen = new ArrayList<Frage>();

    public Frage getRandomFrage2Level(int level){
        List<Frage> möglicheFragen = new ArrayList<Frage>(); // temp. Liste mit allen Fragen die zum Level passen
        for (Frage f : _Fragen){ // foe each Frage f in _Fragen...
            if (f.level == level){
                möglicheFragen.add(f);
            }
        }
        // jetzt eine random raussuchen
        Random rand = new Random();
        int int_random = rand.nextInt(möglicheFragen.size());

        return möglicheFragen.get(int_random);
    }

    public void add(Frage Frage){
        _Fragen.add(Frage);
    }
}