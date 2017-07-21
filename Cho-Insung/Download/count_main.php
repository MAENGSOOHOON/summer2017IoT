<?php
    // 데이터베이스 접속 문자열. (db위치, 유저 이름, 비밀번호)
    $connect=mysqli_connect( "db.appddi.com", "appddi", "dydgur2009!","dbappddi") or  
        die( "SQL server에 연결할 수 없습니다.");
    
    mysqli_query( $connect,"set name utf8");
    mysqli_query( $connect,"set session character_set_connection=utf8");
    mysqli_query( $connect,"set session character_set_results=utf8");
    mysqli_query( $connect,"set session character_set_client=utf8");
    
   
   // 데이터베이스 선택
  
 
   // 세션 시작
   session_start();
 
   // 쿼리문 생성
   $sql = "select main from count";
 
   // 쿼리 실행 결과를 $result에 저장
   $result = mysqli_query($connect, $sql);
   // 반환된 전체 레코드 수 저장.
   $total_record = mysqli_num_rows($result);
 
   // JSONArray 형식으로 만들기 위해서...
  
 
   // 반환된 각 레코드별로 JSONArray 형식으로 만들기.
 
     
      // 가져올 레코드로 위치(포인터) 이동  
          
        
      $row = mysqli_fetch_array($result);
   
   $number = $row[main] +1;
   
   $sql2 =  "update count set main= '$number'";
  mysqli_query($connect, $sql2);


   
   echo "\"$number\" ";
   
   echo "\" $sql2\" ";
        
 
   // 마지막 레코드 이전엔 ,를 붙인다. 그래야 데이터 구분이 되니깐.  
  
  ;
   // JSONArray의 마지막 닫기
  

  
?>