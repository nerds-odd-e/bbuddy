package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.MonthlyBudget;
import com.odde.bbuddy.budget.domain.MonthlyBudgetPlanner;
import com.odde.bbuddy.budget.view.PresentableAddMonthlyBudget;
import com.odde.bbuddy.budget.view.PresentableMonthlyBudgetAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;

import static com.odde.bbuddy.common.Formats.DAY;
import static com.odde.bbuddy.common.controller.ControllerHelper.thenSetMessage;
import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGET_ADD;
import static com.odde.bbuddy.common.controller.Urls.MONTHLYBUDGET_TOTALAMOUNT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@PropertySource("classpath:resultMessages.properties")
public class MonthlyBudgetController {

    private final MonthlyBudgetPlanner planner;
    private final PresentableMonthlyBudgetAmount presentableMonthlyBudgetAmount;
    private final PresentableAddMonthlyBudget presentableAddMonthlyBudget;

    @Value("${monthlybudget.add.success}")
    String successMessage;

    @Value("${monthlybudget.add.failed}")
    String failedMessage;

    @Autowired
    public MonthlyBudgetController(MonthlyBudgetPlanner planner, PresentableMonthlyBudgetAmount presentableMonthlyBudgetAmount, PresentableAddMonthlyBudget presentableAddMonthlyBudget) {
        this.planner = planner;
        this.presentableMonthlyBudgetAmount = presentableMonthlyBudgetAmount;
        this.presentableAddMonthlyBudget = presentableAddMonthlyBudget;
    }

    @RequestMapping(value = MONTHLYBUDGET_ADD, method = POST)
    public String submitAddMonthlyBudget(
            @Valid @ModelAttribute MonthlyBudget monthlyBudget,
            BindingResult result,
            Model model) {
        if (!result.hasFieldErrors())
            planner.addMonthlyBudget(monthlyBudget)
                    .success(thenSetMessage(model, successMessage))
                    .failed(thenSetMessage(model, failedMessage));
        return addMonthlyBudget(model);
    }

    @RequestMapping(value = MONTHLYBUDGET_ADD, method = GET)
    public String addMonthlyBudget(Model model) {
        presentableAddMonthlyBudget.display(model);
        return MONTHLYBUDGET_ADD;
    }

    @RequestMapping(value = MONTHLYBUDGET_TOTALAMOUNT, method = GET)
    public String totalAmountOfMonthlyBudget(
            @RequestParam("startDate") @DateTimeFormat(pattern = DAY) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = DAY) Date endDate,
            Model model) {
        presentableMonthlyBudgetAmount.display(model, planner.getAmount(startDate, endDate));
        return MONTHLYBUDGET_TOTALAMOUNT;
    }

}
