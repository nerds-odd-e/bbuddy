package com.odde.bbuddy.common.task;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static java.io.File.separator;
import static java.lang.String.format;
import static javax.xml.parsers.DocumentBuilderFactory.newInstance;
import static org.apache.commons.io.FileUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.Assert.assertEquals;

public class UpdateUnitTestResultsTaskTest {

    @Rule
    public final TemporaryFolder tmpFolder = new TemporaryFolder();
    private final String taskName = "updateUnitTestResults";
    private final String testResultFileNamePattern = "TEST-com.odde.bbuddy.transaction.controller.%s.xml";
    private BuildResult result;
    private String sourceTestResultsFolder;
    private String defaultXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<testsuite name=\"com.odde.bbuddy.RequireMergeTest$SubClass\" tests=\"1\" skipped=\"0\" failures=\"0\" errors=\"0\" timestamp=\"2016-11-06T10:32:48\" hostname=\"Josephs-MBP-2\" time=\"0.041\">\n" +
            "  <properties/>\n" +
            "  <testcase name=\"should_not_add_account\" classname=\"com.odde.bbuddy.RequireMergeTest$SubClass\" time=\"0.041\"/>\n" +
            "  <system-out><![CDATA[]]></system-out>\n" +
            "  <system-err><![CDATA[]]></system-err>\n" +
            "</testsuite>";
    private String defaultClassName = "com.odde.bbuddy.RequireMergeTest$SubClass";

    @Before
    public void createTask() throws IOException {
        copyFileToRootFolder("build.gradle");
        copyFileToGradleFolder("sonar.gradle", "ci.gradle", "run.gradle", "common.gradle", "dependency.gradle");
    }

    @Before
    public void initSourceTestResultsFolder() {
        sourceTestResultsFolder = tmpFolder.getRoot() + separator + "build" + separator + "test-results";
    }

    @Test
    public void should_not_change_test_result_file_not_matching() throws IOException {
        createTestResultFile("RequireNoChangeTest", defaultClassName);

        runTask();

        assertTaskRunSuccess();
        assertTestResultFileContentUnchanged(defaultXml, "RequireNoChangeTest");
    }

    @Test
    public void should_create_a_new_test_result_file_with_same_content_of_nested_runner_result() throws IOException, ParserConfigurationException, SAXException {
        createTestResultFile("RequireMergeTest$SubClass", "com.odde.bbuddy.RequireMergeTest$SubClass");

        runTask();

        assertTaskRunSuccess();
        assertXmlFileAttributeChanged("com.odde.bbuddy.RequireMergeTest", "name", "RequireMergeTest$SubClass");
    }

    private void assertXmlFileAttributeChanged(String expected, String attributeName, String shortName) throws SAXException, IOException, ParserConfigurationException {
        assertThat(document(shortName).getDocumentElement().getAttribute(attributeName)).isEqualTo(expected);
    }

    private Document document(String shortName) throws SAXException, IOException, ParserConfigurationException {
        return newInstance().newDocumentBuilder().parse(testResultFile(shortName));
    }

    private void assertTestResultFileContentUnchanged(String defaultXml, String shortName) throws IOException {
        assertThat(readFileToString(testResultFile(shortName))).isEqualTo(defaultXml);
    }

    private File testResultFile(String shortName) {
        return new File(sourceTestResultsFolder + separator + format(testResultFileNamePattern, shortName));
    }

    private void createTestResultFile(String shortName, String className) throws IOException {
        writeStringToFile(testResultFile(shortName), defaultXml.replace(defaultClassName, className));
    }

    private void assertTaskRunSuccess() {
        assertEquals(result.task(":" + taskName).getOutcome(), SUCCESS);
    }

    private void runTask() {
        result = GradleRunner.create()
                .withProjectDir(tmpFolder.getRoot())
                .withArguments(taskName)
                .build();
    }

    private void copyFileToRootFolder(String fileName) throws IOException {
        copyFileToDirectory(new File(System.getProperty("user.dir") + separator + fileName), tmpFolder.getRoot());
    }

    private void copyFileToGradleFolder(String... fileNames) throws IOException {
        File gradleFolder = tmpFolder.newFolder("gradle");
        for (String fileName : fileNames)
            copyFileToDirectory(new File(System.getProperty("user.dir") + separator + "gradle" + separator + fileName), gradleFolder);
    }

}
