package com.agnext.unification.entity.nafed;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="commodity_analytics")
public class CommodityAnalytics {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="commodity_id")
    private Long commodityId;

    @Column(name="analytics_name")
    private String analyticsName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public String getAnalyticsName() {
        return analyticsName;
    }

    public void setAnalyticsName(String analyticsName) {
        this.analyticsName = analyticsName;
    }

    @Override
    public String toString() {
        return "CommodityAnalytics{" +
                "id=" + id +
                ", commodityId=" + commodityId +
                ", analyticsName='" + analyticsName + '\'' +
                '}';
    }
}
