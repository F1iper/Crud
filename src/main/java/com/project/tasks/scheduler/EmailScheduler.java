package com.project.tasks.scheduler;

import com.project.tasks.repository.TaskRepository;
import com.project.trello.config.AdminConfig;
import com.project.trello.domain.Mail;
import com.project.trello.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminConfig adminConfig;

//    @Scheduled(cron = "0 0 10 * * *")
//    @Scheduled(fixedDelay = 10000)
//    public void sendInformationEmail() {
//        long size = taskRepository.count();
//        simpleEmailService.send(new Mail(
//                adminConfig.getAdminMail(),
//                SUBJECT,
//                "Currently in database you got: " + size + " tasks"
//        ));
//    }
}
