import java.io.*;
import java.util.*;

public class Salva {

    public static void salvaSuFile(String testo, int numero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("salvataggio.txt", true))) {
            writer.write(testo + " : " + numero);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file: " + e.getMessage());
        }

        riordinaFile("salvataggio.txt");
    }

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

    public static void leggiPunteggi(String nomeFile){
        System.out.println("-".repeat(80));
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
            return;
        }
        System.out.println("-".repeat(80));

    }

    // Classe per rappresentare una riga del file
    static class Entry {
        private final String testo;
        private final int numero;

        public Entry(String testo, int numero) {
            this.testo = testo;
            this.numero = numero;
        }

        public String getTesto() {
            return testo;
        }

        public int getNumero() {
            return numero;
        }
    }
}
