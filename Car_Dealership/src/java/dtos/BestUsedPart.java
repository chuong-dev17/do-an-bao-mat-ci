/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Dell
 */
public class BestUsedPart {

    private String partName;
    private int totalUsed;
    private double totalRevenue;

    public BestUsedPart(String partName, int totalUsed, double totalRevenue) {
        this.partName = partName;
        this.totalUsed = totalUsed;
        this.totalRevenue = totalRevenue;
    }

    public String getPartName() {
        return partName;
    }

    public int getTotalUsed() {
        return totalUsed;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}
