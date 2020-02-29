package node;

public class Tree {

    Node root;

    public Tree() {
        root = null;
    }

    public int add(int s) {
        int rank = 0;
        if (root == null) {
            root = new Node(s);
        } else {
            Node n = root;
            while (true) {
                if (s < n.score) {
                    rank += n.rank + 1;
                    if (n.left == null) {
                        n.left = new Node(s);
                        return rank;
                    } else {
                        n = n.left;
                    }
                } else {
                    n.rank++;
                    if (n.right == null) {
                        n.right = new Node(s);
                        return rank;
                    } else {
                        n = n.right;
                    }
                }
            }
        }
        return rank;
    }
}
