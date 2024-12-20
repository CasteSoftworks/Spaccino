import java.util.HashMap;
import java.util.Scanner;

class Game {
    private Dealer dealer;
    private Market market;
    private RandomEvents randomEvents;
    private int days;

    public Game(String playerName, int initialMoney, int days) {
        this.dealer = new Dealer(playerName, initialMoney);
        this.market = new Market();
        this.randomEvents = new RandomEvents();
        this.days = days;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (int day = 1; day <= days; day++) {
            int lungTratt=(72-String.valueOf(day).length())/2;
            System.out.println("\n"+"-".repeat(lungTratt)+" Giorno " + day + " "+"-".repeat(lungTratt-1));

            // Genera e applica un evento
            RandomEvents.Event event = randomEvents.triggerEvent();
            market.applyEvent(event);
            boolean nuovoGG=true;

            while(nuovoGG){
                System.out.println("-".repeat(80));
                System.out.println("| Giorno: "+day+" | $: "+dealer.getSoldi());
                System.out.println("-".repeat(80));

                // Mostra sostanze disponibili
                System.out.println("Sostanze disponibili sul mercato:");
                for (HashMap.Entry<String, Integer> entry : market.getAvailableCandies().entrySet()) {
                    System.out.println(entry.getKey() + " - Prezzo: $" + entry.getValue());
                }

                // Mostra inventario
                dealer.getInventario();

                // Interazione con il giocatore
                System.out.println("Che cosa vuoi fare? [compra/vendi/inventario/prossimo]");
                String action = scanner.nextLine().toLowerCase();

                
                switch (action) {
                    case "compra":
                        System.out.println("Quale sostanza vuoi comprare?");
                        String buyCandy = scanner.nextLine();
                        buyCandy=primaInUpper(buyCandy);
                        System.out.println("Quante unità vuoi comprare?");
                        int buyQuantity = scanner.nextInt();
                        scanner.nextLine();

                        if (market.getAvailableCandies().containsKey(buyCandy)) {
                            int price = market.getAvailableCandies().get(buyCandy);
                            dealer.compra(buyCandy, buyQuantity, price);
                        } else {
                            System.out.println("\n\tQuesta sostanza non è disponibile!");
                        }

                        scanner.nextLine();
                        break;

                    case "vendi":
                        System.out.println("Quale sostanza vuoi vendere?");
                        String sellCandy = scanner.nextLine();
                        sellCandy=primaInUpper(sellCandy);
                        System.out.println("Quante unità vuoi vendere?");
                        int sellQuantity = scanner.nextInt();
                        scanner.nextLine();

                        if (market.getAvailableCandies().containsKey(sellCandy)) {
                            int price = market.getAvailableCandies().get(sellCandy);
                            dealer.vendi(sellCandy, sellQuantity, price);
                        } else {
                            System.out.println("\n\tQuesta sostanza non è disponibile!");
                        }
                        scanner.nextLine();
                        break;

                    case "prossimo":
                        System.out.println("\n\tHai deciso passare al prossimo giorno.");
                        nuovoGG=false;
                        scanner.nextLine();
                        break;

                    case "inventario":
                        dealer.stampaInventario();
                        scanner.nextLine();
                        break;

                    default:
                        System.out.println("Azione non valida.");
                        scanner.nextLine();

                }
                pulisciSchermo();
            }

            // Aggiorna mercato
            market.updatePrices();
            market.updateAvailableCandies();

            pulisciSchermo();
        }

        // Fine del gioco
        System.out.println("\nIl gioco è terminato! Ecco il tuo risultato:");
        dealer.getInventario();
        System.out.println("Soldi rimanenti: $" + dealer.getSoldi());
        Salva.salvaSuFile(dealer.getNome(), dealer.getSoldi());
        scanner.close();
    }

    public void pulisciSchermo(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String primaInUpper(String input){
        return (input.substring(0,1).toUpperCase()+input.substring(1).toLowerCase());
    }
}