import java.util.*;

public class LRUCache
{

	private class Node {
	    String key;
	    int value;
	    Node prev;
	    Node next;
 
	    public Node(String key, int value) {
		    this.key = key;
		    this.value = value;
    	}
	}

    int capacity;
    HashMap<String, Node> map;
    Node first = null;
    Node last = null;
 
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<String, Node>();
    }
 
    public int get(String key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setFirst(n);
            return n.value;
        }
 
        return -1;
    }

    public void set(String key, int value) {
        if(map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setFirst(old);
        }
        else {
            Node created = new Node(key, value);
            if(map.size() >= capacity) {
                map.remove(last.key);
                remove(last);
                setFirst(created);
 
            }
            else
                setFirst(created);
            map.put(key, created);
        }
    }

    public void printContents() {
    	for (Map.Entry entry : map.entrySet()) {
    		Node x = (Node) entry.getValue();
    		System.out.println(entry.getKey() + " , " + x.value);
    	}
    }
 
    private void remove(Node n) {
        if (n.prev != null)
            n.prev.next = n.next;
        else
            first = n.next;
 
        if (n.next != null)
            n.next.prev = n.prev;
        else
            last = n.prev;
 
    }
 
    private void setFirst(Node n) {
        n.next = first;
        n.prev = null;
 
        if (first != null)
            first.prev = n;
 
        first = n;
 
        if (last == null)
            last = first;
    }

    public static void main(String args[]) {
    	LRUCache lru = new LRUCache(5);
    	lru.set("F", 6);
    	lru.set("E", 5);
    	lru.set("D", 4);
    	lru.set("C", 3);
    	lru.set("B", 2);
    	lru.set("A", 1);
    	int x = lru.get("C");

    	lru.printContents();

    }
}
