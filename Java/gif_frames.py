import os  
from PIL import Image
import json 
import urllib as urllib2

lectura=[]

ruta=os.path.abspath("Config")

with open('Config/Config.txt') as f:
	
	for linea in f:
		lectura.append(linea)
		
folder=lectura[0].strip();

separador="/"

if "\\" in folder:
	separador="\\"
	
def analyseImage(path):  

    im = Image.open(path)
      
    results = {  
        'size': im.size,  
        'mode': 'full',  
    }  
    
    try:  
        while True:  
            if im.tile:  
                tile = im.tile[0]  
                update_region = tile[1]  
                update_region_dimensions = update_region[2:]  
                if update_region_dimensions != im.size:  
                    results['mode'] = 'partial'  
                    break  
            im.seek(im.tell() + 1)  
    except EOFError:  
        pass  
    return results  
   
   
def processImage(path,outputdir):  

    mode = analyseImage(path)['mode']
    im = Image.open(path)  
    
    i = 0  
    p = im.getpalette()
    last_frame = im.convert('RGBA')  
      
    try:  
        while True:  

            if not im.getpalette():  
                im.putpalette(p)  
              
            new_frame = Image.new('RGBA', im.size)  
 
            if mode == 'partial':  
                new_frame.paste(last_frame)  
              
            new_frame.paste(im, (0,0), im.convert('RGBA'))  
            new_frame.save(os.path.join(outputdir, '%s-%d.png' % ('frame', i)), 'PNG')  

            i += 1  
            last_frame = new_frame  
            im.seek(im.tell() + 1)  
            
    except EOFError:  
        pass  
   
def main():
	
	carpeta=folder+'Gif_extractor'
	
	lista_de_archivos = os.listdir(carpeta);
	
	if(len(lista_de_archivos)>0):

		imagenes=[]
		
		indice=-1
	
		for file in lista_de_archivos:

			if file[-4:]==".gif":
				
				++indice
				
				imagenes.append(file)
				
				filename = imagenes[indice]
				
				api_url = "https://apiperiquito.herokuapp.com/recibo-json.php?imagenes=loop.gif";
				
				data = json.load(urllib2.urlopen(api_url))
				
				json_str = json.dumps(data)
				
				y = json.loads(json_str)
				
				nombre_nuevo=y["imagenes_bd"][0]
				
				os.rename(carpeta+separador+filename, carpeta+separador+nombre_nuevo)
				
				imagenes[indice]=nombre_nuevo
				
				outputdir = carpeta+separador+'output'+separador+nombre_nuevo[0:-4];
				
				if not os.path.exists(outputdir):
					os.makedirs(outputdir)

				processImage(carpeta+separador+nombre_nuevo,outputdir)
				
		fic = open(ruta+separador+"llamada_python.txt","w")

		fic.write('gif_frames')
    
		fic.close()
	
if __name__ == "__main__":  
    main()  
