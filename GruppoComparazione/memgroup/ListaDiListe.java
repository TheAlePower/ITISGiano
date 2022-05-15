//CLASSE LISTADILISTE

package memgroup;

import java.io.File;
import java.time.LocalDateTime;

public class ListaDiListe {
    /* -- ATTRIBUTI --*/
    private Nodo head;                      //Nodo head della lista di liste

    /* -- COSTRUTTORI -- */
    public ListaDiListe () {
        this.head = null;
    }

    public ListaDiListe (Nodo n) {
        this.head = n;
    }

    /* -- GETTER/SETTER --*/
    //Getter
    public Nodo getHead () {
        return this.head;
    }

    //Setter
    public void setHead(Nodo head) {
        this.head = head;
    }

    //Ordine inserimento: Montebelluna, Alano, Bassano del Grappa, Thiene, Nove, Cavaso del Tomba
    //Bosco Chiesanuova, San M. di Saline, Desenzano del Garda
    //Sarzana, Carrara
    //Altopascio
    //Mestre

    /* -- METODI -- */
    public void createList () {                               //Metodo di creazione della Lista di liste delle adiacenze(grafo)
        //Lista Montebelluna
        Nodo wm1 = new Nodo("WM");
        head = wm1;

        Nodo wm2 = new Nodo("WE");
        wm1.setNextSublist(wm2);
        Nodo wm3 = new Nodo("XI");
        wm2.setNextSublist(wm3);
        Nodo wm4 = new Nodo("WL");
        wm3.setNextSublist(wm4);
        Nodo wm5 = new Nodo("WH");
        wm4.setNextSublist(wm5);
        Nodo wm6 = new Nodo("WP");
        wm5.setNextSublist(wm6);

        //Lista Alano
        Nodo we1 = new Nodo("WE");
        wm1.setNextList(we1);

        Nodo we2 = new Nodo("WM");
        we1.setNextSublist(we2);
        Nodo we3 = new Nodo("XI");
        we2.setNextSublist(we3);
        Nodo we4 = new Nodo("WL");
        we3.setNextSublist(we4);
        Nodo we5 = new Nodo("WH");
        we4.setNextSublist(we5);
        Nodo we6 = new Nodo("WP");
        we5.setNextSublist(we6);

        //Lista BdG
        Nodo xi1 = new Nodo("XI");
        we1.setNextList(xi1);

        Nodo xi2 = new Nodo("WM");
        xi1.setNextSublist(xi2);
        Nodo xi3 = new Nodo("WE");
        xi2.setNextSublist(xi3);
        Nodo xi4 = new Nodo("WL");
        xi3.setNextSublist(xi4);
        Nodo xi5 = new Nodo("WH");
        xi4.setNextSublist(xi5);
        Nodo xi6 = new Nodo("WP");
        xi5.setNextSublist(xi6);

        //Lista Thiene
        Nodo wl1 = new Nodo("WL");
        xi1.setNextList(wl1);

        Nodo wl2 = new Nodo("WM");
        wl1.setNextSublist(wl2);
        Nodo wl3 = new Nodo("WE");
        wl2.setNextSublist(wl3);
        Nodo wl4 = new Nodo("XI");
        wl3.setNextSublist(wl4);
        Nodo wl5 = new Nodo("WH");
        wl4.setNextSublist(wl5);
        Nodo wl6 = new Nodo("WP");
        wl5.setNextSublist(wl6);

        //Lista Nove
        Nodo wh1 = new Nodo("WH");
        wl1.setNextList(wh1);

        Nodo wh2 = new Nodo("WM");
        wh1.setNextSublist(wh2);
        Nodo wh3 = new Nodo("WE");
        wh2.setNextSublist(wh3);
        Nodo wh4 = new Nodo("XI");
        wh3.setNextSublist(wh4);
        Nodo wh5 = new Nodo("WL");
        wh4.setNextSublist(wh5);
        Nodo wh6 = new Nodo("WP");
        wh5.setNextSublist(wh6);

        //Lista Cavaso del Tomba
        Nodo wp1 = new Nodo("WP");
        wh1.setNextList(wp1);

        Nodo wp2 = new Nodo("WM");
        wp1.setNextSublist(wp2);
        Nodo wp3 = new Nodo("WE");
        wp2.setNextSublist(wp3);
        Nodo wp4 = new Nodo("XI");
        wp3.setNextSublist(wp4);
        Nodo wp5 = new Nodo("WL");
        wp4.setNextSublist(wp5);
        Nodo wp6 = new Nodo("WH");
        wp5.setNextSublist(wp6);

        //Lista Bosco Chiesanuova
        Nodo wa1 = new Nodo("WA");
        wp1.setNextList(wa1);

        Nodo wa2 = new Nodo("WS");
        wa1.setNextSublist(wa2);
        Nodo wa3 = new Nodo("WD");
        wa2.setNextSublist(wa3);

        //Lista San M di Saline
        Nodo ws1 = new Nodo("WS");
        wa1.setNextList(ws1);

        Nodo ws2 = new Nodo("WA");
        ws1.setNextSublist(ws2);
        Nodo ws3 = new Nodo("WD");
        ws2.setNextSublist(ws3);

        //Lista DdG
        Nodo wd1 = new Nodo("WD");
        ws1.setNextList(wd1);

        Nodo wd2 = new Nodo("WA");
        wd1.setNextSublist(wd2);
        Nodo wd3 = new Nodo("WS");
        wd2.setNextSublist(wd3);

        //Lista Sarzana
        Nodo xa1 = new Nodo("XA");
        wd1.setNextList(xa1);

        Nodo xa2 = new Nodo("WG");
        xa1.setNextSublist(xa2);

        //Lista Carrara
        Nodo wg1 = new Nodo("WG");
        xa1.setNextList(wg1);

        Nodo wg2 = new Nodo("XA");
        wg1.setNextSublist(wg2);

        //Lista Altopascio
        Nodo wf1 = new Nodo("WF");
        wg1.setNextList(wf1);

        //Lista Mestre
        Nodo wt1 = new Nodo("WT");
        wf1.setNextList(wt1);

        //head = wm1;
    }

