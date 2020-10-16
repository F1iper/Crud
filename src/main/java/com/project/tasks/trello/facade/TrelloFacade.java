package com.project.tasks.trello.facade;

import com.project.tasks.domain.CreatedTrelloCardDto;
import com.project.tasks.domain.TrelloBoard;
import com.project.tasks.domain.TrelloBoardDto;
import com.project.tasks.domain.TrelloCard;
import com.project.tasks.domain.TrelloCardDto;
import com.project.tasks.mapper.TrelloMapper;
import com.project.tasks.service.TrelloService;
import com.project.tasks.trello.validator.TrelloValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrelloFacade {

  private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

  private final TrelloService trelloService;
  private final TrelloMapper trelloMapper;
  private final TrelloValidator trelloValidator;

  public List<TrelloBoardDto> fetchTrelloBoards() {
    List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
    List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
    return trelloMapper.mapToBoardsDto(filteredBoards);
  }

  public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
    TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
    trelloValidator.validateCard(trelloCard);
    return trelloService.createdTrelloCard(trelloMapper.mapToCardDto(trelloCard));
  }
}
