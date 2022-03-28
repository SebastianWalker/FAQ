import java.io.*;  
import java.util.*;  


public class quiz {
    
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    static void FrageEinlesen(){

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
                        meineFragen.add(eineFrage);
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

  
    static Fragen meineFragen = new Fragen();

    public static void main(String[] args) {

        player player = new player();
        Frage nächsteFrage = new Frage();
        Integer intAntwort;

        System.out.println("Hallo Player!");
        System.out.println("Wie ist dein Name?");

        player.name = System.console().readLine();
 
        System.out.print("Hi ");
        System.out.println(player.name);
        System.out.println("Bitte warte, während ich die Fragen vorbereite...");

        FrageEinlesen();
        pause(1000); // just wait 

        System.out.println("Welche Variante willst du spielen?");
        System.out.println("Echte Zocker schreiben 'yeah'.. alles andere ist langweilig!");

        // ToDo: Falsche Eingaben abfangen
        player.zocker = System.console().readLine().equals("yeah") ? true : false ; 
        

        if (player.zocker){ // 0 = true
            // while loop... weiter solange nicht falsch geantwortet oder max level nicht erreicht
            System.out.println("Nice, du alter Zocker!!!");

            boolean keepGoing = true;
            while(keepGoing){
                System.out.println("Du hast bereits " + player.gewinn + " Nüsse erspielt!");
                nächsteFrage = meineFragen.getRandomFrage2Level(player.level);
                System.out.println(nächsteFrage.Frage);
    
                Collections.shuffle(nächsteFrage.listAntworten);
                for (int i = 0; i<nächsteFrage.listAntworten.size(); i++){  
                    System.out.println("Antwort [" + i + "]:" + nächsteFrage.listAntworten.get(i));
                }
                intAntwort = Integer.parseInt(System.console().readLine());
    
                if (nächsteFrage.listAntworten.get(intAntwort) == nächsteFrage.richtigeAntwort){
                    System.out.println("Stimmt!");
                    player.gewinn = player.getLevel();
                    player.setNextLevel();
                    
                    if (player.getLevel() == -1){
                        System.out.print("\033[H\033[2J");
                        System.out.println("Du hast bis zum Ende durchgehalten!");
                        System.out.println("Du hast " + player.gewinn + " Nüsse erspielt!");
                        System.out.println("Drücke eine Taste zum Beenden...");
                        System.console().readLine();
                        keepGoing=false;

                    }
                    
                    pause(500);
                    System.out.print("\033[H\033[2J");
                    
                }else{
                    System.out.println("Leider Falsch! -- Viel Glück beim nächsten Mal!");
                    keepGoing=false;
                }
            }


            





        }else{ // 1 = false
            System.out.println("Wie langweilig... aber ok.");
            

            // einfach random 5 Fragen aus File lesen..
            // im File sind 3 Fragen pro Level
            // level sind 100, 200, 300, 500, 1000

        } // ende von IF

    } // ende von main

} // ende der Klasse
