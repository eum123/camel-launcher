# 설
spring boot을 이용하여 apache camel 실행 시키는 기능
 
 
디렉토리 구성
=============
 /                - root directory
 
  ㄴ conf /        - 공통 설정 파일. application.properties, camel-core.xml, core.xml, customer-beans.xml
  
  ㄴ conf / route  - camel xml route 파일
 

환경 설정 
========
java 실행 옵션에 CAME__HOME 을 반드시 추가 해야 한다.    
ex) java -jar -DCAMEL_HOME=src/test/resources ....


application.properties
--------------
환경 설정 

* camel.router.scan=true 
> camel XML route 파일의 변경을 감지해서 자동 적용 여부. true-자동적용, false-미적용 
* sample
> ???



camel-core.xml
--------------
thread pool, notification 등 camel에서 사용할 설정
 

core.xml
---------
camel 이외 설정


customer-beans.xml
-----------------------
사용자 정의 bean을 등록하는 설정





개인정보
---------

email : eummanjin@gmail.com




참고
-----
처음 사용해서 기본 문법 남겨둠..


 2. 글머리
# This is a H1
## This is a H2
### This is a H3
#### This is a H4
##### This is a H5

 3. 들여쓰기
> This is a blockqute

 4. 순서없는 목록
* One
* Two
* Three

 5. 코드 블럭
<pre><code> This is a code block </code></pre>

 6. 이미지
![Alt text](/path/to/img.jpg)
![Alt text](http://swalloow.tistory.com/321451611/)
