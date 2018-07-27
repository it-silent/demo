package com.hy.demo.common.utils;

import org.springframework.util.Assert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Times
 *
 * @author silent
 * @date 2018/4/22
 */
public class TimeUtils {

    public static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter FORMATTER_MONTH = DateTimeFormatter.ofPattern("yyyy-MM");

    public static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern("HH:mm");

    public static final DateTimeFormatter FORMATTER_DATE_STR = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static final ZoneId SYSTEM_ZONEID = ZoneId.systemDefault();


    /**
     * 当下
     *      请珍惜时间
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 明日
     */
    public static Date tomorrow() {
        return tomorrow(now());
    }

    /**
     * 明日
     */
    public static Date tomorrow(Date date) {
        return laterDays(date, 1);
    }

    /**
     * 昨日
     */
    public static Date yesterday() {
        return yesterday(now());
    }

    /**
     * 昨日
     */
    public static Date yesterday(Date date) {
        return soonerDays(date, 1);
    }

    /**
     * 几天后
     */
    public static Date laterDays(Date date, int plusDays) {
        return from(toLocalDateTime(date).plusDays(plusDays));
    }

    /**
     * 几天前
     */
    public static Date soonerDays(Date date, int minusDays) {
        return from(toLocalDateTime(date).minusDays(minusDays));
    }

    /**
     * 几分钟后
     */
    public static Date laterMinutes(Date date, int plusMinutes) {
        return from(toLocalDateTime(date).plusMinutes(plusMinutes));
    }

    /**
     * 几分钟前
     */
    public static Date soonerMinutes(Date date, int minusMinutes) {
        return from(toLocalDateTime(date).minusMinutes(minusMinutes));
    }

    /**
     * 几秒后
     */
    public static Date laterSeconds(Date date, int plusSeconds) {
        return from(toLocalDateTime(date).plusSeconds(plusSeconds));
    }

    /**
     * 零点
     */
    public static Date zero(Date date) {
        return zero(toLocalDate(date));
    }

    /**
     * 零点
     */
    public static Date zero(LocalDate date) {
        return from(date.atStartOfDay());
    }

    /**
     * 末点
     */
    public static Date end(Date date) {
        return end(toLocalDate(date));
    }

    /**
     * 末点
     */
    public static Date end(LocalDate date) {
        return from(LocalDateTime.of(date, LocalTime.MAX));
    }

    /**
     * 今日零点
     */
    public static Date zero() {
        return zero(now());
    }

    /**
     * 今日末点
     */
    public static Date end() {
        return end(now());
    }

    /**
     * 明日零点
     */
    public static Date tomorrowZero() {
        return zero(tomorrow());
    }

    /**
     * 明日零点(指定)
     */
    public static Date tomorrowZero(Date date) {
        return zero(tomorrow(date));
    }

    /**
     * 明日末点
     */
    public static Date tomorrowEnd() {
        return end(tomorrow());
    }

    /**
     * 明日末点(指定)
     */
    public static Date tomorrowEnd(Date date) {
        return end(tomorrow(date));
    }

    /**
     * 同天
     */
    public static boolean sameDay(Date oneTime, Date anotherTime) {
        return toLocalDate(oneTime).equals(toLocalDate(anotherTime));
    }

    /**
     * 格式化
     */
    public static String format(Date date) {
        return format(date, FORMATTER_DATE_TIME);
    }

