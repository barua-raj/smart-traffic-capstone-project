<?php

$conn=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
//$nid = $_POST['NID_No'];

// Check connection
if ($conn->connect_error) {
    die("Connection fail: " . $conn->connect_error);
}

$sql = "SELECT * FROM cases WHERE Applicant_name='".$_GET['Applicant_name']."' ORDER BY id DESC ";
//where id='".$_GET['id']."'"

$json_array=array();
$result= $conn->query($sql);
while($row= mysqli_fetch_assoc($result)){
    $json_array["Result"][]=$row;
}
echo json_encode($json_array);

$conn->close();
?>