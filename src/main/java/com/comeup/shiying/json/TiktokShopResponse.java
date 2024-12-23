package com.comeup.shiying.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: linCheng
 * @create: 2023-07-14 15:30
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TiktokShopResponse<T> {

    /**
     * default 0 for success
     */
    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("data")
    private T data;

}
