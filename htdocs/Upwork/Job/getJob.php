<?php 
 
 //Getting the requested id
 $id = $_GET['id'];
 
 //Importing database
 require_once('../dbConnect.php');
 
 //Creating sql query with where clause to get an specific employee
 $sql = "SELECT * FROM tb_job WHERE Id='$id'";
 
 //getting result 
 $r = mysqli_query($con,$sql);
 
 //pushing result to an array 
 $result = array();
 $row = mysqli_fetch_array($r);
 array_push($result,array(
 "id"=>$row['Id'],
"judul"=>$row['Judul'],
 "budget"=>$row['Budget'],
 "desc"=>$row['Desk']
 ));
 
 //displaying in json format 
 echo json_encode(array('result'=>$result));
 
 mysqli_close($con);