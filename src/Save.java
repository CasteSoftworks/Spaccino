import java.io.*;
import java.util.*;

/**
 * La classe <code>Save</code> gestisce le operazioni di salvataggio, lettura e ordinamento dei punteggi su file.
 * Supporta la scrittura di nuove voci nel file, la lettura dei punteggi salvati e l'ordinamento dei punteggi per visualizzarli in ordine decrescente.
 */
public class Save {

    /**
     * Salva una nuova voce di punteggio su file.
     * Aggiunge un nuovo punteggio nel file di salvataggio e successivamente ordina il file.
     * 
     * @param testo La stringa che rappresenta il nome del giocatore o la descrizione del punteggio.
     * @param numero Il punteggio associato al giocatore.
     */
    public static void salvaSuFile(String testo, int numero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("salvataggio.txt", true))) {
            writer.write(testo + " : " + numero);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file: " + e.getMessage());
        }

        riordinaFile("salvataggio.txt");
    }

    /**
     * Riordina il contenuto del file di salvataggio.
     * Legge le voci dal file, le ordina prima per punteggio decrescente e poi per testo alfabetico,
     * e infine riscrive il file con le voci ordinate.
     * 
     * @param nomeFile Il nome del file da ordinare.
     */
    public static void riordinaFile(String nomeFile) {
        List<Entry> entries = new ArrayList<>();

        // Legge il file esistente e aggiunge i dati alla lista
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" : ");
                if (parts.length == 2) {
                    String text = parts[0];
                    int number = Integer.parseInt(parts[1]);
                    entries.add(new Entry(text, number));
                }
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
            return;
        }

        // Ordina la lista: numeri decrescenti, stringhe alfabetiche
        entries.sort(Comparator.comparingInt(Entry::getNumero).reversed()
                .thenComparing(Entry::getTesto));

        // Riscrive il file ordinato
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            for (Entry entry : entries) {
                writer.write(entry.getTesto() + " : " + entry.getNumero());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file: " + e.getMessage());
        }
    }

    /**
     * Legge e visualizza i punteggi salvati nel file.
     * Stampa ogni riga del file di salvataggio, mostrando i punteggi dei giocatori.
     * 
     * @param nomeFile Il nome del file da leggere.
     */
    public static void leggiPunteggi(String nomeFile) {
        System.out.println("-".repeat(80));
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file:\n" + e.getMessage());
            System.out.println("-".repeat(80));
            return;
        }
        System.out.println("-".repeat(80));
    }

    /**
     * La classe <code>Entry</code> rappresenta una voce nel file di salvataggio, 
     * contenente il nome del giocatore (o una descrizione) e il relativo punteggio.
     */
    static class Entry {
        private final String testo;
        private final int numero;

        /**
         * Costruttore della classe <code>Entry</code>.
         * 
         * @param testo Il nome del giocatore o la descrizione del punteggio.
         * @param numero Il punteggio del giocatore.
         */
        public Entry(String testo, int numero) {
            this.testo = testo;
            this.numero = numero;
        }

        /**
         * Restituisce il testo della voce di salvataggio.
         * 
         * @return Il testo (nome del giocatore o descrizione).
         */
        public String getTesto() {
            return testo;
        }

        /**
         * Restituisce il numero (punteggio) associato alla voce.
         * 
         * @return Il punteggio del giocatore.
         */
        public int getNumero() {
            return numero;
        }
    }
}
