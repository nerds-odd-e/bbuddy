package com.odde.bbuddy.common.view;

import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Stream;

public class ModelAndViewCombiner {
    private final ModelAndView origin;

    public ModelAndViewCombiner(ModelAndView modelAndView) {
        origin = modelAndView;
    }

    public ModelAndView combineWith(ModelAndView... toBeMerged) {
        Stream.of(toBeMerged)
                .map(ModelAndView::getModelMap)
                .forEach(origin::addAllObjects);
        return origin;
    }
}
