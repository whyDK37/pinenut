//
//import org.quartz.JobDetail;
//import org.quartz.Trigger;
//import org.springframework.scheduling.quartz.CronTriggerBean;
//import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//public class CronTimerTest {
//
//	private static int time = 0;
//
//	public static void main(String[] args) throws Exception {
//		String cronExpression = "0 */30 8-18 * * ?";
//		//job
//		MethodInvokingJobDetailFactoryBean job = new MethodInvokingJobDetailFactoryBean();
//		job.setTargetObject(new CronTimerTest());
//		job.setTargetMethod("timer");
//		job.setBeanName("timer");
//		job.afterPropertiesSet();
//
//		System.out.println("=="+job.getObjectType().getName());
//		//trigger
//		CronTriggerBean trigger = new CronTriggerBean();
//		trigger.setCronExpression(cronExpression);
//		trigger.setJobDetail((JobDetail) job.getObject());
//		trigger.setName("timer");
//		trigger.afterPropertiesSet();
//
//		//scheduler factory
//		Trigger[] triggers = new Trigger[1];
//		triggers[0] = trigger;
//		SchedulerFactoryBean factory = new SchedulerFactoryBean();
//		factory.setTriggers(triggers);
//		factory.afterPropertiesSet();
//		factory.start();
//
//		try {
//			while (true) {
//				System.out.println(time);
//				Thread.currentThread().sleep(1000);
//				if (time == 10) {
////					factory.cancel(true);
////					concurrentTaskScheduler.prefersShortLivedTasks();
//
//					factory.stop();
//					factory.destroy();
//					break;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void timer() {
//		System.out.println("timer " + time++);
//	}
//}
