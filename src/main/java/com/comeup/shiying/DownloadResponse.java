package com.comeup.shiying;

public class DownloadResponse {
    private String dataJsonStr;
    private Long totalCount;

    public static DownloadResponse of(String dataJsonStr, Long totalCount) {
        DownloadResponse downloadResponse = new DownloadResponse();
        downloadResponse.dataJsonStr = dataJsonStr;
        downloadResponse.totalCount = totalCount;
        return downloadResponse;
    }

    public String getDataJsonStr() {
        return dataJsonStr;
    }

    public void setDataJsonStr(String dataJsonStr) {
        this.dataJsonStr = dataJsonStr;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
