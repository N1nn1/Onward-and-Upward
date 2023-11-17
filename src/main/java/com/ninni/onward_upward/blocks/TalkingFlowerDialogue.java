package com.ninni.onward_upward.blocks;

public enum TalkingFlowerDialogue {
    CONTACT(2),
    WONDER(2),
    INTERACTED(2),
    UNDERWATER(2),
    IN_THE_DARK(2),
    UNDERGROUND(2),
    IN_THE_SKY(2),
    PLACED(2),
    DESTROYED(2);

    public final int duration;

    TalkingFlowerDialogue(int durationInSeconds) {
        this.duration = durationInSeconds * 20;
    }

    public int getDuration() {
        return duration;
    }
}
