package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.TestListener;
import utils.fileReader;

@Listeners(TestListener.class)
public class baseTest {
    private final Logger logger = LogManager.getLogger();
    @BeforeSuite
    public void setUp()
    {
        logger.debug("Test execution environment: {}", fileReader.getPropertyFromFile("Environment"));

    }

}
