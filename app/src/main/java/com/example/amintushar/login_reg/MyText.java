package com.example.amintushar.login_reg;

/**
 * Created by WG on 3/15/2018.
 */

public class MyText {
    public MyText() {
    }

    String uEmail;

    public MyText(String uEmail, String umsg) {
        this.uEmail = uEmail;
        this.umsg = umsg;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getUmsg() {
        return umsg;
    }

    public void setUmsg(String umsg) {
        this.umsg = umsg;
    }

    String umsg;
}
