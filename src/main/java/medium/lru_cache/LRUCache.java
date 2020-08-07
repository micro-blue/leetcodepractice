package medium.lru_cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private int MAX_SIZE;
    private LinkedHashMap<Integer ,Integer> map;
    public LRUCache(int capacity) {
        MAX_SIZE=capacity;
        //这里注意要把accessOrder设为true
        this.map=new LinkedHashMap<Integer ,Integer>(capacity,075F,true){
            protected boolean removeEldestEntry(Map.Entry<Integer ,Integer>  eldest) {
                return size()>MAX_SIZE;
            }
        };

    }

    public int get(int key) {
        Integer res=  map.get(key);
        return res==null?-1:res;
    }

    public void put(int key, int value) {
        map.put(key,value);
    }

    public static void main(String[] args) {
        LRUCache cache=new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
