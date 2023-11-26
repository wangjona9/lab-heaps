import java.io.PrintWriter;

/**
 * A quick experiment with heaps.
 *
 * @author Samuel A. Rebelsky
 */
public class HeapExperiment {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * How many elements do we add to the heap?
   */
  static final int ROUNDS = 32;

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  public static void main(String[] args) {
    // Prepare for output
    PrintWriter pen = new PrintWriter(System.out, true);

    // If we have command-line arguments, we use them to build our heap.
    if (args.length > 0) {
      experiment(pen, args);
    } // if
    // Otherwise, we build a heap of integers
    else {
      // Create an array of integers.
      Integer[] values = new Integer[ROUNDS];
      for (int i = 0; i < values.length; i++) {
        values[i] = i;
      } // for
      ArrayUtils.permute(values);
      // And run the experiment
      experiment(pen, values);
    } // else
  } // main(String[])

  // +------------+--------------------------------------------------
  // | Experiment |
  // +------------+

  /**
   * Run an experiment, building the heap with the values in the
   * array.
   */
  public static <T extends Comparable<T>> void experiment(PrintWriter pen, T[] values) {
    Heap<T> heap = new Heap<T>((x,y) -> x.compareTo(y));

    // Add all of the elements
    for (int i = 0; i < values.length; i++) {
      pen.println("Adding " + values[i]);
      heap.put(values[i]);
      pen.println("Heap is now ...");
      heap.dump(pen);
      pen.println();
    } // for

    // Remove all the elements
    try {
      while (! heap.isEmpty()) {
        pen.print("Removing ");
        pen.flush();
        T val = heap.peek();
        pen.println(val);
        heap.get();
        pen.println("Heap is now ...");
        heap.dump(pen);
        pen.println();
      } // while
    } catch (Exception e) {
      pen.println("Exception: " + e.toString());
    } // try/catch
  } // experiment()

} // class HeapExperiment
