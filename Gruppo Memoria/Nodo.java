//CLASSE NODO

public class Nodo {
    /* -- ATTRIBUTI -- */ 
    String stazione;                    //Identificativo stazione
    String ieri;                        //Stringa contenente il path relativo del file di ieri
    String altroieri;                   //Stringa contenente il path relativo del file dell' altroieri
    Nodo nextList;                      //puntatore a nodo successivo nella lista principale
    Nodo nextSublist;                   //puntatore a nodo successivo nella sottolista*/

    /* -- COSTRUTTORI -- */
    public Nodo () {
        this.stazione = "";
        this.ieri = "";
        this.altroieri = "";
        this.nextList = null;                     //puntatore a nodo successivo nella lista principale
        this.nextSublist = null;
    }

    public Nodo (String a) {
        this.stazione = a;
        this.ieri = "";
        this.altroieri = "";
        this.nextList = null;                     //puntatore a nodo successivo nella lista principale
        this.nextSublist = null;
    }

    /* -- GETTER/SETTER -- */
    //Getter
    public String getStazione () {
        return this.stazione;
    }

    public String getIeri () {
        return this.ieri;
    }

    public String getAltroieri () {
        return this.altroieri;
    }

    public Nodo getNextList () {
        return this.nextList;
    }

    public Nodo getNextSublist () {
        return this.nextSublist;
    }

    //Setter

    public void setStazione (String a) {
        this.stazione = a;
    }

    public void setIeri (String a) {
        this.ieri = a;
    }

    public void setAltroieri (String a) {
        this.altroieri = a;
    }

    public void setNextList (Nodo tmp) {
        this.nextList = tmp;
    }

    public void setNextSublist (Nodo tmp) {
        this.nextSublist = tmp;
    }
}
