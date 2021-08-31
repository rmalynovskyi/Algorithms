import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree {
    private Node root;
    private Queue<Node> queue;
    private int size;

    public BinaryTree() {
        this.root = null;
        this.queue = new ArrayDeque<>();
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(5);
        binaryTree.add(2);
        binaryTree.add(7);
        binaryTree.add(3);
        binaryTree.add(10);
        binaryTree.add(6);
        binaryTree.add(1);
        binaryTree.add(8);
        binaryTree.add(4);
        binaryTree.add(9);
        binaryTree.add(9);
        binaryTree.add(11);
        binaryTree.add(12);
        binaryTree.print();
        System.out.println(binaryTree.size());
        System.out.println("--------------------");

        binaryTree.delete(4);
        binaryTree.delete(7);
        binaryTree.delete(8);
        binaryTree.delete(11);
        binaryTree.print();
        System.out.println(binaryTree.size());
        //System.out.println(binaryTree.get(10));
    }

    public int size() {
        return this.size;
    }

    public boolean add(int value) {
        boolean isAdded = false;
        if (this.root == null) {
            root = new Node(value);
            isAdded = true;
        } else {
            Node node = this.root;
            queue.offer(node);
            Node newNode = new Node(value);
            while (!queue.isEmpty()) {
                node = queue.poll();
                if (this.get(value) != null) {
                    isAdded = false;
                    return isAdded;
                }
                if (value > node.getValue()) {
                    if (node.getRight() == null) {
                        node.setRight(newNode);
                        node.getRight().setParent(node);
                        isAdded = true;
                    } else {
                        queue.offer(node.getRight());
                    }
                } else {
                    if (node.getLeft() == null) {
                        node.setLeft(newNode);
                        node.getLeft().setParent(node);
                        isAdded = true;
                    } else {
                        queue.offer(node.getLeft());
                    }
                }
            }
        }
        if (isAdded) {
            this.size++;
        }
        return isAdded;
    }

    public Node get(int value) {
        Node result = null;
        if (value == this.root.getValue()) {
            result = this.root;
        }
        if (value < this.root.getValue() && this.root.getLeft() != null) {
            this.queue.offer(this.root.getLeft());
        } else if (value > this.root.getValue() && this.root.getRight() != null) {
            this.queue.offer(this.root.getRight());
        }
        while (!this.queue.isEmpty()) {
            Node newRoot = this.queue.poll();
            if (value == newRoot.getValue()) {
                result = newRoot;
            } else {
                if (value < newRoot.getValue() && newRoot.getLeft() != null) {
                    this.queue.offer(newRoot.getLeft());
                } else if (value > newRoot.getValue() && newRoot.getRight() != null) {
                    this.queue.offer(newRoot.getRight());
                }
            }
        }
        return result;
    }

    public boolean delete(int value) {
        boolean isDeleted = false;
        Node node = this.get(value);
        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                isLeft(node);
            } else if (node.getLeft() != null && node.getRight() == null) {
                Node leftChild = node.getLeft();
                leftChild.setParent(node.getParent());
                if (isLeft(node)) {
                    node.getParent().setLeft(leftChild);
                } else {
                    node.getParent().setRight(leftChild);
                }
            } else if (node.getLeft() == null && node.getRight() != null) {
                Node rightChild = node.getRight();
                rightChild.setParent(node.getParent());
                if (isLeft(node)) {
                    node.getParent().setLeft(rightChild);
                } else {
                    node.getParent().setRight(rightChild);
                }
            } else {
                Node heir = getHeir(node);
                Node leftChild = node.getLeft();
                Node rightChild = node.getRight();
                boolean i = isLeft(heir);
                if (heir.getRight() != null && i) {
                    heir.getParent().setLeft(heir.getRight());
                    heir.getRight().setParent(heir.getParent());
                }
                if (heir.getRight() != null && !i) {
                    heir.getParent().setRight(heir.getRight());
                    heir.getRight().setParent(heir.getParent());
                }
                if (isLeft(node)) {
                    node.getParent().setLeft(heir);
                } else {
                    node.getParent().setRight(heir);
                }
                Node h = this.get(heir.getValue());
                h.setLeft(leftChild);
                h.setRight(rightChild);
                h.setParent(node.getParent());
            }
            isDeleted = true;
        }
        if (isDeleted) {
            this.size--;
        }
        return isDeleted;
    }

    private Node getHeir(Node node) {
        Node result = null;
        Node rightChild = node.getRight();
        if (rightChild.getLeft() == null) {
            result = rightChild;
        } else {
            Queue<Node> nodes = new ArrayDeque<>();
            nodes.offer(rightChild.getLeft());
            while (!nodes.isEmpty()) {
                Node n = nodes.poll();
                if (n.getLeft() == null) {
                    result = n;
                } else {
                    nodes.offer(n.getLeft());
                }
            }
        }
        return result;
    }

    private boolean isLeft(Node node) {
        boolean isLeft = false;
        Node parentNode = node.getParent();
        if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == node.getValue()) {
            parentNode.setLeft(null);
            isLeft = true;
        }
        if (parentNode.getRight() != null && parentNode.getRight().getValue() == node.getValue()) {
            parentNode.setRight(null);
        }
        return isLeft;
    }

    public void print() {
        System.out.println(this.root);
    }

    static class Node {
        private int value;
        private Node parent;
        private Node left;
        private Node right;


        Node(int value) {
            this.value = value;
            parent = null;
            left = null;
            right = null;
        }

        Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
        }

        Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
        }

        int getValue() {
            return value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
