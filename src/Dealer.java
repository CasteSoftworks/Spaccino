import java.util.HashMap;
import java.util.Map;

public class Dealer {
    private int soldi;
    private final String nome;

    private HashMap<String, Integer> inventario;

    public Dealer(String user, int denaro){
        this.nome=user;
        this.soldi=denaro;
        this.inventario=new HashMap<>();
    }

    public String getNome(){
        return this.nome;
    }

    public int getSoldi(){
        return this.soldi;
    }

    public void addSoldi(int nuovi){
        this.soldi+=nuovi;
    }

    public void removeSoldi(int nuovi){
        this.soldi-=nuovi;
    }

    public void compra(String cosa, int quanti, int prezzo){
        int spesa=quanti*prezzo;
        if(spesa > getSoldi()){
            System.out.println("\tNon te lo puoi permettere");
        }else{
            removeSoldi(spesa);
            aggiungiInventario(cosa,quanti);
        }
    }

    public void vendi(String cosa, int quanti, int prezzo){
        if(!inventario.containsKey(cosa)){
            System.out.println("\tNon puoi vendere ciò che non possiedi");
            return;
        }

        int guadagno=quanti*prezzo;
        if(inventario.get(cosa)<quanti){
            System.out.println("\tNon ne hai abbastanza, ne possiedi "+inventario.get(cosa));
            return;
        }else{
            addSoldi(guadagno);
            rimuoviInventario(cosa,quanti);
            System.out.println("\tHai venduto "+quanti+" unità di "+cosa+" a "+prezzo+"$ guadagnando "+guadagno+"$");
        }
    }

    public void aggiungiInventario(String cosa, int quantita){
        if(inventario.containsKey(cosa)){
            int old=inventario.get(cosa);
            quantita+=old;
            inventario.put(cosa, quantita);
        }else{
            inventario.put(cosa, quantita);
        }
    }

    public void rimuoviInventario(String cosa, int quantita){
        if(inventario.containsKey(cosa)){
            int old=inventario.get(cosa);
            old-=quantita;
            inventario.put(cosa, old);
            if(inventario.get(cosa)==0){
                inventario.remove(cosa);
            }
        }
    }

    public HashMap<String,Integer> getInventario(){
        return inventario;
    }

    public void stampaInventario() {
        HashMap<String, Integer> inventario = getInventario();
        
        System.out.println("-".repeat(80));
        for (Map.Entry<String, Integer> elem : inventario.entrySet()) {
            System.out.println(elem.getKey() + " : " + elem.getValue());
        }
        System.out.println("-".repeat(80));
    }

}
