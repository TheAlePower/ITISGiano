import ftplib
import os
from datetime import datetime, timedelta, date
import time as t
import moveFiles as move
import convertor

folders = ["/BOSCON_SA", "/I3EME__SA", "/I5NOD__SA", "/I5NQK__SA", "/IK1XHH_SA", "/IK3DVY_SA", "/IK3ITV_SA",
               "/IK8HTM_SA", "/IQ0PG__SA",
               "/IQ3RK__SA", "/IQ3VQ__SA", "/IU3CLX_SA", "/IU3IAH_SA", "/IZ3BGG_SA", "/LIODOM_SA", "/NORZAN_SA",
               "/SANZEN_SA", "/SMauro_SA", "/TESTO1_SA",
               "/THIENE_SA"]     #array che comprende tutte le cartelle del server ftp interessate

def dowloadFile():
    yesterday = datetime.strftime(datetime.now() - timedelta(1), '%d%m%y')  # data di ieri
    befyesterday = datetime.strftime(datetime.now() - timedelta(2), '%d%m%y')  # data l'altro ieri

    dir = os.getcwd() + "/FilesSA"     #se cartella in locale già esiste non viene creata 
    if not os.path.exists(dir):
        os.mkdir(dir)

    ftp = ftplib.FTP("95.225.22.196")  # Connessione al server ftp
    ftp.login("IK1XHHALL", "STAZAUTO")  # Login con username e password
   

    for folder in folders:
        ftp.cwd(folder)            #eguale al comando cd nel terminale
        filenames = ftp.nlst()     #lista contenente tutti i file della cartella corrente 

        for filename in filenames:
            if yesterday in filename or befyesterday in filename:            #se nel nome è presente la data di ieri o l'altro ieri, il file viene scaricato
                file = open(filename, 'wb')
                ftp.retrbinary('RETR ' + filename, file.write)

                file.close()

                currDir = os.getcwd() + '/'+ filename
                destDir = os.getcwd() + '/FilesSA/' + filename

                moveFiles(currDir, destDir)  #il file appena scaricato viene messo nella cartella FilesSa

        ftp.cwd("/")     #ritorno alla directory root


    ftp.quit()

def moveFiles(currPath, destPath):
    os.replace(currPath, destPath);

def main():
    dowloadFile()
    convertor.startConversion()
    move.setFolders()
    delOutdateFiles()
    print("Esecuzione terminata, prossima esecuzione tra 24 ore...")

def delOutdateFiles():
    data = datetime.strftime(datetime.now() - timedelta(5), '%d%m%y')    #data risalente a 5 giorni fa
    path = os.getcwd() + "/FilesSaXml"

    for folder in folders:               
        currentpath = path + folder
        files = os.listdir(currentpath)       #creazione lista contenente tutti i file della cartella

        try:
            for file in files:
                filename = f"{currentpath}/{file}"  
                if data in file:             #se la data di 5 giorni fa è presente tra i nomi dei file, il file in questione viene eliminato
                    os.remove(filename)
        except:
            print("Qualcosa è andato storto")

if __name__ == '__main__':
    timeSpan = 60 * 60 * 24   #24 ore 
    while True:
        main()
        t.sleep(timeSpan)
