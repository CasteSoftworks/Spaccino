import java.util.Scanner;

public class Menu {
    public static void mostraMenu(){
        System.out.println("-".repeat(80));
        printCandyEmpire();
        System.out.println("-".repeat(80));
        System.out.println(" 1 - Gioca\n 2 - Tabella Punteggi\n 3 - Esci\n"+"-".repeat(80));
        try(Scanner scanner = new Scanner(System.in)){
            while(true){
                if(!scanner.hasNextInt()){
                    System.out.println("\t\tcomando errato");
                }else{
                    int input=scanner.nextInt();
                    switch (input) {
                        case 1:
                            startGameMenu();
                            break;
                            
                        case 2:
                            Save.leggiPunteggi("salvataggio.txt");
                            break;
                        case 3:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("\t\tcomando errato");
                            break;
                    }
                }
                    
            }
        }
            
    }

    public static void printCandyEmpire() {
        String[] candyEmpireArt = {
            " SSSS PPPP    A    CCCC  CCCC III N   N  OOO ",
            "S     P   P  A A  C     C      I  NN  N O   O",
            " SSS  PPPP  AAAAA C     C      I  N N N O   O",
            "    S P     A   A C     C      I  N  NN O   O",
            "SSSS  P     S   S  CCCC  CCCC III N   N  OOO"
        };

        // Stampa l'arte in ASCII art mantenendo la larghezza di 80 caratteri
        for (String line : candyEmpireArt) {
            System.out.println(" ".repeat((80-45)/2)+String.format("%-80s", line));
        }
    }

    public static void startGameMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il tuo nome: ");
        String playerName = scanner.nextLine();
                
        System.out.print("Inserisci il capitale iniziale: ");
        int initialMoney = scanner.nextInt();
                
        System.out.print("Inserisci il numero di giorni di gioco: ");
        int days = scanner.nextInt();

        Game game = new Game(playerName, initialMoney, days);
        game.start();

        scanner.close();
    }
}
