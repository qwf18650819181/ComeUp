package com.comeup.algorithm.zuo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 功能描述: 会议排期,排最多的会议数量
 *
 * note: 贪心算法   1. 分析业务 2. 根据业务划分不同贪心策略 3. 举反例直接跳过,不能举例需要举证 证明局部有效 -> 全局有效  4. 不要非要证明局部最优
 *
 *
 * note: 最早结束取哪个
 *
 * note: 贪心算法最常用的结构是堆跟排序
 * @author qiu wanzi
 * @date 2024年3月11日 0011
 */
@Slf4j
public class Zuo11_MeetSchedule {


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Program {
        int cost;
        int profit;

        public static int costCompare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }

        public static int profitCompare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    /**
     * note 3. 项目花费 项目成本跟利润的关系 [1,1],[1,3],[4,4],[3,3]总共这四个项目,只能做两个项目,你的成本是2,求利润最大
     * @param programs
     * @param k
     * @param cost
     * @return
     */
    public int maxProfit(Program[] programs, int k, int cost) {
        int maxProfit = cost;
        PriorityQueue<Program> costQueue = new PriorityQueue<>(Program::costCompare);
        Arrays.stream(programs).forEach(costQueue::offer);
        PriorityQueue<Program> profitQueue = new PriorityQueue<>(Program::profitCompare);
        for (int i = 0; i < k; i++) {
            if (costQueue.isEmpty()) break;
            if (costQueue.peek().cost <= cost) {
                profitQueue.offer(costQueue.poll());
            }
            if (profitQueue.isEmpty()) {
                return maxProfit - cost;
            }
            Program thisTimeProgram = profitQueue.poll();
            maxProfit = cost - thisTimeProgram.cost + thisTimeProgram.getProfit();
            while (!profitQueue.isEmpty()) {
                costQueue.offer(profitQueue.poll());
            }
        }
        return maxProfit - cost;
    }


    /**
     * note 2. 黄金切割 一个60长度的黄金,分隔成10,20,30长度,比如第一次分隔10 + 50则成本是10+50=60,第二次分隔20+30成本是20+30=50,则最终成本是110,求最小成本
     * note: 哈夫曼树 最优二叉树
     * @param arr
     * @return
     */
    public int minCost(int[] arr) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Arrays.stream(arr).forEach(priorityQueue::offer);
        int minCost = 0;
        while (priorityQueue.size() > 1) {
            int cut = priorityQueue.poll() + priorityQueue.poll();
            minCost += cut;
            priorityQueue.offer(cut);
        }
        return minCost;
    }


    /**
     * note 1. 放几个灯 string a = "xxxooxxoooooox" 一个灯可以照亮三个位置
     * @param lightStr
     * @return
     */
    public int minLights(String lightStr) {
        if (lightStr == null || lightStr.isEmpty()) return 0;
        char[] charArray = lightStr.toCharArray();
        int lights = 0;
        int length = charArray.length;
        for (int index = 0; index < length; ) {
            if (charArray[index] == 'x') {
                index++;
                continue;
            }
            lights++;
            index++;
            if (index + 1 < length && charArray[index] == 'o' && charArray[index + 1] == 'o') {
                index = index + 2;
                continue;
            }
            if (index < length && charArray[index] == 'o') {
                index = index + 1;
            }
        }
        return lights;
    }


    /**
     * note: 最多可以安排几个会议
     * @param meetings
     * @param startDate
     * @param endDate
     * @return
     */
    public int maxMeeting(List<Meeting> meetings, Date startDate, Date endDate) {
        if (meetings == null || meetings.isEmpty() || !startDate.before(endDate)) return 0;
        List<Meeting> sortMeetings = meetings.stream().filter(meeting -> !meeting.getBegin().before(startDate) && !meeting.getEnd().after(endDate)).sorted(Comparator.comparing(Meeting::getBegin)).collect(Collectors.toList());
        if (sortMeetings.isEmpty()) return 0;
        return getMaxMeeting(sortMeetings, startDate, 0);
    }

    private static int getMaxMeeting(List<Meeting> sortMeetings, Date startDateLine, int maxMeeting) {
        Meeting minEndMeeting = null;
        for (int i = 0; i < sortMeetings.size(); i++) {
            if (sortMeetings.get(i).getBegin().before(startDateLine)) continue;
            if (Objects.isNull(minEndMeeting)) {
                minEndMeeting = sortMeetings.get(i);
            } else if (sortMeetings.get(i).getEnd().compareTo(minEndMeeting.getEnd()) < 0) {
                minEndMeeting = sortMeetings.get(i);
            }
        }
        if (minEndMeeting == null) {
            return maxMeeting;
        }
        sortMeetings.remove(minEndMeeting);
        return getMaxMeeting(sortMeetings, minEndMeeting.getEnd(), ++maxMeeting);
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meeting {
        Date begin;
        Date end;
    }

}
