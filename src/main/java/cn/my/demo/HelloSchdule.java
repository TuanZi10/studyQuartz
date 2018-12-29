package cn.my.demo;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Flash on 2018/12/29.
 */
@Slf4j
public class HelloSchdule {
    public static void main(String[] args) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).
                withIdentity("myjob1","group1").build();


        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger","grou1")
                .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever()).build();


        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);

        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time {}" + simpleDateFormat.format(d));
    }
}
