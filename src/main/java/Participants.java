import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="participants")
public class Participants {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="user_name")
	private String firstName;
	
	@Column(name="user_last_name")
	private String lastName;
	
	@Column(name="is_santa_already")
	private Boolean isSantaAlready;
	
	public Participants() {
		
	}

	public Participants(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.isSantaAlready = false;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Boolean getIsSantaAlready() {
		return isSantaAlready;
	}


	public void setIsSantaAlready(Boolean isSantaAlready) {
		this.isSantaAlready = isSantaAlready;
	}

}
