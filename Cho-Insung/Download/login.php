<?php
  $id = $_GET['id'];
  $pw = $_GET['pw'];
  $name = $_GET['name'];
  $pw_identify = $_GET['pw_identify'];
  $major = $_GET['major'];
  
  $connect=mysqli_connect( "db.appddi.com", "appddi", "dydgur2009!","dbappddi") or  
        die( "SQL server에 연결할 수 없습니다.");
    
    mysqli_query( $connect,"set name utf8");
    mysqli_query( $connect,"set session character_set_connection=utf8");
    mysqli_query( $connect,"set session character_set_results=utf8");
    mysqli_query( $connect,"set session character_set_client=utf8");


  $sql="INSERT INTO login (id, pw,name,pw_identify,major) VALUES('$id','$pw','$name','$pw_identify','$major' )";
  
  mysqli_query($connect, $sql);
  echo $id;
  echo '<br />';
  echo $pw;
  echo '<br />';
 

?>