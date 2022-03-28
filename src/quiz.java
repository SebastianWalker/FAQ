public class quiz {
    public static void main(String[] args) {

        player player = new player();
        //Frage nächsteFrage = new Frage();
        //Integer intAntwort;
        //Fragen meineFragen = new Fragen();

        System.out.println("Hallo Player!");
        System.out.println("Wie ist dein Name?");

        player.name = System.console().readLine();
 
        System.out.print("Hi ");
        System.out.println(player.name);

        //System.out.println("Bitte warte, während ich die Fragen vorbereite...");
        //meineFragen.FragenEinlesen();
        //helper.pause(1000); // just wait 
        boolean goAgain = true;
        while (goAgain){

        System.out.println("Welche Variante willst du spielen?");
        System.out.println("Echte Zocker schreiben 'yeah'.. alles andere ist langweilig!");

        // ToDo: Falsche Eingaben abfangen
        player.zocker = System.console().readLine().equals("yeah") ? true : false ; 
        
        if (player.zocker){ // 0 = true
            // while loop... weiter solange nicht falsch geantwortet oder max level nicht erreicht
            System.out.println("Nice, du alter Zocker!!!");
            playGame.run(player);
        }else{ // 1 = false
            System.out.println("Wie langweilig... aber ok.");
            player.joker5050used = true;
            playGame.run(player);
        } // ende von IF

        System.out.println("\nWillst du noch eine Runde dein Glück versuchen? \n[j]: klar doch! her mit den Nüssen! \n[n]: ich hab mich schon genug blamiert..");
        goAgain = System.console().readLine().equals("j") ? true : false;
        System.out.print("\033[H\033[2J");

    }
    } // ende von main

} // ende der Klasse
