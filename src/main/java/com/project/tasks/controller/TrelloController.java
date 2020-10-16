package com.project.tasks.controller;

import com.project.tasks.domain.CreatedTrelloCardDto;
import com.project.tasks.domain.TrelloBoardDto;
import com.project.tasks.domain.TrelloCardDto;
import com.project.tasks.trello.facade.TrelloFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TrelloController {

  private final TrelloFacade trelloFacade;

  @GetMapping
  public List<TrelloBoardDto> getTrelloBoards() {
    return trelloFacade.fetchTrelloBoards();
  }

  @PostMapping
  public CreatedTrelloCardDto createTrelloCartCard(@RequestBody TrelloCardDto trelloCardDto) {
    return trelloFacade.createCard(trelloCardDto);
  }
}
