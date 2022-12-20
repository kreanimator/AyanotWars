package com.Events;

import com.Items.GoblinHead;
import com.Items.Item;

public class Quest {


    GoblinHead goblinHead;

    boolean isTaken;
    boolean isDone;

    public Quest(){

    }

    public boolean isTaken() {
        return isTaken = true;
    }

    public boolean isDone() {
        if (goblinHead.quantity == 10){
            return isDone = true;
        }
        return isDone;
    }
}
