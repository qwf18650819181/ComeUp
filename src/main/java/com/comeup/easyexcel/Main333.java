//package com.comeup.easyexcel;
//
//import co.paralleluniverse.fibers.Fiber;
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.read.listener.PageReadListener;
//import com.alibaba.excel.support.ExcelTypeEnum;
//import com.alibaba.excel.util.StringUtils;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.annotation.JSONField;
//import com.comeup.MarketingSalesActivityItemDTO;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Consumer;
//import java.util.stream.Collectors;
//
///**
// * @auth: qwf
// * @date: 2023年11月28日 0028
// * @description: for version 3.3.2
// */
//public class Main333 {
////    public static void main(String[] args) {
////        long l = CustomClassAgent.sizeOf(new T());
////        System.out.println("Hello world!");
////        System.out.println(l);
////    }
//
//    /**
//     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//     *
//     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
//     * @param args
//     */
//    public static void main1(String[] args) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream("E://亚马逊后台活动明细_Z划算_5009_ATVPDKIKX0DER_20231117_DOKOTOO(1).csv");
//
//        EasyExcel.read(fileInputStream, MarketingSalesActivityItemDTO.class, new PageReadListener(getConsumer1()))
//
//                .excelType(ExcelTypeEnum.CSV)
//                .charset(StandardCharsets.UTF_8)
//                .autoCloseStream(true)
//                .sheet(0)
//                .doRead();
//
//
//    }
//
//
//    public static Consumer<List<MarketingSalesActivityItemDTO>> getConsumer1() {
//        return dataList -> {
//            if (org.apache.commons.collections4.CollectionUtils.isEmpty(dataList)) {
//                return;
//            }
//            dataList.forEach(item -> {
//                if (StringUtils.isNotBlank(item.getShopId())) {
//                    System.out.println(item.getShopId());
//                }
//            });
//
//            System.out.println(dataList);
//        };
//    }
//
//
//    /**
//     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
//     *
//     * 子数组是数组中元素的连续非空序列。
//     * @param nums
//     * @param k
//     * @return
//     */
//    public int subarraySum(int[] nums, int k) {
//        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
//        int count =  0;
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = nums.length; j > i; j--) {
//                int sum = list.subList(i, j).stream().mapToInt(Integer::intValue).sum();
//                if (sum == k) count++;
//            }
//        }
//        return count;
//    }
//
//
//    public static void main(String[] args) throws InterruptedException {
//        int a = 5;
//        if (getFlag()) {
//
//            System.out.println(12324);
//        }
//        System.out.println(12324);
//        Money usd = new Money("1.23", "USD");
//        String jsonString = JSON.toJSONString(usd);
//        System.out.println(jsonString);
//        System.out.println(a);
//
//        int i = 1;
//        int i1 = i <<= 3;
//
//
//
//    }
//    private static class Money1 {
//        String[] lockKey;
//
//    }
//    private static class Money {
//        @JSONField(name = "amount")
//        private String amount;
//        @JSONField(name = "currency_code")
//        private String currencyCode;
//
//        Money(String amount, String currencyCode) {
//            this.amount = amount;
//            this.currencyCode = currencyCode;
//        }
//
//        public String getAmount() {
//            return amount;
//        }
//
//        public void setAmount(String amount) {
//            this.amount = amount;
//        }
//
//        public String getCurrencyCode() {
//            return currencyCode;
//        }
//
//        public void setCurrencyCode(String currencyCode) {
//            this.currencyCode = currencyCode;
//        }
//    }
//
//    private static boolean getFlag() {
//        int i = 5;
//        int a = 5;
//        System.out.println(false);
//        System.out.println(true);
//        System.out.println(i);
//        System.out.printf("sout fsdnafn%d%dfsjfe%d%n", i, a, i);
//        return false;
//    }
//
//    public static void fiberMain(String[] args) throws InterruptedException {
//
//        List<Fiber> fibers = new ArrayList<>();
//
//        for (int i = 0; i < 10000_00L; i++) {
//            Fiber<Void> fiber = new Fiber<>(() -> System.out.println("1234"));
//            fiber.start();
//            fibers.add(fiber);
//        }
//        fibers.forEach(item -> {
//            try {
//                item.join();
//            } catch (Exception ignored) {
//            }
//        });
//        System.out.println("end");
//    }
//
//    public static void ThreadMain(String[] args) throws InterruptedException {
//
//        List<Thread> fibers = new ArrayList<>();
//
//        for (int i = 0; i < 10000_00L; i++) {
//            Thread fiber = new Thread(() -> System.out.println("1234"));
//            fiber.start();
//            fibers.add(fiber);
//        }
//        fibers.forEach(item -> {
//            try {
//                item.join();
//            } catch (Exception ignored) {
//            }
//        });
//        System.out.println("end");
//    }
//
//
//    public static class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int x) {
//            val = x;
//            next = null;
//        }
//    }
//
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        if (headA == null) return null;
//
//        for (ListNode pointer = headB; pointer != null; pointer = pointer.next) {
//            ListNode headACopy = null;
//            if (headACopy == null && headA.val == pointer.val) {
//                headACopy = headA;
//            }
//            if (headACopy != null && headACopy.val == pointer.val) {
//                headACopy = headACopy.next;
//                continue;
//            }
//            if (headACopy != null && headACopy.val != pointer.val) {
//                headACopy = null;
//            }
//            if (headACopy != null && headACopy.next == null && pointer.next == null) {
//                return headA;
//            }
//        }
//        return getIntersectionNode(headA.next, headB);
//    }
//
//
//}
