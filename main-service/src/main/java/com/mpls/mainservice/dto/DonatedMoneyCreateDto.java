package com.mpls.mainservice.dto;

import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.validator.annotation.DonateMoneyValidator;

@DonateMoneyValidator
public class DonatedMoneyCreateDto {
    private CountryIdDto to;
    private Long price;

    public CountryIdDto getTo() {
        return to;
    }

    public DonatedMoneyCreateDto setTo(CountryIdDto to) {
        this.to = to;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public DonatedMoneyCreateDto setPrice(Long price) {
        this.price = price;
        return this;
    }
}
