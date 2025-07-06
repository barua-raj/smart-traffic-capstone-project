<?php

$conn=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
//$nid = $_POST['NID_No'];

// Check connection
if ($conn->connect_error) {
    die("Connection fail: " . $conn->connect_error);
}

$sql = "DELETE FROM emergency_number WHERE id ='".$_GET['id']."'";
//where id='".$_GET['id']."'"

if(mysqli_query($conn,$sql)){
	echo  "Deleted";
}else{
	echo "Failed";
}
?>