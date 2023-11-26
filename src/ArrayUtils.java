import java.util.ArrayList;
import java.util.Random;

/**
 * Some assorted utilities for working with arrays.
 *
 * @author Samuel A. Rebelsky
 */
public class ArrayUtils {
  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Permute the values in an array.
   */
  public static <T> void permute(T[] values) {
    Random rand = new Random();
    for (int i = 0; i < values.length; i++) {
      swap(values, i, rand.nextInt(values.length));
    } // for
  } // permute(T[])

  /**
   * Swap the values in positions i and j in an array.
   */
  public static <T> void swap(T[] values, int i, int j) {
    T tmp = values[i];
    values[i] = values[j];
    values[j] = tmp;
  } // swap(T[], int, int)

  /**
   * Swap the values in position i and j in an ArrayList.
   */
  public static <T> void swap(ArrayList<T> values, int i, int j) {
    T tmp = values.get(i);
    values.set(i, values.get(j));
    values.set(j, tmp);
  } // swap(ArrayList<T>, int, int)

} // class ArrayUtils
