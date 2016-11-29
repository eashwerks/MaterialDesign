package info.expensemanager.expfile.activity;

/**
 * Created by Rahul on 30/04/2016.
 */
public class Payee {
    int payid;
    String payeename;
    String address;
    String mobile;

    public int getPayid() {
        return payid;
    }

    public void setPayid(int payid) {
        this.payid = payid;
    }

    public String getPayeename() {
        return payeename;
    }

    public void setPayeename(String payeename) {
        this.payeename = payeename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
