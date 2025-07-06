<?php

$con=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
 $con->set_charset("utf8");

$name = $_POST['name'];
$Driving_license_no = $_POST['Driving_license_no'];
$Vehicle_reg_no = $_POST['Vehicle_reg_no'];

$user_name = $_POST['user_name'];
$phone = $_POST['phone'];
$location= $_POST['location'];
$image= $_POST['image'];


$sql="insert into add_my_driver(d_name,Driving_license_no,Vehicle_reg_no,user_name,phone,location,Status,image) values('".$name."','".$Driving_license_no."','".$Vehicle_reg_no."','".$user_name."','".$phone."','".$location."','Pending','".$image."')";
if(mysqli_query($con,$sql)){
	echo  "success";
}else{
	echo "very failed";
}


?>