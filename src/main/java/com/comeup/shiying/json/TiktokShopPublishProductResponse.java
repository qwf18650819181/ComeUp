package com.comeup.shiying.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: linCheng
 * @create: 2023-07-14 15:30
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TiktokShopPublishProductResponse extends TiktokShopResponse<TiktokShopPublishProductResponse.DataBody> {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataBody {
        @JsonProperty("spu_code")
        private String spuCode;
    }

}
