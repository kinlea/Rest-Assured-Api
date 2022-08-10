import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"},
        features = "src/test/resources/features",
        glue = {"steps","hooks"},
        tags = "@Test",
        monochrome = true)

@Test
public class RunCucumberTest {
}