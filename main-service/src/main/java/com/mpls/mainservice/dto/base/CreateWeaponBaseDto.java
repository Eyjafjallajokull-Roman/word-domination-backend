package com.mpls.mainservice.dto.base;

public class CreateWeaponBaseDto {
    private Long count;

    public Long getCount() {
        return count;
    }

    public CreateWeaponBaseDto setCount(Long count) {
        this.count = count;
        return this;
    }
}
