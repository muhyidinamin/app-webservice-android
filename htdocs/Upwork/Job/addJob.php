<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting values
 $judul = $_POST['judul'];
 $budget = $_POST['budget'];
 $desc = $_POST['desc'];
 
 //Creating an sql query
 $sql = "INSERT INTO tb_job (Judul ,Budget , Desk) VALUES ('$judul','$budget','$desc')";
 
 //Importing our db connection script
 require_once('../dbConnect.php');
 
 //Executing query to database
 if(mysqli_query($con,$sql)){
 echo 'Job Added Successfully';
 }else{
 echo 'Could Not Add Job';
 }
 
 //Closing the database 
 mysqli_close($con);
 }