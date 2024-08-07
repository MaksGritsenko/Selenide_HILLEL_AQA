package base;

import org.example.setup.SetUp;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    private final SetUp setUp = new SetUp();

    @BeforeClass
    public void setUp() {
        setUp.setUp();
    }

    @AfterClass
    public void tearDown() {
        setUp.quitDriver();
    };
}
