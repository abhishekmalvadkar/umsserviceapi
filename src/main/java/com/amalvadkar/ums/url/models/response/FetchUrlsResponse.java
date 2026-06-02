package com.amalvadkar.ums.url.models.response;

import com.amalvadkar.ums.common.model.response.HeaderResponse;
import lombok.Data;

import java.util.List;

@Data
public class FetchUrlsResponse {
    private List<HeaderResponse> headers;
    private List<FetchUrlsDataResponse> data;
}
