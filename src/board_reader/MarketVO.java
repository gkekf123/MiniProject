package board_reader;

import java.sql.Date;

public class MarketVO {

    private String board_num;
    private String account_id;
    private String product_name;
    private String product_content;
    private String price;
    private Date board_date;
    private String city;
    private String sell_yn;
    // private String like; // 임시로 String

    public MarketVO() {

    }

    public MarketVO(String board_num, String account_id, String product_name, String product_content, String price, Date board_date, String city, String sell_yn) {
        this.board_num = board_num;
        this.account_id = account_id;
        this.product_name = product_name;
        this.product_content = product_content;
        this.price = price;
        this.board_date = board_date;
        this.city = city;
        this.sell_yn = sell_yn;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSell_yn() {
        return sell_yn;
    }

    public void setSell_yn(String sell_yn) {
        this.sell_yn = sell_yn;
    }
}

