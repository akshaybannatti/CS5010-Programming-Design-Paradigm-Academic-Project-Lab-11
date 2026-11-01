package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;


/**
 * Class for a forward and backward iterator for the look and say sequence.
 */
public class LookAndSayIterator implements Riterator<BigInteger> {
  private BigInteger current;
  private BigInteger seed;
  private final BigInteger end;

  /**
   * Constructs a LookAndSayIterator.
   *
   * @throws IllegalArgumentException if validation doesn't work.
   */
  public LookAndSayIterator() {
    this(BigInteger.ONE, getDefaultEnd());
  }

  /**
   * Constructs a LookAndSayIterator with given seed and default last value 100.
   *
   * @param seed the starting number for the sequence.
   * @throws IllegalArgumentException if seed is not positive, and if it contains zeros.
   */
  public LookAndSayIterator(BigInteger seed) {
    this(seed, getDefaultEnd());
  }

  /**
   * Constructs a LookAndSayIterator with the given seed and end values.
   *
   * @param seed starting number of sequence
   * @param end  maximum value for the sequence
   * @throws IllegalArgumentException if seed is not positive, and if it contains zeroes.
   */
  public LookAndSayIterator(BigInteger seed, BigInteger end) {
    if (seed == null || end == null) {
      throw new IllegalArgumentException("Seed and end cannot be null");
    }
    if (seed.compareTo(BigInteger.ZERO) <= 0) {
      throw new IllegalArgumentException("Seed must be positive");
    }
    if (seed.compareTo(end) >= 0) {
      throw new IllegalArgumentException("Seed must be less than end");
    }
    if (containsZero(seed)) {
      throw new IllegalArgumentException("Seed cannot contain zeros");
    }
    this.seed = seed;
    this.current = seed;
    this.end = end;
  }

  /**
   * Returns the default end value.
   *
   * @return BigInteger 100 9s.
   */
  private static BigInteger getDefaultEnd() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 100; i++) {
      sb.append('9');
    }
    return new BigInteger(sb.toString());
  }

  /**
   * Checks if BigInteger has 0 in it.
   *
   * @param number for checking the number.
   * @return if zero then true if not then its false.
   */
  private boolean containsZero(BigInteger number) {
    String numStr = number.toString();
    return numStr.contains("0");
  }

  /**
   * Returns true if next number in sequence is less than end value.
   *
   * @return true if next element or it's, false otherwise.
   */
  @Override
  public boolean hasNext() {
    return current.compareTo(end) <= 0;
  }

  /**
   * Returns current number and goes to the next number in sequence.
   *
   * @return the current number
   * @throws NoSuchElementException if there is no next element present there.
   */
  @Override
  public BigInteger next() {
    if (!hasNext()) {
      return this.current;
    }
    BigInteger result = this.current;
    this.current = computeNext(this.current);
    return result;
  }

  /**
   * If true then return the value and if possible go back one step in the sequence.
   *
   * @return if the previous element is there then it's true otherwise it's false.
   */
  @Override
  public boolean hasPrevious() {
    try {
      computePrevious(current);
      return true;
    } catch (IllegalStateException e) {
      return false;
    }
  }

  /**
   * Returns the current number present and reverts it to the previous number.
   *
   * @return the current number
   * @throws NoSuchElementException if previous element does not exit.
   */
  @Override
  public BigInteger prev() {
    if (!hasPrevious() && current.equals(seed)) {
      throw new NoSuchElementException("No previous element in the sequence");
    }
    BigInteger result = current;
    if (hasPrevious()) {
      current = computePrevious(current);
    }
    return result;
  }

  /**
   * Computes the next number in sequence.
   *
   * @param num current number
   * @return next number in sequence
   */
  private BigInteger computeNext(BigInteger num) {
    String numStr = num.toString();
    StringBuilder result = new StringBuilder();
    int i = 0;
    while (i < numStr.length()) {
      char digit = numStr.charAt(i);
      int count = 1;
      while (i + count < numStr.length() && numStr.charAt(i + count) == digit) {
        count++;
      }
      result.append(count).append(digit);
      i += count;
    }

    return new BigInteger(result.toString());
  }

  /**
   * Computes previous number in look and say sequence.
   *
   * @param num current number
   * @return previous number in the sequence
   * @throws IllegalStateException if the number cannot be reversed.
   */
  private BigInteger computePrevious(BigInteger num) {
    String numStr = num.toString();
    if (numStr.length() % 2 != 0) {
      throw new IllegalStateException("Cannot compute previous: odd number of digits");
    }
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < numStr.length(); i += 2) {
      char countChar = numStr.charAt(i);
      char digitChar = numStr.charAt(i + 1);
      if (!Character.isDigit(countChar) || !Character.isDigit(digitChar)) {
        throw new IllegalStateException("Cannot compute previous: invalid format");
      }
      int count = countChar - '0';
      if (count == 0) {
        throw new IllegalStateException("Cannot compute previous: zero count encountered");
      }
      for (int j = 0; j < count; j++) {
        result.append(digitChar);
      }
    }
    return new BigInteger(result.toString());
  }
}