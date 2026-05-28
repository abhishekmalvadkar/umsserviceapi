package com.amalvadkar.ums.url.controllers.rest;

import com.amalvadkar.ums.assertions.CreateUrlResponseAssertions;
import com.amalvadkar.ums.common.AbstractIT;
import com.amalvadkar.ums.utils.TestFileUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql(
        scripts = "/sql/cleanup/cleanup-urls.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class CreateUrlIT extends AbstractIT {

    private static final String ENDPOINT_CREATE_URL = "/urls/create-url";
    private static final String CREATE_URL_WITH_CUSTOM_SLUG_REQUEST_JSON_FILE_PATH = "requests/create-url-with-custom-slug-request.json";
    private static final String CREATE_URL_WITHOUT_SLUG_REQUEST_JSON_FILE_PATH = "requests/create-url-without-slug-request.json";

    @Test
    void should_return_create_url_response_when_user_has_given_custom_slug() throws Exception {

        String request =
                TestFileUtils.readFile(
                        CREATE_URL_WITH_CUSTOM_SLUG_REQUEST_JSON_FILE_PATH);

        Response response =
                umsRequest()
                        .header(REQUEST_HEADER_ROLE_ID_KEY, CUSTOMER_ROLE_ID)
                        .body(request)
                        .when()
                        .post(ENDPOINT_CREATE_URL);

        response.then()
                .statusCode(200);

        long expectedCreatedByUserId = 1L;
        String expectedSlug = "DbExploration";
        String expectedOriginalUrl = "https://abhishekmalvadkar.netlify.app/database-exploration-checklist-for-understanding-a-new-domain/";
        String expectedTitle = "Database Exploration Checklist";
        CreateUrlResponseAssertions.assertCreateUrlResponseWithCustomSlug(
                response,
                expectedTitle,
                expectedOriginalUrl,
                expectedSlug,
                expectedCreatedByUserId
        );
    }

    @Test
    void should_return_create_url_response_when_user_does_not_given_custom_slug() throws Exception {

        String request =
                TestFileUtils.readFile(
                        CREATE_URL_WITHOUT_SLUG_REQUEST_JSON_FILE_PATH);

        Response response =
                umsRequest()
                        .header(REQUEST_HEADER_ROLE_ID_KEY, CUSTOMER_ROLE_ID)
                        .body(request)
                        .when()
                        .post(ENDPOINT_CREATE_URL);

        response.then()
                .statusCode(200);

        long expectedCreatedByUserId = 1L;
        String expectedOriginalUrl = "https://abhishekmalvadkar.netlify.app/database-exploration-checklist-for-understanding-a-new-domain/";
        String expectedTitle = "Database Exploration Checklist";
        CreateUrlResponseAssertions.assertCreateUrlResponseWithGeneratedSlug(
                response,
                expectedTitle,
                expectedOriginalUrl,
                expectedCreatedByUserId
        );

    }
}