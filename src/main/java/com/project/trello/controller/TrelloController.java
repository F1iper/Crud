package com.project.trello.controller;

import com.project.trello.domain.CreatedTrelloCard;
import com.project.trello.domain.TrelloBoardDto;
import com.project.trello.domain.TrelloCardDto;
import com.project.trello.client.TrelloClient;
import com.project.trello.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin("*")
public class TrelloController {

    @Autowired
    private TrelloService trelloService;
    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();


//        return trelloClient.getTrelloBoards()
//                .stream()
//                .filter(board -> board.getId() != null && board.getName() != null)
//                .filter(board -> board.getName().contains("Kodilla"))
//                .collect(Collectors.toList());

    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createNewCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }
}
