package com.odde.bbuddy.budget;

import ca.digitalcave.moss.common.DateUtil;

import java.util.Date;

public class BudgetCategoryTypeMonthly extends BudgetCategoryType {

	public Date getStartOfBudgetPeriod(Date date) {
		return DateUtil.getStartOfMonth(date);
	}

	public Date getEndOfBudgetPeriod(Date date) {
		return DateUtil.getEndOfMonth(date);
	}

	public Date getBudgetPeriodOffset(Date date, int offset) {
		return getStartOfBudgetPeriod(DateUtil.addMonths(DateUtil.getStartOfMonth(date), 1 * offset));
	}

	public long getDaysInPeriod(Date date) {
		return DateUtil.getDaysInMonth(date);
	}

	public String getDateFormat() {
		return "MMM yyyy";
	}

	public String getName() {
		return BudgetCategoryTypes.BUDGET_CATEGORY_TYPE_MONTH.toString();
	}
	@Override
	public String getKey() {
		return "MONTH";
	}
}
