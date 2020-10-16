package com.project.tasks.service;

import static java.util.Optional.ofNullable;

import com.project.tasks.config.AdminConfig;
import com.project.tasks.domain.CreatedTrelloCardDto;
import com.project.tasks.domain.Mail;
import com.project.tasks.domain.TrelloBoardDto;
import com.project.tasks.domain.TrelloCardDto;
import com.project.tasks.trello.client.TrelloClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public CreatedTrelloCardDto createdTrelloCard(final TrelloCardDto trelloCardDto) {
    CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
    ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(
        adminConfig.getAdminMail(),
        SUBJECT,
        "New card: " + trelloCardDto.getName() + " has been created on your Trello account")
    ));
    return newCard;
  }
}
