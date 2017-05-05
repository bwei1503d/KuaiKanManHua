package com.example.muhanxi.kuaikanmanhua.event;

/**
 * Created by muhanxi on 17/4/25.
 */

public class ScrollEvent {

    // true 向上滑动 false向下滑动
    public boolean up ;


    public ScrollEvent(boolean up){
        this.up = up ;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
