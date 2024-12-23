import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Market {
    private List<Candy> allCandies; // Lista globale di tutte le "sostanze"
    private Map<String, Integer> availableCandies; // Sostanze disponibili oggi con prezzi
    private Random random;

    public Market() {
        random = new Random();
        allCandies = Arrays.asList(
            new Candy("Erba", 1),         // Comune
            new Candy("Speed", 2),       // Non comune
            new Candy("PCP", 3),         // Raro
            new Candy("Peyote", 3),      // Raro
            new Candy("Funghetti", 2),   // Non comune
            new Candy("Oppio", 3),       // Raro
            new Candy("LSD", 4),         // Molto raro
            new Candy("MDMA", 3),        // Raro
            new Candy("Eroina", 5),      // Rarissimo
            new Candy("Cocaina", 5),     // Rarissimo
            new Candy("Ecstasy", 3),     // Raro
            new Candy("Hashish", 2),     // Non comune
            new Candy("Crack", 4),       // Molto raro
            new Candy("Ketamina", 4)     // Molto raro
        );
        availableCandies = new HashMap<>();
        updateAvailableCandies(); // Genera una lista iniziale di sostanze disponibili
    }

    public Map<String, Integer> getAvailableCandies() {
        return availableCandies;
    }

    public void updateAvailableCandies() {
        // Resetta le sostanze disponibili
        availableCandies.clear();
        // Estrae tra 5 e 10 sostanze casuali dalla lista globale
        int numberOfCandies = random.nextInt(6) + 5; // Da 5 a 10
        List<Candy> selectedCandies = new ArrayList<>(allCandies);
        Collections.shuffle(selectedCandies);

        for (int i = 0; i < numberOfCandies; i++) {
            Candy candy = selectedCandies.get(i);
            int basePrice = candy.getRarity() * 100; // Prezzo base proporzionale alla rarità
            int priceFluctuation = random.nextInt(101) - 50; // Fluttuazione tra -5 e +5
            int finalPrice = Math.max(1, basePrice + priceFluctuation);
            availableCandies.put(candy.getName(), finalPrice);
        }
    }

    public void updatePrices() {
        // Aggiorna i prezzi solo delle sostanze attualmente disponibili
        for (String candyName : availableCandies.keySet()) {
            int change = random.nextInt(11) - 5; // Fluttuazione tra -5 e +5
            availableCandies.put(candyName, Math.max(1, availableCandies.get(candyName) + change));
        }
    }

    public void applyEvent(RandomEvents.Event event) {
        if (event == null) {
            System.out.println("Oggi non accade nulla di particolare.");
            return;
        }

        String eventTitle = event.getTitle();
        String eventDescription = event.getDescription();
        double priceModifier = event.getPriceModifier();
        String modifier = event.getModifier();

        // Se l'evento contiene "----", sostituisci con una droga disponibile
        if (eventTitle.contains("----") || eventDescription.contains("----")) {
            List<String> availableDrugNames = new ArrayList<>(availableCandies.keySet());
            if (!availableDrugNames.isEmpty()) {
                String randomDrug = availableDrugNames.get(random.nextInt(availableDrugNames.size()));
                eventTitle = eventTitle.replace("----", randomDrug);
                eventDescription = eventDescription.replace("----", randomDrug);

                if (modifier.isEmpty()) {
                    modifier = randomDrug;
                }
            }
        }

        System.out.println("Evento del giorno: " + eventTitle);
        System.out.println(eventDescription);

        // Modifica i prezzi in base all'evento
        for (Map.Entry<String, Integer> entry : availableCandies.entrySet()) {
            String candyName = entry.getKey();
            int oldPrice = entry.getValue();

            if (modifier.isEmpty() || modifier.equals(candyName)) {
                int newPrice = (int) Math.max(1, oldPrice * priceModifier);
                availableCandies.put(candyName, newPrice); // Aggiorna il prezzo
            }
        }
    }

    // Classe interna per rappresentare una "sostanza"
    public static class Candy {
        private String name;
        private int rarity; // Rarità della sostanza

        public Candy(String name, int rarity) {
            this.name = name;
            this.rarity = rarity;
        }

        public String getName() {
            return name;
        }

        public int getRarity() {
            return rarity;
        }
    }
}