import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class Demo {

    @Test
    public void TC01() throws Exception
    {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        JSONObject json = new JSONObject(response.asString());
        JSONObject data = json.getJSONObject("data");
        Assertions.assertTrue(data.get("id") instanceof Integer);
        System.out.println("FirstParallelUnitTest first() start => " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("FirstParallelUnitTest first() end => " + Thread.currentThread().getName());
    }

    @Test
    public void TC02() throws Exception
    {
        System.out.println("FirstParallelUnitTest second() start => " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("FirstParallelUnitTest second() end => " + Thread.currentThread().getName());
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        JSONObject json = new JSONObject(response.asString());
        JSONObject data = json.getJSONObject("data");
        Assertions.assertTrue(data.get("id") instanceof Integer);
    }
}
