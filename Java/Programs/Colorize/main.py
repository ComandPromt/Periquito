
import argparse
import matplotlib.pyplot as plt
import os, sys
import shutil
import subprocess
import json
import urllib as urllib2
import cv2
import platform
from colorizers import *

sistema = platform.system();

sistema=format(sistema);

separador="/";

if sistema=="Windows":
    separador="\\";

carpeta=os.path.abspath("colorization")+separador;
try:

    os.mkdir(carpeta)

    os.mkdir(carpeta+'imgs');

    os.mkdir(carpeta+'imgs_out')

except OSError:

    print("")

parser = argparse.ArgumentParser();

parser.add_argument('--use_gpu', action='store_true', help='whether to use GPU');
parser.add_argument('-o','--save_prefix', type=str, default='saved', help='will save into this file with {eccv16.png, siggraph17.png} suffixes');
opt = parser.parse_args();

# load colorizers
colorizer_eccv16 = eccv16(pretrained=True).eval();

colorizer_siggraph17 = siggraph17(pretrained=True).eval();

if(opt.use_gpu):

	colorizer_eccv16.cuda();

	colorizer_siggraph17.cuda();

# default size to process images is 256x256
# grab L channel in both original ("orig") and resized ("rs") resolutions

lista_de_archivos = os.listdir(carpeta+'imgs');

if(len(lista_de_archivos)>0):

	indice=-1

	for file in lista_de_archivos:
		
		extension=file[-4:];

		if extension==".jpg" or extension==".JPG":
		
			++indice;

			api_url = "https://api-periquito.onrender.com/api/names/loop"+extension+"/modo/2";

			data = json.load(urllib2.request.urlopen(api_url));

			json_str = json.dumps(data);

			y = json.loads(json_str);

			nombre_nuevo=y["respuesta"][0];

			img = load_img(carpeta+'imgs'+separador+file);

			(tens_l_orig, tens_l_rs) = preprocess_img(img, HW=(256,256));

			if(opt.use_gpu):
				tens_l_rs = tens_l_rs.cuda();

			# colorizer outputs 256x256 ab map
			
			# resize and concatenate to original L channel
			
			img_bw = postprocess_tens(tens_l_orig, torch.cat((0*tens_l_orig,0*tens_l_orig),dim=1));
			
			out_img_eccv16 = postprocess_tens(tens_l_orig, colorizer_eccv16(tens_l_rs).cpu());
			
			out_img_siggraph17 = postprocess_tens(tens_l_orig, colorizer_siggraph17(tens_l_rs).cpu());
			
			imagen1=carpeta+"imgs_out"+separador+"%s_eccv16_"+nombre_nuevo;
			
			imagen2=carpeta+"imgs_out"+separador+"%s_iggraph17_"+nombre_nuevo;
			
			plt.imsave(imagen1%opt.save_prefix, out_img_eccv16);
			
			plt.imsave(imagen2%opt.save_prefix, out_img_siggraph17);			

