import java.util.Arrays;
import java.util.List;
import java.util.Random;

class RandomEvents {
    private List<Event> events;
    private Random random;

    public RandomEvents() {
        this.random = new Random();
        events = Arrays.asList(
            new Event("Un rivale è stato arrestato", "Il prezzo di tutte le sostanze aumenta del 20%", 1.2),
            new Event("Una nuova partita è arrivata in città", "Il prezzo di tutte le sostanze diminuisce del 20%", 0.8),
            new Event("Una zona è sotto controllo di un rivale", "Il prezzo di alcune sostanze aumenta del 50%", 1.5),
            new Event("La polizia è in agguato", "Il prezzo di alcune sostanze diminuisce del 50%", 0.5)
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
        private double priceModifier; // Modificatore del prezzo

        public Event(String title, String description, double priceModifier) {
            this.title = title;
            this.description = description;
            this.priceModifier = priceModifier;
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
    }
}