package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Sam Bigham
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
    // +-----------+---------------------------------------------------
    // | Constants |
    // +-----------+

    /**
     * The default capacity of the initial array.
     */
    static final int DEFAULT_CAPACITY = 16;

    // +--------+------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The size of the associative array (the number of key/value pairs).
     */
    public int size;

    /*
     * the capacity of the expandable array
     * default is 16, but it can be expanded
     */
    public int capacity;

    /**
     * The array of key/value pairs.
     */
    public KVPair<K, V> pairs[];

    // +--------------+------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new, empty associative array.
     */
    @SuppressWarnings({ "unchecked" })
    public AssociativeArray() {
        // Creating new arrays is sometimes a PITN.
        this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
                DEFAULT_CAPACITY);
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    } // AssociativeArray()

    // +------------------+--------------------------------------------
    // | Standard Methods |
    // +------------------+

    public AssociativeArray<K, V> clone() {
        AssociativeArray<K, V> aa = new AssociativeArray<>();
        for (int i = 0; i < size; i++) {
            aa.set(pairs[i].key, pairs[i].value);
        }//for
        return aa;
    } // clone()

    /**
     * Convert the array to a string
     */
    public String toString() {
        if (size == 0) {
            String str = "{}";
            return str;
        }//if

        String str = "{ ";
        for (int i = 0; i < size; i++) {
            if (pairs[i] != null) {
                if (i == size - 1) {
                    str = str.concat(pairs[i].key.toString() + ": " + pairs[i].value.toString());
                } else {
                    str = str.concat(pairs[i].key.toString() + ": " + pairs[i].value.toString() + ", ");
                }//else
            } else if (pairs[i] == null) {
                str = str.concat("Null ");
            }//else if
        }
        str = str.concat(" }");
        return str;
    }// toString

    // +----------------+----------------------------------------------
    // | Public Methods |
    // +----------------+

    /**
     * Set the value associated with key to value. Future calls to
     * get(key) will return value.
     */
    public void set(K key, V value) {

        if (size == this.capacity) {
            expand();
            pairs[size] = new KVPair<>(key, value);
            size++;
        }//if

        if (size == 0) {
            pairs[0] = new KVPair<>(key, value);
        } else {
            for (int i = 0; i < size; i++) {
                if (pairs[i] != null) {
                    if (pairs[i].key == key) {
                        pairs[i] = new KVPair<>(key, value); // if they find a key value it sets it to the new one and
                                                             // leaves the function
                        return;
                    }//if
                }//if
            }//for

            pairs[size] = new KVPair<>(key, value);

        }//else

        size++;

    } // set(K,V)

    /**
     * Get the value associated with key.
     *
     * @throws KeyNotFoundException
     *                              when the key does not appear in the associative
     *                              array.
     */
    public V get(K key) throws KeyNotFoundException {
        for (int i = 0; i < size; i++) {
            // System.out.println(pairs[i].key);
            // System.out.println(key);
            if (pairs[i].key == key) {
                return pairs[i].value;
            }
            if (String.valueOf(pairs[i].key).compareTo(String.valueOf(key)) == 0) {
                return pairs[i].value;
            }
        }//for
        throw new KeyNotFoundException();
    } // get(K)

    // || pairs[i].key == String.valueOf(key)

    /**
     * Determine if key appears in the associative array.
     */
    public boolean hasKey(K key) {
        for (int i = 0; i < size; i++) {
            if (pairs[i] != null) {
                if (pairs[i].key == key) {
                    return true;
                }//if
            }//if
        }
        return false; // if the for loop couldn't find the key
    } // hasKey(K)

    /**
     * Remove the key/value pair associated with a key. Future calls
     * to get(key) will throw an exception. If the key does not appear
     * in the associative array, does nothing.
     */
    public void remove(K key) {
        for (int i = 0; i < size; i++) {
            if (pairs[i] != null) {
                if (pairs[i].key == key || pairs[i].key == "1") {
                    pairs[i].key = pairs[size - 1].key;
                    pairs[i].value = pairs[size - 1].value;
                    pairs[size - 1] = null;
                    size--;
                }//if
            }//if
        }//for
    } // remove(K)

    /**
     * Determine how many values are in the associative array.
     */
    public int size() {
        return this.size;
    } // size()

    public int capacity() {
        return this.capacity();
    } // size()
      // +-----------------+---------------------------------------------
      // | Private Methods |
      // +-----------------+

    /**
     * Expand the underlying array.
     */
    public void expand() {
        this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
        this.capacity = this.capacity * 2;
    } // expand()

    /**
     * Find the index of the first entry in `pairs` that contains key.
     * If no such entry is found, throws an exception.
     */
    public int find(K key) throws KeyNotFoundException {
        for (int i = 0; i < size; i++) {
            if (pairs[i].key == key) {
                return ((Integer) (pairs[i].value));
            }//if
        }//for
        throw new KeyNotFoundException();
    } // find(K)

    /*
     * returns a String[] of all the current key values
     */
    public String[] getKeys(){
        String[] strarr = new String[size];
        for(int i = 0; i < size; i++){
            strarr[i] = pairs[i].key.toString();
            //System.out.println(strarr[i]);
        }
        
        return strarr;
    }

    /*
     * returns a String[] of all the current key values in the AA
     */
    public String[] getImageLocs(){
        String[] strarr = new String[this.size];
        
        for(int i = 0; i < this.size; i++){
            strarr[i] = (String) this.pairs[i].key;
        }
        return strarr;
    }
    /*
     * returns a string[] of all the current values in the AA
     */
    public String[] getCategories(){
        String[] strarr = new String[this.size];
        for(int i = 0; i < this.size; i++){
            strarr[i] = (String) this.pairs[i].value;
            if (strarr[i].charAt(0) == ' '){
                strarr[i] = strarr[i].substring(1, strarr[i].length());
            }
        }
        return strarr;
    }

} // class AssociativeArray