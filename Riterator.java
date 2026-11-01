package lookandsay;

import java.util.Iterator;

/**
 * This Interface represents RIterator extends Iterator.
 */
public interface Riterator<T> extends Iterator<T> {

  /**
   * Return the current element T in the sequence,
   * and revert to the previous number in the sequence.
   * If reaches the end, stop there.
   *
   * @return the current element in the sequence and revert to the previous.
   */
  T prev();

  /**
   * Return true if there is previous element; false otherwise.
   *
   * @return true if there is previous element; false otherwise.
   */
  boolean hasPrevious();

}


