import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * One of the criteria from the WCAG 2.1 acessibility guidelines.
 *
 * @author Samuel A. Rebelsky
 */
public class AccessibilityCriterion {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  static final String CRITERIA = "accessibility-criteria.txt";

  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /**
   * Get the standard criteria.
   */
  public static Iterable<AccessibilityCriterion> standardCriteria() {
    return new Iterable<AccessibilityCriterion>() {
      public Iterator<AccessibilityCriterion> iterator() {
        return new Iterator<AccessibilityCriterion>() {
          BufferedReader reader = null;
          String line = null;

          public boolean hasNext() {
            // Start the reader, if necessary
            if (null == reader) {
              try {
                reader = new BufferedReader(new FileReader(CRITERIA));
              } catch (Exception e) {
                return false;
              } // try/catch
            } // if there's no reader
    
            // Read the next line, if necessary
            if (null == line) {
              try {
                line = reader.readLine();
              } catch (Exception e) {
              } // try/catch
            } // if there is no cached line

            // If we still have a null, we're at the end of file.
            if (null == line) {
              try {
                reader.close();
              } catch (Exception e) {
              }
              return false;
            } // if we still have a null
    
            // If we've reached this point, we have a cached line,
            // so there is a next line.
            return true;
          } // hasNext()
    
          public AccessibilityCriterion next() {
            // hasNext should fill the cache.
            if (! hasNext()) {
              throw new NoSuchElementException("no next element");
            } // if there is no next element

            // At this point, we have a line, so we can parse it and return.
            AccessibilityCriterion ac = new AccessibilityCriterion(line);
            line = null;
            return ac;
          } // next()
        }; // new Iterator<AccessibilityCriterion>
      } // iterator()
    }; // new Iterable<AccessibilityCriterion>
  } // standardCriteria()

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The section number.
   */
  String section;

  /**
   * The title of the section.
   */
  String title;

  /**
   * The level (A, AA, or AAA).
   */
  String level;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new AC from the three component pieces.
   */
  public AccessibilityCriterion(String section, String title, String level) {
    this.section = section;
    this.title = title;
    this.level = level;
  } // AccessibilityCriterion(String, String, String)

  /**
   * Build a new AC from a tab-separated-value.
   */
  public AccessibilityCriterion(String info) {
    String[] parts = info.split("\t", 3);
    this.section = parts[0];
    this.title = parts[1];
    this.level = parts[2];
  } // AccessibilityCriterion(String)

  // +-------------------------+-------------------------------------
  // | Standard object methods |
  // +-------------------------+

  /**
   * Compare to another object.
   */
  public boolean equals(Object other) {
    return (other instanceof AccessibilityCriterion) 
        && this.equals((AccessibilityCriterion) other);
  } // equals(Object)

  /**
   * Compare to another AccessibilityCriterion.
   */
  public boolean equals(AccessibilityCriterion other) {
    return this.section.equals(other.section) 
        && this.title.equals(other.title) 
        && this.level.equals(other.level);
  } // equals(AccessibilityCriterion)

  /**
   * Compute the hashcode.
   */
  public int hashCode() {
    return this.section.hashCode() * 7 
        + this.title.hashCode() * 5
        + this.level.hashCode() * 3;
  } // hashCode()

  /**
   * Convert to a string for printing.
   */
  public String toString() {
    return this.section + " " + this.title + " (" + this.level + ")";
  } // toString()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the section.
   */
  public String section() {
    return this.section;
  } // section()

  /**
   * Get the title.
   */
  public String title() {
    return this.title;
  } // title()

  /**
   * Get the level.
   */
  public String level() {
    return this.level;
  } // level()

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Print out all the criteria.  (Intended primarily for testing.)
   */
  public static void main(String[] args) {
    for (AccessibilityCriterion ac : AccessibilityCriterion.standardCriteria()) {
      System.out.println(ac);
    } // for
  } // main(String[])

} // AccessibilityCriterion
