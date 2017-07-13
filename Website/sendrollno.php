<?php
$rollno= $_POST["rollno"];

require "init.php";

$query = "Select * from temp_orders where RollNo like ' ".$rollno." '; ";
$result = mysqli_query($con,$query);

if(mysqli_num_rows($result)==0){
	$response = array();
	$code = "Reg_false";
	$message = "Oops! Looks like you haven't ordered anything yet.";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
}
else{
	$response = array();
	$code = "Reg_true";
	$message = "Order identified ! Please review your order and complete the checkout.";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
}
	mysqli_close($con);	
}


?>