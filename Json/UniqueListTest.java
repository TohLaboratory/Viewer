import java.util.*;

public class UniqueListTest {
    public static void main(String[] args) {
        List<String> nonUniqueList = new ArrayList<String>();
        nonUniqueList.add("ab");
        nonUniqueList.add("b");
        nonUniqueList.add("b");
        nonUniqueList.add("a");
        nonUniqueList.add("123");
        nonUniqueList.add("a");
        nonUniqueList.add("a");

        for (String s : nonUniqueList) {
            System.out.println("nonUniqueList element = " + s);
        }

        Set<String> set = new HashSet<String>();
        set.addAll(nonUniqueList);
        List<String> uniqueList = new ArrayList<String>();
        uniqueList.addAll(set);

        for (String s : uniqueList) {
            System.out.println("uniqueList element = " + s);
        }
    }
}
