import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.Key;

/**
 * Experiments with our AssociativeArray class.
 *
 * @author Sam Bigham
 * @Source Samuel A. Rebelsky
 */
public class AssociativeArrayExperiments {

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run the experiments.
   */
  public static void main(String[] args) {
   PrintWriter pen = new PrintWriter(System.out, true);

    divider(pen);
    experimentStringsToStrings(pen);
    divider(pen);
    experimentBigIntToBigInt(pen);
    divider(pen);
    nullExperiment(pen);
    divider(pen);
  } // main(String[])

  // +-------------+-------------------------------------------------
  // | Experiments |
  // +-------------+

  /*
   * tests null cases for the associative array
   */
  public static void nullExperiment(PrintWriter pen){
    AssociativeArray<String, String> nullTest = new ReportingAssociativeArray<String, String>("nullTest", pen);
    nullTest.size();
    try {
      nullTest.get(null);
    }catch(Exception e){
    }
    nullTest.remove(null);
    nullTest.set(null, null);
    nullTest.hasKey(null);
  }

  /**
   * Our first experiment: Associative arrays with strings as both keys
   * and values.
   */
  public static void experimentStringsToStrings(PrintWriter pen) {
    AssociativeArray<String, String> s2s = new ReportingAssociativeArray<String, String>("s2s", pen);
    s2s.size();
    s2s.set("a", "apple");
    s2s.set("A", "aardvark");
    s2s.size();
    s2s.hasKey("a");
    s2s.hasKey("A");
    try {
      s2s.get("a");
    } catch (Exception e) {
    }//catch
    try {
      s2s.get("A");
    } catch (Exception e) {
    }//catch
    s2s.remove("a");
    s2s.size();
    try {
      s2s.get("a");
    } catch (Exception e) {
    }//catch
    try {
      s2s.get("A");
    } catch (Exception e) {
    }//catch
    s2s.remove("aardvark");
    s2s.size();
    try {
      s2s.get("a");
    } catch (Exception e) {
    }//catch
    try {
      s2s.get("A");
    } catch (Exception e) {
    }//catch
  } // expreimentStringsToStrings

  /**
   * Our second experiment: Associative arrays with big integers as
   * keys and values.
   */
  public static void experimentBigIntToBigInt(PrintWriter pen) {
    AssociativeArray<BigInteger, BigInteger> b2b = new ReportingAssociativeArray<BigInteger, BigInteger>("b2b", pen);

    // Set some values
    for (int i = 0; i < 11; i++) {
      b2b.set(BigInteger.valueOf(i), BigInteger.valueOf(i * i));
    } // for
    b2b.size();

    // Then get them
    for (int i = 0; i < 11; i++) {
      try {
        b2b.get(BigInteger.valueOf(i));
      } catch (Exception e) {
      }//catch
    } // for
    b2b.size();

    // Then remove some of them
    for (int i = 1; i < 11; i += 2) {
      b2b.remove(BigInteger.valueOf(i));
    } // for
    b2b.size();

    // Then see what happens when we get them
    for (int i = 0; i < 11; i++) {
      try {
        b2b.get(BigInteger.valueOf(i));
      } catch (Exception e) {
      }//catch
    } // for
    b2b.size();

    // Then reset or set some values
    for (int i = 0; i < 11; i += 3) {
      b2b.set(BigInteger.valueOf(i), BigInteger.valueOf(i + 10));
    } // for
    b2b.size();

    // Then see what happens when we get them
    for (int i = 0; i < 11; i++) {
      try {
        b2b.get(BigInteger.valueOf(i));
      } catch (Exception e) {
      }//catch
    } // for
    b2b.size();
  }

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Print a divider.
   */
  public static void divider(PrintWriter pen) {
    pen.println();
    pen.println("------------------------------------------------");
    pen.println();
  } // divider(PrintWriter)

} // Associativearr8ayExperiments