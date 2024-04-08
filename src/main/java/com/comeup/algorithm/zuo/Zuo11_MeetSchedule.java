package com.comeup.algorithm.zuo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 功能描述: 会议排期,排最多的会议数量
 *
 * note: 贪心算法   1. 分析业务 2. 根据业务划分不同贪心策略 3. 举反例直接跳过,不能举例需要举证 证明局部有效 -> 全局有效  4. 不要非要证明局部最优
 *
 *
 * note: 最早结束取哪个
 * @author qiu wanzi
 * @date 2024年3月11日 0011
 */
@Slf4j
public class Zuo11_MeetSchedule {



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
