package apichecking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    public static void main(String[] args) {

        LOGGER.info("INF");
        LOGGER.warn("WRN");
        LOGGER.error("ERR");
    }
}
