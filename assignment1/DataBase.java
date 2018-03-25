import java.util.ArrayList;

public class DataBase {

	
	private static DataBase instance;
	public ArrayList<Person> personList = new ArrayList<Person>(); // all users in the social network
	public ArrayList<Adult> adultList = new ArrayList<Adult>();		// all adults in the social network
	public ArrayList<Teenager> teenagerList = new ArrayList<Teenager>();// all teenagers in the social network
	
	public static DataBase getInstance(){
		if(instance == null){
			instance = new DataBase();
		}
		return instance;
	}
	
	private DataBase(){
		
		initData();
	}
	
	/**
	 * initialize data when startup.
	 */
	private void initData(){
		
		
		Adult adult;
		
		adult = new Adult("Alice","Female", 20,"dance,music","student at RMIT");
		personList.add(adult);
		adultList.add(adult);
		
		adult = new Adult("Bob","Male", 21,"sports","working at KFC");
		personList.add(adult);
		adultList.add(adult);
		
		adult = new Adult("Cathy","Female", 22,"math,music","Freelance");
		personList.add(adult);
		adultList.add(adult);
		
		adult = new Adult("Don","Male", 23,"football,baseball","looking for jobs");
		personList.add(adult);
		adultList.add(adult);
		
		
		
		Teenager teenager = new Teenager("Kevin","Male",4,"nothing","nothing",adultList.get(1),adultList.get(0));
		this.teenagerList.add(teenager);
		this.personList.add(teenager);
		teenager = new Teenager("David","Male",4,"nothing","nothing",adultList.get(1),adultList.get(0));
		this.teenagerList.add(teenager);
		this.personList.add(teenager);
	}
	
	/**
	 *  search a user by name
	 * @param name
	 * @return
	 */
	public Person getUserByName(String name){
		
		Person user = null;
		
		for(Person cur : personList){
			if(cur.getName().equals(name)){
				user = cur;
				break;
			}
		}
		
		return user;
	}
	
	/**
	 * search a adult user by name
	 * @param name
	 * @return
	 */
	public Adult getAdultUserByName(String name){
	
		Adult user = null;
		
		for(Adult cur : adultList){
			if(cur.getName().equals(name)){
				user = cur;
				break;
			}
		}
		
		return user;
	}
	
	/**
	 * search a teenager user by name
	 * @param name
	 * @return
	 */
	public Teenager getTeenagerUserByName(String name){
		
		Teenager user = null;
		
		for(Teenager cur : teenagerList){
			if(cur.getName().equals(name)){
				user = cur;
				break;
			}
		}
		
		return user;
	}
	
	/**
	 * add new user
	 */
	public boolean addNewUser(Person newUser){
		boolean flag = false;
		if(newUser != null && !this.personList.contains(newUser)){
			personList.add(newUser);
			if(newUser instanceof Adult){
				this.adultList.add((Adult)newUser);
			}else{
				this.teenagerList.add((Teenager)newUser);
			}
			flag = true;
		}
		return flag;
	}
	
	/**
	 * delete a user from db system
	 * @param user
	 * @return
	 */
	public boolean deleteUser(Person user){
		if(user == null) return false;
		boolean flag = false;
		for(Person temp : personList){
			if(temp == user){
				
				for(Person friend : temp.friendList){
					friend.friendList.remove(temp);
				}
				personList.remove(user);
				flag = true;
			}
			break;
		}
		
		return flag;
	}

}
