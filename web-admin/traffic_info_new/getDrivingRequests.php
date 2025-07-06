<?php

$conn=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
//$nid = $_POST['NID_No'];

// Check connection
if ($conn->connect_error) {
    die("Connection fail: " . $conn->connect_error);
}

$sql = "SELECT * FROM add_my_driver WHERE user_name ='".$_GET['user_name']."' and status='Accepted' or status='Rejected'";
//where id='".$_GET['id']."'"

$json_array=array();
$result= $conn->query($sql);
while($row= mysqli_fetch_assoc($result)){
    $json_array["Result"][]=$row;
}
echo json_encode($json_array);

$conn->close();
?>