package scenarios;

import org.testng.annotations.DataProvider;


public class DataProviders {
    @DataProvider(name = "native")
    public Object[][] dpNativeMethod(){
        return new Object[][] {{"Fwerf", "afs1424ad@gmail.com", "123456qwerty"}};
    }
    @DataProvider(name = "web")
    public Object[][] dpWebMethod(){
        return new Object[][] {{"EPAM"}};
    }
}
