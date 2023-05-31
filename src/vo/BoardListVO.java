package vo;

import java.sql.Date;

//PRODUCT_NAME, PRICE, NAME, PRODUCT_SELL, BOARD_DATE
public class BoardListVO {
	private String product_name;
	private String name;
	private String price;
	private String product_sell;
	private Date board_date;

	public BoardListVO() {
		// TODO Auto-generated constructor stub
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProduct_sell() {
		return product_sell;
	}

	public void setProduct_sell(String product_sell) {
		this.product_sell = product_sell;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}


}