<?php
session_start();
if(!isset($_SESSION['Loggedin']) || $_SESSION['Loggedin'] == false){
      header('Location : home.php');
}

require "init.php";
$roll1 = $_SESSION['Loggedin2'];

if(isset($_POST["add_to_cart"]))
{
    if(isset($_SESSION['shopping_cart1']))
    {
        $item_array_id = array_column($_SESSION['shopping_cart1'], "item_id");
        if(!in_array($_GET["id"], $item_array_id))
                {
                $count = count($_SESSION['shopping_cart1']);
                $item_array = array(
                    'item_id'         =>     $_GET["id"],
                    'item_name'       =>     $_POST["hidden_name"],
                    'item_price'      =>     $_POST["hidden_price"],
                    'item_quantity'   =>     $_POST["quantity"]
               );
               $_SESSION['shopping_cart1'][$count] = $item_array;
          }
          else
          {
               echo '<script>alert("Item Already Added")</script>';
               echo '<script>window.location="food.php"</script>';
          }
     }
     else
     {
          $item_array = array(
               'item_id'      =>     $_GET["id"],
               'item_name'      =>     $_POST["hidden_name"],
               'item_price'      =>     $_POST["hidden_price"],
               'item_quantity'     =>     $_POST["quantity"]
          );
          $_SESSION['shopping_cart1'][0] = $item_array;
     }
}
if(isset($_GET["action"]))
{
     if($_GET["action"] == "delete")
     {
          foreach($_SESSION['shopping_cart1'] as $keys => $values)
          {
               if($values["item_id"] == $_GET["id"])
               {
                    unset($_SESSION['shopping_cart1'][$keys]);
                    echo '<script>alert("Item Removed")</script>';
                    echo '<script>window.location="food.php"</script>';
               }
          }
     }

     else if($_GET["action"] == "confirm")
     {
       if(!empty($_SESSION['shopping_cart1']))
       {
            $total = 0;
            foreach($_SESSION['shopping_cart1'] as $keys => $values)
            {
              $name=$values["item_name"];
              $quant=$values["item_quantity"];
              $cost1=$values["item_quantity"];
              $cost2=$values["item_price"];
              $cost=$cost1*$cost2;
              $query3 = "INSERT INTO temp_orders(RollNo,ItemName,Quantity,Price)VALUES('$roll1','$name','$quant','$cost')";
              $result = mysqli_query($con, $query3)or die("Error: " .mysqli_error($con));
            }
            echo '<script>alert("Your order has been placed !")</script>';
    
        }
     }
   }

?>

<html>
  <head>
    <title>A-la-carte</title>
    <meta charset="utf-8">
    <link href="css/FoodMaster.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

 <body>
        <br />
        <div class="container" style="width:700px;">
             <h3 align="center">VCafe Shopping Cart</h3><br />
             <?php
               $query = "SELECT * FROM canteen ORDER BY id ASC";
               $result = mysqli_query($con, $query);
               if(mysqli_num_rows($result) > 0)
               {
                    while($row = mysqli_fetch_array($result))
                    {
               ?>
               <div class="col-md-4">
                    <form method="POST" action="food.php?action=add&id=<?php echo $row["id"]; ?>">
                       <div style="border:1px solid #333; background-color:#f1f1f1; border-radius:5px; padding:16px;" align="center">
                            <img src="<?php echo $row["Image"]; ?>" class="img-responsive" /><br />
                            <h4 class="text-info"><?php echo $row["ItemName"]; ?></h4>
                            <h4 class="text-danger">$ <?php echo $row["Cost"]; ?></h4>
                            <input type="text" name="quantity" class="form-control" value="1" />
                            <input type="hidden" name="hidden_name" value="<?php echo $row["ItemName"]; ?>" />
                            <input type="hidden" name="hidden_price" value="<?php echo $row["Cost"]; ?>" />
                            <input type="submit" name="add_to_cart" style="margin-top:5px;" class="btn btn-success" value="Add to Cart" />
                       </div>
                    </form>
               </div>
             <?php
                    }
               }
               ?>
               <div style="clear:both"></div>
               <br />
               <h3>Order Details</h3>
               <div class="table-responsive">
                    <table class="table table-bordered">
                         <tr>
                              <th width="40%">Item Name</th>
                              <th width="10%">Quantity</th>
                              <th width="20%">Price</th>
                              <th width="15%">Total</th>
                              <th width="5%">Action</th>
                         </tr>
                         <?php
                         if(!empty($_SESSION['shopping_cart1']))
                         {
                              $total = 0;
                              foreach($_SESSION['shopping_cart1'] as $keys => $values)
                              {
                         ?>
                         <tr>
                              <td><?php echo $values["item_name"]; ?></td>
                              <td><?php echo $values["item_quantity"]; ?></td>
                              <td>$ <?php echo $values["item_price"]; ?></td>
                              <td>$ <?php echo number_format($values["item_quantity"] * $values["item_price"], 2); ?></td>
                              <td><a href="food.php?action=delete&id=<?php echo $values["item_id"]; ?>"><span class="text-danger">Remove</span></a></td>
                         </tr>
                         <?php
                                   $total = $total + ($values["item_quantity"] * $values["item_price"]);
                              }
                         ?>
                         <tr>
                              <td colspan="3" align="right">Total</td>
                              <td align="right">$ <?php echo number_format($total, 2); ?></td>
                              <td></td>
                         </tr>
                         <?php
                         }
                         ?>
                    </table>

                    <div class="col-md-4">
                         <form method="POST" action="food.php?action=confirm">
                           <input type="submit" name="Confirm" style="margin-top:5px;" class="btn2 btn-success" value="Confirm Order"  />
                         </form>
                    </div>




                    <?php
                    $roll=$_SESSION['Loggedin2'];

                    $query2="SELECT * FROM customer WHERE RollNo='$roll' ";
                    $result2 = mysqli_query($con,$query2);

                    if(mysqli_num_rows($result) > 0)
                    {
                         while($row = mysqli_fetch_array($result))
                         {


                         }
                    }
                    ?>
               </div>
          </div>
          <br />

          <?php

           ?>

     </body>
</html>
