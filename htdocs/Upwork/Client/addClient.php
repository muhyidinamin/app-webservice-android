<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting values
 $depan = $_POST['depan'];
 $belakang = $_POST['belakang'];
 $email = $_POST['email'];
 $username = $_POST['username'];
 $password = $_POST['password'];
 
 //Creating an sql query
 $sql = "INSERT INTO tb_worker (Depan ,Belakang ,Email ,Username, Password) VALUES ('$depan','$belakang','$email','$username','$password')";
 
 //Importing our db connection script
 require_once('../dbConnect.php');
 
 //Executing query to database
 if(mysqli_query($con,$sql)){
 echo 'Employee Added Successfully';
 }else{
 echo 'Could Not Add Employee';
 }
 
 //Closing the database 
 mysqli_close($con);
 }