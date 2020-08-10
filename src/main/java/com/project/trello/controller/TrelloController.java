package com.project.trello.controller;

import com.project.domain.CreatedTrelloCard;
import com.project.domain.TrelloBoardDto;
import com.project.domain.TrelloCardDto;
import com.project.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin("*")
public class TrelloController {


    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();


//        return trelloClient.getTrelloBoards()
//                .stream()
//                .filter(board -> board.getId() != null && board.getName() != null)
//                .filter(board -> board.getName().contains("Kodilla"))
//                .collect(Collectors.toList());

    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createNewCard(@RequestBody TrelloCardDto trelloCardDto) {

        return trelloClient.createNewCard(trelloCardDto);
    }
}
