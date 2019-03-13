import data_structures.*;

public class P1Tester {
    private LinearListADT<Integer> list;

    public P1Tester() {
        list = new ArrayLinearList(10);
        runTests();
    }

    private void runTests() {
        for(int i=1; i <= 10; i++)
            list.addFirst(i);
        System.out.println("Should now print 10 .. 1");
        for(Integer i : list)
            System.out.println(i);
        for(int i=1; i <= 10; i++)
            if(list.removeFirst() == null)
                throw new RuntimeException("ERROR with removeFirst");
        for(Integer i : list)
            System.out.println(i);

        if(!list.isEmpty())
            throw new RuntimeException("ERROR in inEmpty");
        if(list.size() != 0)
            throw new RuntimeException("ERROR in size");

        for(int i=1; i <= 1000; i++) {
            for(int j=1; j <= 7; j++)
                list.addFirst(j);
            for(int j=1; j <= 7; j++)
                list.removeLast();
        }

        for(int i=1; i <= 1000; i++) {
            for(int j=1; j <= 7; j++)
                list.addLast(j);
            for(int j=1; j <= 7; j++)
                list.removeFirst();
        }

        list.addFirst(-1);
        if(list.peekLast() != -1)
            throw new RuntimeException("ERROR in peekLast");
        list.clear();
        list.addLast(-1);
        if(list.peekFirst() != -1)
            throw new RuntimeException("ERROR in peekLast");

        list.clear();
        for(int i=1; i <= 10; i++)
            list.addFirst(i);

        for(int i=1; i <= 10; i++)
            if(list.find(i) != i)
                throw new RuntimeException("ERROR in find");
            else
                System.out.println("FOUND " + i);

        for(int i=1; i <= 10; i++)
            if(!list.contains(i))
                throw new RuntimeException("ERROR in find");

        list.clear();
        for(int i=1; i <= 10; i++)
            list.addFirst(i);

        for(int i=1; i <= 5; i++) {
            Integer tmp = list.remove(i);
            if(tmp == null)
                System.out.println("ERROR in remove");
            else
                System.out.println("removed " + tmp);
        }
        for(Integer i : list)
            System.out.println(i);
    }

    public static void main(String [] args) {
        new P1Tester();
    }
}
