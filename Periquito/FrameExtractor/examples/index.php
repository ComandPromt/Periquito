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
	$numimmp4=check_images("./","mp4");
	$numiflv=check_images("./","flv");
	$numim3u8=check_images("./","m3u8");
	$numimkv=check_images("./","mkv");
	$numimts=check_images("./","ts");
	$numiavi=check_images("./","avi");
	$numi3gp=check_images("./","3gp");
	$numimov=check_images("./","mov");
	$numiwmv=check_images("./","wmv");
	$videos=array();

	if(count($numimmp4)==1){
		$videos[0]=$numimmp4[0];
	
	}
	
	if(count($numiflv)==1 && $videos[0]==null){
		$videos[0]=$numiflv[0];
	}
	
	if(count($numim3u8)==1 && $videos[0]==null){
		$videos[0]=$numim3u8[0];
	}
	
	if(count($numimkv)==1 && $videos[0]==null){
		$videos[0]=$numimkv[0];
	}
	
	if(count($numimts)==1 && $videos[0]==null){
		$videos[0]=$numimts[0];
	}
		
	if(count($numiavi)==1 && $videos[0]==null){
		$videos[0]=$numiavi[0];
	}
	
	if(count($numi3gp)==1 && $videos[0]==null){
		$videos[0]=$numi3gp[0];
	}
	
	if(count($numimov)==1 && $videos[0]==null){
		$videos[0]=$numimov[0];
	}
	
	if(count($numiwmv)==1 && $videos[0]==null){
		$videos[0]=$numiwmv[0];
	}
	
	if(count($videos)==0){
			print '<h1 name="salida">No tienes videos</h1>';
	}
	
	else{
		date_default_timezone_set('Europe/Madrid');
		include_once 'includes/bootstrap.php';

			$video = new Video('video/'.$videos[0]);
			$process = $video->extractFrames(new Timecode(1), new Timecode(1))
				->save('output/'.substr($videos[0],0,-4).'_frame_%timecode.jpg', null, Media::OVERWRITE_EXISTING);
			$frames = $process->getOutput();
			$frame_paths = array();
			
			if(empty($frames) === false){
				
				foreach ($frames as $frame){
					array_push($frame_paths, $frame->getMediaPath());
				}
			}
	print '<h1 name="salida">Exito!</h1>';
	unlink('video/'.$videos[0]);
	}
}

?>
