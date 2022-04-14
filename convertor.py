import os
from encodings import utf_8
from os import listdir
from os.path import isfile, join
import xml.etree.ElementTree as ET

#variabili utili
line_start = 23
line_end = 67


file_path = __file__

def convertXml():
    path = file_path[:len(file_path)-(len(__name__)+4)]+"\\FilesSA\\"
    
    file_paths = [f for f in listdir(path) if isfile(join(path, f))]
    
    for i in file_paths:
        tmp_path = path+i
        tmp_file = open(tmp_path)
        tmp_path = tmp_path
        dest_path = tmp_path.replace("tmp_pre","FilesSaXml").replace("txt", "xml")
        #dest_path = os.getcwd() + "\\FilesSaXml"
        #dest_path = dest_path.replace("txt", "xml")
        root = ET.Element('Values')
        k = int(0)
        for j in tmp_file:
            values = j[line_start:line_end]
            tmp_value = ET.SubElement(root,'Value')
            tmp_value.attrib['time'] = str(k*2)
            tmp_tag = ET.SubElement(tmp_value,'intensRX1')
            tmp_tag.text = values[0:8]
            tmp_tag = ET.SubElement(tmp_value,'intensRX2')
            tmp_tag.text = values[9:17]
            tmp_tag = ET.SubElement(tmp_value,'SDRX1')
            tmp_tag.text = values[18:26]
            tmp_tag = ET.SubElement(tmp_value,'SDRX2')
            tmp_tag.text = values[27:35]
            tmp_tag = ET.SubElement(tmp_value,'Att')
            tmp_tag.text = values[36:44]
            k += 1
        tree = ET.ElementTree(root)
        indent(root)
        tree.write(dest_path)
        tmp_file.close
        
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

def deleteTxt():
    dir = os.getcwd() + "\\FilesSA"

    files = os.listdir(dir)
    filestxt = [file for file in files if file.endswith(".txt")]

    for file in filestxt:
        pathfile = os.path.join(dir, file)
        os.remove(pathfile)

def startConversion():
    convertXml()
    deleteTxt()
    print("Conversione terminata")