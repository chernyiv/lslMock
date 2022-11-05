import org.junit.jupiter.api.RepeatedTest;

class SimpleRandomizationReturnDataTest {

    @RepeatedTest(100)
    public void setAndReturnData() {
        SimpleRandomizationReturnData e = new SimpleRandomizationReturnData();
        e.setAndReturnData();
    }
}