    public void Stampa1 (Nodo a) {             //Metodo per testing che stampa la lista di liste delle adiacenze(grafo) delle varie stazioni
        System.out.print(a.getStazione());

        if (a.getNextSublist() != null) {
            System.out.print("-->");
            Stampa1(a.getNextSublist());
        }

        if (a.getNextList() != null) {
            System.out.print("\n");
            System.out.print("|\n");
            System.out.print("|\n");
            Stampa1(a.getNextList());
        }
    }

    public void Stampa2 (Nodo a) {                          //Metodo per testing che stampa il contenuto delle variabili 'ieri' e 'altroieri'
        System.out.print(a.getStazione() + "\n");         //di ogni nodo della lista principale
        System.out.print(a.getAltroieri() + "\n");
        System.out.print(a.getIeri() + "\n");

        if (a.getNextList() != null) {
            System.out.print("\n");
            Stampa2(a.getNextList());
        }
    }

    // --------------------------------------
    // Metodo per formattare la data con zeri
    // Input : LocalDateTime dt che rappresenta il tempo
    // Output : un tempo formattato con zeri sul giorno e mese, per evitare problemi con il file
    // --------------------------------------
    private static String formatDate(LocalDateTime dt) {
        return   (dt.getDayOfMonth() < 9 ? "0" + dt.getDayOfMonth() : String.valueOf(dt.getDayOfMonth()))
               + (dt.getMonth().getValue() < 9 ? "0" + dt.getMonth().getValue() : dt.getMonth().getValue())
               + ((dt.getYear()) - 2000);
    }

    public void RiempiLista (Nodo a) {
        String altroieri = a.getStazione() + formatDate(LocalDateTime.now().minusDays(2)) + "SA.xml";                       // Contiene il valore in data dell'altro ieri
        String ieri = a.getStazione() + formatDate(LocalDateTime.now().minusDays(1)) + "SA.xml";                            // Contiene il valore in data di ieri
        String directoryString = "";                // path relativa della directory
        File directory;                             // File che rappresenta la directory

        if (a.getStazione().equals("WM")) {
            directoryString = "./FilesSaXml/I3EME__SA/";

        } else if (a.getStazione().equals("WE")) {
            directoryString = "./FilesSaXml/SANZEN_SA/";

        } else if (a.getStazione().equals("XI")) {                   // Hey siamo noi
            directoryString = "./FilesSaXml/IU3IAH_SA/";

        } else if (a.getStazione().equals("WL")) {
            directoryString = "./FilesSaXml/THIENE_SA/";

        } else if (a.getStazione().equals("WH")) {
            directoryString = "./FilesSaXml/IQ3RK__SA/";

        } else if (a.getStazione().equals("WP")) {
            directoryString = "./FilesSaXml/IU3CLX_SA/";

        } else if (a.getStazione().equals("WA")) {
            directoryString = "./FilesSaXml/BOSCON_SA/";

        } else if (a.getStazione().equals("WS")) {
            directoryString = "./FilesSaXml/SMauro_SA/";

        } else if (a.getStazione().equals("WD")) {
            directoryString = "./FilesSaXml/NORZAN_SA/";

        } else if (a.getStazione().equals("XA")){
            directoryString = "./FilesSaXml/IK1XHH_SA/";

        } else if (a.getStazione().equals("WG")) {
            directoryString = "./FilesSaXml/I5NQK__SA/";

        } else if (a.getStazione().equals("WF")) {
            directoryString = "./FilesSaXml/I5NOD__SA/";

        } else if (a.getStazione().equals("WT")) {
            directoryString = "./FilesSaXml/IK3ITV_SA/";

        }

        directory = new File(directoryString);

        // Se directory esiste
        if (directory.exists()) {
            // Se ieri esiste
            if (new File(directoryString + ieri).exists()) {
                // Lo settiamo
                a.setIeri(directoryString + ieri);
            }

            // Se altroieri esiste
            if (new File(directoryString + altroieri).exists()) {
                // Lo settiamo
                a.setAltroieri(directoryString + altroieri);
            }
        }

        // Se la prossima sottolista non è vutoa
        if (a.getNextSublist() != null) {
            // Chiamiamo la riempitura
            RiempiLista(a.getNextSublist());
        }

        // Se la prossima lista non è vuota
        if (a.getNextList() != null) {
            // Chiamiamo la riempitura
            RiempiLista(a.getNextList());
        }
    }
}
