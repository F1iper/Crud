package com.project.tasks.trello.validator;

import com.project.tasks.domain.TrelloBoard;
import com.project.tasks.domain.TrelloCard;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TrelloValidator {

  private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

  public void validateCard(final TrelloCard trellocard) {
    if (trellocard.getName().contains("test")) {
      LOGGER.info("Someone is testing my application");
    } else {
      LOGGER.info("Seems that my application is used in proper way.");
    }
  }

  public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards) {
    LOGGER.info("Starting filtering boards...");
    List<TrelloBoard> filteredBoards = trelloBoards.stream()
        .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("test"))
        .collect(Collectors.toList());
    LOGGER.info("Boards has been filtered. Current list size: " + filteredBoards.size());
    return filteredBoards;
  }

}
