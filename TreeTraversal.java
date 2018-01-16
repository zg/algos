import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal {
    public void visit(Node node) {
        System.out.print(node.val + " ");
    }

    public void inOrderTraversal(Node node) {
        if(node != null) {
            inOrderTraversal(node.left);
            visit(node);
            inOrderTraversal(node.right);
        }
    }

    public void preOrderTraversal(Node node) {
        if(node != null) {
            visit(node);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal(Node node) {
        if(node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            visit(node);
        }
    }

    public TreeTraversal() {
        Tree tree = new Tree();
        for(int i = 0; i < 10; i += 1) {
            Node node = new Node();
            node.val = i;
            tree.insert(node);
        }

        preOrderTraversal(tree.root);
        System.out.println();
        inOrderTraversal(tree.root);
        System.out.println();
        postOrderTraversal(tree.root);
        System.out.println();
    }

    public static void main(String[] args) {
        TreeTraversal tt = new TreeTraversal();
    }

    private class Tree {
        public Node root;

        public void insert(Node node) {
            if(root == null) {
                root = node;
                return;
            }

            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while(true) {
                Node n = queue.remove();
                if(n.left == null) {
                    n.left = node;
                    break;
                } else {
                    queue.offer(n.left);
                }

                if(n.right == null) {
                    n.right = node;
                    break;
                } else {
                    queue.offer(n.right);
                }
            }
        }
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
    }
}
