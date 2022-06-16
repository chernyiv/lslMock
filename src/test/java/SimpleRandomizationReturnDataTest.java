import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleRandomizationReturnDataTest {

    @RepeatedTest(10)
    public void setAndReturnData() {
        SimpleRandomizationReturnData.setAndReturnData();
    }
}