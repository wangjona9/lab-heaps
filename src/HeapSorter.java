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

  private HeapSorter() { 
  } // HeapSorter()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort using an out-of-place heapsort.
   */
  public <T> void sort(T[] values, Comparator<? super T> order) {
    // Put all the values in an ArrayList.
    ArrayList<T> lst = new ArrayList<T>();
    for (int i = 0; i < values.length; i++) {
      lst.add(values[i]);
    } // for

    // Create a new heap in which the largest value is at the top
    Heap<T> heap = new Heap<T>(lst, (x,y) -> order.compare(y,x));

    // Remove all the values from the heap, putting them back in values.
    // TODO: Fill in the details
  } // sort(T[], Comparator<T>)

} // class HeapSorter

