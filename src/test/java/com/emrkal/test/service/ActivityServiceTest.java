/**
 * 
 */
package com.emrkal.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emrkal.enums.ToDoStatus;
import com.emrkal.model.Activity;
import com.emrkal.model.User;
import com.emrkal.repository.ActivityRepository;
import com.emrkal.repository.UserRepository;
import com.emrkal.service.ActivityService;

/**
 * ActivityService {@link com.emrkal.service.ActivityService} sınıfındaki
 * metodların testi yapılmaktadır.
 * <p>
 * 
 * @see ActivityServiceTest
 * @version 1.0
 * @author Emrullah KALKAN
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EntityScan(basePackages = { "com.emrkal.model" })
@EnableJpaRepositories(basePackages = { "com.emrkal.repository" })
public class ActivityServiceTest {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ActivityRepository activityRepository;

	private Activity activity;

	private User user;

	@Before
	public void createActivityData() {
		user = userRepository.findByUsername("EMRKAL");
		assertThat(user.getId()).isNotNull();

		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.add(Calendar.DAY_OF_MONTH, -5);
		endDate.add(Calendar.DAY_OF_MONTH, -2);
		activity = new Activity("Test Activity 1", startDate.getTime(), endDate.getTime(), ToDoStatus.TODO, "Test 1 Description", user);

		activity = activityService.saveActivity(activity, user);
		assertThat(activity).isNotNull();
		assertThat(activity.getId() > 0);
	}

	@Test
	public void findAllByUserOrderByEndDateAsc() {
		List<Activity> testActivityList = activityService.findAllByUserOrderByEndDateAsc(user);
		assertThat(testActivityList).isNotEmpty();
		assertThat(testActivityList.size()).isEqualTo(1);
	}

	@Test
	public void findActivityByIdAndUserAndUser() {
		Activity testActivity = activityService.findActivityByIdAndUserAndUser(activity.getId(), user);
		assertThat(testActivity).isNotNull();
		assertThat(testActivity).isEqualTo(activity);
		assertThat(testActivity.getUser().getId()).isEqualTo(user.getId());
	}

	@Test
	public void findActivityListByStartAndEndDateAndByActivityName() {
		List<Activity> testActivityList = activityService.findActivityListByStartAndEndDateAndByActivityName(activity.getStartDate(), activity.getEndDate(), activity.getActivityName(),
				ToDoStatus.TODO, user);
		assertThat(testActivityList).isNotEmpty();
		assertThat(testActivityList.size()).isEqualTo(1);
		assertThat(testActivityList.get(0).getActivityName()).isEqualTo(activity.getActivityName());
	}

	@Test
	public void findActivityListByStartAndEndDate() {
		List<Activity> testActivityList = activityService.findActivityListByStartAndEndDate(activity.getStartDate(), activity.getEndDate(), ToDoStatus.TODO, user);
		assertThat(testActivityList).isNotEmpty();
		assertThat(testActivityList.size()).isEqualTo(1);
		assertThat(testActivityList.get(0).getActivityName()).isEqualTo(activity.getActivityName());
	}

	@Test
	public void CountByStatus() {
		Long count = activityService.CountByStatus(ToDoStatus.TODO, user);
		assertThat(count == 1);
	}

	@After
	public void removeActivityTest() {
		activityRepository.delete(activity);
		Activity removeUserTest = activityRepository.findOne(activity.getId());
		assertThat(removeUserTest).isNull();

	}

}
