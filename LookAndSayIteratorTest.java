import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.util.NoSuchElementException;
import lookandsay.LookAndSayIterator;
import org.junit.Test;

/**
 * Tests for LookAndSayIterator.
 */
public class LookAndSayIteratorTest {

  /**
   * Note: Add timeouts for large tests.
   */
  @Test
  public void testDefaultConstructor() {
    LookAndSayIterator iterator = new LookAndSayIterator();
    assertEquals(new BigInteger("1"), iterator.next());
  }

  @Test
  public void testSingleArgumentConstructor() {
    LookAndSayIterator iterator = new LookAndSayIterator(new BigInteger("21"));
    assertEquals(new BigInteger("21"), iterator.next());
  }

  @Test
  public void testTwoArgumentConstructor() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("1"),
        new BigInteger("1000")
    );
    assertEquals(new BigInteger("1"), iterator.next());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNegativeSeed() {
    new LookAndSayIterator(new BigInteger("-1"), new BigInteger("1000"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithZeroSeed() {
    new LookAndSayIterator(new BigInteger("0"), new BigInteger("1000"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithSeedContainingZero() {
    new LookAndSayIterator(new BigInteger("102"), new BigInteger("1000"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithSeedEqualToEnd() {
    new LookAndSayIterator(new BigInteger("100"), new BigInteger("100"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithSeedGreaterThanEnd() {
    new LookAndSayIterator(new BigInteger("1000"), new BigInteger("100"));
  }

  @Test
  public void testBasicForwardSequence() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("1"),
        new BigInteger("1000000")
    );
    assertEquals(new BigInteger("1"), iterator.next());
    assertEquals(new BigInteger("11"), iterator.next());
    assertEquals(new BigInteger("21"), iterator.next());
    assertEquals(new BigInteger("1211"), iterator.next());
    assertEquals(new BigInteger("111221"), iterator.next());
    assertEquals(new BigInteger("312211"), iterator.next());
  }

  @Test
  public void testForwardSequenceFromMiddle() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("21"),
        new BigInteger("1000000")
    );
    assertEquals(new BigInteger("21"), iterator.next());
    assertEquals(new BigInteger("1211"), iterator.next());
    assertEquals(new BigInteger("111221"), iterator.next());
  }

  @Test
  public void testHasNextAtBoundary() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("1"),
        new BigInteger("30")
    );
    assertTrue(iterator.hasNext());
    iterator.next();
    assertTrue(iterator.hasNext());
    iterator.next();
    assertTrue(iterator.hasNext());
    iterator.next();
    assertFalse(iterator.hasNext());
  }



  @Test
  public void testBasicBackwardSequence() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("312211"),
        new BigInteger("10000000")
    );
    assertEquals(new BigInteger("312211"), iterator.prev());
    assertEquals(new BigInteger("111221"), iterator.prev());
    assertEquals(new BigInteger("1211"), iterator.prev());
    assertEquals(new BigInteger("21"), iterator.prev());
    assertEquals(new BigInteger("11"), iterator.prev());
    assertEquals(new BigInteger("1"), iterator.prev());
  }

  @Test
  public void testHasPreviousFromStart() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("11"),
        new BigInteger("1000000")
    );
    assertTrue(iterator.hasPrevious());
    assertEquals(new BigInteger("11"), iterator.prev());
    assertFalse(iterator.hasPrevious());
  }

  @Test
  public void testHasPreviousWithOddDigits() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("123"),
        new BigInteger("1000000")
    );
    assertFalse(iterator.hasPrevious());
  }

  @Test(expected = NoSuchElementException.class)
  public void testPrevWhenNoPreviousElement() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("1"),
        new BigInteger("1000000")
    );
    iterator.prev();
  }

  @Test
  public void testForwardThenBackward() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("1"),
        new BigInteger("1000000")
    );
    assertEquals(new BigInteger("1"), iterator.next());
    assertEquals(new BigInteger("11"), iterator.next());
    assertEquals(new BigInteger("21"), iterator.next());
    assertEquals(new BigInteger("1211"), iterator.prev());
    assertEquals(new BigInteger("21"), iterator.prev());
    assertEquals(new BigInteger("11"), iterator.prev());
  }

  @Test
  public void testBackwardThenForward() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("1211"),
        new BigInteger("1000000")
    );
    assertEquals(new BigInteger("1211"), iterator.prev());
    assertEquals(new BigInteger("21"), iterator.prev());
    assertEquals(new BigInteger("11"), iterator.prev());
    assertEquals(new BigInteger("1"), iterator.next());
    assertEquals(new BigInteger("11"), iterator.next());
    assertEquals(new BigInteger("21"), iterator.next());
  }

  @Test
  public void testSequenceBeyondSeed() {
    LookAndSayIterator iterator = new LookAndSayIterator(
        new BigInteger("11"),
        new BigInteger("1000000")
    );
    assertTrue(iterator.hasPrevious());
    assertEquals(new BigInteger("11"), iterator.prev());
    assertEquals(new BigInteger("1"), iterator.prev());
    assertFalse(iterator.hasPrevious());
  }


}