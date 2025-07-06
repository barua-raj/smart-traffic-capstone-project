<?php 
 
  
 $email  = $_REQUEST['user_name'];
 $password  = $_REQUEST['password'];
 

$con=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');

$sql = "SELECT * FROM `users` where user_name='".$email."' and password ='".$password."' ";

 $r = mysqli_query($con,$sql);
 
 $res = mysqli_fetch_array($r);
 
  $result = array();

 
 if(sizeof($res)>0){
	 
    array_push($result,array("error"=>"no","id"=>$res['id'],"name"=>$res['name'],"NID_No"=>$res['NID_No'],"phone"=>$res['phone'],
								"Police_id"=>$res['Police_id'],"Role"=>$res['Role']));
 	 
 }else { 
	 array_push($result,array("error"=>"Invalid Username or Password"));
 };
 
 echo json_encode(array("result"=>$result));
 
 
 mysqli_close($con);
 
?>