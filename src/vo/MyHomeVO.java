package vo;

public class MyHomeVO { // 원래 AccountVO 여야 함 -> 로그인화면에서 만드는 것 : 테스트를 위해 임시 제작
	
	private String account_id;
	private String pw;
	private String name;
	private String e_mail;
	private String phone_number;
	private String address;
	
	public MyHomeVO() {} // 기본 생성자
	
	public MyHomeVO(String account_id, String pw, String name, String e_mail, String phone_number, String address) {
		super();
		this.account_id = account_id;
		this.pw = pw;
		this.name = name;
		this.e_mail = e_mail;
		this.phone_number = phone_number;
		this.address = address;
	}
	
	// getter, setter
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
