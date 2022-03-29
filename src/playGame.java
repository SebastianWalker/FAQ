import java.util.*;

public class playGame {
    
    public static void run(player player){
        Frage nächsteFrage = new Frage();
        Integer intAntwort;
        Fragen meineFragen = new Fragen();
        
        System.out.println("Bitte warte, während ich die Fragen vorbereite...");
        meineFragen.FragenEinlesen();
         helper.pause(1000); // just wait 

        boolean keepGoing = true;
        while(keepGoing){
            System.out.print("\033[H\033[2J");
            if (player.zocker){
                System.out.println("Du hast bereits " + player.gewinn + " Nüsse erspielt!");
            }else{
                System.out.println("Du hast bereits " + player.gewinn + " Nüsse erspielt! ");
                if (player.gewinn >=300){System.out.println("Davon sind sogar schon 300 Nüsse save!");}
            }
            

            nächsteFrage = meineFragen.getRandomFrage2Level(player.level);
            System.out.println(nächsteFrage.Frage);

            Collections.shuffle(nächsteFrage.listAntworten);
            for (int i = 0; i<nächsteFrage.listAntworten.size(); i++){  
                System.out.println("[" + i + "]:" + nächsteFrage.listAntworten.get(i));
            }
            if (!player.joker5050used){
                System.out.println("Drücke 5050 um deinen Joker einzusetzen!");
            }
            
             intAntwort = input.Antwort();   
        
            if(intAntwort==5050 && !player.joker5050used){ // Joker einsetzen
                player.joker5050used=true; // Joker darf nicht mehr genutzt werden
                System.out.print("\033[H\033[2J");
                System.out.println(nächsteFrage.Frage); // Frage erneut anzeigen

                // eine Frage ist richtig, damit bleiben 3 übrig von denen zwei weg müssen.. also suchen wir zufällig eine aus und behalten die
                List<String> möglicheAntworten = new ArrayList<String>();
                for (String f : nächsteFrage.listAntworten){
                    if (f != nächsteFrage.richtigeAntwort){
                        möglicheAntworten.add(f);
                    }
                }
                Random rand = new Random();
                rand.setSeed(System.currentTimeMillis());
                int int_random = rand.nextInt(3);

                for (int i = 0; i<nächsteFrage.listAntworten.size(); i++){  
                    String f = nächsteFrage.listAntworten.get(i);
                    if (f==nächsteFrage.richtigeAntwort || f==möglicheAntworten.get(int_random)){
                        System.out.println("[" + i + "]:" + f);
                    }else{
                        System.out.println("[" + i + "]: ...");
                    }
                }
                intAntwort = input.Antwort();
            }

            // wenn Joker das zweite mal eingesetz wird.. muss man das verhindern, sonst sucht er die Antwort mit index5050 im listAntworten
            if (intAntwort==5050 && player.joker5050used){
                while (intAntwort==5050){
                    System.out.println("Der Jocker ist schon aufgebraucht...");
                    intAntwort = input.Antwort();
                }
                
            }

            if (nächsteFrage.listAntworten.get(intAntwort) == nächsteFrage.richtigeAntwort){
                System.out.println("Stimmt!");
                player.gewinn = player.getLevel();
                player.setNextLevel();
                
                if (player.getLevel() == -1){
                    System.out.print("\033[H\033[2J");
                    System.out.println("Du hast bis zum Ende durchgehalten!");
                    System.out.println("Du hast " + player.gewinn + " Nüsse erspielt!");
                    keepGoing=false;

                }
                
               
            }else{
                
                if (player.zocker){player.gewinn = 0;}
                if (player.level >= 2 && !player.zocker) {player.gewinn=300;}
                
                System.out.println("Leider Falsch! Richtg wäre '" + nächsteFrage.richtigeAntwort + "' gewesen...\nDu hast es versucht.. und "+ player.gewinn +" gewonnen... \nViel Glück beim nächsten Mal!");
                keepGoing=false;
            }
        }


    }
    
    
}
