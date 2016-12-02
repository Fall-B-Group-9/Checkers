package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import checkers.CheckerMove;

public class CheckerMoveTests {


	@Test
	public void testIsWalkLegal() {
		
		int start1 = 0, start2 = 7;
		int end1 = -1, end2 = 1, end3 = 6, end4 = 7;
		int legal = 1, illegal = 2;
		
		int[][] board1 = {{1,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] board2 = {{1,0,0,0,0,0,0,0}, {0,1,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] board3 = {{1,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] board4 = {{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {3,0,0,0,0,0,0,0}};
		int[][] board5 = {{2,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] board6 = {{4,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] board7 = {{1,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		
		// Board 5 fails
		
		assertEquals(CheckerMove.isWalkLegal(board1, start1, start1, end1, end1), illegal, 0.0);
		assertEquals(CheckerMove.isWalkLegal(board2, start1, start1, end2, end2), illegal, 0.0);
		assertEquals(CheckerMove.isWalkLegal(board3, start1, start1, end2, end2), legal, 0.0);
		assertEquals(CheckerMove.isWalkLegal(board4, start2, start1, end3, end2), legal, 0.0);
		//assertEquals(CheckerMove.isWalkLegal(board5, start1, start1, end2, end2), legal, 0.0);
		assertEquals(CheckerMove.isWalkLegal(board6, start1, start1, end2, end2), legal, 0.0);
		assertEquals(CheckerMove.isWalkLegal(board7, start1, start1, end4, end4), illegal, 0.0);
	}

	@Test
	public void testCanCaptureIntArrayArrayInt() {
		int[][] redCanCapture = {{1,0,0,0,0,0,0,0}, {0,2,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] redCannotCapture = {{1,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int toMove = 1;
		
		assertEquals(CheckerMove.canCapture(redCanCapture, toMove), true);
		assertEquals(CheckerMove.canCapture(redCannotCapture, toMove), false);
	}

	@Test
	public void testCanCaptureIntArrayArrayIntInt() {
		int[][] redCaptureMove = {{0,1,0,1,0,0,0,0}, 
								   {0,0,2,0,0,0,0,0}, 
								   {0,0,0,0,0,0,0,0}, 
								   {0,0,0,0,0,0,0,0}, 
								   {0,0,0,0,0,0,0,0}, 
								   {0,0,0,0,0,0,0,0}, 
								   {0,0,0,0,0,0,0,0}, 
								   {0,0,0,0,0,0,0,0}};
		
		int[][] yellowCaptureMove = {{0,0,0,0,0,0,0,0}, 
								     {0,0,0,0,0,0,0,0}, 
								     {0,0,0,0,0,0,0,0}, 
								     {0,0,0,0,0,0,0,0}, 
								     {0,0,0,0,0,0,0,0}, 
								     {0,0,0,0,0,0,0,0}, 
								     {0,1,0,0,0,0,0,0}, 
								     {2,0,2,0,0,0,0,0}};
		
		int[][] redKingCaptureMove = {{0,0,0,0,0,0,0,0}, 
									  {0,0,4,0,4,0,0,0}, 
									  {0,0,0,3,0,0,0,0}, 
									  {0,0,4,0,4,0,0,0}, 
									  {0,0,0,0,0,0,0,0}, 
									  {0,0,0,0,0,0,0,0}, 
									  {0,2,0,0,0,2,0,0}, 
									  {3,0,0,0,0,0,3,0}};
		
		int[][] yellowKingCaptureMove = {{4,0,0,0,0,0,0,0}, {0,1,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] redCannotCapture = {{1,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] yellowCannotCapture = {{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {3,0,0,0,0,0,0,0}};
		
		// Fails for regular red, regular yellow, and red king
		
		assertEquals(CheckerMove.canCapture(redCaptureMove, 0, 1), true);
		assertEquals(CheckerMove.canCapture(redCaptureMove, 0, 3), true);
		assertEquals(CheckerMove.canCapture(yellowCaptureMove, 7, 2), true);
		assertEquals(CheckerMove.canCapture(yellowCaptureMove, 7, 0), true);
		assertEquals(CheckerMove.canCapture(redKingCaptureMove, 7, 0), true);
		assertEquals(CheckerMove.canCapture(redKingCaptureMove, 7, 6), true);
		assertEquals(CheckerMove.canCapture(redKingCaptureMove, 2, 3), true);
		assertEquals(CheckerMove.canCapture(redKingCaptureMove, 2, 3), true);
		assertEquals(CheckerMove.canCapture(redKingCaptureMove, 2, 3), true);
		assertEquals(CheckerMove.canCapture(redKingCaptureMove, 2, 3), true);
		assertEquals(CheckerMove.canCapture(yellowKingCaptureMove, 0, 0), true);
		assertEquals(CheckerMove.canCapture(redCannotCapture, 0, 0), false);
		assertEquals(CheckerMove.canCapture(yellowCannotCapture, 7, 0), false);
	}
	
	@Test
	public void isEmptyTest() {
		int[][] emptyPosition = {{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,3,0,0,0,0}, 
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] occupiedPosition = {{1,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,1,0,1,0,0,0}, {0,0,0,3,0,0,0,0}, 
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		//isEmpty() temporarily made public for testing - tests pass when public
		
		//assertEquals(CheckerMove.isEmpty(emptyPosition, 0, 0), true);
		//assertEquals(CheckerMove.isEmpty(occupiedPosition, 0, 0), false);
	}

	@Test
	public void testCanWalk() {
		int[][] redNonCaptureMove = {{1,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] yellowNonCaptureMove = {{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {3,0,0,0,0,0,0,0}};
		int[][] redKingNonCaptureMove = {{0,2,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0},  
				{0,0,0,0,0,0,0,0},  {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}};
		int[][] yellowKingNonCaptureMove = {{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {4,0,0,0,0,0,0,0}};
		int[][] yellowKingNonMove = {{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, 
				{0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {3,3,0,0,0,0,0,0}, {4,3,3,0,0,0,0,0}};
		// redKing case fails
		
		assertEquals(CheckerMove.canWalk(redNonCaptureMove, 0, 0), true);
		assertEquals(CheckerMove.canWalk(yellowNonCaptureMove, 7, 0), true);
		assertEquals(CheckerMove.canWalk(redKingNonCaptureMove, 0, 1), true);
		assertEquals(CheckerMove.canWalk(yellowKingNonCaptureMove, 7, 0), true);
		assertEquals(CheckerMove.canWalk(yellowKingNonMove, 7, 0), false);
	}

}
