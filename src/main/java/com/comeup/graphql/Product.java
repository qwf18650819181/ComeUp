package com.comeup.graphql;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年12月23日 星期一
 * @version: 1.0
 */
public class Product {
    private String bodyHtml;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;
    private String handle;
    private Long id;
    private List<Image> images;
    private List<Option> options;
    private String productType;     // 用于过滤和搜索产品的产品的分类
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date publishedAt;
    private String publishedScope;
    private String tags;    // 产品标签（以逗号分隔）
    private String templateSuffix;
    private String title;  // 产品名称
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedAt;
    private List<Variant> variants;  // sku列表
    private String vendor;
    private String status;



}
