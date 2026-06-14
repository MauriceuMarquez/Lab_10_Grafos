package listlinked;

public class TestListLinked {
    public static void main(String[] args) {

        ListLinked<Integer> lista = new ListLinked<>();

        lista.addLast(10);
        lista.addLast(20);
        lista.addLast(30);

        System.out.println(lista);

        System.out.println(lista.get(0));
        System.out.println(lista.get(1));
        System.out.println(lista.get(2));

        System.out.println("Size: " + lista.size());
    }
}