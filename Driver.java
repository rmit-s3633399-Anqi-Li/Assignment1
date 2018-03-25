import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Driver calss
 * 
 *The driver class is where user interaction occurs,
 * and it should utilise other classes to manage the social network. 
 * 
 * The driver class is where user interaction occurs, and it should utilise other classes to manage the social network. It should support the following functionalities:
 * Add a person into the network
 * Select a person by name
 * Display the profile of the selected person
 * Update the profile information of the selected person
 * Delete the selected person
 * Connect two persons in a meaningful waye.g.friend,parent
 * Find out whether a person is a direct friend of another person
 * Find out the name(s)of a person’schild(ren) or the names of the parents
 */
public class Driver {
	private int menuChoice = 0;
	private Person seletedPerson = null;
	private DataBase db = DataBase.getInstance();
	Scanner inputChoice = new Scanner(System.in);
	
	public void start(){
		
		showMenu();
	}
	
	/**
	 *  Main Menu operations
	 */

	public void showMenu() {
		
		System.out.println();
		System.out.println("MiniNet Menu \n===================================");
		System.out.println("1. List everyone.");
		System.out.println("2. Find out the parent names of a teenager");
		System.out.println("3. Find out all children of a adult");
		System.out.println("4. Are these two direct friends?");
		System.out.println("5. Exit");

		try {
			System.out.println();
			System.out.println("Enter an option:");
			
			menuChoice = inputChoice.nextInt();

			switch (menuChoice) {
			case 1:
				listEveryOneFunction();
				break;
			case 2:
				findoutParentNames();
				showMenu();
				break;
			case 3:
				findoutChidlrenNames();
				showMenu();
				break;
			case 4:
				checkRelationship();
				showMenu();
				break;
			default:
				showErrorMessage("the input is outside of that range");
				showMenu();
				break;
			}
		} catch (InputMismatchException e) {
			showErrorMessage(null);
			showMenu();
		}

	}
	
	/**
	 * Find out parents names for a teenager.
	 */
	private void findoutParentNames(){
		
		System.out.println();
		System.out.print("Please input the teenager's name : ");
		String name = inputChoice.next();

		Teenager teenager = db.getTeenagerUserByName(name);
		if(teenager == null){
			showErrorMessage("not found the inputed name in social network");
			return;
		}
		Adult mother = teenager.getMother();
		Adult father = teenager.getFather();
		System.out.println(teenager.getName()+"'s father is : " + father.getName());
		System.out.println(teenager.getName()+"'s mother is : " + mother.getName());
	}
	
	/**
	 * Find out all children names for a adult
	 */
	private void findoutChidlrenNames(){
		
		System.out.println();
		System.out.print("Please input the adult's name : ");
		String name = inputChoice.next();

		Adult adult = db.getAdultUserByName(name);
		if(adult == null){
			showErrorMessage("not found the inputed name in social network");
			return;
		}
		if(adult.getAllChildren().size() == 0){
			System.out.println();
			System.out.print(adult.getName() + " doesn't have any child.");
		}else{
			System.out.println("children list \n===================================");
			for(Teenager child : adult.getAllChildren()){
				System.out.println("Name : "+child.getName() + " , Gender : " + child.getGender() + " , Age : " + child.getAge());
			}
			System.out.println();
		}
	}
	
	/**
	 * Find out whether a person is a direct friend of another person
	 */
	private void checkRelationship(){
		
		System.out.println();
		System.out.print("Please input the first person's name : ");
		String name1 = inputChoice.next();

		Person user1 = db.getUserByName(name1);
		if(user1 == null){
			showErrorMessage("not found the inputed name in social network");
			return;
		}
		
		System.out.println();
		System.out.print("Please input the second person's name : ");
		String name2 = inputChoice.next();

		Person user2 = db.getUserByName(name2);
		if(user2 == null){
			showErrorMessage("not found the inputed name in social network");
			return;
		}
		
		if(user1.friendList.contains(user2)){
			
			System.out.println();
			System.out.print(user1.getName()+" and "+user2.getName() + "are direct friends in the social network.");
			System.out.println();
		}else{
			System.out.println();
			System.out.print(user1.getName()+" and "+user2.getName() + "are not direct friends in the social network.");
			System.out.println();
		}
	}
	
	/**
	 *  All user list operations
	 */
	
