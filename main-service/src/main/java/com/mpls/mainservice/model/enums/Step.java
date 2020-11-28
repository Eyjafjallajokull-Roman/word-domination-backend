package com.mpls.mainservice.model.enums;

public enum Step {
    START(1L),
    FIRST(2L),
    SECOND(3L),
    THIRD(4L),
    FOURTH(5L),
    FIFTH(6L),
    SIXTH(7L),
    END(8L);
    private final Long sequenceNumber;

    Step(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Step next() {
        if (values().length > this.ordinal()+1) {
            return values()[this.ordinal()+1];
        } else {
            return END;
        }
    }

    public Long sequenceNumber() {
        return sequenceNumber;
    }

}
