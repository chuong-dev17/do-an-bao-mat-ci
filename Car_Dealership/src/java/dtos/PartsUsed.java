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
public class PartsUsed {
    private String serviceTicketID;
    private String partID;
    private String numberUsed;
    private String price;

    public PartsUsed() {
    }

    public PartsUsed(String serviceTicketID, String partID, String numberUsed, String price) {
        this.serviceTicketID = serviceTicketID;
        this.partID = partID;
        this.numberUsed = numberUsed;
        this.price = price;
    }

    public String getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(String serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getNumberUsed() {
        return numberUsed;
    }

    public void setNumberUsed(String numberUsed) {
        this.numberUsed = numberUsed;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    } 
}
