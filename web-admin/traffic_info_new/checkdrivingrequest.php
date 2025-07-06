<?php

$conn=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');
//$nid = $_POST['NID_No'];

// Check connection
if ($conn->connect_error) {
    die("Connection fail: " . $conn->connect_error);
}
$sql="SELECT * FROM users INNER JOIN add_my_driver WHERE add_my_driver.status='Pending' and add_my_driver.d_name ='".$_GET['name']."' and users.user_name=add_my_driver.user_name";
//$sql = "SELECT * FROM add_my_driver WHERE name ='".$_GET['name']."' and status='Pending' ";
//where id='".$_GET['id']."'"

$json_array=array();
$result= $conn->query($sql);
while($row= mysqli_fetch_assoc($result)){
    $json_array["Result"][]=$row;
}
echo json_encode($json_array);

$conn->close();
?>