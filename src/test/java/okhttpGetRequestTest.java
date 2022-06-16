import org.junit.jupiter.api.RepeatedTest;

class okhttpGetRequestTest {

    @RepeatedTest(10)
    public void sendGetRequestTest(){
        okhttpGetRequest.sendGetRequest("https://www.google.ru/");
    }

}