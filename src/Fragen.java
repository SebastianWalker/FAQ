import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// Klasse enthält mehrere Fragen und Methoden um die Fragen abzurufen
public class Fragen {
    
    static List<Frage> FragenListe = new ArrayList<Frage>();

    public Frage getRandomFrage2Level(int level){
        List<Frage> möglicheFragen = new ArrayList<Frage>(); // temp. Liste mit allen Fragen die zum Level passen
        for (Frage f : FragenListe){ // for each Frage f in _Fragen...
            if (f.level == level){
                möglicheFragen.add(f);
            }
        }
        // jetzt eine random raussuchen
        Collections.shuffle(möglicheFragen, new Random(System.currentTimeMillis())); // double random :D
        return möglicheFragen.get(0);

    }

    public void add(Frage Frage){
        FragenListe.add(Frage);
    }

    public void FragenEinlesen(){

        int i = 0; // counter für Daten .. hauptsächlich um die Headerzeile zu überspringen

        try{  
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
                        for (int k = 2; k<splitLine.length; k++){
                            eineFrage.listAntworten.add(splitLine[k]); // egal wie viele Antwortmöglichkeiten in der Textdatei stehen...
                        }
                        FragenListe.add(eineFrage);
                    }
                    i++;
                }  
                fr.close();    //closes the stream and release the resources  
        }catch(IOException e){  
             e.printStackTrace();  
        }   
    }

}