package hooks;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;

public class Hooks {

        @Before()
        public void setScenario(Scenario scenario) {
            Logger.getRootLogger().info("-----------" + scenario.getName() + "-----------");
        }

        @After()
        public void dismissAll(Scenario scenario) {
            Logger.getRootLogger().info(" ending -----------" + scenario.getName() + "-----------");

        }
    }

