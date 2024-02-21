package com.comeup.pdf;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Woods Lee
 * @Date: 2019/4/19 13:55
 */
public class PDFGenerator {

    public static void main(String[] args) throws Exception {

        Map<String, Object> dataMap = buildGenerateJudgementData();
        String generateFileName = "客诉责任判定书";
        Map<String, String> fileNameMap = new HashMap<>();
        try {
            ITextPDFUtils.processPdf(dataMap, "CustomerComplaintsResponsibilityPunishJudgement", generateFileName, 595.0F, 842.0F, fileNameMap);
        } finally {
//            fileNameMap.values().forEach(fileName -> new File(fileName).delete());
        }

    }



    public static Map<String, Object> buildGenerateJudgementData() {
        Map<String, Object> data = new HashMap<>();
        // 客诉信息
        data.put("orderNo", ciphertextHandle("orderId1234", 3));
        data.put("createDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        data.put("skuDesc", "sku desc");
        data.put("feedbackQty", "feedbackQty");
        data.put("returnReason", "质量问题");
        data.put("comments", "comments");
        data.put("feedbackRemark", "feedbackRemark");
        data.put("imgUrls", Collections.singleton("https://stage-nsy-products.oss-cn-hangzhou.aliyuncs.com/kww/bfp/1693987899044.jpg"));
        // 溯源信息
        data.put("supplierName", "supplierName");
        data.put("supplierDepartment", "supplierDepartment");
        data.put("purchaserName", "purchaserName");
        data.put("qcName", "qcName");
        // 责任判定
        data.put("responsiblePartyChinese", "responsiblePartyChinese");
        data.put("handleWayChinese", "handleWayChinese");
        data.put("judgeRemark", "judgeRemark");
        data.put("judgeBy", "judgeBy");
        // 客诉罚款
        data.put("punishPrice", "¥100.00/件");
        data.put("punishQty", "punishQty");
        data.put("isPunish", "isPunish是");
        data.put("punishAmount", BigDecimal.ZERO);
        return data;
    }

    /**
     * 后几位明文，加上6位 "*"的前缀
     *
     * @param str             字符串
     * @param plaintextLength 明文长度
     * @return 替换后的字符串
     */
    private static String ciphertextHandle(String str, int plaintextLength) {
        if (StringUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }
        String defaultSixCiphertext = StringUtils.repeat("*", 6);
        // 长度小于明文长度
        if (str.length() <= plaintextLength) {
            return defaultSixCiphertext.concat(str);
        }
        // 后3位明文，其余用"*"代替
        return defaultSixCiphertext.concat(StringUtils.substring(str, str.length() - plaintextLength, str.length()));
    }
}

