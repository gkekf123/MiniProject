package vo;

public class LoginVO {
    private String ACCOUNT_ID;
    private String PW;
    private String NAME;
    private String ADDRESS;
    private String EMAIL;
    private String PHONE_NUMBER;

    
    
    public LoginVO(String accountId, String password) {
        this.ACCOUNT_ID = accountId;
        this.PW = password;
    }

    public LoginVO(String accountId, String password, String name, String email, String phoneNumber, String address) {
        this.ACCOUNT_ID = accountId;
        this.PW = password;
        this.NAME = name;
        this.EMAIL = email;
        this.PHONE_NUMBER = phoneNumber;
        this.ADDRESS = address;
    }

   public String getACCOUNT_ID() {
      return ACCOUNT_ID;
   }

   public void setACCOUNT_ID(String aCCOUNT_ID) {
      ACCOUNT_ID = aCCOUNT_ID;
   }

   public String getPW() {
      return PW;
   }

   public void setPW(String pW) {
      PW = pW;
   }

   public String getNAME() {
      return NAME;
   }

   public void setNAME(String nAME) {
      NAME = nAME;
   }

   public String getADDRESS() {
      return ADDRESS;
   }

   public void setADDRESS(String aDDRESS) {
      ADDRESS = aDDRESS;
   }

   public String getEMAIL() {
      return EMAIL;
   }

   public void setEMAIL(String eMAIL) {
      EMAIL = eMAIL;
   }

   public String getPHONE_NUMBER() {
      return PHONE_NUMBER;
   }

   public void setPHONE_NUMBER(String pHONE_NUMBER) {
      PHONE_NUMBER = pHONE_NUMBER;
   }

   
    
 
}