	private void listEveryOneFunction(){
		
		System.out.println();
		System.out.println("User List \n===================================");
		
		for(int i = 0 ; i < db.personList.size() ; i ++){
			Person currentUser = db.personList.get(i);
			System.out.println((i+1)+". Name: "+currentUser.getName() +  " , Age: " + currentUser.getAge());
		}
		//show the options for user list view
		
		System.out.println("Options \n===================================");
		System.out.println("1. Select a person by index");
		System.out.println("2. Select a person by name");
		System.out.println("3. Add a new person to the social network");
		System.out.println("4. Back");
		System.out.println("5. Exit");
		
		try {
			System.out.println("Enter an option:");
			inputChoice = new Scanner(System.in);
			menuChoice = inputChoice.nextInt();

			switch (menuChoice) {
			case 1:
				selectPersonByIndexFunction();
				break;
			case 2:
				selectPersonByNameFunction();
				break;
			case 3:
				addNewUser();
				listEveryOneFunction();
				break;
			case 4:
				showMenu();
				break;
			case 5:
				break;
			default:
				showErrorMessage("the input is outside of that range");
				listEveryOneFunction();
				break;
			}
		} catch (InputMismatchException e) {
			showErrorMessage(null);
			listEveryOneFunction();
		}
		
		
	}
	

	private void selectPersonByIndexFunction(){
		
		try {
			System.out.println("Please input an index :");
			menuChoice = inputChoice.nextInt()-1;
			if(menuChoice < 0 || menuChoice >= db.personList.size()){
				showErrorMessage("the input index is out side the range");
				selectPersonByIndexFunction();
			}else{
				this.seletedPerson = db.personList.get(menuChoice);
				if(seletedPerson == null) throw new InputMismatchException();
				showSelectedPersonFunction();
			}
			
		} catch (InputMismatchException e) {
			showErrorMessage(null);
			selectPersonByIndexFunction();
		}
		
	}
	
	private void selectPersonByNameFunction(){
		System.out.println("Please input a name :");
		String seletedName = inputChoice.next();
		this.seletedPerson = db.getUserByName(seletedName);
		if(seletedPerson == null){
			showErrorMessage("the inputed name doesn't exist in system");
			selectPersonByNameFunction();
		}else{
			showSelectedPersonFunction();
		}
		
	}
	
	private void addNewUser(){
		
		Person newUser = null;
		String name = null;
		String gender = null;
		int age = -1;
		String hobbies = null;
		String status = null;
		//parents for a teenager
		Adult father = null;
		Adult mother = null;
		// name:
		do{
			System.out.println("Please input the name of the new user: ");
			name = inputChoice.next();
			if(this.db.getUserByName(name)!=null){
				name = null;
				this.showErrorMessage("the name has been used.");
			}
		}while(name == null);
		
		// gender:
		do{
			System.out.println("Please input the gender of the new user (male or female) : ");
			gender = inputChoice.next();
			if(!gender.equals("male") && !gender.equals("female")){
				gender = null;
				this.showErrorMessage(null);
			}
		}while(gender == null);
		
		// age:
		do{
			System.out.println("Please input age of the new user: ");
			try{
				age = inputChoice.nextInt();
			}catch(Exception e){
				this.showErrorMessage(null);
				age = -1;
			}
		}while(age <= 0);
		//if he or she is a teenager, parents are required to assign to the teenager.
		if(age < 16){
			// father:
			do{
				System.out.println("Please input the father's name of the new user: ");
				String fatherName = inputChoice.next();
				father = this.db.getAdultUserByName(fatherName);
				if(father==null){
					fatherName = null;
					this.showErrorMessage("the father cannot be found in the social network.");
				}else if(father.getGender().equals("female")){
					father = null;
					this.showErrorMessage("you can not assgin a female as a father");
				}
			}while(father == null);
			
			// mother:
			do{
				System.out.println("Please input the mother name of the new user: ");
				String motherName = inputChoice.next();
				mother = this.db.getAdultUserByName(motherName);
				if(mother==null){
					this.showErrorMessage("the mother cannot be found in the social network.");
				}else if(mother.getGender().equals("male")){
					mother = null;
					this.showErrorMessage("you can not assgin a male as a mother");
				}else if(mother.getPartner() == null && father.getPartner() == null){
					
				}else if(father.getPartner()!=mother.getPartner()){
					this.showErrorMessage("the inputed father and mother are not couple");
					mother = null;
				}
				
			}while(mother == null);
			
		}
		
		// hobbies:
		System.out.println("Please input the hobbies of the new user: ");
		hobbies = inputChoice.next();
		
		// status:
		System.out.println("Please input the status of the new user: ");
		status = inputChoice.next();
		
		if(age < 16){
			if(father.getPartner() == null){
				Adult.setPartners(father, mother);
			}
			newUser = new Teenager(name,gender,age,hobbies,status,father,mother);
		}else{
			newUser = new Adult(name,gender,age,hobbies,status);
		}
		
		this.db.addNewUser(newUser);
		System.out.println();
		System.out.println("New user has been added successfully!");
	}
	

	
	/**
	 *  selected person  operations
	 */
	private void showSelectedPersonFunction(){
		
		System.out.println();
		System.out.println("The selected user : "+ this.seletedPerson.getName());
		System.out.println();
		
		System.out.println("Options \n===================================");
		System.out.println("1. Display the profile of the selected person.");
		System.out.println("2. Delete the selected person.");
		System.out.println("3. Back.");
		System.out.println("4. Exit");
		
		// show the options for the selected user
		try {
			System.out.println();
			System.out.println("Enter an option:");
			inputChoice = new Scanner(System.in);
			menuChoice = inputChoice.nextInt();

			switch (menuChoice) {
			case 1:
				showSelectedPersonProfile();
				break;
			case 2:
				deleteSelectedPerson();
				listEveryOneFunction();
				break;
			case 3:
				listEveryOneFunction();
				break;
			case 4:
				//do nothing to exit.
				break;
			default:
				showErrorMessage("the input is outside of that range");
				listEveryOneFunction();
				break;
			}
		} catch (InputMismatchException e) {
			showErrorMessage(null);
			showSelectedPersonFunction();
		}
		
	}
	
