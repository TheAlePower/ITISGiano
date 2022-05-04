import ftplib
import os
from datetime import datetime, timedelta, date
import time as t
import moveFiles as move
import convertor


def dowloadFile():
    yesterday = datetime.strftime(datetime.now() - timedelta(1), '%d%m%y')  # data di ieri
    befyesterday = datetime.strftime(datetime.now() - timedelta(2), '%d%m%y')  # data l'altro ieri

    dir = os.getcwd() + "/FilesSA"
    if not os.path.exists(dir):
        os.mkdir(dir)

    ftp = ftplib.FTP("95.225.22.196")  # Connessione al server ftp
    ftp.login("IK1XHHALL", "STAZAUTO")  # Login con username e password
    # ftp.retrlines('LIST')    #elenco di tutte le cartelle e file presenti (ls -l) in ASCII mode

    folders = ["/BOSCON_SA", "/I3EME__SA", "/I5NOD__SA", "/I5NQK__SA", "/IK1XHH_SA", "/IK3DVY_SA", "/IK3ITV_SA",
               "/IK8HTM_SA", "/IQ0PG__SA",
               "/IQ3RK__SA", "/IQ3VQ__SA", "/IU3CLX_SA", "/IU3IAH_SA", "/IZ3BGG_SA", "/LIODOM_SA", "/NORZAN_SA",
               "/SANZEN_SA", "/SMauro_SA", "/TESTO1_SA",
               "/THIENE_SA"]

    for folder in folders:
        ftp.cwd(folder)
        filenames = ftp.nlst()

        for filename in filenames:
            if yesterday in filename or befyesterday in filename:
                file = open(filename, 'wb')
                ftp.retrbinary('RETR ' + filename, file.write)

                file.close()

                currDir = os.getcwd() + '/'+ filename
                destDir = os.getcwd() + '/FilesSA/' + filename

                moveFiles(currDir, destDir)

        ftp.cwd("/")


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
    data = datetime.strftime(datetime.now() - timedelta(5), '%d%m%y')
    path = os.getcwd() + "/FilesSaXml"

    for folder in folders:
        currentpath = path + folder
        files = os.listdir(currentpath)

        try:
            for file in files:
                filename = f"{currentpath}/{file}"
                print(filename)
                if data in file:
                    os.remove(filename)
        except:
            print("Qualcosa è andato storto")

if __name__ == '__main__':
    timeSpan = 86400
    while True:
        main()
        t.sleep(timeSpan)
