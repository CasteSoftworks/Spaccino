import java.util.HashMap;
import java.util.Map;

/**
 * Rappresenta un commerciante (Dealer) nel gioco "Candy Empire".
 * Un Dealer possiede un inventario di articoli e una quantità di denaro che può essere
 * utilizzata per acquistare e vendere beni sul mercato.
 */
public class Dealer {

    private int soldi;
    private final String nome;
    private HashMap<String, QuantityPricePair> inventario;

    /**
     * Classe che rappresenta una coppia di quantità e prezzo di un articolo.
     * Questa classe viene utilizzata per memorizzare la quantità e il prezzo d'acquisto
     * di ogni articolo nel inventario del Dealer.
     */
    public static class QuantityPricePair {
        private int quantita;
        private int prezzo;

        /**
         * Costruisce una nuova coppia di quantità e prezzo.
         * 
         * @param quantita la quantità dell'articolo
         * @param prezzo il prezzo d'acquisto dell'articolo
         */
        public QuantityPricePair(int quantita, int prezzo) {
            this.quantita = quantita;
            this.prezzo = prezzo;
        }

        /**
         * Restituisce la quantità dell'articolo.
         * 
         * @return la quantità dell'articolo
         */
        public int getQuantita() {
            return quantita;
        }

        /**
         * Imposta la quantità dell'articolo.
         * 
         * @param quantita la nuova quantità dell'articolo
         */
        public void setQuantita(int quantita) {
            this.quantita = quantita;
        }

        /**
         * Restituisce il prezzo d'acquisto dell'articolo.
         * 
         * @return il prezzo d'acquisto dell'articolo
         */
        public int getPrezzo() {
            return prezzo;
        }

        /**
         * Imposta il prezzo d'acquisto dell'articolo.
         * 
         * @param prezzo il nuovo prezzo d'acquisto dell'articolo
         */
        public void setPrezzo(int prezzo) {
            this.prezzo = prezzo;
        }
    }

    /**
     * Crea un nuovo Dealer con un nome e un ammontare iniziale di denaro.
     * 
     * @param user  il nome del Dealer
     * @param denaro la quantità iniziale di denaro del Dealer
     */
    public Dealer(String user, int denaro) {
        this.nome = user;
        this.soldi = denaro;
        this.inventario = new HashMap<>();
    }

    /**
     * Restituisce il nome del Dealer.
     * 
     * @return il nome del Dealer
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la quantità di denaro posseduta dal Dealer.
     * 
     * @return il denaro posseduto dal Dealer
     */
    public int getSoldi() {
        return this.soldi;
    }

    /**
     * Aggiunge una somma al denaro posseduto dal Dealer.
     * 
     * @param nuovi la somma da aggiungere
     */
    public void addSoldi(int nuovi) {
        this.soldi += nuovi;
    }

    /**
     * Rimuove una somma dal denaro posseduto dal Dealer.
     * 
     * @param nuovi la somma da rimuovere
     */
    public void removeSoldi(int nuovi) {
        this.soldi -= nuovi;
    }

    /**
     * Permette al Dealer di acquistare un articolo specifico.
     * Se il Dealer ha abbastanza denaro, l'articolo viene aggiunto al suo inventario.
     * 
     * @param cosa   il nome dell'articolo da acquistare
     * @param quanti la quantità da acquistare
     * @param prezzo il prezzo unitario dell'articolo
     */
    public void compra(String cosa, int quanti, int prezzo) {
        int spesa = quanti * prezzo;
        if (spesa > getSoldi()) {
            System.out.println("\tNon te lo puoi permettere");
        } else {
            removeSoldi(spesa);
            aggiungiInventario(cosa, quanti, prezzo);
        }
    }

    /**
     * Permette al Dealer di vendere un articolo specifico.
     * Se il Dealer possiede abbastanza unità dell'articolo, l'articolo viene venduto
     * e il denaro guadagnato viene aggiunto al suo saldo.
     * 
     * @param cosa   il nome dell'articolo da vendere
     * @param quanti la quantità da vendere
     * @param prezzo il prezzo unitario dell'articolo
     */
    public void vendi(String cosa, int quanti, int prezzo) {
        if (!inventario.containsKey(cosa)) {
            System.out.println("\tNon puoi vendere ciò che non possiedi");
            return;
        }

        int guadagno = quanti * prezzo;
        if (inventario.get(cosa).getQuantita() < quanti) {
            System.out.println("\tNon ne hai abbastanza, ne possiedi " + inventario.get(cosa).getQuantita());
            return;
        } else {
            addSoldi(guadagno);
            rimuoviInventario(cosa, quanti);
            System.out.println("\tHai venduto " + quanti + " unità di " + cosa + " a " + prezzo + "$ guadagnando " + guadagno + "$.");
        }
    }

    /**
     * Aggiunge una quantità di un articolo all'inventario del Dealer.
     * Se l'articolo è già presente, la quantità viene aggiornata e il prezzo viene ricalcolato
     * come la media ponderata del prezzo precedente e del nuovo prezzo.
     * 
     * @param cosa     il nome dell'articolo
     * @param quantita la quantità da aggiungere
     * @param prezzo   il prezzo d'acquisto dell'articolo
     */
    public void aggiungiInventario(String cosa, int quantita, int prezzo) {
        if (inventario.containsKey(cosa)) {
            QuantityPricePair pair = inventario.get(cosa);
            int oldQuantita = pair.getQuantita();
            int oldPrezzo = pair.getPrezzo();
            int nuovoPrezzo = (oldPrezzo * oldQuantita + prezzo * quantita) / (oldQuantita + quantita);
            pair.setQuantita(oldQuantita + quantita);
            pair.setPrezzo(nuovoPrezzo);
        } else {
            inventario.put(cosa, new QuantityPricePair(quantita, prezzo));
        }
    }

    /**
     * Rimuove una quantità di un articolo dall'inventario del Dealer.
     * Se la quantità rimanente di un articolo è zero, l'articolo viene rimosso dall'inventario.
     * 
     * @param cosa     il nome dell'articolo
     * @param quantita la quantità da rimuovere
     */
    public void rimuoviInventario(String cosa, int quantita) {
        if (inventario.containsKey(cosa)) {
            QuantityPricePair pair = inventario.get(cosa);
            int oldQuantita = pair.getQuantita();
            oldQuantita -= quantita;
            pair.setQuantita(oldQuantita);
            if (pair.getQuantita() == 0) {
                inventario.remove(cosa);
            }
        }
    }

    /**
     * Restituisce l'inventario del Dealer.
     * 
     * @return una mappa contenente gli articoli e le loro quantità e prezzi d'acquisto
     */
    public HashMap<String, QuantityPricePair> getInventario() {
        return inventario;
    }

    /**
     * Stampa l'inventario del Dealer in un formato leggibile,
     * mostrando per ciascun articolo la quantità e il prezzo d'acquisto.
     */
    public void stampaInventario() {
        System.out.println("-".repeat(80));
        for (Map.Entry<String, QuantityPricePair> entry : inventario.entrySet()) {
            String nomeArticolo = entry.getKey();
            int quantita = entry.getValue().getQuantita();
            int prezzo = entry.getValue().getPrezzo();
            System.out.println(nomeArticolo + ": " + quantita + " - Prezzo d'acquisto: " + prezzo + "$");
        }
        System.out.println("-".repeat(80));
    }
}
