생성자 대신 정적 팩터리 매서드를 고려하라
=============
> 클래스는 생성자와 별도로 정적 팩터리 메서드(static factory method)를 제공할 수 있다.
  정적 팩터리 메서드가 생성자보다 좋은 장점 다섯가지를 알아보자.
  
### 1. 첫번째, 이름을 가질 수 있다.
  * 이름만 잘 지으면 반환될 객체의 특성을 쉽게 묘사할 수 있다.
    - 생성자인 BigInteger(int, int, Random)과 정적 팩터리 메서드인 BigInteger.probablePrime 중 후자가 '값이 소수인 BigInteger를 반환한다'는
      의미를 더 잘 설명 할 수 있다.
### 2. 두번째, 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
  * 대표적인 예인 Boolean.valueOf(boolean) 메서드는 객체를 아예 생성하지 않는다.
    - 같은 객체가 자주 요청되는 상황에서 성능을 상당히 끌어올려주고 플라이웨이트 패턴도 이와 비슷한 기법이다.
    - 반복되는 요청에 같은 객체를 반환하는 식으로 정적 팩터리 방식의 클래스는 언제 어느 인스턴스를 살아 있게 할지를 통제 할 수 있다.
      이런 클래스를 인스턴스 통제라고 한다. 인스턴스를 통제하면 클래스를 싱글턴, 인스턴스화 불가로 만들 수 있으며 불변 값 클래스에서 동치인 
      인스턴스가 단 하나뿐임을 보장할 수 있다.
### 3. 세번째, 반환타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
  * 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 엄청난 유연성을 말한다.
  * 자바 컬렉션 프레임워크는 핵심 인터페이스들에 수정 불가나 동기화 등의 기능을 덧붙인 총 45개의 유틸리티 구현체를 제공하는데 이 구현체 대부분을
    단 하나의 인스턴스화 불가 클래스인 java.util.Collections에서 정적 팩터리 메서드를 통해 얻도록 했다. 이는 클래스를 공개하지 않기 때문에 API
    외견을 훨씬 작게 만들수 있었고 이로서 프로그래머가 API를 사용하기 위해 익여햐 하는 개념의 수와 난이도도 낮췄다.
  * 자바 8부터는 인터페이스가 정적 메서드를 가질 수 없다는 제한이 풀렸기 때문에 동반 클래스에 두었던 public 정적 멤버들 상당수를 그냥 인터페이스 
    자체에 두면 된다. 하지만 정적 메서드들을 구현하기 위한 코드 중 많은 부분은 여전히 별도의 package-private 클래스에 두어야 할 수 있다. 
    자바 8에서도 인터페이스에는 public 정적 멤버만 허용하기 때문이다. 자바 9에서는 private 정적 메서드까지 허락하지만 정적 필드와 정적 멤버 
    클래스는 여전히 public 이어야 한다.
### 4. 네번째, 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
  * 반환 타입의 하위 타입이기만 하면 어떤 클래스의 객체를 반환하든 상관없다.
### 5. 다섯 번째, 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
  * 서비스 제공자 프래임워크를 만드는 근간이 되며 대표적으로 JDBC(Java Database Connectivity)가 있다.
    - 서비스 제공자 프레임워크는 3개의 핵심 컴포넌트로 이뤄진다. 구현체의 동작을 정의 하는 서비스 인터페이스(service interface), 제공자가 
      구현체를 등록할 때 사용하는 제공자 등록 API(provider registration API), 클라이언트가 서비스의 인스턴스를 얻을 때 사용하는 서비스 
      접근 API(serviceaccess API).
    - JDBC에서는 Connection이 서비스 인터페이스 역할을, DriverManager.registerDriver가 제공자 등록 API 역할을, DriverManager.getConnection이
      서비스 접근 API역할을 Driver가 서비스 제공자 인터페이스 역할을 수행한다.
      
      
> 이제 단점을 알아볼 차례이다. 

### 1. 첫번째, 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.
  * 앞서 이야기한 컬렉션 프레임워크의 유틸리티 구현 클래스들은 상속할 수 없다는 이야기이다.
  * 이 제약은 상속보다 컴포지션을 사용하도록 유도하고 불변 타입으로 만들려면 이 제약을 지켜야 한다는 점에서 오히려 장점으로 받아들일 수 있다.
### 2. 두번째, 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.
  * 생성자처럼 API설명에 명확히 드러나지 않으니 사용자는 정적 팩터리 메서드 방식 클래스를 인스턴스화할 방법을 찾아야 한다.
  * API문서를 잘 써롷고 메서드 이름도 널리 알려진 규약을 따라 짓는 식으로 문제를 완화해줘야 한다.
  * 다음은 정적 팩터리 메서드에 흔히 사용하는 명명 방식들이다.
    - from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드
      ex) Date d = Date.from(instant);
    - of : 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드
      ex) Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
    - valueOf : from과 of의 더 자세한 버전
      ex) BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
    - instance 혹은 getInstance : (매개변수를 받는다면) 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지 않는다.
      ex)StackWalker luke = StackWalker.getInstance(options);
    - create 혹은 newInstance : instance 혹은 getInstance와 같지만 매번 새로운 인스턴스를 생성해 반환함을 보장한다.
      ex) Object newArray = Array.newInstance(classObject, arrayLen);
    - getType : getInstance와 같으나, 생성할 클래스가 아닌 클래스에 팩터리 메서드를 정의할 때 쓴다. "Type"은 팩터리 메서드가 반환할 객체 타입
      ex) FileStore fs = Files.getFileStore(path);
    - newType : newInstance와 같으나 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할때 쓴다. "Type"은 팩터리 메서드가 반환할 객체 타입
      ex) BufferedReader br = Files.newBufferedReader(path);
    - type : getType과 newType의 간결한 버전
      ex) List<Complaint> litany = Collections.list(legacyLitany);
