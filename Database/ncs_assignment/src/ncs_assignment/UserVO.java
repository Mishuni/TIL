package ncs_assignment;

public class UserVO {
	private String id;
	private int pw;
	
	public UserVO() {
		super();
	}
	public UserVO(String id, int pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + "]";
	}
}
