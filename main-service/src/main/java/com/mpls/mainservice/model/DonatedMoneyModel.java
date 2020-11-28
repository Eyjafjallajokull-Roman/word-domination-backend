package com.mpls.mainservice.model;

import com.mpls.mainservice.model.enums.Step;

import javax.persistence.*;

@Entity
public class DonatedMoneyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private CountryModel from;
    @OneToOne
    private CountryModel to;

    @Enumerated(value = EnumType.STRING)
    private Step step;

    private Long price;

    public Long getPrice() {
        return price;
    }

    public DonatedMoneyModel setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DonatedMoneyModel setId(Long id) {
        this.id = id;
        return this;
    }

    public CountryModel getFrom() {
        return from;
    }

    public DonatedMoneyModel setFrom(CountryModel from) {
        this.from = from;
        return this;
    }

    public CountryModel getTo() {
        return to;
    }

    public DonatedMoneyModel setTo(CountryModel to) {
        this.to = to;
        return this;
    }

    public Step getSessionNumber() {
        return step;
    }

    public DonatedMoneyModel setSessionNumber(Step step) {
        this.step = step;
        return this;
    }
}