	private void showSelectedPersonProfile(){
		
		System.out.println();
		System.out.println("Selected Person Profile \n===================================");
		System.out.println("Name    :    " + this.seletedPerson.getName());
		System.out.println("Gender  :    " + this.seletedPerson.getGender());
		if(this.seletedPerson instanceof Adult){
			Adult selectedAdult = (Adult)this.seletedPerson;
			System.out.println("Partner :    " + selectedAdult.getPartner()==null?"Empty":selectedAdult.getName());
		}
		System.out.println("Age     :    " + this.seletedPerson.getAge());
		System.out.println("Hobbies :    " + this.seletedPerson.getHobbies());
		System.out.println("Status  :    " + this.seletedPerson.getStatus());
		System.out.println();

		System.out.println("Friend List \n===================================");
		if(this.seletedPerson.friendList.size()>0){
			for(Person friend : this.seletedPerson.friendList){
				System.out.println(friend.getName());
			}
		}else{
			System.out.println("Friend list is empty. Make a new friend Now!");
		}

		System.out.println();
		 
		System.out.println("Options \n===================================");
		System.out.println("1. Update Hobbies.");
		System.out.println("2. Update Status.");
		System.out.println("3. Break a relationship with a friend.");
		System.out.println("4. Make a new friend with a person.");
		System.out.println("5. Build a relationship(couple) with another person.");
		System.out.println("6. Back.");
		System.out.println("7. Exit");
		
		// show the options for the selected user
		try {
			System.out.println();
			System.out.println("Enter an option:");
			inputChoice = new Scanner(System.in);
			menuChoice = inputChoice.nextInt();

			switch (menuChoice) {
			case 1://update hobbies
				updateSelectedPersonHobbies();
				showSelectedPersonProfile();
				break;
			case 2://update status
				updateSelectedPersonStatus();
				showSelectedPersonProfile();
				break;
			case 3://break relationship with a friend
				breakRelationshipWithFirend();
				showSelectedPersonProfile();
				break;
			case 4:// make a new friend
				MakeNewFriend();
				showSelectedPersonProfile();
				break;
			case 5:// make a new friend
				buildRelationship();
				showSelectedPersonProfile();
				break;
			case 6://back to selected person page
				showSelectedPersonFunction();
				break;
			case 7://exit
				break;
			default:
				showErrorMessage("the input is outside of that range");
				listEveryOneFunction();
				break;
			}
		} catch (InputMismatchException e) {
			showErrorMessage(null);
			showSelectedPersonFunction();
		}
		
	}
	
	/**
	 * update the status for the selected person
	 */
	private void updateSelectedPersonStatus(){
		System.out.println();
		System.out.print("Please input the new status : ");
		String newStatus = inputChoice.next();
		this.seletedPerson.setStatus(newStatus);
		System.out.print(this.seletedPerson.getName() + "'s profile has been updated successfully!");
	}
	
	/**
	 * update the hobbies for the selected person
	 */
	private void updateSelectedPersonHobbies(){
		System.out.println();
		System.out.print("Please input the new hobbies : ");
		String newHobbies = inputChoice.next();
		this.seletedPerson.setHobbies(newHobbies);
		System.out.print(this.seletedPerson.getName() + "'s profile has been updated successfully!");
	}
	
