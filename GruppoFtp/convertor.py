import os
from encodings import utf_8
from os import listdir
from os.path import isfile, join
import xml.etree.ElementTree as ET

#inizio e fine dati nelle righe del file originale
line_start = 23
line_end = 67

#path attuale del file 
file_path = __file__

#finzione che estre i dati e li tagga/flagga
def convertXml():
    #path della cartella di partenza partendo dal path del file
    path = file_path[:len(file_path)-(len(__name__)+4)]+"/FilesSA/"
    
    #aperture di tutti i path nella cartella
    file_paths = [f for f in listdir(path) if isfile(join(path, f))]
    
    #scorrimento dei file 
    for i in file_paths:
        #creo il path temporaneo per la creazione del file xml
        tmp_path = path+i

        #apro il file di partenza
        tmp_file = open(tmp_path)

        #creo il path per il file di destinazione
        tmp_path = tmp_path
        dest_path = tmp_path.replace("tmp_pre","FilesSaXml").replace("txt", "xml")


        #dest_path = os.getcwd() + "\\FilesSaXml"
        #dest_path = dest_path.replace("txt", "xml")

        #inizializzo l'albero dell'xml
        root = ET.Element('Values')
        k = int(0)
        #scorro le righe
        for j in tmp_file:
            #estraggo la parte con i dati della linea
            values = j[line_start:line_end]

            #apro il nuovo tag per i valori nella riga
            tmp_value = ET.SubElement(root,'Value')
            #calcolo il time
            tmp_value.attrib['time'] = str(k*2)
            
            #rx1
            tmp_tag = ET.SubElement(tmp_value,'intensRX1')
            tmp_tag.text = values[0:8]

            #rx2
            tmp_tag = ET.SubElement(tmp_value,'intensRX2')
            tmp_tag.text = values[9:17]

            #sdrx1
            tmp_tag = ET.SubElement(tmp_value,'SDRX1')
            tmp_tag.text = values[18:26]

            #sdrx2
            tmp_tag = ET.SubElement(tmp_value,'SDRX2')
            tmp_tag.text = values[27:35]

            #att
            tmp_tag = ET.SubElement(tmp_value,'Att')
            tmp_tag.text = values[36:44]

            #couter per il tempo
            k += 1
        #indentazione del file
        tree = ET.ElementTree(root)
        indent(root)

        #scrivo il file
        tree.write(dest_path)
        tmp_file.close
        
#funzione per indentare l'xml(non lo fa in automatico)
def indent(elem, level=0):
    i = "\n" + level*"  "
    if len(elem):
        if not elem.text or not elem.text.strip():
            elem.text = i + "  "
        if not elem.tail or not elem.tail.strip():
            elem.tail = i
        for elem in elem:
            indent(elem, level+1)
        if not elem.tail or not elem.tail.strip():
            elem.tail = i
    else:
        if level and (not elem.tail or not elem.tail.strip()):
            elem.tail = i

#funzione che cancella i file
def deleteTxt():
    dir = os.getcwd() + "/FilesSA"

    files = os.listdir(dir)
    filestxt = [file for file in files if file.endswith(".txt")]

    for file in filestxt:
        pathfile = os.path.join(dir, file)
        os.remove(pathfile)

#funzione principale
def startConversion():
    convertXml()
    deleteTxt()
    print("Conversione terminata")