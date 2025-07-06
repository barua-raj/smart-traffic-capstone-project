<?php

$con=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
 $con->set_charset("utf8");

$Applicant_name = $_POST['Applicant_name'];
$Applicant_type = $_POST['Applicant_type'];
$License_no = $_POST['License_no'];

$crime = $_POST['crime'];
$imprisonment = $_POST['imprisonment'];
$fine= $_POST['fine'];
$fine_type = $_POST['fine_type'];
$Vehicle_type = $_POST['Vehicle_type'];
$Vehicle_number = $_POST['Vehicle_number'];

$Type_of_fine = $_POST['Type_of_fine'];
$NID_No = $_POST['NID_No'];
$Police_id= $_POST['Police_id'];
$feedback = $_POST['feedback'];
$address = $_POST['address'];
$case_date = $_POST['case_date'];
$case_time = $_POST['case_time'];
$case_location = $_POST['case_location'];
$seized_document = $_POST['seized_document'];
$appearance_last_date = $_POST['appearance_last_date'];
$Status = 'Pending';


$sql="insert into Cases(Applicant_name,Applicant_type,License_no,crime,imprisonment,fine,fine_type,Vehicle_type,Vehicle_number,Type_of_fine,NID_No,Police_id,feedback,Status,address,case_date,case_time,case_location,seized_document,appearance_last_date) values('".$Applicant_name."','".$Applicant_type."','".$License_no."','".$crime."','".$imprisonment."','".$fine."','".$fine_type."','".$Vehicle_type."','".$Vehicle_number."','".$Type_of_fine."','".$NID_No."','".$Police_id."','".$feedback."','".$Status."','".$address."','".$case_date."','".$case_time."','".$case_location ."','".$seized_document."','".$appearance_last_date."')";

if(mysqli_query($con,$sql)){
	echo  "success";
}else{
	echo "very failed";
}

?>