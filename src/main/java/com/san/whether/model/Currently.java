package com.san.whether.model;

public class Currently extends  DataPoint {

    private Double nearestStormBearing;
    private Double nearestStormDistance;

    public Double getNearestStormBearing() {
        return nearestStormBearing;
    }

    public void setNearestStormBearing(Double nearestStormBearing) {
        this.nearestStormBearing = nearestStormBearing;
    }

    public Double getNearestStormDistance() {
        return nearestStormDistance;
    }

    public void setNearestStormDistance(Double nearestStormDistance) {
        this.nearestStormDistance = nearestStormDistance;
    }
}
