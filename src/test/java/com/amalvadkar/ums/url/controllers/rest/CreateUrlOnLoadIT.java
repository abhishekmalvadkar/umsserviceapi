package com.amalvadkar.ums.url.controllers.rest;

import com.amalvadkar.ums.common.AbstractIT;
import com.amalvadkar.ums.utils.TestFileUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

class CreateUrlOnLoadIT extends AbstractIT {

    private static final String ENDPOINT_CREATE_URL_ON_LOAD = "/urls/create-url-on-load";
    private static final String CREATE_URL_ON_LOAD_RESPONSE_JSON_FILE_PATH = "responses/create-url-on-load-response.json";

    @Test
    void should_return_create_url_on_load_response() throws Exception {

        String expectedResponse =
                TestFileUtils.readFile(
                        CREATE_URL_ON_LOAD_RESPONSE_JSON_FILE_PATH);

        Response response =
                umsRequest()
                        .header(REQUEST_HEADER_ROLE_ID_KEY, CUSTOMER_ROLE_ID)
                        .when()
                        .get(ENDPOINT_CREATE_URL_ON_LOAD);

        response.then()
                .statusCode(200);

        JSONAssert.assertEquals(
                expectedResponse,
                response.asPrettyString(),
                true
        );
    }
}