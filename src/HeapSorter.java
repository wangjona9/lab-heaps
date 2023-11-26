import java.util.Comparator;

/**
 * An out-of-place implementation of heapsort.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
 */
public class HeapSorter implements Sorter {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  public static final Sorter SORTER = new HeapSorter();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  private HeapSorter() { }

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort using an out-of-place heapsort.
   */
  public <T> void sort(T[] values, Comparator<? super T> order) {
    // Create a new heap in which the largest value is at the top
    Heap<T> heap = new Heap<T>((x,y) -> order.compare(y,x));
    // Add all the elements to the heap.
    for (int i = 0; i < values.length; i++) {
      heap.put(values[i]);
    } // for
    // Remove all the values from the heap, putting them back in values.
    for (int i = values.length-1; i >= 0; i--) {
      try {
        values[i] = heap.get();
      } catch (Exception e) {
      }
    } // for
  } // sort(T[], Comparator<T>)

} // class HeapSorter

