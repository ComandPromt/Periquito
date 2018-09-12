<?php
error_reporting(0);
session_start();

include("../funciones.php");

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

  if(count($videos)==1){ 
 
	rename($videos[0],"video".substr($videos[0],-4)) or die(print '<h1 name="salida">Cambia el nombre</h1>');  

	rename("video".substr($videos[0],-4),"../FrameExtractor/examples/video/".$videos[0]);
	header("Location: ../FrameExtractor/examples/index.php");
	$_SESSION['video2gif']=true;
  }
  else{
	print '<h1 name="salida">No tienes videos</h1>';
  }
