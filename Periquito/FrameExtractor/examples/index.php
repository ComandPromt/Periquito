<?php
namespace PHPVideoToolkit;
if (!file_exists("output")) {
    mkdir("output", 777, true);
}

if (!file_exists("tmp")) {
    mkdir("tmp", 777, true);
}

if (!file_exists("video")) {
    mkdir("video", 777, true);
}

include("../../funciones.php");

if(count(check_images("output","jpg"))>=1){
	print '<h1 name="salida">Ya has convertido un video a frames!</h1>';
}

else{
	$numimmp4=check_images("video","mp4");
	$numimpg=check_images("video","mpg");
	$numiavi=check_images("video","avi");
	$numimkv=check_images("video","mkv");
$videos=array();
if(count($numimmp4)==1){
	$videos[0]=$numimmp4[0];

}	
if(count($numiavi)==1 && $videos[0]==null){
	$videos[0]=$numiavi[0];
}
if(count($numimpg)==1 && $videos[0]==null){
	$videos[0]=$numimpg[0];
}
if(count($numimkv)==1 && $videos[0]==null){
	$videos[0]=$numimkv[0];
}
	if(count($videos)==0){
			print '<h1 name="salida">No tienes videos</h1>';
	}
	else{
		date_default_timezone_set('Europe/Madrid');
		include_once 'includes/bootstrap.php';

			$video = new Video('video/'.$videos[0]);
			$process = $video->extractFrames(new Timecode(10), new Timecode(10))
				->save('output/'.substr($videos[0],0,-4).'_frame_%timecode.jpg', null, Media::OVERWRITE_EXISTING);
			$frames = $process->getOutput();
			$frame_paths = array();
			
			if(empty($frames) === false){
				
				foreach ($frames as $frame){
					array_push($frame_paths, $frame->getMediaPath());
				}
			}
	print '<h1 name="salida">Exito!</h1>';
	}
}
?>