package constants;


import utils.fileReader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class reporterConstant {
    public static final String FILE_SEPARATOR = File.separator;
    public static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    public static final Path TEST_REPORTER_PROPERTY_FILE_DIRECTORY
            = Paths.get(PROJECT_DIRECTORY, "src", "main", "resources", "test-reporter.properties");
    public static final String EXTENT_FULL_REPORT_DIRECTORY
            = fileReader.getTestReporterProperty("extent_full_report_dir");
    public static final String EXTENT_REPORT_FILE_NAME_PREFIX
            = fileReader.getTestReporterProperty("extent_report_file_name_prefix");
    public static final String EXTENT_REPORT_THEME
            = fileReader.getTestReporterProperty("extent_reporter_theme");
    public static final String EXTENT_REPORT_DOCUMENT_TITLE
            = fileReader.getTestReporterProperty("extent_document_title");
    public static final String EXTENT_REPORT_REPORTER_NAME
            = fileReader.getTestReporterProperty("extent_reporter_name");
    public static final String APPLICATION_NAME
            = fileReader.getTestReporterProperty("application_name");
    public static final String TEST_DEVELOPER
            = fileReader.getTestReporterProperty("test_developer");
}
