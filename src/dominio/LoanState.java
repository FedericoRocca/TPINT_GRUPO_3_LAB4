package dominio;

public class LoanState {

	private int id;
	private String state;
	
	
	public LoanState() {
	}
	
	public LoanState(int id, String state) {
		super();
		this.id = id;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "LoanState [id=" + id + ", state=" + state + "]";
	}
}
