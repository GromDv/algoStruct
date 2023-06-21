class Tree {
    Node root;

    class Node {
        int value;
        private Color color;
        Node left;
        Node right;

    }

    private enum Color {
        RED, BLACK
    }

    public Node find(int value) {
        return find(root, value);
    }

    private Node find(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            return node;
        }
        if (node.value < value) {
            return find(node.right, value);
        } else {
            return find(node.left, value);
        }
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node();
            root.value = value;
            root.color = Color.BLACK;
        } else {
            insert(root, value);
            root = rebalance(root);
        }

        //root.color = BLACK;
    }

    public void insert(Node node, int value) {
        if (node.value != value) {
            if (node.value < value) {
                if (node.right == null) {
                    node.right = new Node();
                    node.right.value = value;
                    node.right.color = Color.RED;
                } else {
                    insert(node.right, value);
                    node.right = rebalance(node.right);
                }
            } else {
                if (node.left == null) {
                    node.left = new Node();
                    node.left.value = value;
                    node.left.color = Color.RED;
                } else {
                    insert(node.left, value);
                    node.right = rebalance(node.left);
                }
            }
        }
    }

    private void colorSwap(Node node) {
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }

    private Node leftSwap(Node node) {
        Node left = node.left;
        Node mid = left.right;
        left.right = node;
        node.left = mid;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }

    private Node rightSwap(Node node) {
        Node right = node.right;
        Node mid = right.left;
        right.left = node;
        node.right = mid;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needBalance;
        do {
            needBalance = false;
            if (result.right != null && result.right.color == Color.RED && (result.left == null || result.left.color == Color.BLACK)) {
                needBalance = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED && result.left.left != null && result.left.left.color == Color.RED) {
                needBalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED && result.right != null && result.right.color == Color.RED) {
                needBalance = true;
                colorSwap(result);
            }
        } while (needBalance);
        return result;
    }
}

public class Main {
    public static void main(String[] args) {

        Tree tree = new Tree();

        for (int i = 1; i <= 7; i++) {
            tree.insert(i);
            System.out.println("+");
        }
        System.out.println("end");
    }
}
   
