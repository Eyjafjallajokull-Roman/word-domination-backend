package com.mpls.mainservice.dto.ecology.graphic;

public class EcologyGraphicDto {
    private Long value;
    private Long name;

    public Long getValue() {
        return value;
    }

    public EcologyGraphicDto setValue(Long value) {
        this.value = value;
        return this;
    }

    public Long getName() {
        return name;
    }

    public EcologyGraphicDto setName(Long name) {
        this.name = name;
        return this;
    }
}
