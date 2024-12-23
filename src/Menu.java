import java.util.Scanner;

/**
 * La classe <code>Menu</code> gestisce l'interfaccia principale dell'applicazione, mostrando il menu e permettendo
 * all'utente di selezionare diverse opzioni, come avviare il gioco, visualizzare la tabella dei punteggi o uscire.
 */
public class Menu {
    
    /**
     * Mostra il menu principale e gestisce le scelte dell'utente.
     * Il menu consente di avviare il gioco, visualizzare i punteggi salvati o uscire dall'applicazione.
     * L'input dell'utente viene letto in un ciclo, con un controllo di validità per assicurarsi che sia un numero intero.
     */
    public static void mostraMenu(){
        System.out.println("-".repeat(80));
        printCandyEmpire();
        System.out.println("-".repeat(80));
        System.out.println(" 1 - Gioca\n 2 - Tabella Punteggi\n 3 - Esci\n"+"-".repeat(80));
        
        try(Scanner scanner = new Scanner(System.in)){
            while(true){
                if(!scanner.hasNextInt()){
                    System.out.println("\t\tcomando errato - Inserisci un  numero");
                    scanner.next();
                }else{
                    int input = scanner.nextInt();
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

    /**
     * Stampa l'ASCII art del titolo del gioco "Candy Empire".
     * L'arte viene centrata per una larghezza di 80 caratteri.
     */
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
            System.out.println(" ".repeat((80-45)/2) + String.format("%-80s", line));
        }
    }

    /**
     * Mostra il menu per avviare una nuova partita, chiedendo all'utente il nome del giocatore,
     * il capitale iniziale e il numero di giorni di gioco.
     * Crea e avvia un oggetto <code>Game</code> con le informazioni inserite dall'utente.
     */
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
