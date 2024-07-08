package lecture.personal;

public class BinarySearchTree<E extends Comparable<E>> {

    E root;

    private BinarySearchTree<E> left;
    private BinarySearchTree<E> right;

    public BinarySearchTree() {
        this.root = null;
        this.left = null;
        this.right = null;
    }

    public BinarySearchTree(E root) {
        this.insert(root);
    }

    public BinarySearchTree(E root, BinarySearchTree<E> left, BinarySearchTree<E> right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public void insert(E element) {
        if (this.root == null) {
            this.root = element;
            this.left = new BinarySearchTree<>();
            this.right = new BinarySearchTree<>();
        } else {
            int comp = element.compareTo(root);
            if (comp == 0) {
                return;
            } else if (comp < 0) {
                this.left.insert(element);
            } else if (comp > 0) {
                this.right.insert(element);
            }
        }
    }

    public boolean remove(E element) {
        if (this.isEmpty()) {
            return false;
        }
        int comp = element.compareTo(this.root);
        if (comp == 0) {
            if (this.getSize() == 1) {
                this.clear();
            } else if (this.left.isEmpty()) {
                this.root = this.right.root;
                this.left = this.right.left;
                this.right = this.right.right;
            } else if (this.right.isEmpty()) {
                this.root = this.left.root;
                this.left = this.left.left;
                this.right = this.left.right;
            } else {
                E maxLeft = this.left.findMax();
                this.left.remove(maxLeft);
                this.root = maxLeft;
            }

            return true;
        }
        BinarySearchTree<E> parent = this.getParentBinarySearchTree(element);
        if (parent == null) {
            return false;
        }

        BinarySearchTree<E> toDelete;
        boolean toDeleteInLeftTree;

        if (parent.left.root.compareTo(element) == 0) {
            toDelete = parent.left;
            toDeleteInLeftTree = true;
        } else {
            toDelete = parent.right;
            toDeleteInLeftTree = false;
        }

        if (toDelete.getSize() == 1) {
            if (toDeleteInLeftTree) {
                parent.left = new BinarySearchTree<>();
            } else {
                parent.right = new BinarySearchTree<>();
            }
        } else if (toDelete.left.isEmpty()) {
            if (toDeleteInLeftTree) {
                parent.left = toDelete.right;
            } else {
                parent.right = toDelete.right;
            }
        } else if (toDelete.right.isEmpty()) {
            if (toDeleteInLeftTree) {
                parent.left = toDelete.left;
            } else {
                parent.right = toDelete.left;
            }
        } else {
            E maxToDeleteLeft = toDelete.left.findMax();
            toDelete.left.remove(maxToDeleteLeft);
            toDelete.root = maxToDeleteLeft;
        }

        return true;

    }

    private BinarySearchTree<E> getParentBinarySearchTree(E element) {
        if (this.root == null) {
            return null;
        }
        int comp = element.compareTo(this.root);
        if (!this.left.isEmpty() && this.left.root.compareTo(element) == 0) {
            return this;
        }
        if (!this.right.isEmpty() && this.right.root.compareTo(element) == 0) {
            return this;
        }
        if (comp < 0) {
            return this.left.getParentBinarySearchTree(element);
        }
        if (comp > 0) {
            return this.right.getParentBinarySearchTree(element);
        }
        return null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void clear() {
        this.root = null;
        this.left = null;
        this.right = null;
    }

    public int getHeight() {
        if (this.isEmpty()) {
            return 0;
        }
        return this.left.getHeight() > this.right.getHeight() ? 1 + this.left.getHeight() : 1 + this.right.getHeight();
    }

    public int getSize() {
        if (this.isEmpty()) {
            return 0;
        }
        return 1 + this.left.getSize() + this.right.getSize();
    }

    public boolean contains(E element) {
        if (this.isEmpty()) {
            return false;
        }
        int comp = element.compareTo(this.root);
        if (comp == 0) {
            return true;
        } else if (comp < 0) {
            return this.left.contains(element);
        } else {
            return this.right.contains(element);
        }
    }

    public E findMin() {
        if (this.getSize() <= 1) {
            return this.root;
        }
        E min = this.left.findMin();
        if (min == null) {
            return this.root;
        } else {
            return this.root.compareTo(min) <= 0 ? this.root : min;
        }
    }

    public E findMax() {
        if (this.getSize() <= 1) {
            return this.root;
        }
        E max = this.right.findMax();
        if (max == null) {
            return this.root;
        } else {
            return this.root.compareTo(max) >= 0 ? this.root : max;
        }
    }

    public String traverseInOrder() {
        if (this.isEmpty()) {
            return "";
        }
        return this.left.traverseInOrder() + this.root + this.right.traverseInOrder();
    }

    public String traversePreOrder() {
        if (this.isEmpty()) {
            return "";
        }
        return this.root + this.left.traversePreOrder() + this.right.traversePreOrder();
    }

    public String traversePostOrder() {
        if (this.isEmpty()) {
            return "";
        }
        return this.left.traversePostOrder() + this.right.traversePostOrder() + this.root;
    }

    public String traverseLevelOrder() {
        if (this.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<BinarySearchTree<E>> queue = new Queue<>();
        queue.enqueue(this);
        while (!queue.isEmpty()) {
            BinarySearchTree<E> current = queue.dequeue();
            if (!current.isEmpty()) {
                queue.enqueue(current.left);
                queue.enqueue(current.right);
                sb.append(current.root);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "Der Baum ist leer.";
        }
        return this.traverseLevelOrder();
    }

}