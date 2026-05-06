package DemoBlaze.apis;


import DemoBlaze.utils.logs.LogsManager;
import DemoBlaze.validations.hardAssert;
import DemoBlaze.validations.softAssert;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class UserManagementAPI {
    RequestSpecification requestSpecification;
    Response response;
    hardAssert verification;
    softAssert validate;
    private static String authToken;

    //endpoints
    private static final String createAccount_endpoint = "/signup";
    private static final String logInAccount_endpoint = "/login";
    private static final String logInCheck_endpoint = "/check";

    public UserManagementAPI() {
        // Constructor
        requestSpecification = RestAssured.given();
        verification = new hardAssert();
        validate = new softAssert();
    }


    @Step("Create a new user account with minimal details")
    public UserManagementAPI createRegisterUserAccount(String name, String pass)
    {
        Map <String, String> formParams = new HashMap<>();
        formParams.put("name", name);
        formParams.put("password", pass);

        response =  requestSpecification.spec(Builder.getUserManagementRequestSpecification(formParams))
                .post(createAccount_endpoint);
        LogsManager.info(response.asPrettyString());
        return this;
    }
    @Step("Create a new user account with minimal details")
    public UserManagementAPI LoginAccount(String name, String pass)
    {
        Map <String, String> formParams = new HashMap<>();
        formParams.put("username", name);
        formParams.put("password", pass);

        response =  requestSpecification.spec(Builder.getUserManagementRequestSpecification(formParams))
                .post(logInAccount_endpoint);
        LogsManager.info(response.asPrettyString());
        String rawResponse = response.asString(); // "Auth_token: YWJkdTEyMzE3NzgyMjQ="
        authToken= rawResponse.replace("Auth_token: ", "").replace("\"", "").trim();
        return this;
    }
    @Step("Create a new user account with minimal details")
    public UserManagementAPI LoginCheck()
    {
        Map <String, String> formParams = new HashMap<>();
        formParams.put("token",authToken);


        response =  requestSpecification.spec(Builder.getUserManagementRequestSpecification(formParams))
                .post(logInCheck_endpoint);
        LogsManager.info(response.asPrettyString());


        return this;
    }




    @Step("Verify that user is created successfully")
    public UserManagementAPI verifyUserAlreadySignedUp() {
        verification.assertFalse(response.jsonPath().get("errorMessage") == "This user already exist.",
                "User is created for second time");
        long responseTime = response.getTime();
        verification.assertTrue(responseTime < 4000L,
                "Response time was too slow! Expected < 4000ms but got: " + responseTime + "ms");
        return this;
    }

    @Step("Verify that user is created successfully")
    public UserManagementAPI verifyUserSignedSuccessfuly() {
        validate.assertEquals(response.jsonPath().get("message"), "",
                "User is not created successfully");
        long responseTime = response.getTime();
        validate.assertTrue(responseTime < 4000L,
                "Response time was too slow! Expected < 4000ms but got: " + responseTime + "ms");
        return this;
    }
    //negative validation
    @Step("Verify that account not created with invalid data")
    public UserManagementAPI verifySignupInvalidname() {
        validate.assertFalse(response.jsonPath().get("Message")== "",
                "invalid username");
        long responseTime = response.getTime();
        validate.assertTrue(responseTime < 4000L,
                "Response time was too slow! Expected < 4000ms but got: " + responseTime + "ms");
        return this;
    }
    @Step("Verify can't login without signup")
    public UserManagementAPI verifyLoginInvalidUsername() {
        validate.assertFalse(response.jsonPath().get("errorMessage")== "User does not exist.",
                "invalid username");
        long responseTime = response.getTime();
        validate.assertTrue(responseTime < 4000L,
                "Response time was too slow! Expected < 4000ms but got: " + responseTime + "ms");
        return this;
    }

    @Step("Validate sign in with invalid password")
    public UserManagementAPI verifyLoginInvalidpassword() {
        validate.assertFalse(response.jsonPath().get("errorMessage")== "Wrong password",
                "invalidpassword");
        long responseTime = response.getTime();
        validate.assertTrue(responseTime < 4000L,
                "Response time was too slow! Expected < 4000ms but got: " + responseTime + "ms");
        return this;
    }
    //validations
    @Step("Verify that user is logged in successfully")
    public UserManagementAPI verifySuccessfulLogIn(String token) {
        validate.assertEquals(response.jsonPath().getString("Item.token"), authToken,
                "User Login unsuccessful");
        long responseTime = response.getTime();
        validate.assertTrue(responseTime < 4000L,
                "Response time was too slow! Expected < 4000ms but got: " + responseTime + "ms");
        return this;
    }
}

