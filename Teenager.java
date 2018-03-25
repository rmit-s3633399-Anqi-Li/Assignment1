/**
 * @author Anqi Li s3633399 
 * subclass Teenager inherits from superclass Person. 
 * Teenager(dependent)
 * Description:
 * 
 * Other than .friendships., there are other types of connections between people. 
 * Some persons on the network are under 16 years of age. They are considered as dependents to adults. 
 * No isolated dependent can exist on the network meaning a dependent must have some connections. 
 * They always connect to exactly two adults who are the parents. The parents of a dependent must connect. 
 * Can assume there are no orphans, no single parents in this scenario.
 *  All couples are mutually exclusive to other couples. Situations like adult A and B are the parents of X, 
 *  An adult may have no connections.
 *  A dependent can be a friend ONLY to another dependent who is also younger than 16 and from a different family. 
 *  The age difference between these two young friends cannot be more than 3 years. 
 *  A person who is 2 years old or younger does not have any friends. 
 *  For example a 4-year-old cannot be friend with a 2-year-old although their age difference is only 2.
 *
 */
public class Teenager extends Person {

	//a teenager who is under 16  must have parents in the social network 
	private Adult father;
	private Adult mother;
	public Teenager(String name, String gender, int age, String hobbies, String status,Adult father,Adult mother) {
		super(name, gender, age, hobbies, status);
		this.father = father;
		this.mother = mother;
	}
	public Adult getFather() {
		return father;
	}
	public Adult getMother() {
		return mother;
	}
	
	





}
