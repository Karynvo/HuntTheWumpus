/*
 * @author: Jennifer Tran and Karyn Vo
 * The test unit class for WumpusMap.
 */

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class WumpusMapTest {

	private final static CellState W = CellState.Wumpus;
	private final static CellState B = CellState.Blood;
	private final static CellState S = CellState.Slime;
	private final static CellState P = CellState.Pit;
	private final static CellState G = CellState.Goop;
	private final static CellState E = CellState.Empty;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testStaticMapHidden() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(8);
		map.setCHunter(6);
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [O] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());
	}

	@Test
	public void testStaticMapShown() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(8);
		map.setCHunter(6);
		map.showMap();
		assertEquals("[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n"
				+ "[ ] [S] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n"
				+ "[S] [P] [S] [ ] [ ] [B] [ ] [ ] [ ] [ ]\n"
				+ "[ ] [S] [ ] [ ] [B] [B] [B] [ ] [ ] [ ]\n"
				+ "[ ] [ ] [ ] [B] [B] [W] [B] [B] [ ] [ ]\n"
				+ "[ ] [ ] [ ] [ ] [B] [B] [B] [ ] [ ] [ ]\n"
				+ "[ ] [ ] [S] [ ] [ ] [B] [ ] [ ] [S] [ ]\n"
				+ "[ ] [S] [P] [S] [ ] [ ] [ ] [S] [P] [S]\n"
				+ "[ ] [ ] [S] [ ] [ ] [ ] [O] [ ] [S] [ ]\n"
				+ "[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n", map.toString());
	}

	@Test
	public void testHunterMoves() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(8);
		map.setCHunter(6);

		assertTrue(map.getState(8, 6).equals("O"));
		assertFalse(map.move("UP"));
		assertTrue(map.getRHunter() == 7);
		assertTrue(map.getCHunter() == 6);
		assertTrue(map.getState(7, 6).equals("O"));
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [O] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [ ] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());

		assertFalse(map.move("doWn"));
		assertTrue(map.getRHunter() == 8);
		assertTrue(map.getCHunter() == 6);
		assertTrue(map.getState(8, 6).equals("O"));
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [ ] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [O] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());

		assertFalse(map.move("Left"));
		assertTrue(map.getRHunter() == 8);
		assertTrue(map.getCHunter() == 5);
		assertTrue(map.getState(8, 5).equals("O"));
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [ ] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [O] [ ] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());

		assertFalse(map.move("right"));
		assertTrue(map.getRHunter() == 8);
		assertTrue(map.getCHunter() == 6);
		assertTrue(map.getState(8, 6).equals("O"));
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [ ] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [ ] [O] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());

		assertFalse(map.move("down"));

		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [ ] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [ ] [ ] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [O] [X] [X] [X]\n", map.toString());

		assertFalse(map.move("down"));

		assertEquals("[X] [X] [X] [X] [X] [X] [O] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [ ] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [ ] [ ] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [ ] [X] [X] [X]\n", map.toString());
	}

	@Test
	public void testHunterMovesIntoSlime() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(3);
		map.setCHunter(2);
		map.getState(3, 2);
		assertFalse(map.move("up"));
		assertEquals("Beware! You are standing in Slime. A Pit is near!",
				outContent.toString().trim());
	}

	@Test
	public void testHunterMovesIntoGoop() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, S, E },
				{ E, E, E, B, B, W, B, G, P, S },
				{ E, E, E, E, B, B, B, E, S, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(3);
		map.setCHunter(7);
		map.getState(3, 7);
		assertFalse(map.move("down"));
		assertEquals(
				"Beware! You are standing in Goop. The Wumpus and a Pit are near!",
				outContent.toString().trim());
	}

	@Test
	public void testHunterMovesIntoBlood() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(3);
		map.setCHunter(3);
		map.getState(3, 3);
		assertFalse(map.move("right"));
		assertEquals("Beware! You are standing in Blood. The Wumpus is near!",
				outContent.toString().trim());
		assertFalse(map.move("right"));
	}

	@Test
	public void testHunterMovesIntoPitAndDies() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(3);
		map.setCHunter(2);
		map.getState(3, 2);
		assertFalse(map.move("up"));
		assertEquals("Beware! You are standing in Slime. A Pit is near!",
				outContent.toString().trim());
		assertTrue(map.move("left"));
		//assertEquals("Beware! You are standing in Slime. A Pit is near!\n"+"\n\nNoo! You lost because you moved to a Pit!", outContent.toString().trim());
		//matches, but \n \r do not match
	}

	@Test
	public void testHunterMovesIntoWumpusAndDies() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(3);
		map.setCHunter(3);
		map.getState(3, 3);
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [O] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());
		assertFalse(map.move("right"));
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [ ] [O] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());
		assertEquals("Beware! You are standing in Blood. The Wumpus is near!",
				outContent.toString().trim());
		assertFalse(map.move("right"));
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [ ] [B] [O] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());
		assertTrue(map.move("down"));
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [ ] [B] [B] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [O] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());
	}

	@Test
	public void testHunterShootsMissesAndDiesRight() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(3);
		map.setCHunter(3);
		map.getState(3, 3);
		map.shoot("right");
		assertEquals("Sorry! You lost because you shot the arrow to yourself!",
				outContent.toString().trim());
	}

	@Test
	public void testHunterShootsMissesAndDiesDown() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(3);
		map.setCHunter(3);
		map.getState(3, 3);
		map.shoot("down");
		assertEquals("Sorry! You lost because you shot the arrow to yourself!",
				outContent.toString().trim());
	}

	@Test
	public void testHunterShootsWumpusUp() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(1);
		map.setCHunter(5);
		map.setRWumpus(4);
		map.setCWumpus(5);
		map.getState(1, 5);
		map.shoot("up");
		assertEquals("Yayy! You won because you shot the Wumpus!", outContent
				.toString().trim());
	}

	@Test
	public void testHunterShootsWumpusLeft() {
		CellState[][] m1 = { { E, E, E, E, E, E, E, E, E, E },
				{ E, S, E, E, E, E, E, E, E, E },
				{ S, P, S, E, E, B, E, E, E, E },
				{ E, S, E, E, B, B, B, E, E, E },
				{ E, E, E, B, B, W, B, B, E, E },
				{ E, E, E, E, B, B, B, E, E, E },
				{ E, E, S, E, E, B, E, E, S, E },
				{ E, S, P, S, E, E, E, S, P, S },
				{ E, E, S, E, E, E, E, E, S, E },
				{ E, E, E, E, E, E, E, E, E, E }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(4);
		map.setCHunter(0);
		map.setRWumpus(4);
		map.setCWumpus(5);
		map.getState(4, 0);
		map.shoot("left");
		assertEquals("Yayy! You won because you shot the Wumpus!", outContent
				.toString().trim());
	}

	@Test
	public void testWraparoundWumpusAndPit() {
		CellState[][] m1 = { { B, S, E, E, E, E, E, E, E, E },
				{ G, P, S, E, E, E, E, E, E, E },
				{ E, S, E, E, S, S, E, E, E, E },
				{ E, E, E, S, P, P, S, E, E, E },
				{ E, E, E, E, S, S, E, E, E, E },
				{ E, E, E, E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E, E, E, S },
				{ G, E, E, E, E, E, E, E, S, P },
				{ B, B, E, E, E, E, E, E, E, G },
				{ W, B, B, E, E, E, E, E, B, B }, };
		WumpusMap map = new WumpusMap(m1);
		map.setRHunter(6);
		map.setCHunter(0);
		map.setRWumpus(9);
		map.setCWumpus(0);
		map.getState(6, 0);
		assertEquals("[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[O] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X]\n", map.toString());
		map.shoot("down");
		assertEquals("Yayy! You won because you shot the Wumpus!", outContent
				.toString().trim());
		map.showMap();
		assertEquals("[B] [S] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n"
				+ "[G] [P] [S] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n"
				+ "[ ] [S] [ ] [ ] [S] [S] [ ] [ ] [ ] [ ]\n"
				+ "[ ] [ ] [ ] [S] [P] [P] [S] [ ] [ ] [ ]\n"
				+ "[ ] [ ] [ ] [ ] [S] [S] [ ] [ ] [ ] [ ]\n"
				+ "[ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]\n"
				+ "[O] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [S]\n"
				+ "[G] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [S] [P]\n"
				+ "[B] [B] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [G]\n"
				+ "[W] [B] [B] [ ] [ ] [ ] [ ] [ ] [B] [B]\n", map.toString());
	}

	@Test
	public void testRandomize() {
		WumpusMap map1 = new WumpusMap(10, 10);
		map1.setMap();
		WumpusMap map2 = new WumpusMap(10, 10);
		map2.setMap();
		assertFalse(map1.toString().equals(map2.toString()));
	}
}
