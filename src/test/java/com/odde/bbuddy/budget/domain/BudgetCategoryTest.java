package com.odde.bbuddy.budget.domain;

import ca.digitalcave.moss.common.DateUtil;
import com.odde.bbuddy.budget.BudgetCategoryImpl;
import com.odde.bbuddy.budget.BudgetCategoryType;
import com.odde.bbuddy.budget.BudgetCategoryTypeMonthly;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BudgetCategoryTest {

	@Test
	public void testBudgetCategory(){
		try {
			BudgetCategoryType bct = new BudgetCategoryTypeMonthly();
			BudgetCategoryImpl bc = new BudgetCategoryImpl();
			bc.setPeriodType(bct);
			bc.setAmount(DateUtil.getDate(2007, Calendar.APRIL, 1), 100);
			bc.setAmount(DateUtil.getDate(2007, Calendar.MAY, 1), 200);
			bc.setAmount(DateUtil.getDate(2007, Calendar.JUNE, 1), 240);
			bc.setAmount(DateUtil.getDate(2007, Calendar.JULY, 1), 10);
			bc.setAmount(DateUtil.getDate(2007, Calendar.AUGUST, 1), 130);
			bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 1), 13);
			bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 333);
			bc.setAmount(DateUtil.getDate(2007, Calendar.NOVEMBER, 1), 331);

			assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 1)), 1);
			assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 10)), 1);
			assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 28)), 1);

			assertEquals((double) 300, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 1), DateUtil.getDate(2007, Calendar.MAY, 31)), 1);
			assertEquals((double) 149, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 15), DateUtil.getDate(2007, Calendar.MAY, 15)), 1);

		}
		catch (Exception e){
			fail("Exception: " + e);
		}
	}
	
}
