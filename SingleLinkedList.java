package lecture.personal;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<E> {

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

    public SingleLinkedList() {
        this.head = null;
    }

    public SingleLinkedList(Node<E> head) {
        this.head = head;
    }

    public void add(E element) {
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

    public void add(int index, E element) {
        if (index == 0) {
            addFirst(element);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            current.setNext(new Node<>(element, current.getNext()));
        }
    }

    public void addAll(SingleLinkedList<E> list) {
        Node<E> current = head;
        while (current.hasNext()) {
            current = current.getNext();
        }
        current.setNext(list.head);
    }

    public void addAll(int index, SingleLinkedList<E> list) {
        if (index == 0) {
            head = list.head;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            current.setNext(list.head);
        }
    }

    public void addFirst(E element) {
        head = new Node<>(element, head);
    }

    public void clear() {
        head = null;
    }

    public boolean contains(E element) {
        Node<E> current = head;
        while (current != null) {
            if (current.getElement().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean containsAll(SingleLinkedList<E> list) {
        Node<E> current = list.head;
        while (current != null) {
            if (!contains(current.getElement())) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SingleLinkedList) {
            SingleLinkedList<?> list = (SingleLinkedList<?>) obj;
            if (size() != list.size()) {
                return false;
            }
            Node<E> current = head;
            SingleLinkedList<?>.Node<?> otherCurrent = list.head;

            while (current != null && otherCurrent != null) {
                if (!current.getElement().equals(otherCurrent.getElement())) {
                    return false;
                }
                current = current.getNext();
                otherCurrent = otherCurrent.getNext();
            }

            if (current != null || otherCurrent != null) {
                return false;
            }

            return true;
        }
        return false;
    }

    public E get(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    public int indexOf(E element) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.getElement().equals(element)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = current.getElement();
                current = current.getNext();
                return element;
            }
        };
    }

    public int lastIndexOf(E element) {
        Node<E> current = head;
        int index = -1;
        int currentIndex = 0;
        while (current != null) {
            if (current.getElement().equals(element)) {
                index = currentIndex;
            }
            current = current.getNext();
            currentIndex++;
        }
        return index;
    }

    // public ListIterator<E> listIterator() {
    // return new ListIterator<E>() {

    // };
    // }

    public boolean remove(int index) {
        if (index < this.size()) {
            if (index == 0) {
                head = head.getNext();
                return true;
            } else {
                Node<E> current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.getNext();
                }
                current.setNext(current.getNext().getNext());
                return true;
            }
        }
        return false;
    }

    public boolean remove(E element) {
        if (!this.isEmpty()) {
            if (head.getElement().equals(element)) {
                head = head.getNext();
                return true;
            } else {
                Node<E> current = head;
                while (current.hasNext()) {
                    if (current.getNext().getElement().equals(element)) {
                        current.setNext(current.getNext().getNext());
                        return true;
                    }
                    current = current.getNext();
                }

            }
        }
        return false;
    }

    public boolean removeAll(SingleLinkedList<E> list) {
        Iterator<E> iterator = list.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            if (remove(iterator.next())) {
                removed = true;
            }
        }
        return removed;
    }

    public void set(int index, E element) {
        if (index < this.size()) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.setNext(new Node<>(element, current.getNext().getNext()));
        }
    }

    public boolean isEmpty() {
        return head == null;
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

    public SingleLinkedList<E> subList(int fromIndex, int toIndex) {
        SingleLinkedList<E> subList = new SingleLinkedList<>();
        Node<E> current = head;
        for (int i = 0; i < fromIndex; i++) {
            current = current.getNext();
        }
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.getElement());
            current = current.getNext();
        }
        return subList;
    }

    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            array[index] = current.getElement();
            current = current.getNext();
            index++;
        }
        return array;
    }

    public void print() {
        Node<E> current = head;
        while (current != null) {
            System.out.print(current.getElement() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public void printRecursive() {
        if (this.isEmpty()) {
            return;
        }
        System.out.println(head.getElement());
        (new SingleLinkedList<>(head.getNext())).printRecursive();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement()).append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

}
