<?php
 
  
  $connect=mysqli_connect( "db.appddi.com", "appddi", "dydgur2009!","dbappddi") or  
        die( "SQL server에 연결할 수 없습니다.");
   
   mysqli_query( $connect,"set name utf8");
   mysqli_query( $connect,"set session character_set_connection=utf8");
   mysqli_query( $connect,"set session character_set_results=utf8");
   mysqli_query( $connect,"set session character_set_client=utf8");

  
  $id      = $_POST['data1'];
  $comment = $_POST['data2'];
  $time    = $_POST['data3'];
  $number  = $_POST['data4'];
    
   

 

  $sql="INSERT INTO freeboardComment (id,comment,time,number) VALUES('$id','$comment','$time','$number')";
  
$result   =  mysqli_query($connect, $sql);

  if($result)
    echo "1";
  else 
    echo "-1";
?>