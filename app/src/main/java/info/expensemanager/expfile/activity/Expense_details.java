package info.expensemanager.expfile.activity;

import java.util.Date;

/**
 * Created by Rahul on 07/04/2016.
 */
public class Expense_details {
    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getDatelabel() {
        return datelabel;
    }

    public void setDatelabel(String datelabel) {
        this.datelabel = datelabel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayeeid() {
        return payeeid;
    }

    public void setPayeeid(int payeeid) {
        this.payeeid = payeeid;
    }

    public String getPayeename() {
        return payeename;
    }

    public void setPayeename(String payeename) {
        this.payeename = payeename;
    }

    public String getCheckno() {
        return checkno;
    }

    public void setCheckno(String checkno) {
        this.checkno = checkno;
    }

    int trans_id;
    String category;
    int catid;
    String datelabel;
    Date date;
    String detail;
    double amount;
    int payeeid;
    String payeename;
    String checkno;

    public int getApprove_status() {
        return approve_status;
    }

    public void setApprove_status(int approve_status) {
        this.approve_status = approve_status;
    }

    int approve_status;

    public int getBysms_status() {
        return bysms_status;
    }

    public void setBysms_status(int bysms_status) {
        this.bysms_status = bysms_status;
    }

    int bysms_status;



}
