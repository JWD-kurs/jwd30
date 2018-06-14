package jwd.wafepa.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {
	private ActivityService activityService;
	
	@Before
	public void setUp() {
		activityService = new InMemoryActivityService();
		
		activityService.save(new Activity("Swimming"));
		activityService.save(new Activity("Running"));
	}
	
	@Test
	public void testFindOne() {
		Activity a = activityService.findOne(1L);
		
		Assert.assertEquals("Swimming", a.getName());
	}
	
	@Test
	public void testFindAll() {
		List<Activity> acs = activityService.findAll();
		
		Assert.assertEquals(2, acs.size());
	}
}
