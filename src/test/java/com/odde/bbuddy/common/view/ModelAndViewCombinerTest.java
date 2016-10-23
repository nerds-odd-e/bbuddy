package com.odde.bbuddy.common.view;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.AbstractMap.SimpleEntry;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelAndViewCombinerTest {

    @Test
    public void merge_model_map_entry() {
        ModelAndView afterCombined = combinerWithOriginal().combineWith(
                toBeCombined("key", "value"), toBeCombined("anotherKey", "anotherValue"));

        assertThat(afterCombined.getModelMap()).containsExactly(
                new SimpleEntry<>("key", "value"), new SimpleEntry<>("anotherKey", "anotherValue"));
    }

    private ModelAndViewCombiner combinerWithOriginal() {
        return new ModelAndViewCombiner(new ModelAndView());
    }

    private ModelAndView toBeCombined(String key, String value) {
        ModelAndView toBeCombined = new ModelAndView();
        toBeCombined.addObject(key, value);
        return toBeCombined;
    }
}
