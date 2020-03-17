package com.san.whether.model;

import java.io.Serializable;
import java.util.List;

public class Daily implements Serializable {
    private String summary;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<DailyDataPoint> getData() {
        return data;
    }

    public void setData(List<DailyDataPoint> data) {
        this.data = data;
    }

    private String icon;
    private List<DailyDataPoint> data;

}
