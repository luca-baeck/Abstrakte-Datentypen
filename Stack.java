package lecture.personal;

public class Stack<E> {

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

    public Stack() {
        this.head = null;
    }

    public Stack(Node<E> head) {
        this.head = head;
    }

    public void push(E element) {
        if (head == null) {
            head = new Node<>(element, null);
        } else {
            Node<E> newHead = new Node<>(element, head);
            head = newHead;
        }
    }

    public E pop() {
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
