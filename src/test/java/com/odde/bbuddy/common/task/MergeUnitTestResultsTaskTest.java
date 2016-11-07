package com.odde.bbuddy.common.task;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static java.io.File.separator;
import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.copyFileToDirectory;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.Assert.assertEquals;

public class MergeUnitTestResultsTaskTest {

    @Rule
    public final TemporaryFolder tmpFolder = new TemporaryFolder();
    private final String taskName = "mergeUnitTestResults";
    private final String testResultFileNamePattern = "TEST-com.odde.bbuddy.transaction.controller.%s.xml";
    private BuildResult result;
    private String targetTestResultsFolder;
    private String sourceTestResultsFolder;

    @Before
    public void createTask() throws IOException {
        copyFileToDirectory(gradleFile(), tmpFolder.getRoot());
    }

    @Before
    public void initializeFolderNames() {
        targetTestResultsFolder = tmpFolder.getRoot() + separator + "build" + separator + "test-results";
        sourceTestResultsFolder = tmpFolder.getRoot() + separator + "build" + separator + "test-results";
    }

    @Test
    public void should_success_when_no_result_need_merge() throws IOException {
        createTestResultFile("RequireNoMergeTest");

        runTask();

        assertTaskRunSuccess();
    }

    @Test
    public void should_create_a_new_test_result_file_with_same_content_of_nested_runner_result() throws IOException {
        createTestResultFile("RequireNoMergeTest");
        createTestResultFile("TransactionAddControllerTest$Add");

        runTask();

        assertFileExists(true, "TransactionAddControllerTest");
    }

    private void assertFileExists(boolean expected, String fileName) {
        assertThat(testResultFile(targetTestResultsFolder, fileName).exists()).isEqualTo(expected);
    }

    private File testResultFile(String folder, String fileName) {
        return new File(folder + separator + format(testResultFileNamePattern, fileName));
    }

    private void createTestResultFile(String fileName) throws IOException {
        String defaultXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<testsuite name=\"com.odde.bbuddy.account.controller.AccountAddControllerTest\" tests=\"1\" skipped=\"0\" failures=\"0\" errors=\"0\" timestamp=\"2016-11-06T10:32:48\" hostname=\"Josephs-MBP-2\" time=\"0.041\">\n" +
                "  <properties/>\n" +
                "  <testcase name=\"should_not_add_account\" classname=\"com.odde.bbuddy.account.controller.AccountAddControllerTest\" time=\"0.041\"/>\n" +
                "  <system-out><![CDATA[]]></system-out>\n" +
                "  <system-err><![CDATA[]]></system-err>\n" +
                "</testsuite>";
        writeStringToFile(testResultFile(sourceTestResultsFolder, fileName), defaultXml);
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

    private File gradleFile() {
        return new File(workingDirectory() + separator + "build.gradle");
    }

    private String workingDirectory() {
        return System.getProperty("user.dir");
    }

}
