import imageio
import os
import json
import urllib as urllib2

def gifMaker(inputPath,outputPath):

    reader = imageio.get_reader(inputPath)

    fps = reader.get_meta_data()['fps']

    writer = imageio.get_writer(outputPath, fps=fps)

    for frames in reader:
        writer.append_data(frames)

    writer.close()


lectura=[]

path=os.path.abspath("Config")

with open('Config/Config.txt') as f:

	for linea in f:
		lectura.append(linea)

folder=lectura[0].strip();

separador="/"

if "\\" in folder:

	separador="\\"

carpeta=folder+'Video_2_Gif'

lista_de_archivos = os.listdir(carpeta);

outputdir = carpeta+separador+'output'

if not os.path.exists(outputdir):
	os.makedirs(outputdir)

if(len(lista_de_archivos)>0):

	imagenes=[]

	indice=-1

	for file in lista_de_archivos:

		extension=file[-4:]

		if extension==".mp4" or extension[-4:]==".avi":

			++indice

			imagenes.append(file)

			filename = imagenes[indice]

			api_url = "https://apiperiquito.herokuapp.com/recibo-json.php?imagenes=loop."+extension;

			data = json.load(urllib2.urlopen(api_url))

			json_str = json.dumps(data)

			y = json.loads(json_str)

			nombre_nuevo=y["imagenes_bd"][0]

			os.rename(carpeta+separador+filename, carpeta+separador+nombre_nuevo)

			imagenes[indice]=nombre_nuevo

			try:
				gifMaker(carpeta+separador+nombre_nuevo,carpeta+separador+'output'+separador+nombre_nuevo[0:-4]+'.gif')

			except:

				print('')

fic = open(path+separador+"llamada_python.txt","w")

fic.write('video_gif')

fic.close()
