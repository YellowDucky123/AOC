// everything in this class would revolve around the "node" variable as it is the current node
// the root is just to keep track of the head of the tree

public class treeWrapper<T> {
    TreeNode<T> root;   // root of tree
    
    TreeNode<T> node;   // current node we are at

    public treeWrapper(T root) {
        TreeNode<T> r = new TreeNode<T>(root);
        this.root = r;
        this.node = this.root;
    }

    public TreeNode<T> addChild(T child) {  // add a child in the current node
        if(this.root.data == null) {
            this.root = new TreeNode<T>(child);
            this.node = this.root;
            return this.node;
        }
        return node.addChild(child);
    }

    public TreeNode<T> moveToChild(T child) {
        node = node.children.get(child);
        return node;
    }

    public TreeNode<T> moveToParent() {  
        node = node.parent;
        return node;
    }

    public void printNode() {   // print the name of current node
        System.out.println(this.node.data);
    }

    public void printRoot() {
        System.out.println(this.root.data);
    }
}
