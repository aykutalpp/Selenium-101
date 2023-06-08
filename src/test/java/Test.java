import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class Test {

    @BeforeClass
    public static void before (){
        System.out.println("BeforeAll");
    }
    @Before
    public void beforeClass (){
        System.out.println("BeforeEach");
    }
    @org.junit.Test
    public void testOne () {
        System.out.println("Test One");
    }
    @org.junit.Test
    public void testTwo () {
        System.out.println("Test Two");
    }
    @org.junit.Test
    public void testThree () {
        System.out.println("Test Three");
    }

    @After
    public void afterClass (){
        System.out.println("AfterEach");
    }

    @AfterClass
    public static void after (){
        System.out.println("AfterAll");
    }

}
