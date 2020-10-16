package com.project.tasks.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.project.tasks.domain.TrelloBoard;
import com.project.tasks.domain.TrelloBoardDto;
import com.project.tasks.domain.TrelloCard;
import com.project.tasks.domain.TrelloCardDto;
import com.project.tasks.domain.TrelloList;
import com.project.tasks.domain.TrelloListDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

  @Autowired
  private final TrelloMapper trelloMapper = new TrelloMapper();

  @Test
  public void shouldMapToBoards() {
    //Given
    List<TrelloListDto> theList = new ArrayList<>();
    List<TrelloBoardDto> boardsDtos = new ArrayList<>();
    boardsDtos.add(new TrelloBoardDto("1", "board", theList));

    //When
    List<TrelloBoard> result = trelloMapper.mapToBoards(boardsDtos);

    //Then
    assertEquals(1, result.size());
    assertEquals("1", result.get(0).getId());
    assertEquals("board", result.get(0).getName());
    assertEquals(0, result.get(0).getLists().size());
  }

  @Test
  public void shouldMapToBoardsDto() {
    //Given
    List<TrelloList> trelloLists = new ArrayList<>();
    List<TrelloBoard> trelloBoards = new ArrayList<>();
    trelloBoards.add(new TrelloBoard("1", "board", trelloLists));

    //When
    List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoards);

    //Then
    assertEquals("1", result.get(0).getId());
    assertEquals("board", result.get(0).getName());
    assertEquals(0, result.get(0).getLists().size());
  }

  @Test
  public void shouldMapToList() {
    //Given
    List<TrelloListDto> trelloListDtos = new ArrayList<>();
    trelloListDtos.add(new TrelloListDto("1", "list", false));

    //When
    List<TrelloList> result = trelloMapper.mapToList(trelloListDtos);

    //Then
    assertEquals(result.get(0).getId(), trelloListDtos.get(0).getId());
    assertEquals(result.get(0).getName(), trelloListDtos.get(0).getName());
    assertEquals(result.size(), trelloListDtos.size());
  }

  @Test
  public void shouldMapToListDto() {
    //Given
    List<TrelloList> trelloLists = new ArrayList<>();
    trelloLists.add(new TrelloList("1", "theList", false));

    //When
    List<TrelloListDto> result = trelloMapper.mapToListDto(trelloLists);

    //Then
    assertEquals(result.get(0).getId(), trelloLists.get(0).getId());
    assertEquals(result.get(0).getName(), trelloLists.get(0).getName());
    assertEquals(result.size(), trelloLists.size());
  }

  @Test
  public void shouldMapToCardDto() {
    //Given
    TrelloCard trelloCard = new TrelloCard("card", "desc", "pos", "1");

    //When
    TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);

    //Then
    assertEquals("card", result.getName());
    assertEquals("desc", result.getDescription());
    assertEquals("pos", result.getPos());
    assertEquals("1", result.getListId());
  }

  @Test
  public void shouldMapToCard() {
    //Given
    TrelloCardDto trelloCardDto = new TrelloCardDto("cardDto", "desc", "pos", "2");

    //When
    TrelloCard result = trelloMapper.mapToCard(trelloCardDto);

    //Then
    assertEquals("cardDto", result.getName());
    assertEquals("desc", result.getDescription());
    assertEquals("pos", result.getPos());
    assertEquals("2", result.getListId());
  }
}
