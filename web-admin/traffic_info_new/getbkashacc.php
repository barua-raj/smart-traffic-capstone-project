<?php 
 
  
 $phone  = $_REQUEST['phone'];
 $pin  = $_REQUEST['pin'];
 

$con=mysqli_connect('localhost','root','','finalproject_traffic_info') or die('database connection failed');

$sql = "SELECT * FROM `bkash_account` where phone='".$phone."' and pin ='".$pin."' ";

 $r = mysqli_query($con,$sql);
 
 $res = mysqli_fetch_array($r);
 
  $result = array();

 
 if(sizeof($res)>0){
	 
    array_push($result,array("error"=>"no","id"=>$res['id'],"phone"=>$res['phone'],"pin"=>$res['pin']));
 	 
 }else { 
	 array_push($result,array("error"=>"Invalid Information"));
 };
 
 echo json_encode(array("result"=>$result));
 
 
 mysqli_close($con);
 
?>