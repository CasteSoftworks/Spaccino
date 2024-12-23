import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * La classe <code>RandomEvents</code> gestisce gli eventi casuali che possono influenzare i prezzi delle sostanze nel gioco.
 * Gli eventi vengono attivati con una probabilità del 30%, e possono avere effetti variabili sui prezzi delle sostanze disponibili.
 */
class RandomEvents {
    private List<Event> events;
    private Random random;

    /**
     * Costruttore della classe <code>RandomEvents</code>.
     * Inizializza la lista degli eventi e la generazione casuale di eventi.
     */
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

    /**
     * Attiva un evento casuale con una probabilità del 30%.
     * Se l'evento non si verifica, restituisce <code>null</code>.
     * 
     * @return Un oggetto <code>Event</code> se l'evento si verifica, altrimenti <code>null</code>.
     */
    public Event triggerEvent() {
        // Probabilità del 30% che un evento si verifichi
        if (random.nextDouble() > 0.3) {
            return null; // Nessun evento
        }

        // Se un evento si verifica, selezionane uno casuale
        return events.get(random.nextInt(events.size()));
    }

    /**
     * La classe <code>Event</code> rappresenta un singolo evento che può accadere nel gioco, influenzando i prezzi delle sostanze.
     * Ogni evento ha un titolo, una descrizione, un modificatore di prezzo e un modificatore opzionale che specifica quale sostanza 
     * è influenzata dall'evento.
     */
    public static class Event {
        private String title;
        private String description;
        private double priceModifier; // Modificatore di prezzo
        private String modifier; // Se non vuoto, indica quale sostanza è influenzata

        /**
         * Costruttore della classe <code>Event</code>.
         * 
         * @param title Il titolo dell'evento.
         * @param description La descrizione dell'evento.
         * @param priceModifier Il modificatore di prezzo per l'evento.
         * @param modifier Se presente, indica quale sostanza è influenzata dall'evento.
         */
        public Event(String title, String description, double priceModifier, String modifier) {
            this.title = title;
            this.description = description;
            this.priceModifier = priceModifier;
            this.modifier = modifier;
        }

        /**
         * Restituisce il titolo dell'evento.
         * 
         * @return Il titolo dell'evento.
         */
        public String getTitle() {
            return title;
        }

        /**
         * Restituisce la descrizione dell'evento.
         * 
         * @return La descrizione dell'evento.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Restituisce il modificatore di prezzo dell'evento.
         * 
         * @return Il modificatore di prezzo dell'evento.
         */
        public double getPriceModifier() {
            return priceModifier;
        }

        /**
         * Restituisce il modificatore della sostanza influenzata dall'evento.
         * 
         * @return Il modificatore della sostanza, o <code>null</code> se non specificato.
         */
        public String getModifier() {
            return modifier;
        }
    }
}
