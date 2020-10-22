package com.project.tasks.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TrelloTest {

  @Test
  public void shouldGetBoard() {
    //Given
    Trello underTest = new Trello(1, 1);

    //When
    int testBoard = underTest.getBoard();

    //Then
    assertEquals(1, testBoard);
  }

  @Test
  public void getCard() {
    //Given
    Trello underTest = new Trello(1, 1);

    //When
    int testCard = underTest.getCard();

    //Then
    assertEquals(1, testCard);
  }
}
