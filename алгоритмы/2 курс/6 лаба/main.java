import java.util.ArrayList;

public class main {
    static class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    static class Map<K, V> {
        // массив цепочек
        private ArrayList<HashNode<K, V>> bucketArray;
        // Текущая емкость списка массивов
        private int numBuckets;
        // Текущий размер списка массивов
        private int size;
        public Map() {
            bucketArray = new ArrayList<>();
            numBuckets = 10;
            size = 0;
            // Создать пустые цепочки
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);
        }

        public int size() { return size; }
        public boolean isEmpty() { return size() == 0; }
        // Реализую хеш-функцию
        private int getBucketIndex(K key) {
            int hashCode = key.hashCode();
            float a = (float) (0.6180339887*hashCode);
            int res = (int)a;
            double res2 = a - res;
            int index = (int) (numBuckets*res2);
            return index;
        }

        public void add(K key, V value) {
            // Найти начало цепочки для данного ключа
            int bucketIndex = getBucketIndex(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);
            // Проверяем, присутствует ли ключ
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }
            // Вставляем ключ в цепочку
            size++;
            head = bucketArray.get(bucketIndex);
            HashNode<K, V> newNode = new HashNode<K, V>(key, value);
            newNode.next = head;
            bucketArray.set(bucketIndex, newNode);
        }

        public V delete(K key) {
            // Применяем хеш-функцию для поиска индекса для данного ключа
            int bucketIndex = getBucketIndex(key);
            // Получить голову цепи
            HashNode<K, V> head = bucketArray.get(bucketIndex);
            // Поиск ключа в его цепочке
            HashNode<K, V> prev = null;
            while (head != null)
            {
                // если ключ найден
                if (head.key.equals(key)) break;
                // Остальное продолжаем двигаться по цепочке
                prev = head;
                head = head.next;
            }
            // Если ключа там не было
            if (head == null) return null;
            // Уменьшаем размер
            size--;
            // Удалить ключ
            if (prev != null)  prev.next = head.next;
            else bucketArray.set(bucketIndex, head.next);
            return head.value;
        }

        public V find(K key) {
            // Найти начало цепочки для данного ключа
            int bucketIndex = getBucketIndex(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);
            // Поиск ключа в цепочке
            while (head != null) {
                if (head.key.equals(key)) return head.value;
                head = head.next;
            }
            return null;
        }

        public static void main(String[] args) {
            Map<Integer,String>map = new Map<>();
            map.add(1,"thмis" );
            map.add(1,"thмisй" );
            map.add(2,"coьder");
            map.add(3,"tнhis");
            map.add(4,"h1i");
            map.add(60,"hiг");
            map.add(25,"co2der");
            map.add(33,"thiйs");
            map.add(41,"hнi");
            map.add(60,"hаiг");
            map.add(27,"codаer");
            map.add(38,"thцis");
            map.add(46,"hйi");
            map.add(601,"hiуг");
            System.out.println(map.find(1));
            System.out.println(map.size());
            System.out.println(map.delete(1));
            System.out.println(map.delete(601));
            System.out.println(map.size());
            System.out.println(map.isEmpty());
            System.out.println(map.find(2));
        }
    }