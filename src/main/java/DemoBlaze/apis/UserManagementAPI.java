package DemoBlaze.apis;


import DemoBlaze.utils.logs.LogsManager;
import DemoBlaze.validations.hardAssert;
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

    public UserManagementAPI() {
        // Constructor
        requestSpecification = RestAssured.given();
        verification = new hardAssert();
    }

    //endpoints
    private static final String createAccount_endpoint = "/signup";
    private static final String logInAccount_endpoint = "/login";




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
        formParams.put("name", name);
        formParams.put("password", pass);

        response =  requestSpecification.spec(Builder.getUserManagementRequestSpecification(formParams))
                .post(logInAccount_endpoint);
        LogsManager.info(response.asPrettyString());
        return this;
    }





    @Step("Verify that user is created successfully")
    public UserManagementAPI verifyUserAlreadySignedUp() {
        verification.assertFalse(response.jsonPath().get("errorMessage") == "This user already exist.",
                "User is created for second time");
        return this;
    }

    @Step("Verify that user is created successfully")
    public UserManagementAPI verifyUserSignedSuccessfuly() {
        verification.assertEquals(response.jsonPath().get("message"), "",
                "User is not created successfully");
        return this;
    }
    //negative validation
    @Step("Verify that account not created with invalid data")
    public UserManagementAPI verifySignupInvalidname() {
        verification.assertFalse(response.jsonPath().get("Message")== "",
                "invalid username");
        return this;
    }
    @Step("Verify can't login without signup")
    public UserManagementAPI verifyLoginInvalidname() {
        verification.assertFalse(response.jsonPath().get("errorMessage")== "User does not exist.",
                "invalid username");
        return this;
    }

    @Step("Verify that account not created")
    public UserManagementAPI verifyLoginInvalidpassword() {
        verification.assertFalse(response.jsonPath().get("errorMessage")== "Wrong password",
                "invalidpassword");
        return this;
    }
    //validations
    @Step("Verify that user is created successfully")
    public UserManagementAPI verifySuccessfulLogIn() {
        verification.assertEquals(response.jsonPath().get("message"), "Auth_token: YWJkdTExMTIyMjMxNzcxNzE5",
                "User Logedin succefully");
        return this;
    }
}

