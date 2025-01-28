import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TreeNode<T> {
    T data;

    long size;
    TreeNode<T> parent;
    Map<T, TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new HashMap<>();
        this.parent = null;
    }

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.put(child, childNode);
        return childNode;
    }

    public TreeNode<T> getChild(T child) {
        return this.children.get(child);
    }
}
