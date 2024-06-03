package com.reviewer.reviewer.services;

import com.reviewer.reviewer.models.Dashboard;
import com.reviewer.reviewer.models.Form;
import com.reviewer.reviewer.repositories.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;

    public Dashboard addNewValueFormSent(Form form){
        var dashboard = dashboardRepository.findByFormId(form.getId());
        if(dashboard == null){
                var newDashboard = new Dashboard(1,0,form);
                dashboardRepository.save(newDashboard);
                return newDashboard;
        }
        dashboard.setFormQuantitySent(dashboard.getFormQuantitySent() + 1);
        dashboardRepository.save(dashboard);
        return dashboard;
    }
    public Dashboard addNewValueFormAnsweredSent(Form form){
        var dashboard = dashboardRepository.findByFormId(form.getId());
        if(dashboard == null){
            var newDashboard = new Dashboard(1,0,form);
            dashboardRepository.save(newDashboard);
            return newDashboard;
        }
        dashboard.setQuantityAnsweredFormSent(dashboard.getQuantityAnsweredFormSent() + 1);
        dashboardRepository.save(dashboard);
        return dashboard;
    }
}
