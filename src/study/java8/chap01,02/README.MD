Oracle JDK와 OpenJDK
=============
> JDK는 2개 버전으로 나뉜다. 하나는 폐쇄적인 상업 코드 기반의 Oracle JDK이고 하나는 오픈 소스 기반의 OpenJDK이다. 
제공되는 기능적 차이 외에 Java 6 시대에는 분명히 OpenJDK가 Oracle JDK보다 성능이나 안정성이 크게 떨어졌다. 하지만 오늘날에는 Oracle JDK만이 제공하는 일부 라이브러리를 제외하고는 차이가 없다

  
### 1. Oracle JDK
  * 기본적으로 OpenJDK를 기반으로 하며 저작권자가 오픈소스화를 거절한 컴포넌트 등이 추가된 버전이 Oracle JDK이다.
  * Oracle JDK 바이너리에 적용되던 BCL 라이센스가 2018년 7월 부로 Java SE Subscription라는 이름의 년 단위 유료 구독형 라이센스로 새롭게 개편되었다.
    (최근 자바 유료화라고 많이들 나오는데 월래 oracleJDK 라이센스는 유료였고 이걸 구독형 라이센스로 바꾼 것)
  * 구독한 라이센스 기간이 종료되면 사용자는 사용 권리를 잃어버린다. 라이센스를 갱신하거나 다른 무료 OpenJDK 바이너리로 전환해야 한다. 
    (GPL 라이센스 기반의 OpenJDK 소스 코드는 LTS 지원 없이 6개월마다 새로운 버전이 공개된다.)
  * 새로운 라이센스에서도 비상업적인 용도에 한해서는 이전과 같은 무료 사용이 가능하다.
### 2. OpenJDK
  * OpenJDK도 운영 환경에에서 TCK 인증을 받은 OpenJDK 기반의 빌드 버전을 사용하면 운영 환경에 아무런 문제가 없다.
  * Java SE 7부터 모든 JDK는 OpenJDK 레퍼런스 소스 코드를 기반으로 제작된다. 
  * OpenJDK의 운영 주체는 오라클이다. 오라클 또한 OpenJDK를 기반으로 자사의 부가적인 기능을 추가한 Oracle JDK를 제작하여 배포한다.
  * 오픈소스이니만큼 http://openjdk.java.net/contribute/에 가면 누구나 openjdk에 기여 할 수 있습니다.
  * OpenJDK 구현체는 JCP(오라클, IBM, SAP 등으로 구성)에 의해서 결정된다.
    - 많은 상업 JVM 벤더들이 이 OpenJDK 레퍼런스 소스 코드를 기반으로 각자의 JVM을 제작한다. 
    - 엄격한 테스트의 TCK 인증을 통과하면 OpenJDK를 완벽히 준수하는 구현체로 인정된다.
    - Oracle JDK 또한 Java SE 7부터 이 OpenJDK를 기반으로 앞서 설명한 추가 요소를 담아 배포한다.
