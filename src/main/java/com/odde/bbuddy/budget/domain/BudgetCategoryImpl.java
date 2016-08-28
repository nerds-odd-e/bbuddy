package com.odde.bbuddy.budget.domain;

import ca.digitalcave.moss.common.DateUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class BudgetCategoryImpl {

    private BudgetCategoryType periodType;
    private Map<String, Long> amounts;

    public long getAmount(Date startDate, Date endDate) {
        if (startDate.after(endDate))
            throw new RuntimeException("Start date cannot be before End Date!");

        Logger.getLogger(this.getClass().getName()).info("Starting to calculate the budgeted amount for period between " + startDate + " and " + endDate + ".");

        //If Start and End are in the same budget period
        if (getBudgetPeriodType().getStartOfBudgetPeriod(startDate).equals(
                getBudgetPeriodType().getStartOfBudgetPeriod(endDate))) {
//			Logger.getLogger().info("Start Date and End Date are in the same period.");
            long amount = getAmount(startDate);
//			Logger.getLogger().info("Amount = " + amount);
            long daysInPeriod = getBudgetPeriodType().getDaysInPeriod(startDate);
//			Logger.getLogger().info("Days in Period = " + daysInPeriod);
            long daysBetween = DateUtil.getDaysBetween(startDate, endDate, true);
//			Logger.getLogger().info("Days Between = " + daysBetween);

//			Logger.getLogger().info("Returning " + (long) (((double) amount / (double) daysInPeriod) * daysBetween));
//			Logger.getLogger().info("Finished calculating the budget amount.\n\n");
            return (long) (((double) amount / (double) daysInPeriod) * daysBetween);
        }

        //If the area between Start and End overlap at least two budget periods.
        if (getBudgetPeriodType().getBudgetPeriodOffset(startDate, 1).equals(
                getBudgetPeriodType().getStartOfBudgetPeriod(endDate))
                || getBudgetPeriodType().getBudgetPeriodOffset(startDate, 1).before(
                getBudgetPeriodType().getStartOfBudgetPeriod(endDate))) {
//			Logger.getLogger().info("Start Date and End Date are in different budget periods.");
            long amountStartPeriod = getAmount(startDate);
//			Logger.getLogger().info("Amount Start Period = " + amountStartPeriod);
            long daysInStartPeriod = getBudgetPeriodType().getDaysInPeriod(startDate);
//			Logger.getLogger().info("Days in Start Period = " + daysInStartPeriod);
            long daysAfterStartDateInStartPeriod = DateUtil.getDaysBetween(startDate, getBudgetPeriodType().getEndOfBudgetPeriod(startDate), true);
//			Logger.getLogger().info("Days After Start Date in Start Period = " + daysAfterStartDateInStartPeriod);
            double totalStartPeriod = (((double) amountStartPeriod / (double) daysInStartPeriod) * daysAfterStartDateInStartPeriod);
//			Logger.getLogger().info("Total in Start Period = " + totalStartPeriod);

            double totalInMiddle = 0;
            for (String periodKey : getBudgetPeriods(
                    getBudgetPeriodType().getBudgetPeriodOffset(startDate, 1),
                    getBudgetPeriodType().getBudgetPeriodOffset(endDate, -1))) {
                totalInMiddle += getAmount(getPeriodDate(periodKey));
                Logger.getLogger(this.getClass().getName()).info("Added " + getAmount(getPeriodDate(periodKey)) + " to total for one period in between; current value is " + totalInMiddle);
            }
//			Logger.getLogger().info("Total in Middle = " + totalInMiddle);

            long amountEndPeriod = getAmount(endDate);
//			Logger.getLogger().info("Amount End Period = " + amountEndPeriod);
            long daysInEndPeriod = getBudgetPeriodType().getDaysInPeriod(endDate);
//			Logger.getLogger().info("Days in End Period = " + daysInEndPeriod);
            long daysBeforeEndDateInEndPeriod = DateUtil.getDaysBetween(getBudgetPeriodType().getStartOfBudgetPeriod(endDate), endDate, true);
//			Logger.getLogger().info("Days before End Period = " + daysBeforeEndDateInEndPeriod);
            double totalEndPeriod = (long) (((double) amountEndPeriod / (double) daysInEndPeriod) * daysBeforeEndDateInEndPeriod);
//			Logger.getLogger().info("Total in End Period = " + totalEndPeriod);

//			Logger.getLogger().info("Sum of Start Period, Middle, and End Period = " + (totalStartPeriod + totalInMiddle + totalEndPeriod));
//			Logger.getLogger().info("Finished Calculating the Budget Amount\n\n");
            return (long) (totalStartPeriod + totalInMiddle + totalEndPeriod);
        }

        throw new RuntimeException("You should not be here.  We have returned all legitimate numbers from getAmount(Date, Date) in BudgetCategoryImpl.  Please contact Wyatt Olson with details on how you got here (what steps did you perform in Buddi to get this error message).");
    }

    public BudgetCategoryType getBudgetPeriodType() {
        if (getPeriodType() == null)
            setPeriodType(new BudgetCategoryTypeMonthly());
        return getPeriodType();
    }

    public long getAmount(Date periodDate){
        Long l = getAmounts().get(getPeriodKey(periodDate));
        if (l == null)
            return 0;
        return l;
    }

    public List<String> getBudgetPeriods(Date startDate, Date endDate){
        List<String> budgetPeriodKeys = new LinkedList<String>();

        Date temp = getBudgetPeriodType().getStartOfBudgetPeriod(startDate);

        while (temp.before(getBudgetPeriodType().getEndOfBudgetPeriod(endDate))){
            budgetPeriodKeys.add(getPeriodKey(temp));
            temp = getBudgetPeriodType().getBudgetPeriodOffset(temp, 1);
        }

        return budgetPeriodKeys;
    }

    private String getPeriodKey(Date periodDate){
        Date d = getBudgetPeriodType().getStartOfBudgetPeriod(periodDate);
        return getBudgetPeriodType().getName() + ":" + DateUtil.getYear(d) + ":" + DateUtil.getMonth(d) + ":" + DateUtil.getDay(d);
    }

    private Date getPeriodDate(String periodKey){
        String[] splitKey = periodKey.split(":");
        if (splitKey.length == 4){
            int year = Integer.parseInt(splitKey[1]);
            int month = Integer.parseInt(splitKey[2]);
            int day = Integer.parseInt(splitKey[3]);
            return getBudgetPeriodType().getStartOfBudgetPeriod(DateUtil.getDate(year, month, day));
        }

        throw new DataModelProblemException("Cannot parse date from key " + periodKey);
    }

    public BudgetCategoryType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(BudgetCategoryType periodType) {
        this.periodType = periodType;
    }

    public Map<String, Long> getAmounts() {
        if (amounts == null)
            amounts = new HashMap<String, Long>();
        return amounts;
    }

    public void setAmount(Date periodDate, long amount){
        getAmounts().put(getPeriodKey(periodDate), amount);
    }
}
