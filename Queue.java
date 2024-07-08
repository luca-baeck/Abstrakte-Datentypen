package lecture.personal;

public class Queue<E> {

    @SuppressWarnings("hiding")
    public class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private Node<E> head;

    public Queue() {
        this.head = null;
    }

    public Queue(Node<E> head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int size() {
        int size = 0;
        Node<E> current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    public void enqueue(E element) {
        if (head == null) {
            head = new Node<>(element, null);
        } else {
            Node<E> current = head;
            while (current.hasNext()) {
                current = current.getNext();
            }
            current.setNext(new Node<>(element, null));
        }
    }

    public E dequeue() {
        if (head == null) {
            return null;
        } else {
            E element = head.getElement();
            head = head.getNext();
            return element;
        }
    }

    public E peek() {
        if (head == null) {
            return null;
        } else {
            return head.getElement();
        }
    }

}
