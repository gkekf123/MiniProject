package vo;

import java.sql.Date;

public class MainVO {
    private String BOARD_NUM;  // 게시물 번호
    private String ACCOUNT_ID;  // 작성자 ID
    private String PRODUCT_NAME;  // 상품명
    private String PRODUCT_COUNT;  // 상품 내용
    private String PRICE;  // 가격
    private Date BOARD_DATE;  // 작성일
    private String PRODUCT_SELL;  // 상품 판매 여부

    public MainVO(String boardNum, String accountId, String productName, String productContent, String price,
                   Date boardDate, String productSell) {
        this.BOARD_NUM = boardNum;
        this.ACCOUNT_ID = accountId;
        this.PRODUCT_NAME = productName;
        this.PRODUCT_COUNT = productContent;
        this.PRICE = price;
        this.BOARD_DATE = boardDate;
        this.PRODUCT_SELL = productSell;
    }

	public String getBOARD_NUM() {
		return BOARD_NUM;
	}

	public void setBOARD_NUM(String bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}

	public String getACCOUNT_ID() {
		return ACCOUNT_ID;
	}

	public void setACCOUNT_ID(String aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}

	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}

	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}

	public String getPRODUCT_COUNT() {
		return PRODUCT_COUNT;
	}

	public void setPRODUCT_COUNT(String pRODUCT_COUNT) {
		PRODUCT_COUNT = pRODUCT_COUNT;
	}

	public String getPRICE() {
		return PRICE;
	}

	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}

	public Date getBOARD_DATE() {
		return BOARD_DATE;
	}

	public void setBOARD_DATE(Date bOARD_DATE) {
		BOARD_DATE = bOARD_DATE;
	}

	public String getPRODUCT_SELL() {
		return PRODUCT_SELL;
	}

	public void setPRODUCT_SELL(String pRODUCT_SELL) {
		PRODUCT_SELL = pRODUCT_SELL;
	}

    
    
}
