<?php

$conn=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
$Registration_no = $_GET['Registration_No'];
// Check connection
if ($conn->connect_error) {
    die("Connection fail: " . $conn->connect_error);
}

$sql = "SELECT * FROM `Vehicle_registration_BRTA` WHERE Registration_No ='".$Registration_no."' ";
//where id='".$_GET['id']."'"

$json_array=array();
$result= $conn->query($sql);
while($row= mysqli_fetch_assoc($result)){
    $json_array["Result"][]=$row;
}
echo json_encode($json_array);

$conn->close();
?>