
from pathManager import  load_img_paths

import Multimedia

import os

import argparse

ap = argparse.ArgumentParser(description='Combine frames')

ap.add_argument("-i", "--inputDir", required=True, type=str,
                help="frames folder")
                
ap.add_argument("-fps", "--frameRate", required=True, type=float,
                help="frame rate")
                
ap.add_argument("-o", "--outputDir", default="./sequence.avi", type=str,
                help="Output folder")

args = vars(ap.parse_args())

print ("\n \n \n---------\n \n \n")

#files = pathManager.find_paths(input_folder,is_sorted=True)

input_folder= os.path.expanduser(args['inputDir'])

output_file = os.path.expanduser(args['outputDir'])

fps = float(args['frameRate'])

files = load_img_paths(input_folder)

if len(files)!=0:

    Multimedia.video_from_frames(files,output_file,fps)
    
else:

    print('\n Error: No file found')