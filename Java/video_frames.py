__author__ = 'vfdev'

import argparse
import os, sys
import shutil
import subprocess
import json
import urllib as urllib2
import cv2

lectura=[]

path=os.path.abspath("Config")

with open('Config/Config.txt') as f:

	for linea in f:
		lectura.append(linea)

folder=lectura[0].strip();

carpeta=folder+'Frame_Extractor'

lista_de_archivos = os.listdir(carpeta);

separador="/"

if "\\" in folder:

	separador="\\"

def main(args):

    if(len(lista_de_archivos)>0):

		imagenes=[]

		indice=-1

    for file in lista_de_archivos:

			if file[-4:]==".mp4" or file[-4:]==".avi":

				extension=file[-4:]

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

				outputdir=carpeta+separador+'output'+separador+nombre_nuevo[0:-4]

				if not os.path.exists(outputdir):

					os.makedirs(outputdir)

				cap = cv2.VideoCapture()

				cap.open(carpeta+separador+nombre_nuevo)

				if not cap.isOpened():

					parser.error("Failed to open input video")

					return 1

				frameCount = cap.get(cv2.CAP_PROP_FRAME_COUNT)

				maxframes = 999999999

				skipDelta = 0

				if maxframes and frameCount > maxframes:
					skipDelta = frameCount / maxframes

				frameId = 0

				rotateAngle = args.rotate if args.rotate else 0


				while frameId < frameCount:

					ret, frame = cap.read()

					if not ret:

						print( "Failed to get the frame {f}".format(f=frameId))

						break

					if rotateAngle > 0:

						if rotateAngle == 90:
							frame = cv2.transpose(frame)
							frame = cv2.flip(frame, 1)

						elif rotateAngle == 180:
							frame = cv2.flip(frame, -1)

						elif rotateAngle == 270:
							frame = cv2.transpose(frame)
							frame = cv2.flip(frame, 0)

					fname = "frame_" + str(frameId) + ".jpg"

					ofname = os.path.join(outputdir, fname)

					ret = cv2.imwrite(ofname, frame)

					if not ret:
						print( "Failed to write the frame {f}".format(f=frameId))
						break

					frameId += int(1 + skipDelta)

					cap.set(cv2.CAP_PROP_POS_FRAMES, frameId)

    return 0

def write_exif_model(folder_path, model, fields=None):

    cmd = ['exiftool', '-overwrite_original', '-r']

    for field in fields:

        if field in model:

            cmd.append('-' + field + "=" + model[field])

    cmd.append(folder_path)

    ret = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    out, err = ret.communicate()

    return ret.returncode == 0 and len(err) == 0

if __name__ == "__main__":

    parser = argparse.ArgumentParser(description="Video2Frames converter")

    parser.add_argument('--rotate', type=int, choices={90, 180, 270}, help="Rotate clock-wise output frames")

    args = parser.parse_args()

    ret = main(args)

    fic = open(path+separador+"llamada_python.txt","w")

    fic.write('video_frames')

    fic.close()

    exit(ret)
