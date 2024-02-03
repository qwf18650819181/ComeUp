package com.comeup.shiying;

import java.util.List;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
public class CustomExcelResponse {
    /**
     * excel 标题头
     */
    private List<List<String>> headers;

    /**
     * excel 数据内容
     */
    private List<List<Object>> data;

    public List<List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(List<List<String>> headers) {
        this.headers = headers;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }
}
