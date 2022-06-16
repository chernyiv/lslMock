public class SimpleRandomizationReturnData {

    public static void main(String[] args) {
        setAndReturnData();
    }

    public static void setAndReturnData() {

        newSimpleRandomization.Data res = newSimpleRandomization.Execution.setAndReturnData();
        System.out.println(res.toString());
    }
}
