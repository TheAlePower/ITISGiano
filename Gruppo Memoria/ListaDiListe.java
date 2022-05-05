//CLASSE LISTADILISTE

import java.io.File;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;  
import java.time.format.DateTimeFormatter;

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

    public void RiempiLista (Nodo a) {                      //Metodo che "riempie" la lista di liste assegnando, per ogni stazione della lista
        String altroieri;                              //principale, alle due variabili 'ieri' e 'altroieri' i path relativi dei file
        String ieri; 
        String dir = "";
        File entity = new File("");                

        if (a.getStazione().equals("WM")) {                         //Non ho usato uno Switch case poiché mi dava problemi con le stringhe
            //dir = "./FilesSaXml/I3EME__SA/";
            dir = "./FilesSaXml/I3EME__SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WE")) {
            //dir = "./FilesSaXml/SANZEN_SA/";
            dir = "./FilesSaXml/I3EME__SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("XI")) {
            dir = "./FilesSaXml/IU3IAH_SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WL")) {
            dir = "./FilesSaXml/THIENE_SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WH")) {
            dir = "./FilesSaXml/IQ3RK__SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WP")) {
            dir = "./FilesSaXml/IU3CLX_SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WA")) {
            dir = "./FilesSaXml/BOSCON_SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WS")) {
            dir = "./FilesSaXml/SMauro_SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WD")) {
            dir = "./FilesSaXml/NORZAN_SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("XA")){
            dir = "./FilesSaXml/I5NQK__SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WG")) {
            dir = "./FilesSaXml/I5NQK__SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WF")) {
            dir = "./FilesSaXml/I5NOD__SA/";
            entity = new File(dir);
        }else if (a.getStazione().equals("WT")) {
            dir = "./FilesSaXml/IK3ITV_SA/";
            entity = new File(dir);
        }
        
        if (entity.exists()) {
            String list [] = entity.list();                                         //"XI040522.xml"

            if (list.length == 2) {
                //Ci sono solo i file di ieri e dell'altroieri

                //variabile terminanti in ...1 -> Altoieri
                //variabile terminanti in ...2 -> Ieri

                //Divido il nome del file e estraggo la data di entrambi i file<FUNZIONA>
                String stripXml1 = list[0].substring(0, 8);                      //"XI040522SA" - Altroieri
                String stripXml2 = list[1].substring(0, 8);                      //Ieri
                String finalList1 [] = {
                    stripXml1.substring(2, 4),                                   //"04"
                    stripXml1.substring(4, 6),                                   //"05"
                    stripXml1.substring(6, 8),                                   //"22"
                };
                String finalList2 [] = {
                    stripXml2.substring(2, 4),
                    stripXml2.substring(4, 6),
                    stripXml2.substring(6, 8),
                };

                String finalFile1 = finalList1[2] + finalList1[1] + finalList1[0];                      //"220504" - inverto il formato della data
                String finalFile2 = finalList2[2] + finalList2[1] + finalList2[0];

                //Estraggo la data corrente da sistema <FUNZIONA>
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String [] current_date = dateFormat.format(date).split("/");                  //[2022; 05; 04]

                String finalCurrent = current_date[0] + current_date[1] + current_date[2];          //"2022..."

                String temp1 = finalFile1;
                String temp2 = finalFile2;

                finalFile1 = finalCurrent.substring(0, 2) + temp1;        //"20220504"
                finalFile2 = finalCurrent.substring(0, 2) + temp2;

                //Converto le date
                LocalDate dFile1 = LocalDate.parse(finalFile1, DateTimeFormatter.BASIC_ISO_DATE);
                LocalDate dFile2 = LocalDate.parse(finalFile2, DateTimeFormatter.BASIC_ISO_DATE);
                LocalDate dCurrent = LocalDate.parse(finalCurrent, DateTimeFormatter.BASIC_ISO_DATE);

                long diff1 = dFile1.until(dCurrent, ChronoUnit.DAYS);       //tra oggi e l'altroieri
                long diff2 = dFile2.until(dCurrent, ChronoUnit.DAYS);       //tra oggi e ieri

                if (diff2 == 1 && diff1 == 2) {
                    //Ieri e altroieri sono idonei
                    a.setIeri(dir + list[1]);
                    a.setAltroieri(dir + list[0]);
                }else if (diff2 == 1 && diff1 != 2) {
                    //Solo ieri è idonei
                    a.setIeri(dir + list[1]);
                    a.setAltroieri("");
                }else if (diff2 != 1 && diff1 == 2) {
                    //Solo l'altroieri è idoneo
                    a.setIeri("");
                    a.setAltroieri(dir + list[0]);
                }else if (diff2 != 1 && diff1 != 2) {
                    //Né ieri né l'altroieri sono idonei
                    a.setIeri("");
                    a.setAltroieri("");
                }
            }
            else if (list.length == 1) {
                //C'è solo un file: controllo in base alla data del sistema se è di ieri, altroieri o un giorno prima

                //Divido il nome del file e estraggo la data <FUNZIONA>
                String stripXml = list[0].substring(0, 8);                      //"XI040522SA"
                String finalList [] = {
                    stripXml.substring(2, 4),                                   //"04"
                    stripXml.substring(4, 6),                                   //"05"
                    stripXml.substring(6, 8),                                   //"22"
                };

                String finalFile = finalList[2] + finalList[1] + finalList[0];                      //"220504" - inverto il formato della data

                //Estraggo la data corrente da sistema <FUNZIONA>
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String [] current_date = dateFormat.format(date).split("/");                  //[2022; 05; 04]

                String finalCurrent = current_date[0] + current_date[1] + current_date[2];          //"2022..."

                String temp = finalFile;

                finalFile = finalCurrent.substring(0, 2) + temp;        //"20220504"

                //Converto le date
                LocalDate dFile = LocalDate.parse(finalFile, DateTimeFormatter.BASIC_ISO_DATE);
                LocalDate dCurrent = LocalDate.parse(finalCurrent, DateTimeFormatter.BASIC_ISO_DATE);

                long diff = dFile.until(dCurrent,ChronoUnit.DAYS);

                if (diff == 1) {
                    //Il file è di ieri
                    a.setIeri(dir + list[0]);
                    a.setAltroieri("");
                }else if (diff == 2) {
                    //Il file è dell'altroieri
                    a.setIeri("");
                    a.setAltroieri(dir + list[0]);
                }else {
                    //Il file è di un giorno ancora prima
                }
            }else if (list.length == 0) {
                //Nella lista (nella cartella) non è presente alcun file
                a.setIeri("");
                a.setAltroieri("");
            }
        }

        if (a.getNextList() != null) {
            RiempiLista(a.getNextList());
        }
    }
    
    /* -- MAIN -- */
    public static void main (String[] args) {
        //MAIN DI PROVA
        ListaDiListe grafo = new ListaDiListe();
        grafo.createList();
        grafo.RiempiLista(grafo.getHead());
        grafo.Stampa2(grafo.getHead());

        /*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();
        String [] current_date = dateFormat.format(date).split("/");

        for (int i = 0; i < current_date.length; i++) {
            System.out.print(current_date[i]);
        }*/

        /*LocalDate dBefore = LocalDate.parse("20180521", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate dAfter = LocalDate.parse("20220530", DateTimeFormatter.BASIC_ISO_DATE);

        long diff = dBefore.until(dAfter,ChronoUnit.DAYS);
        System.out.println("difference is : " + diff + " days");*/

        /*String path1 = new File("C:/Users/Admin/Desktop/PC/Scuola/TPSIT/Progetto Giano/FilesSaXml/I3EME__SA").getAbsolutePath();
        String path2 = "./FilesSaXml/I3EME__SA";

        File f = new File(path1);
        /*String prova [] = f.list();
        for (int i = 0; i < prova.length; i++) {
            System.out.println(prova[i]);
        }
        System.out.println(f.exists());
        System.out.println(path1);*/
    }
}
