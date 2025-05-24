import java.util.*;

public class Main {
    public static void main(String[] args) {
        ProductsNode<String> root = new ProductsNode<>("Root");
        root.setLeft(new ProductsNode<>("Left"));
        root.setRight(new ProductsNode<>("Right"));
        root.getLeft().setLeft(new ProductsNode<>("Left.Left"));
        root.getLeft().setRight(new ProductsNode<>("Left.Right"));

        BST<String> productCatalog = new BST<>(root);
        Iterator<String> iterator = productCatalog.createBFSIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.getNext());
        }
    }
}

public interface Iterator<T> {
    boolean hasNext();

    T getNext();
}

public interface ProductsTree<T> {
    Iterator<T> createBFSIterator();
}

public class BST<T> implements ProductsTree<T> {
    private ProductsNode<T> root;

    public BST(ProductsNode<T> root) {
        this.root = root;
    }

    @Override
    public Iterator<T> createBFSIterator() {
        return new BFSIterator<>(root);
    }
}

public class ProductsNode<T> {
    T value;
    ProductsNode<T> left;
    ProductsNode<T> right;

    public ProductsNode(T value) {
        this.value = value;
    }

    public void setLeft(ProductsNode<T> left) {
        this.left = left;
    }

    public void setRight(ProductsNode<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public ProductsNode<T> getLeft() {
        return left;
    }

    public ProductsNode<T> getRight() {
        return right;
    }
}

public class BFSIterator<T> implements Iterator<T> {
    private Queue<ProductsNode<T>> queue = new LinkedList<>();

    public BFSIterator(ProductsNode<T> root) {
        if (root != null) {
            queue.offer(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T getNext() {
        ProductsNode<T> current = queue.poll();
        if (current.getLeft() != null)
            queue.offer(current.getLeft());
        if (current.getRight() != null)
            queue.offer(current.getRight());
        return current.getValue();
    }
}
