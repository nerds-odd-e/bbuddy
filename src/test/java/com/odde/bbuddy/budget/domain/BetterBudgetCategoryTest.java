package com.odde.bbuddy.budget.domain;

//import org.homeunix.thecave.buddi.model.impl.BudgetCategoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class BetterBudgetCategoryTest {

	private BudgetCategoryImpl bc = new BudgetCategoryImpl();
	private Date fourthOfJuly2011;
	private Date secondOfJuly2011;
	private Date twentyJune2011;
	private Date tenthMarch2011;
	private Date twentyApril2011;
	private Date twentyMay2011;
	
	@Before
	public void initializeDates() throws ParseException {
		fourthOfJuly2011 = new SimpleDateFormat("yyMMdd").parse("110704");
		secondOfJuly2011 = new SimpleDateFormat("yyMMdd").parse("110702");
		twentyJune2011 = new SimpleDateFormat("yyMMdd").parse("110620");
		tenthMarch2011 = new SimpleDateFormat("yyMMdd").parse("110310");
		twentyApril2011 = new SimpleDateFormat("yyMMdd").parse("110420");
		twentyMay2011 = new SimpleDateFormat("yyMMdd").parse("110520");
	}

	@Test (expected=RuntimeException.class)
	public void testBeginDateIsLaterThanStartDateThrowsAnException() throws Exception {
		bc.getAmount(fourthOfJuly2011 , secondOfJuly2011);
	}
	
	@Test
	public void getAmountOfARangeInsideABudgetPeriod() throws Exception {
		bc.setAmount(secondOfJuly2011, 31);
		assertEquals(3, bc.getAmount(secondOfJuly2011, fourthOfJuly2011));
	}

	@Test
	public void getAmountOfARangeTwoNeigbouringBudgetPeriods() throws Exception {
		bc.setAmount(twentyJune2011, 300);
		bc.setAmount(secondOfJuly2011, 31);
		assertEquals(114, bc.getAmount(twentyJune2011, fourthOfJuly2011));
	}
	
	@Test
	public void getAmountOfARangeWithManyBudgetPeriodsInBetween() throws Exception {
		bc.setAmount(tenthMarch2011, 31);
		bc.setAmount(twentyApril2011, 3000);
		bc.setAmount(twentyMay2011, 3100);
		bc.setAmount(twentyJune2011, 3000);
		bc.setAmount(secondOfJuly2011, 31);
		assertEquals(9100 + 2 + 22, bc.getAmount(tenthMarch2011, secondOfJuly2011));
	}
	
}
