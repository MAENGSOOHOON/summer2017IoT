<?php
  $title = $_POST['title'];
  $content = $_POST['content'];
  $name = $_POST['name'];
  $anonymity = $_POST['anonymity'];
  $time = $_POST['time'];
  
  $connect=mysqli_connect( "db.appddi.com", "appddi", "dydgur2009!","dbappddi") or  
        die( "SQL server에 연결할 수 없습니다.");
    
    mysqli_query( $connect,"set name utf8");
    mysqli_query( $connect,"set session character_set_connection=utf8");
    mysqli_query( $connect,"set session character_set_results=utf8");
    mysqli_query( $connect,"set session character_set_client=utf8");


  $sql="INSERT INTO freeboard (title,name, content,anonymity, time) VALUES('$title','$name', '$content',  '$anonymity', '$time')";
  
  mysqli_query($connect, $sql);
  
  $sql="SELECT * from freeboard where title = '$title' and time = '$time' ";  
  $result =  mysqli_query($connect, $sql);
  

  
  while($row = mysqli_fetch_array($result)){
      echo $row[inumber];
    }
  
 

?>