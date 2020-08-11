package com.project.service;

import com.project.domain.CreatedTrelloCard;
import com.project.domain.Mail;
import com.project.domain.TrelloBoardDto;
import com.project.domain.TrelloCardDto;
import com.project.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello card";
    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCard createTrelloCard(final TrelloCardDto trelloCardDto) {

        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        emailService.send(new Mail(
                "",
                SUBJECT,
                "New card: " + trelloCardDto.getName() + " has been created on your Trello account")
        );

        return newCard;
    }
}
