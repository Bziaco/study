생성자 대신 정적 팩터리 매서드를 고려하라
=============
> 클래스는 생성자와 별도로 정적 팩터리 메서드(static factory method)를 제공할 수 있다.
<pre><code>
public static Boolean valueOf(boolean b) {
  return b ? Boolean.TRUE : Boolean.FALSE;
}
</code></pre>
