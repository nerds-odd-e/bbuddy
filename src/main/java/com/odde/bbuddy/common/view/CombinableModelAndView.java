package com.odde.bbuddy.common.view;

import org.springframework.web.servlet.ModelAndView;

public interface CombinableModelAndView {
    default ModelAndView combineWith(ModelAndView... allToBeCombined) {
        return new ModelAndViewCombiner(original()).combineWith(allToBeCombined);
    }

    ModelAndView original();
}
