import java.util.*;  


public class quiz {
    
    

    public static void main(String[] args) {

        player player = new player();
        Frage nächsteFrage = new Frage();
        Integer intAntwort;
        Fragen meineFragen = new Fragen();

        System.out.println("Hallo Player!");
        System.out.println("Wie ist dein Name?");

        player.name = System.console().readLine();
 
        System.out.print("Hi ");
        System.out.println(player.name);
        System.out.println("Bitte warte, während ich die Fragen vorbereite...");

        meineFragen.FragenEinlesen();
        
        helper.pause(1000); // just wait 

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
                if (!player.joker5050used){
                    System.out.println("Drücke 5050 um deinen Joker einzusetzen!");
                }
                intAntwort = Integer.parseInt(System.console().readLine());
    
                if(intAntwort==5050){ // Joker einsetzen
                    player.joker5050used=true; // Joker darf nicht mehr genutzt werden
                    System.out.println(nächsteFrage.Frage); // Frage erneut anzeigen

                    // eine Frage ist richtig, damit bleiben 3 übrig von denen zwei weg müssen.. also suchen wir zufällig eine aus und behalten die
                    List<String> möglicheAntworten = new ArrayList<String>();
                    for (String f : nächsteFrage.listAntworten){
                        if (f != nächsteFrage.richtigeAntwort){
                            möglicheAntworten.add(f);
                        }
                    }
                    Random rand = new Random();
                    int int_random = rand.nextInt(3);

                    for (int i = 0; i<nächsteFrage.listAntworten.size(); i++){  
                        String f = nächsteFrage.listAntworten.get(i);
                        if (f==nächsteFrage.richtigeAntwort || f==möglicheAntworten.get(int_random)){
                            System.out.println("Antwort [" + i + "]:" + f);
                        }else{
                            System.out.println("Antwort [" + i + "]: ...");
                        }
                    }
                    intAntwort = Integer.parseInt(System.console().readLine());
                }


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
                    
                    helper.pause(500);
                    System.out.print("\033[H\033[2J");
                    
                }else{
                    System.out.println("Leider Falsch! \n Du hast gezockt.. und nichts gewonnen... \n Viel Glück beim nächsten Mal!");
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
