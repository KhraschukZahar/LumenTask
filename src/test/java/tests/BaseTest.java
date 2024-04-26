package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import specifications.Specifications;

public class BaseTest {
    protected SoftAssert softAssert = new SoftAssert();
    private final static String BASE_URL = "https://block.io";
    @BeforeClass
    public void setup() {}

    @BeforeSuite
    public void beforeMethod() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecOK200());
    }

}
