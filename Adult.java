/**
 * @author Anqi Li s3633399
 * a subclass Adult inherits from a superclass Person
 */
import java.util.ArrayList;

public class Adult extends Person {

	
	private Adult partner = null;
	private ArrayList<Teenager> children = new ArrayList<Teenager>();
	
	public Adult(String name, String gender, int age, String hobbies, String status) {
		super(name, gender, age, hobbies, status);
		// TODO Auto-generated constructor stub
	}

	private boolean setPartner(Adult partner){
		if(this.partner== null && partner != null){
			this.partner = partner;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addChild(Teenager newChild){
		boolean flag = false;
		if(newChild.getFather() != this && newChild.getMother() != this){
			flag =  false;
		}
		else if(children.contains(newChild)){
			flag =  false;
		}else{
			children.add(newChild);
			flag = true;
		}
		return flag;
	}
	
	public Adult getPartner(){
		return this.partner;
	}
	
	public static boolean setPartners(Adult a, Adult b){
		
		boolean flag = false;
		if(!a.getGender().equals(b.getGender()) && a.getPartner() == null && b.getPartner() == null ){
			a.setPartner(b);
			b.setPartner(a);
			flag = true;
		}
		return flag;
	}

	public ArrayList<Teenager> getAllChildren(){
		return children;
	}


}
