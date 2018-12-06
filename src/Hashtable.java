import java.util.ArrayList;
import java.lang.Math;

public class Hashtable<K,V>{
        class HashNode<K,V>{
            String key;
            String value;
            public HashNode<K,V> next;
            public HashNode(String key, String value){
                this.key = key;
                this.value = value;
                this.next = null;
            }
        }
        private HashNode[] buckets;
        private int num_buckets;
        private int size;

        public Hashtable(){
            num_buckets = 250000;
            size = 0;
            buckets = new HashNode[num_buckets];
            for(int i = 0; i < num_buckets; i++){
                buckets[i]=null;
            }
        }

        public int size(){ return size;}

        public boolean isEmpty(){ return size()==0;}

        private int getBucket(String key){
            int code = Math.abs(key.hashCode());
            code%= num_buckets;
            return code;
        }

        public Object find(String key){
            int bucket_id = getBucket(key);
            HashNode node = buckets[bucket_id];
            while(node!=null){
                if(node.key.equals(key)){
                    return node.value;
                }
                node = node.next;
            }
            return null; //if not found
        }
        public void add(String key, String value){
            int bucket_id = getBucket(key);
            HashNode node = buckets[bucket_id];
            while(node!=null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }
            size++;
            node = buckets[bucket_id];
            HashNode newnode = new HashNode(key, value);
            newnode.next = node;
            buckets[bucket_id] = newnode;
        }

        public String remove(String key){
            int bucket_id = getBucket(key);
            HashNode head = buckets[bucket_id];
            HashNode prev = null;
            while(head !=null && !head.key.equals(key)){
                //if( head.key.equals(key))
                  //  break;
                prev = head;
                head = head.next;
            }
            if( head.key.equals(key)) {
                if (head == null) {
                    return null;
                }
                if (prev == null) {
                    buckets[bucket_id] = head.next;

                    size--;
                } else {
                    prev.next = head.next;
                }
            }
            return head.value;
        }

        public boolean containsKey(String key){
            int bucket_id = getBucket(key);
            HashNode node = buckets[bucket_id];
            while(node!=null){
                if(node.key.equals(key)){
                    return true;
                }
                node = node.next;
            }
            return false; //if not found
        }
}

