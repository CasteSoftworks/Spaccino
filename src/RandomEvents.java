import java.util.Arrays;
import java.util.List;
import java.util.Random;

class RandomEvents {
    private List<Event> events;
    private Random random;

    public RandomEvents() {
        this.random = new Random();
        events = Arrays.asList(
            new Event("La polizia sta arrestando molti rivali", "Il prezzo di tutte le sostanze aumenta del 20%", 1.2,""),
            new Event("Una nuova partita di ---- è arrivata in città", "Il prezzo di ---- diminuisce del 20%", 0.8,""),
            new Event("Una partita di ---- è stata tagliata male", "Il prezzo di ---- crolla", 0.2, null),
            new Event("Un nuovo spacciatore di ---- è arrivato in città", "Il prezzo della ---- scende", 0.8, ""),
            new Event("Uno spacciatore di ---- è stato ucciso", "Il prezzo della ---- cresce vertiginosamente", 2, "")
        );
    }

    public Event triggerEvent() {
        // Probabilità del 30% che un evento si verifichi
        if (random.nextDouble() > 0.3) {
            return null; // Nessun evento
        }

        // Se un evento si verifica, selezionane uno casuale
        return events.get(random.nextInt(events.size()));
    }

    // Classe interna per rappresentare un evento
    public static class Event {
        private String title;
        private String description;
        private double priceModifier; //price modifyer
        private String modifier; //if it is not blank only the specified drug has the change

        public Event(String title, String description, double priceModifier, String modifier) {
            this.title = title;
            this.description = description;
            this.priceModifier = priceModifier;
            this.modifier=modifier;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public double getPriceModifier() {
            return priceModifier;
        }
        public String getModifier() {
            return modifier;
        }
    }
}