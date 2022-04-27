import os


def createDirs():
    dir = os.getcwd() + "/FilesSaXml"
    if not os.path.exists(dir):
        os.mkdir(dir)

    os.chdir(dir)
    folders = ["/BOSCON_SA", "/I3EME__SA", "/I5NOD__SA", "/I5NQK__SA", "/IK1XHH_SA", "/IK3DVY_SA", "/IK3ITV_SA",
               "/IK8HTM_SA", "/IQ0PG__SA",
               "/IQ3RK__SA", "/IQ3VQ__SA", "/IU3CLX_SA", "/IU3IAH_SA", "/IZ3BGG_SA", "/LIODOM_SA", "/NORZAN_SA",
               "/SANZEN_SA", "/SMauro_SA", "/TESTO1_SA",
               "/THIENE_SA"]

    #print(os.getcwd())
    #print(dir)

    for folder in folders:
        dir = os.getcwd() + "/" + folder
        if not os.path.exists(dir):
            os.mkdir(dir)


def moveXmls():
    os.chdir("..")

    path = os.getcwd() + "/FilesSA"
    files = os.listdir(path)

    for file in files:
        folder = findFolder(file)
        destpath = os.getcwd() + f"/FilesSaXml/{folder}/{file}"
        filepath = path + f"/{file}"

        os.replace(filepath, destpath)



def findFolder(filename):
    acroyms = {
        "WA": "BOSCON_SA",
        "WM": "I3EME__SA",
        "WF": "I5NOD__SA",
        "WG": "I5NQK__SA",
        "XA": "IK1XHH_SA",
        "WT": "IK3ITV_SA",
        "WH": "IQ3RK__SA",
        "WP": "IU3CLX_SA",
        "XI": "IU3IAH_SA",
        "WD": "NORZAN_SA",
        "WE": "SANZEN_SA",
        "WS": "SMauro_SA",
        "WL": "THIENE_SA",
        "JJ": "TESTO1_SA"
    }
    dir = filename[:2]
    return acroyms[dir]




def setFolders():
    createDirs()
    moveXmls()