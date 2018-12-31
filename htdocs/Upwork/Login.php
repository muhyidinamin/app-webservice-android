<?php 
 function userLogin($username, $password){
 	require_once('dbConnect.php');
 	$sql = "SELECT * FROM tb_worker WHERE Username = '$username' AND Password = '$password'";
 	$r = mysqli_query($con,$sql);
 	$result = array();
 	$row = mysqli_fetch_array($r);
	 array_push($result,array(
	 "depan"=>$row['Depan'],
	 "belakang"=>$row['Belakang'],
	 "email"=>$row['Email'],
	 "username"=>$row['Username'],
	 "password"=>$row['Password']
	 ));
	 return $result;
 }

 if($_SERVER['REQUEST_METHOD']=='POST'){
 	if(isset($_POST['username']) and isset($_POST['password'])){
 		$user = userLogin($_POST['username'], $_POST['password']);
 		$user['error']=false;
 		$user['messege']='Login berhasil';
 	} else{
 		$user['error']=true;
 		$user['messege']="Isi username dan password";
 	}

 //displaying in json format 
 echo json_encode(array('result'=>$user));
 }