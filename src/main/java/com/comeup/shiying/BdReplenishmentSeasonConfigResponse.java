package com.comeup.shiying;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
public class BdReplenishmentSeasonConfigResponse implements Serializable {

    private static final long serialVersionUID = 24156155350046884L;

    private Integer configSeasonId;

    private Integer season;

    private String seasonName;


    private Integer thisYearDays;

    private String createBy;

    private Date createDate;

    private String updateBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;


}
