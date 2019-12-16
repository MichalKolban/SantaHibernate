import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="santa_connections")
public class SantaConnections {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="santa_name")
	private String santaName;
	
	@Column(name="santa_last_name")
	private String santaLastName;
	
	@Column(name="reciving_participant_name")
	private String userName;
	
	@Column(name="reciving_participant_last_name")
	private String userLastName;
	
	public SantaConnections() {
		
	}

	public SantaConnections(String santaName, String santaLastName, String userName, String userLastName) {
		super();
		this.santaName = santaName;
		this.santaLastName = santaLastName;
		this.userName = userName;
		this.userLastName = userLastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSantaName() {
		return santaName;
	}

	public void setSantaName(String santaName) {
		this.santaName = santaName;
	}

	public String getSantaLastName() {
		return santaLastName;
	}

	public void setSantaLastName(String santaLastName) {
		this.santaLastName = santaLastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

}
