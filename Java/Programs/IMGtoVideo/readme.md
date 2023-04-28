### IMGtoVideo
Crate a video from a list of frames
<p>
you can use the caracters '*', '?', and character ranges expressed with [] in order to target your file
</p>

```
python main.py -i [source_folder] -fps [frame_rate] -o [output_file]
```
*example*

```
python main.py -i '~/Document/images/*.jpg' -fps 24 -o ./video.avi
```
## NB
PLease do not forget the cotes ' in case  you want to use special keys