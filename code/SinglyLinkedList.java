public class SinglyLinkedList<E>{

  private static class Node<E>{
    private E element;
    private Node<E> next;
    public Node(E e, Node<E> n){
      next = n;
      element = e;
    }
    public E getElement(){
      return element;
    }
    public Node<E> getNext(){
      return next;
    }
    public void setNext(Node<E> n){
      next = n;
    }
  }

  private Node<E> head = null;
  private Node<E> tail = null;
  private int size = 0;

  public SinglyLinkedList(){}

  public int size(){
    return size;
  }

  public boolean isEmpty(){
    return (size == 0);
  }

  public E first(){
    if (isEmpty())
      return null;
    return head.getElement();
  }

  public E last(){
    if (isEmpty())
      return null;
    return tail.getElement();
  }

  public void addFirst(E element){
    head = new Node<>(element, head);
    if (isEmpty())
      tail = head;
    size++;
  }

  public void addLast(E element){
    Node <E> newNode = new Node<E>(element, null);
    if (isEmpty())
      head = newNode;
    else
      tail.setNext(newNode);
    tail = newNode;
    size++;
  }

  public E removeFirst(){
    if(isEmpty())
      return null;
    E headElement = head.getElement();
    size--;
    head = head.getNext();
    if(isEmpty())
      tail = null;
    return headElement;
  }

}
