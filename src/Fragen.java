import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fragen {
    
    static List<Frage> _Fragen = new ArrayList<Frage>();

    public Frage getRandomFrage2Level(int level){
        List<Frage> möglicheFragen = new ArrayList<Frage>(); // temp. Liste mit allen Fragen die zum Level passen
        for (Frage f : _Fragen){ // foe each Frage f in _Fragen...
            if (f.level == level){
                möglicheFragen.add(f);
            }
        }
        // jetzt eine random raussuchen
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int int_random = rand.nextInt(möglicheFragen.size());

        return möglicheFragen.get(int_random);
    }

    public void add(Frage Frage){
        _Fragen.add(Frage);
    }

    public void FragenEinlesen(){

        int i = 0; // counter für Daten .. hauptsächlich um die Headerzeile zu überspringen

        try  
            {  
                File file=new File("src/Fragen.txt");    //creates a new file instance  
                FileReader fr=new FileReader(file);   //reads the file  
                BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  

                String line;  
                String[] splitLine;
            
                while((line=br.readLine())!=null){  
                    Frage eineFrage = new Frage();
                    if (i>0){
                        splitLine = line.split(";");
                        eineFrage.level = Integer.parseInt(splitLine[0]);
                        eineFrage.Frage = splitLine[1];
                        eineFrage.richtigeAntwort = splitLine[2];
                        eineFrage.listAntworten.add(splitLine[2]);
                        eineFrage.listAntworten.add(splitLine[3]);
                        eineFrage.listAntworten.add(splitLine[4]);
                        eineFrage.listAntworten.add(splitLine[5]);
                        _Fragen.add(eineFrage);
                    }
                    i++;
                }  
                fr.close();    //closes the stream and release the resources  
            }  
                catch(IOException e)  
            {  
             e.printStackTrace();  
            }   
    }

}