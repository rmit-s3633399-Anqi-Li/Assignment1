import java.util.ArrayList;

/**
 * Person class
 * which actually is the user class of the social network.
 *
 */
public class Person {

	private String name;
	private int age;
	private String gender;
	private String hobbies;
	private String status;
	
	
	ArrayList<Person> friendList = new ArrayList<Person>();
	
	public Person(String name,String gender, int age ,String hobbies,String status) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.hobbies = hobbies;
		this.status = status;
	}

	public String getName(){
		return name;
	}
	
	public String getGender(){
		return gender;
	}
	
	public int getAge(){
		return age;
	}

	
	
	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
