package study.effective_java_3E.item01;

public class Foo implements FooInterface{
	String name;
	String age;
	
	public Foo() {}
	
	public Foo(String name, String age) {
		this.name = name;
		this.age = age;
	}
	
	public static Foo getChildFooWithName(String name) {
		Foo myFoo = new MyFoo();
		myFoo.age = name;
		return myFoo;
	}
	
	public static void daySchedule() {
		System.out.println("출근한다.");
		System.out.println("운동을 간다.");
		dayweekSchedule();
	}
	
	public static void weekSchedule() {
		System.out.println("데이트를 한다.");
		System.out.println("영화를 본다.");
		dayweekSchedule();
	}
	
	private static void dayweekSchedule() {
		System.out.println("공부한다.");
		System.out.println("잔다.");
	}
}