private 생성자나 열거 타입으로 싱글턴임을 보증하라
=============
* 싱글턴이란 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말한다.
  - 싱글턴의 전형적인 예로는 함수와 같은 무상태(stateless) 객체나 설계상 유일해야 하는 시스템 컴포넌트를 들 수 있다.
  - 하지만 클래스를 싱글턴으로 만들면 이를 사용하는 클라이언트를 테스트하기가 어려워질 수 있다. 타입을 인터페이스로 정의한 다음
    그 인터페이스를 구현해서 만든 싱글턴이 아니라면 싱글턴 인스턴스를 가짜(mock) 구현으로 대체할 수 없기 때문이다.
* 싱글턴을 만드는 방식은 보통 둘 중 하나이다. 두 방식 모두 생성자는 private로 감춰두고 유일한 인스턴스에 접근할 수 있는 수단으로
  public static 멤버를 하나 마련해둔다.  아래는 public static 멤버가 final필드인 방식 이다.

  <pre><code>public class Elvis {
    public static final Evis INSTANCE = new Elvis();
    private Elvis() {...}

    public void leaveTheBuilding() {....}
  }</code></pre>
  
  - private 생성자는 public static final 필드인 Elvis.INSTANCE를 초기화 할때 딱 한번만 호출된다. public이나 protected 생성자가
    없으므로 전체시스템에 하나뿐임이 보장된다. 그러나 리플렉션 API인 AccessibleObject.setAccessible을 사용해 private 생성자를 
    호출할 수 있다. 이러한 공격을 방어하기 위해 두번째 객체가 생성되려할 때 예외를 던지게 하면 된다.
  
* 싱글턴을 만드는 방법 두번째 방법에서는 정적 팩터리 메서드를 public static 멤버로 제공한다.
    
  <pre><code>public class Elvis {
    private static final Evis INSTANCE = new Elvis();
    private Elvis() {...}
    public static Elvis getInstance() { return INSTANCE }

    public void leaveTheBuilding() {....}
  }</code></pre>
     
  - Evis.getInstance는 항상 같은 객체의 참조를 반환하므로 제2의 Elvis 인스턴스란 결코 만들어지지 않지만 이도 리플렉션을 통한 예외는 적용된다.
  - 정적 팩터리 방식의 첫번째 장점은 API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다. 유일한 인스턴스를 반환하던 팩터리 메서드가 호출하는 
    스레드별로 다른 인스턴스를 넘겨주게 할 수 있다.
  - 두번째 장점은 원한다면 정적 팩터리를 제니릭 싱글턴 팩터리로 만들 수 있다는 점이다.
  - 세번째 장점은 정적 팩터리의 메서드 참조를 공급자(supplier)로 사용할 수 있다.
  
* 둘 중 하나의 방식으로 만ㄷ느 싱글턴 클래스를 직렬화하려면 단순히 Serializable을 구현한다고 선언하는 것만으로는 부족하다
  - 모든 인스턴스 필드를 일시적(transient)이라고 선언하고 readResolve 메서드를 제공해야 한다. 이렇게 하지 않으면 직렬화된 인스턴스를 
    역직렬화할 때마다 새로운 인스턴스가 만들어진다. 아래코드는 싱글턴임을 보장해주는 readResolve 메서드이다.
    
    <pre><code>private Object readResolve() {
      // '진짜' Elvis를 반환하고 가자 Elvis는 가비지 컬렉터에 맡긴다.
      return INSTANCE
    }</code></pre>

* 싱글턴을 만드는 세번째 방법은 원소가 하나인 열거 타입을 선언하는 것이다.
  <pre><code>public enum Elvis {
    INSTANCE;
    
    public void leaveTheBuilding() {...}
  }</code></pre>
  
  - public 필드 방식과 비슷하지만 더 간결하고 추가 노력없이 직렬화할 수 있고 복잡한 직렬상황 및 리플렉션 공격에서도 제2의 인스턴스 생성을 
    완벽히 막아준다.
  - 원소가 하나뿐임 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다.
