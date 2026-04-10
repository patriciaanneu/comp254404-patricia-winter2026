package Ex2;

public class SortedTableMapTest {

    public static void main(String[] args) {
        SortedTableMap<Integer, String> map = new SortedTableMap<>();

        map.put(1950, "Cinderella");
        map.put(1989, "Ariel");
        map.put(2005, null);

        System.out.println("containKey(1950): " + map.containKey(1950));
        System.out.println("containKey(1989): " + map.containKey(1989));
        System.out.println("containKey(2013): " + map.containKey(2013));
        System.out.println("containKey(2005): " + map.containKey(2005));

        System.out.println("get(1950): " + map.get(1950));
        System.out.println("get(2005): " + map.get(2005));
        //key does not exist
        System.out.println("get(2013): " + map.get(2013));
    }
}
