<?php
 $connect=mysqli_connect( "db.appddi.com", "appddi", "dydgur2009!","dbappddi");   
 extract($_REQUEST);


 mysqli_query( $connect,"set name utf8");
    mysqli_query( $connect,"set session character_set_connection=utf8");

    mysqli_query( $connect,"set session character_set_results=utf8");

    mysqli_query( $connect,"set session character_set_client=utf8");

 if ($_FILES["file1"]["error"] > 0)
  {
  echo "Error: " . $_FILES["file1"]["error"] . "<br>";
  }
else
  {
  echo "Upload: " . $_FILES["file1"]["name"] . "<br>";
  echo "Type: " . $_FILES["file1"]["type"] . "<br>";
  echo "Size: " . ($_FILES["file1"]["size"] / 1024) . " kB<br>";
  echo "Stored in: " . $_FILES["file1"]["tmp_name"];

  }
 
 $image_dir= 'image/';
 $file_name = iconv("UTF-8","EUC-KR",$_FILES['file1']['name']); 
 $image_file = $image_dir . $file_name;
 $image_path1 = "\\http://appddi.com/database/\\".$image_dir .$_FILES['file1']['name']; 
 $image_file2 = "image/". $file_name;
  if ( !is_dir($image_dir) ); 
  if ( !is_writable($image_dir) );
//  chmod("images/" , 777);
 
  $file_name2 = iconv("UTF-8","EUC-KR",$_FILES['file2']['name']);
  $image_file2 = $image_dir . $file_name2;
  if(move_uploaded_file($_FILES["file2"]["tmp_name"], $image_file2)){
 
  umask(0);
 // chmod("images/" , 777);
   print '<p> The file2 has been successfully uploaded </p>';
  $image_path2 = "\\http://appddi.com/database/\\".$image_dir .$_FILES['file2']['name'];
 }else{
  $image_path2 = "";
  }
  $file_name3 = iconv("UTF-8","EUC-KR",$_FILES['file3']['name']);
  $image_file3 = $image_dir . $file_name3;
  if(move_uploaded_file($_FILES["file3"]["tmp_name"], $image_file3)){
 
  umask(0);
 // chmod("images/" , 777);
   print '<p> The file3 has been successfully uploaded </p>';
  $image_path3 = "\\http://appddi.com/database/\\".$image_dir .$_FILES['file3']['name'];
 }else{
  $image_path3 = "";
  }
  $file_name4 = iconv("UTF-8","EUC-KR",$_FILES['file3']['name']);
  $image_file4 = $image_dir . $file_name4;
  if(move_uploaded_file($_FILES["file4"]["tmp_name"], $image_file4)){
 
  umask(0);
 // chmod("images/" , 777);
   print '<p> The file4 has been successfully uploaded </p>';
  $image_path4 = "\\http://appddi.com/database/\\".$image_dir .$_FILES['file4']['name'];
 }
  $image_path4 = "";
  
  $file_name5 = iconv("UTF-8","EUC-KR",$_FILES['file5']['name']);
  $image_file5 = $image_dir . $file_name5;
  if(move_uploaded_file($_FILES["file5"]["tmp_name"], $image_file5)){
 
  umask(0);
 // chmod("images/" , 777);
   print '<p> The file5 has been successfully uploaded </p>';
  $image_path5 = "\\http://appddi.com/database/\\".$image_dir .$_FILES['file5']['name'];
 }else{
  $image_path5 = "";
  }
  $file_name6 = iconv("UTF-8","EUC-KR",$_FILES['file6']['name']);
  $image_file6 = $image_dir . $file_name6;
  if(move_uploaded_file($_FILES["file6"]["tmp_name"], $image_file6)){
 
  umask(0);
 // chmod("images/" , 777);
   print '<p> The file6 has been successfully uploaded </p>';
  $image_path6 = "\\http://appddi.com/database/\\".$image_dir .$_FILES['file6']['name'];
 }else{
  $image_path6 = "";
  }
  
  $file_name7 = iconv("UTF-8","EUC-KR",$_FILES['file7']['name']);
  $image_file7 = $image_dir . $file_name7;
  if(move_uploaded_file($_FILES["file7"]["tmp_name"], $image_file7)){
 
  umask(0);
 // chmod("images/" , 777);
   print '<p> The file7 has been successfully uploaded </p>';
  $image_path7 = "\\http://appddi.com/database/\\".$image_dir .$_FILES['file7']['name'];
 }else{
  $image_path7 ="";
 }
 
 if(move_uploaded_file($_FILES["file1"]["tmp_name"], $image_file)){
 
  umask(0);
 // chmod("images/" , 777);
   print '<p> The file has been successfully uploaded </p>';
 
  }else
      { 
        switch ($_FILES["file"] ["error"])
         {  case 1:
                   print '<p> The file is bigger than this PHP installation allows</p>';
                   break;
            case 2:
                   print '<p> The file is bigger than this form allows</p>';
                   break;
            case 3:
                   print '<p> Only part of the file was uploaded</p>';
                   break;
            case 4:
                   print '<p> No file was uploaded</p>';
                   break;
         }

    }
    $all_image_path = $image_path1."\n".$image_path2."\n".$image_path3."\n".$image_path4."\n".$image_path5."\n".$image_path6."\n".$image_path7;
    
    $all_memu = $menu1."!".$menu2."!".$menu3."!".$menu4."!".$menu5."!".$menu6."!".$menu7."!".$menu8;
    $all_payment = $payment1."!".$payment2."!".$payment3."!".$payment4."!".$payment5."!".$payment6."!".$payment7."!".$payment8;
   // echo $menu1;
   // echo $menu2;
   // echo $menu3;
  //  echo $menu4;
    

    
 //   echo $all_payment;
    $sql="INSERT INTO info ( image_path, title, content,category,time,phone,address,latitude, longitude, people, menu, payment,time2) VALUES('$all_image_path','$title','$content','$category','$time','$phone','$address','$latitude','$longitude','$people','$all_memu', '$all_payment','$time2' )";
    
    
    $result=mysqli_query($connect,$sql);
 ?>
