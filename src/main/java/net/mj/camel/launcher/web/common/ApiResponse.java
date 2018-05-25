package net.mj.camel.launcher.web.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import net.mj.camel.launcher.web.common.ApiResponseCode;

@Data
public class ApiResponse<T> {
    @JsonProperty("code")
    private ApiResponseCode code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;

    public ApiResponse() {
    }

    public ApiResponse(T result) {
        this.code = ApiResponseCode.OK;
        this.message = this.code.getMessage();
        this.result = result;
    }

    public ApiResponse(ApiResponseCode code, T result) {
        this.code = code;
        this.message = this.code.getMessage();
        this.result = result;
    }

    public ApiResponse(ApiResponseCode code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }
}
