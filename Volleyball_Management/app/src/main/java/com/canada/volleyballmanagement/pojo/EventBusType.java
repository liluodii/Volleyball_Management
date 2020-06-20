package com.canada.volleyballmanagement.pojo;

/**
 * Created by Designer on 20-03-2019.
 */

public class EventBusType {
    //Team Update=1
    //Player Update=2
    //Team Manager Update=3
    //Tournament=4
    //Tournament team=5

    int Type = 0;

    public int getType() {
        return Type;
    }

    public EventBusType(int type) {
        Type = type;
    }

    public void setType(int type) {
        Type = type;
    }
}
