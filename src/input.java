import java.util.Arrays;

public class input {
    // erlaubte Eingaben:
    // String: j, n
    // Integer: 0,1,2,3,5050

    private static String[] erlaubteStrings = {"j", "n"};
    private static int[] erlaubteInteger = {0,1,2,3,5050};

    public static String Menue(){
        boolean notPassed = true;

        while (notPassed){
            String strMenue = System.console().readLine();
            for ( int j=0; j<erlaubteStrings.length; j++){
                if (strMenue == erlaubteStrings[j]){
                    notPassed=false;
                    return strMenue;
                }
            }
            // keine korrekte Eingabe...
            System.out.println("Bitte nur die vorgegebenen Eingaben nutzen: " + Arrays.toString(erlaubteStrings));
        }
        
        return "u shall not pass"; // nur wegen compiler error
    }


    public static int Antwort(){
        boolean notPassed = true;

        while (notPassed){

            try {
                int intAntwort = Integer.parseInt(System.console().readLine());
                for ( int j=0; j<erlaubteInteger.length; j++){
                    if (intAntwort == erlaubteInteger[j]){
                        notPassed=false;
                        return intAntwort;
                    }
                }
            } catch (Exception e) { // keine Integer eingebene -> NumberFormatException
                notPassed=true; // nur für die Lesbarkeit
            }

            // keine korrekte Eingabe...
            System.out.println("Bitte nur die vorgegebenen Eingaben nutzen: " + Arrays.toString(erlaubteInteger));
        }
        
        return -1; // nur wegen compiler error
    }


}
