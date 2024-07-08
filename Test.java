package lecture.personal;

public class Test {
    public static void main(String[] args) {
        // SingleLinkedList<Integer> list = new SingleLinkedList<>();
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // list.add(4);
        // list.add(5);
        // list.printRecursive();

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);
        bst.insert(1);
        bst.insert(9);
        bst.insert(0);

        System.out.println("Height " + bst.getHeight());
        System.out.println("Size " + bst.getSize());

        System.out.println(bst.contains(5));
        System.out.println(bst.contains(3));
        System.out.println(bst.contains(11));

        System.out.println("Min " + bst.findMin());
        System.out.println("Max " + bst.findMax());

        System.out.println(bst.toString());

        System.out.println("Remove " + bst.remove(3));

        System.out.println(bst.traverseInOrder());
        System.out.println(bst.traversePreOrder());
        System.out.println(bst.traversePostOrder());
        System.out.println(bst.traverseLevelOrder());

    }
}
