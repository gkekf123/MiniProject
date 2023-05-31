package vo;

public class BuyListVO {
	private String account_id; //산 사람
	private String board_num; //물건 번호
	
	public BuyListVO() {
		// TODO Auto-generated constructor stub
	}
	
	public BuyListVO(String account_id, String board_num) {
		super();
		this.account_id = account_id;
		this.board_num = board_num;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getBoard_num() {
		return board_num;
	}

	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	
	
}
