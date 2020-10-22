package com.project.tasks.trello.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import com.project.tasks.domain.TrelloBoard;
import com.project.tasks.domain.TrelloBoardDto;
import com.project.tasks.domain.TrelloList;
import com.project.tasks.domain.TrelloListDto;
import com.project.tasks.mapper.TrelloMapper;
import com.project.tasks.service.TrelloService;
import com.project.tasks.trello.validator.TrelloValidator;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

  @InjectMocks
  private TrelloFacade trelloFacade;
  @Mock
  private TrelloService trelloService;
  @Mock
  private TrelloValidator trelloValidator;
  @Mock
  private TrelloMapper trelloMapper;


  @Test
  public void shouldFetchEmptyList() {
    //Given
    List<TrelloListDto> trelloLists = new ArrayList<>();
    List<TrelloBoardDto> trelloBoards = new ArrayList<>();
    List<TrelloList> mappedTrelloLists = new ArrayList<>();
    List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();

    trelloLists.add(new TrelloListDto("1", "listDto", false));
    trelloBoards.add(new TrelloBoardDto("1", "boardDto", trelloLists));
    mappedTrelloLists.add(new TrelloList("1", "list", false));
    mappedTrelloBoards.add(new TrelloBoard("1", "board", mappedTrelloLists));

    when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
    when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
    when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
    when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

    //When
    List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

    //Then
    assertNotNull(trelloBoardDtos);
    assertEquals(0, trelloBoardDtos.size());
  }

  @Test
  public void shouldFetchTrelloBoards() {
    //Given
    List<TrelloListDto> trelloLists = new ArrayList<>();
    List<TrelloBoardDto> trelloBoards = new ArrayList<>();
    List<TrelloList> mappedTrelloLists = new ArrayList<>();
    List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();

    trelloLists.add(new TrelloListDto("1", "theList", false));
    trelloBoards.add(new TrelloBoardDto("1", "theTask", trelloLists));
    mappedTrelloLists.add(new TrelloList("1", "theList", false));
    mappedTrelloBoards.add(new TrelloBoard("1", "theTask", mappedTrelloLists));

    when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
    when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
    when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
    when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

    //When
    List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

    //Then
    assertNotNull(trelloBoardDtos);
    assertEquals(1, trelloBoardDtos.size());

    trelloBoardDtos.forEach(trelloBoardDto -> {
      assertEquals("1", trelloBoardDto.getId());
      assertEquals("theTask", trelloBoardDto.getName());

      trelloBoardDto.getLists().forEach(trelloListDto -> {
        assertEquals("1", trelloListDto.getId());
        assertEquals("theList", trelloListDto.getName());
        assertEquals(false, trelloListDto.isClosed());
      });
    });


  }


}
