package com.project.tasks.mapper;

import static java.util.stream.Collectors.toList;

import com.project.tasks.domain.TrelloBoard;
import com.project.tasks.domain.TrelloBoardDto;
import com.project.tasks.domain.TrelloCard;
import com.project.tasks.domain.TrelloCardDto;
import com.project.tasks.domain.TrelloList;
import com.project.tasks.domain.TrelloListDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TrelloMapper {

  public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto) {
    return trelloBoardDto.stream()
        .map(trelloBoard ->
            new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(),
                mapToList(trelloBoard.getLists())))
        .collect(toList());
  }

  public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
    return trelloBoards.stream()
        .map(trelloBoard ->
            new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(),
                mapToListDto(trelloBoard.getLists()))
        )
        .collect(toList());
  }

  public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
    return trelloListDto.stream()
        .map(trelloList -> new TrelloList(trelloList.getId(), trelloList.getName(),
            trelloList.isClosed()))
        .collect(toList());
  }

  public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
    return trelloLists.stream()
        .map(trelloList -> new TrelloListDto(trelloList.getId(), trelloList.getName(),
            trelloList.isClosed()))
        .collect(toList());
  }

  public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
    return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(),
        trelloCard.getListId());
  }

  public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
    return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(),
        trelloCardDto.getPos(), trelloCardDto.getListId());
  }
}
