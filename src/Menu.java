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
            "  CCCC AAAAA  N   N DDDD Y   Y    EEEEE M   M PPPP III RRRR  EEEEE",
            " C     A   A  NN  N D   D Y Y     E     MM MM P   P I  R   R E     ",
            " C     AAAAA  N N N D   D  Y      EEEE  M M M PPPP  I  RRRR  EEEE  ",
            " C     A   A  N  NN D   D  Y      E     M   M P     I  R  R  E     ",
            "  CCCC A   A  N   N DDDD   Y      EEEEE M   M P    III R   R EEEEE",
        };

        // Stampa l'arte in ASCII art mantenendo la larghezza di 80 caratteri
        for (String line : candyEmpireArt) {
            System.out.println(String.format("%-80s", line));
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
