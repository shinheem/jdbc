package koreait.jdbc.day03;

//DTO : Data Transfer Object는 가변객체로 데이터를 객체간에 전달하기 위한 목적입니다.
//	ㄴ 메소드의 매개변수 또는 리턴 타입으로 사용하기 위해
//	ㄴ 테이블의 컬럼 구성과 같은 클래스를 정의하여 값을 저장하기 위한 것입니다.
//VO : VALUE OBJECT 는 불변객체로 equals 와 hashcode 재정의를 하여 set 또는 map 객체에서 활용,
public class StudentDto {
	private String stuno;
	private String name;
	private int age;
	private String address;
	
	public StudentDto() {
		
	}
	
	public StudentDto(String stuno, String name, int age, String address) {
		super();
		this.stuno = stuno;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getStuno() {
		return stuno;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getAddress() {
		return address;
	}

	public void setStuno(String stuno) {
		this.stuno = stuno;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "StudentDto [stuno=" + stuno + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}
}