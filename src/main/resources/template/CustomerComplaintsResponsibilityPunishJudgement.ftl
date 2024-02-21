<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Title</title>
    <style mce_bogus="1" type="text/css">
        body{
            font-family: STSong-Light;
        }
        .template {
            color: black;
            padding: 10px 40px 10px 40px;
        }
        .template div {
            line-height: 1.5;
        }

        .table1 table{
            line-height: 1;
            margin-top: 5px;
            width: 100%;
            border : 0.5px solid black;
            border: 0.5px solid black;
            table-layout:fixed;
            border-collapse: collapse;
            overflow:hidden;
        }
        .table1 td{
            text-align:center;
            border: 0.5px solid black;
            border: 0.5px solid black;
            word-break:break-all;
            border-collapse: collapse;
            font-size: 15px;
        }
    </style>
</head>
<body>
    <div id="templateNumFiv" class="template">
        <table class="table1" style="width: 100%;table-layout:fixed;word-break:break-all;padding-bottom: 0px;margin-bottom: 0px;border-bottom: 0px;border-collapse:collapse;" >
            <tr style="height: 70px;">
                <td style="text-align: center;background-color: #F2F2F2;font-size: 28px;" colspan="4">客诉责任判定书</td>
            </tr>
            <!-- 客诉信息 -->
            <tr style="height: 40px;">
                <td style="text-align: center;background-color: #FCE2D6;font-size: 18px;" colspan="4">客诉信息</td>
            </tr>
            <tr>
                <td style="text-align: center;" >订单号</td>
                <td style="text-align: left;" >${(orderNo)?default("")}</td>
                <td style="text-align: center;">客诉申请时间</td>
                <td style="text-align: left;">${(createDate)?default("")}</td>
            </tr>
            <tr>
                <td style="text-align: center;" >SKU</td>
                <td style="text-align: left;" >${(skuDesc)?default("")}</td>
                <td style="text-align: center;">反馈件数</td>
                <td style="text-align: left;">${(feedbackQty)?default("")}</td>
            </tr>
            <tr>
                <td style="text-align: center;" >客退原因</td>
                <td style="text-align: left;" >${(returnReason)?default("")}</td>
                <td style="text-align: center;">问题描述</td>
                <td style="text-align: left;">${(comments)?default("")}</td>
            </tr>
            <tr>
                <td style="text-align: center;">反馈备注</td>
                <td style="text-align: left;" colspan="3">${(feedbackRemark)?default("")}</td>
            </tr>
            <tr>
                <td style="text-align: center;">反馈图片</td>
                <td style="text-align: left;" colspan="3">
                    <#list imgUrls as imgUrl>
                        <img style="width: 100px;height:110px;" src="${(imgUrl)?default("")}"/>
                    </#list>
                </td>
            </tr>


            <!-- 溯源信息 -->
            <tr><td colspan="4">&nbsp;</td></tr>
            <tr style="height: 40px;">
                <td style="text-align: center;background-color: #FCE2D6;font-size: 18px;" colspan="4">溯源信息</td>
            </tr>
            <tr>
                <td style="text-align: center;" >供应商名称</td>
                <td style="text-align: left;" >${(supplierName)?default("")}</td>
                <td style="text-align: center;">对接部门</td>
                <td style="text-align: left;">${(supplierDepartment)?default("")}</td>
            </tr>
            <tr>
                <td style="text-align: center;" >采购员</td>
                <td style="text-align: left;" >${(purchaserName)?default("")}</td>
                <td style="text-align: center;">QC跟单</td>
                <td style="text-align: left;">${(qcName)?default("")}</td>
            </tr>

            <!-- 责任判定 -->
            <tr><td colspan="4">&nbsp;</td></tr>
            <tr style="height: 40px;">
                <td style="text-align: center;background-color: #FCE2D6;font-size: 18px;" colspan="4">责任判定</td>
            </tr>
            <tr>
                <td style="text-align: center;" >责任方</td>
                <td style="text-align: left;" >${(responsiblePartyChinese)?default("")}</td>
                <td style="text-align: center;">处理方式</td>
                <td style="text-align: left;">${(handleWayChinese)?default("")}</td>
            </tr>
            <tr>
                <td style="text-align: center;" >判定处理备注</td>
                <td style="text-align: left;" colspan="3">${(judgeRemark)?default("")}</td>
            </tr>
            <tr>
                <td style="text-align: center;" >责任判定人</td>
                <td style="text-align: left;" colspan="3">${(judgeBy)?default("")}</td>
            </tr>
            <!-- 客诉罚款 -->
            <tr><td colspan="4">&nbsp;</td></tr>
            <tr style="height: 40px;">
                <td style="text-align: center;background-color: #FCE2D6;font-size: 18px;" colspan="4">客诉罚款</td>
            </tr>
            <tr>
                <td style="text-align: center;" >处罚单价</td>
                <td style="text-align: left;" >${(punishPrice)?default("")}</td>
                <td style="text-align: center;">处罚件数</td>
                <td style="text-align: left;">${(punishQty)?default("")}</td>
            </tr>
            <tr>
                <td style="text-align: center;" >是否处罚</td>
                <td style="text-align: left;" colspan="3">${(isPunish)?default("")}</td>
            </tr>
            <tr>
                <td style="text-align: center;" >处罚扣款金额</td>
                <td style="text-align: left;color: red;" colspan="3">${(punishAmount)?default("")}</td>
            </tr>
            <tr height="70px;"><td colspan="4">&nbsp;</td></tr>
        </table>
    </div>
</body>
</html>