<?php
  $connect=mysqli_connect( "db.appddi.com", "appddi", "dydgur2009!","dbappddi");
  
   if ($connect->connect_error) {
        die('conn Connect Error!');
    } else echo "¼º°ø";


?>