
import glob, os

from PIL import Image

from pathlib import Path

from os import remove

lectura=[]

with open('Config/Config.txt') as f:

	for linea in f:
		lectura.append(linea)

folder=lectura[0].strip();

separador="/"

if "\\" in folder:

	separador="\\"

ruta=os.path.abspath("Config")

carpeta=ruta+separador+"imagenes"

os.chdir(carpeta)

photo_collection = []

for file in glob.glob("*.webp"):

    photo_collection.append(file)

    for photo in photo_collection:

        im = Image.open(photo).convert('RGB')
        new = Path(photo).stem
        im.save((str(new)+'.png'), 'png')
	
	remove(photo)

fic = open(ruta+separador+"llamada_python.txt","w")

fic.write('finish')
    
fic.close()
