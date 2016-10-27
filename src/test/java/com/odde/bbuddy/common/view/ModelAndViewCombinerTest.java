package com.odde.bbuddy.common.view;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.AbstractMap.SimpleEntry;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelAndViewCombinerTest {

    @Test
    public void merge_model_map_entry() {
        ModelAndView original = new ModelAndView();

        ModelAndView afterCombined = new ModelAndViewCombiner(original).combineWith(
                toBeCombined("key", "value"), toBeCombined("anotherKey", "anotherValue"));

        assertThat(afterCombined).isSameAs(original);
        assertThat(afterCombined.getModelMap()).containsExactly(
                new SimpleEntry<>("key", "value"), new SimpleEntry<>("anotherKey", "anotherValue"));
    }

    private ModelAndView toBeCombined(String key, String value) {
        ModelAndView toBeCombined = new ModelAndView();
        toBeCombined.addObject(key, value);
        return toBeCombined;
    }
}
