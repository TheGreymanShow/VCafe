<?php
$event = $_POST["event"];
$name = $_POST["name"];
$email = $_POST["email"];
$phone = $_POST["phone"];
$college = $_POST["college"];
$amba = $_POST["amba"];
$amount_paid = $_POST["amount"];
$status = $_POST["status"];

require "init.php";

$query = "Select * from temp where email like ' ".$email." '; ";
$result = mysqli_query($con,$query);

if(mysqli_num_rows($result)>0){
	$response = array();
	$code = "Reg_false";
	$message = "Reg failed : User already Exists !";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
}
else{
	/*(Event,Name,Email,Phone,College,Ambassador,Amount Paid,Status)*/
	$query = "insert into temp values(' ".$event." ',' ".$name." ',' ".$email." ',' ".$phone." ',' ".$college." ',' ".$amba." ','0','Hold');" ;
	$result = mysqli_query($con,$query);	
	
	if(!$result)
	{
		$response = array();
		$code = "Reg_false";
		$message = "Reg failed : Connection error occured !";
		array_push($response,array("code"=>$code,"message"=>$message));
		echo json_encode(array("server_response"=>$response));
	}
	else{	
		$response = array();
		$code = "Reg_true";
		$message = "Registration Successful !";
		array_push($response,array("code"=>$code,"message"=>$message));
		echo json_encode(array("server_response"=>$response));
	}
	mysqli_close($con);	
}


?>