<?php

 $conn=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
 $conn->set_charset("utf8");
// Check connection
if ($conn->connect_error) {
    die("Connection fail: " . $conn->connect_error);
}

$sql = "SELECT * FROM `crime` ";
//where id='".$_GET['id']."'"

$json_array=array();
$result= $conn->query($sql);
while($row= mysqli_fetch_assoc($result)){
    $json_array["Result"][]=$row;
}
echo json_encode($json_array);

$conn->close();
?>