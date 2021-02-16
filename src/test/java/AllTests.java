import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.everis.boundary.CarResourcesTest;
import com.everis.control.CarServiceTest;

@RunWith(Suite.class)
@SuiteClasses({CarServiceTest.class, CarResourcesTest.class })
public class AllTests {

}
