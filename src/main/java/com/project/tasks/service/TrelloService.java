package com.project.tasks.service;

import com.project.config.AdminConfig;
import com.project.tasks.domain.CreatedTrelloCard;
import com.project.tasks.domain.Mail;
import com.project.tasks.domain.TrelloBoardDto;
import com.project.tasks.domain.TrelloCardDto;
import com.project.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello card";

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private SimpleEmailService emailService;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCard createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "New card: " + trelloCardDto.getName() + " has been created on your Trello account")
        ));
        return newCard;
    }
}
