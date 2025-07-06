<?php

$con=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
 $con->set_charset("utf8");

$owner_name = $_POST['owner_name'];
$number = $_POST['number'];
$contact_name = $_POST['contact_name'];



$sql="insert into emergency_number(owner_name,number,contact_name) values('".$owner_name."','".$number."','".$contact_name."')";
if(mysqli_query($con,$sql)){
	echo  "success";
}else{
	echo "very failed";
}


?>