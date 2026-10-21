package HW.HW4.q41ab;

public class LLNode<T> {

    private T data;
    private LLNode<T> link;

    //constructor
    public LLNode(T data) {
        this.data = data;
        this.link = null;
    }

    public T data() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LLNode<T> next() {
        return link;
    }

    public void setLink(LLNode<T> link) {
        this.link = link;
    }
}