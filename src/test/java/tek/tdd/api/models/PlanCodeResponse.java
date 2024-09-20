package tek.tdd.api.models;

import java.util.Date;

public class PlanCodeResponse {
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private int id;
    private String planType;
    private double planBasePrice;
    private String icon;
    private Date expirationDate;
    private boolean planExpired;
    private boolean isNew;

    public PlanCodeResponse() {
    }

    public PlanCodeResponse(String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, int id, String planType, double planBasePrice, String icon, Date expirationDate, boolean planExpired, boolean isNew) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.id = id;
        this.planType = planType;
        this.planBasePrice = planBasePrice;
        this.icon = icon;
        this.expirationDate = expirationDate;
        this.planExpired = planExpired;
        this.isNew = isNew;

    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public int getId() {
        return id;
    }

    public String getPlanType() {
        return planType;
    }

    public double getPlanBasePrice() {
        return planBasePrice;
    }

    public String getIcon() {
        return icon;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public boolean isPlanExpired() {
        return planExpired;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public void setPlanBasePrice(double planBasePrice) {
        this.planBasePrice = planBasePrice;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setPlanExpired(boolean planExpired) {
        this.planExpired = planExpired;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
