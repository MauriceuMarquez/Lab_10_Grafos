package listlinked;

public class ListLinked<E> {

    private Node<E> head;
    private int size;

    public ListLinked() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(newNode);
        }

        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node<E> current = head;

        while (current != null) {
            sb.append(current.getData());

            if (current.getNext() != null) {
                sb.append(" -> ");
            }

            current = current.getNext();
        }

        return sb.toString();
    }
}