	/**
	 * break a relationship with a friend for this selected person
	 */
	private void breakRelationshipWithFirend(){
		System.out.println();
		if(this.seletedPerson.friendList.size() == 0){
			System.out.print("The friend list of the selected person is empty, no people to to break relationship with!");
			return;
		}
		
		System.out.print("Please input the the friend's name who you want to break relationship with : ");
		String name = inputChoice.next();
		boolean flag = false;
		for(Person friend : this.seletedPerson.friendList){
			if(friend.getName().equals(name)){
				this.seletedPerson.friendList.remove(friend);
				friend.friendList.remove(this.seletedPerson);
				flag = true;
				break;
			}
		}
		if(flag){
			System.out.println(this.seletedPerson.getName() + "'s profile has been updated successfully!");
		}else{
			showErrorMessage("not found the inputed name in friend list");
		}
		
	}
	
	/**
	 * make a new friend for the selected person
	 */
	private void MakeNewFriend(){
		System.out.println();
		System.out.print("Please input the the new friend name : ");
		String name = inputChoice.next();
		if(this.seletedPerson.getName().equals(name)){
			showErrorMessage("you cannot make friend with yourself");
			return;
		}

		Person newFriend = db.getUserByName(name);
		if(newFriend == null){
			showErrorMessage("not found the inputed name in social network");
			return;
		}
		if(this.seletedPerson.friendList.contains(newFriend)){
			showErrorMessage("they are already friends");
			return;
		}
		if(this.seletedPerson instanceof Teenager){
			Teenager seletedTeenager = (Teenager)this.seletedPerson;
			if(newFriend instanceof Teenager){
				Teenager newTeenagerFriend = (Teenager)newFriend;
				
				//A person who is 2 years old or younger does not have any friends.
				if(seletedTeenager.getAge() <=2 || newTeenagerFriend.getAge() <= 2){
					showErrorMessage("A person who is 2 years old or younger does not have any friends");
					return;
				}
				//The age difference between these two young friends cannot be more than 3 years.
				if(Math.abs(seletedTeenager.getAge() - newTeenagerFriend.getAge()) > 3){
					showErrorMessage("The age difference between these two young friends cannot be more than 3 years");
					return;
				}
				//make friends:
				this.seletedPerson.friendList.add(newFriend);
				newFriend.friendList.add(this.seletedPerson);
				System.out.println(this.seletedPerson.getName() + " and " + newFriend.getName() + " have become friends now!");
				return;
			}else{//for other situation:
				showErrorMessage("A dependent can be a friend ONLY to another dependent");
				return;
			}
		}else{//for other situation:
			if(newFriend instanceof Teenager){
				showErrorMessage("A dependent can be a friend ONLY to another dependent");
				return;
			}
			//make friends:
			this.seletedPerson.friendList.add(newFriend);
			newFriend.friendList.add(this.seletedPerson);
			System.out.println(this.seletedPerson.getName() + " and " + newFriend.getName() + " have become friends now!");
			return;
		}
		
		
	}
	
	
	/**
	 * Build a relationship
	 * 
	 * build a relationship between two users.
	 * they may become the direct friends or couple.
	 */
	private void buildRelationship(){
		
		if(this.seletedPerson instanceof Teenager){
			System.out.println("The selected person still is a teenager, he cannot have a partner.");
			return;
		}
		Adult seletedAdult = (Adult)this.seletedPerson;
		if(seletedAdult.getPartner() != null){
			System.out.println("The selected person already has a partner.");
			return;
		}
		
		System.out.println();
		System.out.print("Please input the the partner name : ");
		String name = inputChoice.next();
		if(this.seletedPerson.getName().equals(name)){
			showErrorMessage("you cannot become a partner of yourself");
			return;
		}
		
		Adult selectedAdult = (Adult)this.seletedPerson;
		Adult partner = db.getAdultUserByName(name);
		if(partner == null){
			showErrorMessage("not found the inputed name in social network");
			return;
		}
		if(partner.getPartner() != null){
			showErrorMessage("This person aldready has a partner");
			return;
		}
		Adult.setPartners(selectedAdult, partner);
		
		
	}
	
	
	private void deleteSelectedPerson(){
		db.deleteUser(this.seletedPerson);
		System.out.println("The selected Person "+ seletedPerson.getName() +"has been deleted from the social network.");
		this.seletedPerson = null;
	}
	
	private void showErrorMessage(String errorMsg){
		if(errorMsg == null || errorMsg.length() == 0){
			errorMsg = "invalid input";
		}
		System.err.println("Error: "+errorMsg+", please input any thing to continue.");
		inputChoice.next();
		
		
	}

}
