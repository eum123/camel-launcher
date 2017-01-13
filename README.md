# 설명

spring boot을 이용하여 apache camel 실행 시키는 기능.
XML route 파일의 변경을 감지하여 자동 반영한다.
사용자 library를 classloader에 추가한다.


배포
====
mvn package 를 통해 필요한 라이브러리 모두 취합하여 하나의 library로 만든다.
아래 설정을 하여 실행한다.

 
 
디렉토리 구성
=============
 /                - root directory
 
  ㄴ conf /        - 공통 설정 파일. application.properties, camel-core.xml, core.xml, customer-beans.xml
  
  ㄴ conf / route  - camel xml route 파일
 

환경 설정 
========
java 실행 옵션에 CAME__HOME 을 반드시 추가 해야 한다.    
ex) java -jar -DCAMEL_HOME=src/test/resources ....

* CAMEL_HOME : camel home 위치. 설정하지 않으면 user.dir를 home으로 설정한다.
* CAMEL_CONF : 설정 파일 위치. 설정하지 않으면 CAMEL_HOME/conf 로 설정한다.
* CAMEL_ROUTER : XML route 파일 위치. 설정하지 않으면 CAMEL_CONF/router 로 설정한다.
* CAMEL_LIB : 외부 라이브러리 추가 위치. 처음 동작 시 외부 라이브러리를 읽어서 반영한다. 설정하지 않으면 CAMEL_HOME/lib로 설정한다.
* logging.config : logback 설정 파일 위치 지정.


application.properties
--------------
환경 설정 

* camel.router.scan=true 
: camel XML route 파일의 변경을 감지해서 자동 적용 여부. true-자동적용, false-미적용 .
* camel.router.scan.interval=5000
: camel.router.scan=true일때 동작하며 설정 정보 reload 주기. 단위 millisecond.




camel-core.xml
--------------
thread pool, notification 등 camel에서 사용할 설정.
 

core.xml
---------
camel 이외 설정.


customer-beans.xml
-----------------------
사용자 정의 bean을 등록하는 설정.





개인정보
---------

email : eummanjin@gmail.com

camel에 대해서 많이 사용해보지 않았지만 정보 공유 및 문의 사항있으면 메일주세요. 