    /**
     * 格式化
     */
    public static String format(Date date, String pattern) {
        return format(date, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化
     */
    public static String format(Date date, DateTimeFormatter formatter) {
        return toLocalDateTime(date).format(formatter);
    }

    /**
     * 格式化
     */
    public static String format(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * 格式化
     */
    public static String format(YearMonth month) {
        return month.format(FORMATTER_MONTH);
    }

    /**
     * 跨天集
     */
    public static LocalDate[] crossDay(LocalDate start, LocalDate end) {
        return crossDay(from(start), from(end));
    }

    /**
     * 跨天集
     */
    public static LocalDate[] crossDay(Date start, Date end) {
        Assert.isTrue(start.before(end));

        LocalDate startDate = toLocalDate(start);
        LocalDate endDate = toLocalDate(end);

        int plus = 0;
        Set<LocalDate> crossDays = new LinkedHashSet<>();
        for (;;) {
            LocalDate day = startDate.plusDays(plus++);
            crossDays.add(day);

            if (day.equals(endDate)) {
                break;
            }
        }

        return crossDays.toArray(new LocalDate[crossDays.size()]);
    }

    /**
     * 跨周集
     */
    public static String[] crossWeek(LocalDate start, LocalDate end) {
        return crossWeek(from(start), from(end));
    }

    /**
     * 跨周集
     */
    public static String[] crossWeek(Date start, Date end) {
        Assert.isTrue(start.before(end));

        LocalDate startDate = toLocalDate(start);
        LocalDate endDate = toLocalDate(end);

        int plus = 0;
        Set<String> crossWeeks = new LinkedHashSet<>();
        for (;;) {
            LocalDate day = startDate.plusWeeks(plus++);
            int week = day.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
            int weekYear = day.get (IsoFields.WEEK_BASED_YEAR);

            if (day.getYear() > endDate.getYear() || (day.getYear() == endDate.getYear() && day.getDayOfYear() > endDate.getDayOfYear())) {
                break;
            }
            crossWeeks.add(weekYear + "-" + (week < 10 ? "0" + week : week + ""));
        }

        return crossWeeks.toArray(new String[crossWeeks.size()]);
    }

    /**
     * 跨月集
     */
    public static String[] crossMonth(Date start, Date end) {
        Assert.isTrue(start.before(end));

        LocalDate startDate = toLocalDate(start);
        LocalDate endDate = toLocalDate(end);

        int plus = 0;
        Set<String> crossMonths = new LinkedHashSet<>();
        for (;;) {
            LocalDate day = startDate.plusMonths(plus++);
            int month = day.getMonthValue();
            int year = day.getYear();

            if (day.getYear() > endDate.getYear() || (day.getYear() == endDate.getYear() && month > endDate.getMonthValue())) {
                break;
            }
            crossMonths.add(year + "-" + (month < 10 ? "0" + month : month + ""));
        }

        return crossMonths.toArray(new String[crossMonths.size()]);
    }

    /**
     * 跨月集
     */
    public static String[] crossMonth(LocalDate start, LocalDate end) {
        return crossMonth(from(start), from(end));
    }

    /**
     * 时间差值(分钟)
     */
    public static long diffMinutes(Date oneTime, Date anotherTime){
        return TimeUnit.SECONDS.toMinutes(diffSeconds(oneTime, anotherTime));
    }

    /**
     * 时间差值(分钟)
     */
    public static long diffMinutes(Temporal oneTime, Temporal anotherTime) {
        return TimeUnit.SECONDS.toMinutes(diffSeconds(oneTime, anotherTime));
    }

    /**
     * 时间差值(秒)
     */
    public static long diffSeconds(Date oneTime, Date anotherTime){
        return diffSeconds(oneTime.toInstant(), anotherTime.toInstant());
    }

    /**
     * 时间差值(秒)
     */
    public static long diffSeconds(Temporal oneTime, Temporal anotherTime){
        return Math.abs(Duration.between(oneTime, anotherTime).getSeconds());
    }

    /**
     * 给定时间是否为周末
     */
    public static boolean isWeekend(Date date) {
        DayOfWeek dow = LocalDateTime.ofInstant(date.toInstant(), SYSTEM_ZONEID).getDayOfWeek();

        return dow == DayOfWeek.SUNDAY || dow == DayOfWeek.SATURDAY;
    }

    /**
     * 当前所在周日期
     */
    public static String[] week() {
        LocalDate today = LocalDate.now();
        int todayWeek = LocalDate.now().getDayOfWeek().getValue();
        LocalDate monday = today.minusDays(todayWeek - 1);

        String[] week = new String[7];
        IntStream.range(0, 7).forEach(index -> week[index] = monday.plusDays(index).toString());

        return week;
    }

    /**
     * 时间段按天分区
     */
    public static Date[][] split(Date start, Date end) {
        Assert.isTrue(start.before(end));
        if (sameDay(start, end)) {
            return new Date[][] {new Date[] {start, end}};
        } else {
            LocalDate[] crossDay = crossDay(start, end);

            Date[][] parts = new Date[crossDay.length][];
            parts[0] = new Date[] {start, end(start)};
            for (int i = 1; i < parts.length - 1; i++) {
                parts[i] = new Date[] {zero(crossDay[i]), end(crossDay[i])};

            }
            parts[parts.length - 1] = new Date[] {zero(end), end};

            return parts;
        }
    }

    /**
     * 判断给定字符时间是否为给定格式(HH:ss)
     */
    public static boolean matchTimeFormat(String time) {
        return matchTimeFormat(time, FORMATTER_TIME);
    }

    /**
     * 判断给定字符时间是否为给定格式
     */
    public static boolean matchTimeFormat(String time, DateTimeFormatter DateTimeFormatter) {
        try {
            return LocalTime.parse(time, DateTimeFormatter).format(DateTimeFormatter).equals(time);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断给定字符时间是否为给定格式(yyyy-MM-dd)
     */
    public static boolean matchFormat(String day) {
        return matchFormat(day, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * 判断给定字符时间是否为给定格式
     */
    public static boolean matchFormat(String day, DateTimeFormatter DateTimeFormatter) {
        try {
            return LocalDate.parse(day, DateTimeFormatter).format(DateTimeFormatter).equals(day);
        } catch (Exception e) {
            return false;
        }
    }










    /**
     * Form (LocalDate + LocalTime) -> To (Date) 
     */
    public static Date from(LocalDate date, LocalTime time) {
        return Date.from(LocalDateTime.of(date, time).atZone(SYSTEM_ZONEID).toInstant());
    }

    /**
     * Form (LocalDate) -> To (Date)
     */
    public static Date from(LocalDate date) {
        return Date.from(LocalDateTime.of(date, LocalTime.now()).atZone(SYSTEM_ZONEID).toInstant());
    }

    /**
     * Form (LocalDateTime) -> To (Date) 
     */
    public static Date from(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(SYSTEM_ZONEID).toInstant());
    }

    /**
     * From (Date) TO (LocalDate)
     */
    public static LocalDate toLocalDate(Date date) {
        return LocalDate.from(date.toInstant().atZone(SYSTEM_ZONEID));
    }

    /**
     * From (Date) TO (LocalTime)
     */
    public static LocalTime toLocalTime(Date date) {
        return LocalTime.from(date.toInstant().atZone(SYSTEM_ZONEID));
    }

    /**
     * From (Date) TO (LocalDateTime)
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.from(date.toInstant().atZone(SYSTEM_ZONEID));
    }
}
