package com.odde.bbuddy.common.view;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewCombiner {
    private final ModelAndView origin;

    public ModelAndViewCombiner(ModelAndView modelAndView) {
        origin = modelAndView;
    }

    public static ModelAndViewCombiner combine(ModelAndView modelAndView) {
        return new ModelAndViewCombiner(modelAndView);
    }

    public ModelAndView with(ModelAndView toBeMerged) {
        toBeMerged.getModel().forEach(origin::addObject);
        return origin;
    }
}
