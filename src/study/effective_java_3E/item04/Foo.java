package study.effective_java_3E.item04;

public class Foo {

	public static void main(String[] args) {
		
		//private 생성자를 만들기 전 객체가 만들어진다.
		//FooBehavior객체에 private 생성자를 생성하면 객체가 안만들어진다.
		//FooBank 저금 = new FooBank();
		
		
		int 내재산 = 10000000;
		int 월급 = 2000000;
		FooBank.plus(내재산, 월급);
		

	}

}
