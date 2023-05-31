package vo;

import java.sql.Date;

public class BoardVO {
	//board 테이블
	private String board_num;
	private String account_id;
	private String product_name;
	private String product_content;
	private String price;
	private Date board_date;
	private String product_sell;
	
	//생성자
	public BoardVO() {
	}
	
	public BoardVO(String board_num, String account_id, String product_name, String product_content, String price,
			Date board_date, String product_sell) {
		super();
		this.board_num = board_num;
		//login한 계정으로 바꿔주기
		this.account_id = account_id;
		this.product_name = product_name;
		this.product_content = product_content;
		this.price = price;
		this.board_date = board_date;
		this.product_sell = product_sell;
	}
	
	//getter & setter
	public String getBoard_num() {
		return board_num;
	}
	
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	
	public String getAccount_id() {
		return account_id;
	}
	
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public String getProduct_content() {
		return product_content;
	}
	
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public Date getBoard_date() {
		return board_date;
	}
	
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	
	public String getProduct_sell() {
		return product_sell;
	}
	
	public void setProduct_sell(String product_sell) {
		this.product_sell = product_sell;
	}
}