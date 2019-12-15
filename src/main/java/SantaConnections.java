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
	
	@Column(name="santa_whole_name")
	private String santaWholeName;
	
	@Column(name="user_whole_name")
	private String goodChild;
	
	public SantaConnections() {
		
	}

	public SantaConnections(String santaWholeName, String goodChild) {
		this.santaWholeName = santaWholeName;
		this.goodChild = goodChild;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSantaWholeName() {
		return santaWholeName;
	}

	public void setSantaWholeName(String santaWholeName) {
		this.santaWholeName = santaWholeName;
	}

	public String getGoodChild() {
		return goodChild;
	}

	public void setGoodChild(String goodChild) {
		this.goodChild = goodChild;
	}
	
}
