<?php
 $connect=mysqli_connect( "db.appddi.com", "appddi", "dydgur2009!","dbappddi");   
  extract($_REQUEST);

  
 mysqli_query( $connect,"set name utf8");
 mysqli_query( $connect,"set session character_set_connection=utf8");
 mysqli_query( $connect,"set session character_set_results=utf8");
 mysqli_query( $connect,"set session character_set_client=utf8");

    $a = iconv("EUC-KR","UTF-8", $title);
    $b = iconv("EUC-KR","UTF-8", $content);
    $c = iconv("EUC-KR","UTF-8", $time);
    
    umask(0);
    $sql="INSERT INTO event (title, content, day) VALUES('$a','$b','$c' )";
    echo $sql;
    
    $result=mysqli_query($connect,$sql);
 ?>
