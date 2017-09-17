package ua.dubok;

import java.util.Arrays;


/*
Custom implementation of HashMap with open addresation.

 * @param <Integer> the type of keys maintained by this map
 * @param <Long> the type of mapped values
 *
 * @author  Josh Bloch
 *
 * @see     Object#hashCode()
 * @see     Collection
 * @see     Map
 * @see     Hashtable
 */
public class MyHashMap<Integer, Long> {


    private int capacity;

    private int initialCapacity = 16;

    private Entry<Integer, Long> [] table;

    public MyHashMap(){
        table = new Entry[initialCapacity];
    }


    /* (non-Javadoc)
     * @see java.util.HashMap#put(K key, V value)
     */
    public void put(Integer key, Long value) {
        if(key == null){
            throw new NullPointerException("Null as a key is not allowed");
        }
        int probe = 0;
        if(capacity == initialCapacity) {
            initialCapacity *=2;
            table = Arrays.copyOf(table, initialCapacity);

        }

        Entry<Integer, Long> entry = new Entry<>(key, value);

        while (table[hash(key, probe)] != null) {
            if(table[hash(key,probe)].getKey().equals(key)){
                table[hash(key, probe)] = entry;
                return;
            } else {
                probe++;
            }
        }
        table[hash(key, probe)] = entry;
        capacity++;
    }

    /* (non-Javadoc)
    * @see java.util.HashMap#get(K key)
    */
    public Long get(Integer key) {
        int probe = 0;
        if(table[hash(key,probe)] == null){
            return null;
        }
        while (table[hash(key, probe)] != null) {
            if(!table[hash(key, probe)].getKey().equals(key)) {
                int hash = hash(key, probe);
                probe++;
            }
            else{
                return table[hash(key, probe)].getValue();
            }
        }
        return null;
    }

    /* (non-Javadoc)
    * @see java.util.HashMap#size()
    */
    public int size(){
        return capacity;
    }

    class Entry<K, V>{
        final K key;
        final V value;


        public Entry(K key, V value){
            this.key = key;
            this.value = value;

        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }


        @Override
        public String toString() {
            return "key = " + key + " value = " + value;
        }
    }

    private int hash(Object key, int probe){
        int hash = Math.abs(key.hashCode()) % initialCapacity;
        return (hash + probe);
    }

}


