package com.amalvadkar.ums.url.controllers.rest;

import com.amalvadkar.ums.assertions.JsonAssertions;
import com.amalvadkar.ums.common.AbstractIT;
import com.amalvadkar.ums.utils.TestFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql(
        scripts = {
                "/sql/cleanup/cleanup-urls.sql",
                "/sql/test-data/fetch-urls.sql"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class FetchUrlsIT extends AbstractIT {

    private static final String ENDPOINT_FETCH_URLS = "/urls/fetch-urls";
    private static final String FETCH_ALL_URLS_REQUEST_JSON = "requests/fetch-urls/fetch-all-urls-request.json";
    private static final String FETCH_ACTIVE_URLS_REQUEST_JSON = "requests/fetch-urls/fetch-active-urls-request.json";
    private static final String FETCH_ALL_URLS_RESPONSE_JSON = "responses/fetch-urls/fetch-all-urls-response.json";
    private static final String FETCH_ACTIVE_URLS_RESPONSE_JSON = "responses/fetch-urls/fetch-active-urls-response.json";

    @Test
    void should_fetch_all_urls_when_no_filter_is_provided() throws Exception {

        String request =
                TestFileUtils.readFile(
                        FETCH_ALL_URLS_REQUEST_JSON
                );

        String actualResponse =
                umsRequest()
                        .header(REQUEST_HEADER_ROLE_ID_KEY, CUSTOMER_ROLE_ID)
                        .body(request)
                        .when()
                        .post(ENDPOINT_FETCH_URLS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();

        JsonAssertions.assertJsonEquals(
                FETCH_ALL_URLS_RESPONSE_JSON,
                actualResponse
        );
    }

    @Test
    void should_fetch_urls_by_status() throws Exception {

        String request =
                TestFileUtils.readFile(
                        FETCH_ACTIVE_URLS_REQUEST_JSON
                );

        String actualResponse =
                umsRequest()
                        .header(REQUEST_HEADER_ROLE_ID_KEY, CUSTOMER_ROLE_ID)
                        .body(request)
                        .when()
                        .post(ENDPOINT_FETCH_URLS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();

        JsonAssertions.assertJsonEquals(
                FETCH_ACTIVE_URLS_RESPONSE_JSON,
                actualResponse
        );
    }


}