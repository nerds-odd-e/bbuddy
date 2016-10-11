package com.odde.bbuddy.common.builder;

import com.odde.bbuddy.common.controller.ResultRange;
import com.odde.bbuddy.common.controller.ResultRangeFactory;

public class ResultRangeBuilder {

    public static ResultRangeBuilder defaultResultRange() {
        return new ResultRangeBuilder();
    }

    public ResultRange build() {
        return new ResultRangeFactory().create(1);
    }
}
