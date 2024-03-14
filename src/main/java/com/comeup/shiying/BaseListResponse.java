package com.comeup.shiying;

import java.util.List;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月14日 0014
 * @version: 1.0
 */
public class BaseListResponse<T> {
    private long totalCount;
    private List<T> content;

    public BaseListResponse() {
    }

    public static <T> BaseListResponse<T> of(Long totalCount) {
        BaseListResponse<T> baseListResponse = new BaseListResponse();
        baseListResponse.setTotalCount(totalCount);
        return baseListResponse;
    }

    public static <T> BaseListResponse<T> of(List<T> content, Long totalCount) {
        BaseListResponse<T> baseListResponse = new BaseListResponse();
        baseListResponse.setTotalCount(totalCount);
        baseListResponse.setContent(content);
        return baseListResponse;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getContent() {
        return this.content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
