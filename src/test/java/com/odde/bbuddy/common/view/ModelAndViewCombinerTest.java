package com.odde.bbuddy.common.view;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.view.ModelAndViewCombiner.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ModelAndViewCombinerTest {

    @Test
    public void merge_model_map_entry() {
        ModelAndView modelAndView = new ModelAndView();
        ModelAndView toBeMerged = new ModelAndView();
        toBeMerged.addObject("key", "value");

        ModelAndView newModelAndView = combine(modelAndView).with(toBeMerged);

        assertThat(newModelAndView.getModelMap()).containsEntry("key", "value");
    }
}
