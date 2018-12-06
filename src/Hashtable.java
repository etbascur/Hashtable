import java.util.ArrayList;

public class Hashtable<K,V> {
        class HashNode<K,V>{
            K key;
            V value;
            public HashNode<K,V> next;
            public HashNode(K key, V value){
                this.key = key;
                this.value = value;
                this.next = null;
            }
        }
        private ArrayList<HashNode<K,V>> buckets;
        private int num_buckets;
        private int size;

        public Hashtable(){
            num_buckets = 20270;
            size = 0;
            buckets = new ArrayList<>();
            for(int i = 0; i < num_buckets; i++){
                buckets.add(null);
            }
        }

        public int size(){ return size;}

        public boolean isEmpty(){ return size()==0;}

        private int getBucket(K key){
            int code = key.hashCode();
            code%= num_buckets;
            return code;
        }

        public Object find(K key){
            int bucket_id = getBucket(key);
            HashNode<K,V> node = buckets.get(bucket_id);
            while(node!=null){
                if(node.key.equals(key)){
                    return node.value;
                }
                node = node.next;
            }
            return null; //if not found
        }
        public void add(K key, V value){
            int bucket_id = getBucket(key);
            HashNode<K,V> node = buckets.get(bucket_id);
            while(node!=null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }
            size++;
            node = buckets.get(bucket_id);
            HashNode<K,V> newnode = new HashNode<>(key, value);
            newnode.next = node;
            buckets.set(bucket_id, newnode);
        }

        public V remove(K key){
            int bucket_id = getBucket(key);
            HashNode<K,V> head = buckets.get(bucket_id);
            HashNode<K,V> prev = null;
            while(head !=null){
                if( head.key==key)
                    break;
                prev = head;
                head = head.next;
            }
            if(head == null){
                return null;
            }
            size--;
            if(prev != null){
                prev.next =head.next;
            }
            else{
                buckets.set(bucket_id, head.next);
            }
            return head.value;
        }

        public boolean containsKey(K key){
            int bucket_id = getBucket(key);
            HashNode<K,V> node = buckets.get(bucket_id);
            while(node!=null){
                if(node.key.equals(key)){
                    return true;
                }
                node = node.next;
            }
            return false; //if not found
        }
}

