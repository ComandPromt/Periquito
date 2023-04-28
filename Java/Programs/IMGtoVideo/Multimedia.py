import cv2
from tqdm import tqdm
import pathManager



def video_from_frames(frames_path,output_path,fps):
    frame_array = []
    for i in tqdm(range(len(frames_path))):
        #pathManager.get_name(frames_path[i],True)
        img = cv2.imread(frames_path[i])
        height, width, layers = img.shape
        size = (width,height)
        #inserting the frames into an image array
        frame_array.append(img)

    out=cv2.VideoWriter(output_path,cv2.VideoWriter_fourcc(*'XVID'), fps, size)

    print ("\n info  :  frames under {0} was loaded successifly  \n".format(output_path))

    print(len(frame_array))

    for i in tqdm(range(len(frame_array))):
        # writing to a image array
        out.write(frame_array[i])
    out.release()

    print ("\n Success  :  video was saved under  {0} \n".format(output_path))
