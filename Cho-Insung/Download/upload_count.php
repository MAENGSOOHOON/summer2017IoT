<?php  
$con=mysqli_connect("db.appddi.com","appddi","dydgur2009!","dbappddi");  
  
if (mysqli_connect_errno($con))  
{  
   echo "Failed to connect to MySQL: " . mysqli_connect_error();  
}  
  $name =$_GET[name];
 
 

  echo $name;
  ;
  


  
  $sql = "select * from info where title='$name'";
  echo $sql;
  
  $result2 = mysqli_query($con,$sql );  
  
  echo $result2;
  
  $row = mysqli_fetch_array($result2);

   $total_record = mysqli_num_rows($result2);

  echo $row[count];
  
  $qq =  $row[count] +1;
  echo $qq;
  
  

  $sql2 = "update info set count = '$qq' where title = '$name'";
  
  echo $sql2;
$result = mysqli_query($con, $sql2 );  
// echo $result;
  
 

  
mysqli_close($con);  
?>  