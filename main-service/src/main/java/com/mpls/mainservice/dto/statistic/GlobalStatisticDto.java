package com.mpls.mainservice.dto.statistic;

import com.mpls.mainservice.model.enums.Step;

import java.util.List;

public class GlobalStatisticDto {
    private Step step;
    private List<GlobalDecisionDto> globalDecisionDto;

    public Step getStep() {
        return step;
    }

    public GlobalStatisticDto setStep(Step step) {
        this.step = step;
        return this;
    }

    public List<GlobalDecisionDto> getGlobalDecisionDto() {
        return globalDecisionDto;
    }

    public GlobalStatisticDto setGlobalDecisionDto(List<GlobalDecisionDto> globalDecisionDto) {
        this.globalDecisionDto = globalDecisionDto;
        return this;
    }

}
