<?php

session_start();
$rollno1 = "";
$password1= "";
require "init.php";
//fetch username and password from database
if(isset($_POST['rollno']) && isset($_POST['password']))
{
$rollno1 = $_POST['rollno'];
$password1 = $_POST['password'];
}

$query = "SELECT * FROM customer WHERE RollNo='$rollno1'and Password='$password1' ";
$result = mysqli_query($con, $query) or die("Error: " . mysqli_error($con));
$row=mysqli_fetch_array($result);
$rollno2 = $row['RollNo'];
$password2 = $row['Password'];

if(isset($_SESSION['Loggedin']) && $_SESSION['Loggedin']==true){
    header('Location : food.php');
    }

if(isset($_POST['rollno']) && isset($_POST['password']))
{
    if($_POST['rollno'] == $rollno2 && $_POST['password'] == $password2)
    {
      $_SESSION['Loggedin']=true;
      $_SESSION['Loggedin2']=$rollno2;
      header('Location:food.php');
    }
}

?>

<html>
<head>
  <title>V Cafe</title>
  <meta charset="utf-8">
  <link href="css/master.css" rel="stylesheet">
</head>

<body>

  <div class="main">

    <div class="form">

      <form method="POST" action="home.php" id="contact-form" class="form">

          <div class="form-group">
            <div class="field">
              <input  type="text"  placeholder="Enter your Roll no" class="form-control" name="rollno" id="rollno">
            </div>
          </br>
          </div>

          <div  class="form-group">
            <div class="field">
              <input type="password" placeholder="Enter Your Password" class="form-control" name="password" id="password">
            </div>
            </br>
          </div>

          <div class="form-group">
            <button type="submit" class="btn btn-success" value="login">Enter</button>
          </div>

      </form>
    </div>
  </div>


  <!-- Bootstrap core JavaScript -->
      <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.js"></script>
  	<script src="js/jquery-scrolltofixed-min.js"></script>
  	<script src="js/jquery.vegas.js"></script>
  	<script src="js/jquery.mixitup.min.js"></script>
  	<script src="js/jquery.validate.min.js"></script>
  	<script src="js/script.js"></script>
  	<script src="js/bootstrap.js"></script>

  <!-- Slideshow Background  -->
  	<script>
  $.vegas('slideshow', {
    delay:5000,
    backgrounds:[
       { src:'./images/a.jpg', fade:2000 },
  	 { src:'./images/a1.jpg', fade:2000 },
      { src:'./images/a2.jpg', fade:2000 },
  	 { src:'./images/a3.jpg', fade:2000 },
      { src:'./images/a4.jpg', fade:2000 },
      { src:'./images/a5.jpg', fade:2000 },
    ]
  });
  /*('overlay', {
  src:'./img/overlay.png'
  });*/
  	</script>
  <!-- /Slideshow Background -->

  <!-- Mixitup : Grid -->
      <script>
  		$(function(){
      $('#Grid').mixitup();
        });
      </script>
  <!-- /Mixitup : Grid -->

      <!-- Custom JavaScript for Smooth Scrolling - Put in a custom JavaScript file to clean this up -->
      <script>
        $(function() {
          $('a[href*=#]:not([href=#])').click(function() {
            if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'')
              || location.hostname == this.hostname) {

              var target = $(this.hash);
              target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
              if (target.length) {
                $('html,body').animate({
                  scrollTop: target.offset().top
                }, 1000);
                return false;
              }
            }
          });
        });
      </script>


</body>

</html>
