<?php

 $conn=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
  $conn->set_charset("utf8");
// Check connection
if ($conn->connect_error) {
    die("Connection fail: " . $conn->connect_error);
}

$sql = "UPDATE  add_my_driver SET status='Accepted' where id='".$_GET['id']."'";
//where id='".$_GET['id']."'"

if(mysqli_query($conn,$sql)){
	echo  "Request Accepted";
}else{
	echo "Failed";
}
?>