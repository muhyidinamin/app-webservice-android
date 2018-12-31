<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
 $username = $_POST['username'];
 $password = $_POST['password'];
 $depan = $_POST['depan'];
 $belakang = $_POST['belakang'];
 $email = $_POST['email'];
 
 //importing database connection script 
 require_once('../dbConnect.php');
 
 //Creating sql query 
 $sql = "UPDATE tb_worker SET Depan = '$depan', Belakang = '$belakang', Email = '$email', Password = '$password' WHERE Username = '$username';";
 
 //Updating database table 
 if(mysqli_query($con,$sql)){
 echo 'Employee Updated Successfully';
 }else{
 echo 'Could Not Update Employee Try Again';
 }
 
 //closing connection 
 mysqli_close($con);
 }