import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.io.PrintWriter;

/**
 * A simple implementation of the heap data structure.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 * @author Your Name Here
 */
public class Heap<T> implements LinearStructure<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The comparator used to prioritize elements.
   */
  Comparator<T> priority;

  /**
   * The collection of values, organized as a heap.  We use an
   * ArrayList because Java makes generic arrays difficult to
   * use.
   */
  ArrayList<T> values;

  /**
   * The size of the heap. We don't use values.size() because we
   * want to permit situations in which only part of the ArrayList
   * is used for the heap (e.g., when doing Heapsort).
   */
  int size;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty, heap.
   */
  public Heap(Comparator<T> priority) {
    this.priority = priority;
    this.values = new ArrayList<T>();
    this.size = 0;
  } // Heap(Comparator<T>)

  /**
   * Create a heap from an ArrayList. Does *not* copy the ArrayList;
   * external changes to the ArrayList will affect the heap.
   */
  public Heap(ArrayList<T> values, Comparator<T> priority) {
    this.priority = priority;
    this.values = values;
    this.size = values.size();
    heapify();
  } // Heap(ArrayList<T>, Comparator<T>)

  // +------------------+--------------------------------------------
  // | Iterable methods |
  // +------------------+

  /**
   * Get an iterator for this heap.
   */
  public Iterator<T> iterator() {
    // We don't want people to remove, so we can't just use the
    // underlying iterator.
    return new Iterator<T>() {
      Iterator<T> it = Heap.this.values.iterator();

      public boolean hasNext() {
        return it.hasNext();
      } // hasNext()

      public T next() {
        return it.next();
      } // next()

      public void remove() {
        throw new UnsupportedOperationException("remove is unsupported");
      } // remove()
    }; // new Iterator<T>
  } // iterator()

  // +--------------------------+------------------------------------
  // | Linear structure methods |
  // +--------------------------+

  /**
   * Get and remove the highest-priority element.
   */
  public T get() throws Exception {
    if (this.isEmpty()) {
      throw new Exception("Empty heap");
    } // if
    T tmp = this.values.get(0);
    // Move the last value to the root
    this.values.set(0, this.values.get(--this.size));
    this.values.set(this.size, null);
    // Restore the heap property
    heapDown(0);
    // And we're done.
    return tmp;
  } // get()

  /**
   * Determine if the heap is empty.
   */
  public boolean isEmpty() {
    return this.size == 0;
  } // isEmpty()

  /**
   * Determine if the heap is full.
   */
  public boolean isFull() {
    return false;
  } // isFull()
  
  /**
   * Peek at the highest-priority element.
   */
  public T peek() throws Exception {
    if (this.isEmpty()) {
      throw new Exception("Empty heap");
    } // if
    return this.values.get(0);
  } // peek()

  /**
   * Add an element to the heap.
   */
  public void put(T value) {
    int pos = this.size++;
    if (pos < this.values.size()) {
      this.values.set(pos, value);
    } else {
      this.values.add(value);
    }
    heapUp(pos);
  } // put(T)

  // +----------------------+----------------------------------------
  // | Other public methods |
  // +----------------------+

  /**
   * Print the heap in a semi-readable form.
   */
  public void dump(PrintWriter pen) {
    this.dump(pen, 0, "  ");
  } // dump(PrintWriter)

  /**
   * Get the size of the heap.
   */
  public int size() {
    return this.size();
  } // size()

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Dump the heap rooted at position i, using the given indent.
   */
  public void dump(PrintWriter pen, int i, String indent) {
    if (i >= this.size) {
      pen.println(indent + "∅");
    } else {
      dump(pen, right(i), indent + "    ");
      pen.println(indent + this.values.get(i));
      dump(pen, left(i), indent + "    ");
    } // if/else
  } // dump(PrintWriter, int, String)

  /**
   * Restore a heap starting at position i by swapping down as
   * necessary.
   */
  void heapDown(int i) {
    int n = values.size() - 1;
    ArrayUtils.swap(values, i, n);
    boolean check = true;
    while (check){ //remember to update i
      if(priority.compare(values.get(i), values.get(left(i))) > 0){
        System.out.println("unimplemented");
      }else if (priority.compare(values.get(i), values.get(right(i))) > 0){
        System.out.println("unimplemented");
      }else{
        check = false;
      }
    }
  } // heapDown(i)

  /**
   * Restore a heap in which position i may be in the wrong place
   * with respect to its ancestors by swapping up as necessary.
   */
  void heapUp(int i) {

    if (i == 0){
      return;
    }
   
    if (priority.compare(values.get(parent(i)), values.get(i)) > 0){
      System.out.println("parent: " +parent(i)+ " i: " + i);
      ArrayUtils.swap(values, parent(i), i);
      heapUp(parent(i));
    }
    
  } // heapUp(i)

  /**
   * Turn the underlying array into a heap.
   */
  void heapify() {
    heapifyCLRS();
  } // heapify()

  /**
   * Turn the underlying array into a heap, using the technique from 
   * CLRS.
   */
  void heapifyCLRS() {
    for (int i = this.size/2; i >= 0; i--) {
      heapDown(i);
    } // for
  } // heapifyCLRS()

  /**
   * Turn the underlying array into a heap, using the "obvious" technique
   * suggested by SamR.
   */
  void heapifySamR() {
    // We can build the heap by, in effect, adding each element from
    // position 1 to the last position.
    int n = this.values.size();
    for (this.size = 2; this.size < n; this.size++) {
      heapUp(this.size - 1);
    } // for
  } // heapifySamR

  /**
   * Get the index of the left child of position i.
   */
  int left(int i) {
    return 2*i + 1;
  } // left(int)

  /**
   * Get the index of the parent of position i.
   */
  int parent(int i) {
    return (i-1)/2;
  } // parent(int)

  /**
   * Get the index of the right child of position i.
   */
  int right(int i) {
    return 2*i + 2;
  } // right(int)

} // class Heap<T>
