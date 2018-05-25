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
application.yml로 변경함


<pre>
camel:
  spring:
    core-resource-name: core.xml                               # core 설정
  path:
    home: /work/project/camel-launcher/src/main/resources      # home 위치
    conf: ${camel.path.home}/conf                              # config 위치
  springboot:
    xml-routes-reload-directory: ${camel.path.conf}/router/    # 자동 reload할 router 파일 위치
    xml-routes: file://${camel.path.conf}/router/*.xml         # router 파일
    main-run-controller: true
</pre>







개인정보
---------

email : eummanjin@gmail.com

camel에 대해서 많이 사용해보지 않았지만 정보 공유 및 문의 사항있으면 메일주세요. 



