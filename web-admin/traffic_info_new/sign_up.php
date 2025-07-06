<?php

$con=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');

$name = $_POST['name'];
$date_of_birth = $_POST['date_of_birth'];
$Blood_group = $_POST['Blood_group'];

$Role = $_POST['Role'];
$Driving_license_no = $_POST['Driving_license_no'];
$Vehicle_reg_no= $_POST['Vehicle_reg_no'];
$user_name = $_POST['user_name'];
$password = $_POST['password'];
$photo_name = $_POST['photo_name'];

$NID_No = $_POST['NID_No'];
$phone = $_POST['phone'];
$division= $_POST['division'];
$Police_id = $_POST['Police_id'];


$sql="insert into users(name,date_of_birth,Blood_group,Role,Driving_license_no,Vehicle_reg_no,user_name,password,photo_name,NID_No,phone,division,Police_id) values('".$name."','".$date_of_birth."','".$Blood_group."','".$Role."','".$Driving_license_no."','".$Vehicle_reg_no."','".$user_name."','".$password."','".$photo_name."','".$NID_No."','".$phone."','".$division."','".$Police_id."')";
if(mysqli_query($con,$sql)){


	$u_sql="Update National_identity_table SET isUsed ='true' where NID_No='".$NID_No."' ";

	if (mysqli_query($con,$u_sql)) {
		# code...
		echo  "success";
	}
	
}else{
	echo "very failed";
}


?>