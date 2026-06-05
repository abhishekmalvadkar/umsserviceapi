package com.amalvadkar.ums.url.controllers;

import com.amalvadkar.ums.common.AbstractIT;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static com.amalvadkar.ums.url.enums.SlugStatusEnum.INACTIVE_SLUG;
import static com.amalvadkar.ums.url.enums.SlugStatusEnum.UNKNOWN_SLUG;
import static org.assertj.core.api.Assertions.assertThat;

@Sql(
        scripts = {
                "/sql/cleanup/cleanup-urls.sql",
                "/sql/test-data/visit-url.sql"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class VisitUrlIT extends AbstractIT {

    private static final String ENDPOINT_VISIT_URL = "/urls/visit-url/{slug}";

    @Test
    void should_redirect_to_original_url_when_slug_exists() {
        umsRequestWithoutHeader()
                .pathParam("slug", "valid-slug")
                .when()
                .redirects().follow(false)
                .get(ENDPOINT_VISIT_URL)
                .then()
                .statusCode(302)
                .header(
                        "Location",
                        "https://google.com"
                );
    }

    @Test
    void should_show_error_message_when_slug_does_not_exist() {
        String response =
                umsRequestWithoutHeader()
                        .pathParam("slug", "unknown-slug")
                        .when()
                        .get(ENDPOINT_VISIT_URL)
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();
        assertThat(response).contains(UNKNOWN_SLUG.message());
    }

    @Test
    void should_show_error_message_when_slug_is_inactive() {
        String response =
                umsRequestWithoutHeader()
                        .pathParam("slug", "inactive-slug")
                        .when()
                        .get(ENDPOINT_VISIT_URL)
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();
        assertThat(response).contains(INACTIVE_SLUG.message());
    }
}
