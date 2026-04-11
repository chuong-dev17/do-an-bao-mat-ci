/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Admin_Coder
 */
public class SalesPersonDTO {
    private String salesID;
    private String salesName;
    private String birthday;
    private String sex;
    private String salesAddress;

    public SalesPersonDTO() {
    }

    public SalesPersonDTO(String salesID, String salesName, String birthday, String sex, String salesAddress) {
        this.salesID = salesID;
        this.salesName = salesName;
        this.birthday = birthday;
        this.sex = sex;
        this.salesAddress = salesAddress;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalesAddress() {
        return salesAddress;
    }

    public void setSalesAddress(String salesAddress) {
        this.salesAddress = salesAddress;
    }

    @Override
    public String toString() {
        return "SalesPersonDTO{" + "salesID=" + salesID + ", salesName=" + salesName + ", birthday=" + birthday + ", sex=" + sex + ", salesAddress=" + salesAddress + '}';
    }
    
    
}
