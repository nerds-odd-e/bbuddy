package com.odde.bbuddy.common.interceptor;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static com.odde.bbuddy.common.view.MessageSources.LABEL_TEXT_SHORT_NAME;
import static java.util.stream.Collectors.toList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class LabelTextInterceptorTest {

    private final Locale currentLocale = Locale.ENGLISH;
    ExposedResourceBundleMessageSource stubExposedResourceBundleMessageSource = mock(ExposedResourceBundleMessageSource.class);
    LabelTextInterceptor interceptor = new LabelTextInterceptor(stubExposedResourceBundleMessageSource);
    HttpServletRequest stubRequest = mock(HttpServletRequest.class);
    HttpServletResponse stubResponse = mock(HttpServletResponse.class);
    ModelAndView mockModelAndView = mock(ModelAndView.class);
    Object dummyHandler = new Object();

    @Before
    public void given_default_locale() {
        when(stubRequest.getLocale()).thenReturn(currentLocale);
    }

    @Test
    public void no_matched_view() throws Exception {
        given_messages_with_key_and_message();
        given_view_name("noMatchedView");

        postHandle();

        verify(mockModelAndView, never()).addObject(anyString(), anyObject());
    }

    @Test
    public void matched_view_containing_no_slash() throws Exception {
        given_messages_with_key_and_message(
                new SimpleEntry<>("noSlashView.label.first", "first message"),
                new SimpleEntry<>("noSlashView.label.second", "second message"));
        given_view_name("noSlashView");

        postHandle();

        verify(mockModelAndView).addObject("label.first", "first message");
        verify(mockModelAndView).addObject("label.second", "second message");
    }

    @Test
    public void not_all_key_match_view_name() throws Exception {
        given_messages_with_key_and_message(
                new SimpleEntry<>("noSlashView.label.first", "first message"),
                new SimpleEntry<>("noMatchedView.label.another", "no matched view message")
        );
        given_view_name("noSlashView");

        postHandle();

        verify(mockModelAndView).addObject("label.first", "first message");
        verify(mockModelAndView, never()).addObject("label.another", "no matched view message");
    }

    @Test
    public void matched_view_containing_slash() throws Exception {
        given_messages_with_key_and_message(
                new SimpleEntry<>("view.label.first", "message")
        );
        given_view_name("/view");

        postHandle();

        verify(mockModelAndView).addObject("label.first", "message");
    }

    @Test
    public void matched_view_containing_two_slash() throws Exception {
        given_messages_with_key_and_message(
                new SimpleEntry<>("view.add.label.first", "message")
        );
        given_view_name("/view/add");

        postHandle();

        verify(mockModelAndView).addObject("label.first", "message");
    }

    @Test
    public void common_label_should_be_added() throws Exception {
        given_messages_with_key_and_message(
                new SimpleEntry<>("label.common", "Common")
        );
        given_view_name("anyView");

        postHandle();

        verify(mockModelAndView).addObject("label.common", "Common");
    }

    private void given_message_key_with_message(SimpleEntry<String, String> entry) {
        when(stubExposedResourceBundleMessageSource.getMessageOverrided(eq(entry.getKey()), eq(null), eq(currentLocale))).thenReturn(entry.getValue());
    }

    private void postHandle() throws Exception {
        interceptor.postHandle(stubRequest, stubResponse, dummyHandler, mockModelAndView);
    }

    private void given_view_name(String noMatchedView) {
        when(mockModelAndView.getViewName()).thenReturn(noMatchedView);
    }

    private void given_message_keys(List<String> keys) {
        when(stubExposedResourceBundleMessageSource.getKeys(eq(LABEL_TEXT_SHORT_NAME), any(Locale.class)))
                .thenReturn(new HashSet<>(keys));
    }

    private void given_messages_with_key_and_message(SimpleEntry<String, String>... messageEntries) {
        given_message_keys(keysOf(messageEntries));
        Stream.of(messageEntries)
                .forEach(this::given_message_key_with_message);
    }

    private List<String> keysOf(SimpleEntry<String, String>[] messageEntries) {
        return Stream.of(messageEntries).map(SimpleEntry::getKey).collect(toList());
    }

}
