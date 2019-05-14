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
		System.out.println("����Ѵ�.");
		System.out.println("��� ����.");
		dayweekSchedule();
	}
	
	public static void weekSchedule() {
		System.out.println("����Ʈ�� �Ѵ�.");
		System.out.println("��ȭ�� ����.");
		dayweekSchedule();
	}
	
	private static void dayweekSchedule() {
		System.out.println("�����Ѵ�.");
		System.out.println("�ܴ�.");
	}
}