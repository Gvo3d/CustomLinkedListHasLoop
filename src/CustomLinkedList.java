import java.util.ArrayList;
import java.util.Iterator;

public class CustomLinkedList implements Iterable{

    private Node firstNode;
    private Node lastNode;

    @Override
    public Iterator iterator() {
        return new CLLIterator(firstNode);
    }

    private class Node {
        String value;
        Node next;
    }

    public void add(String value) {
        Node node = new Node();
        node.value = value;
        node.next = null;

        if (lastNode != null) {
            lastNode.next = node;
        }

        lastNode = node;

        if (firstNode == null) {
            firstNode = node;
        }
    }

    public void introduceCycle(int nodeIndex) {
        int currentIndex = 1;
        Node currentNode = firstNode;
        while (currentNode != null && currentIndex < nodeIndex) {
            currentNode = currentNode.next;
            currentIndex++;
        }
        lastNode.next = currentNode;
    }

    public void output() {
        Node currentNode = firstNode;
        while (currentNode != null) {
            System.out.println(currentNode.value);
            currentNode = currentNode.next;
        }
    }

    public boolean hasLoop() {
        ArrayList<Node> arr = new ArrayList();
        boolean wasLoop = false;
        CLLIterator iter = new CLLIterator(firstNode);
        arr.add(firstNode);
        do {
            for (Object obj:arr){
                if (iter.nextNode()==obj) {
                    iter.releaseNext();
                    wasLoop=true;
                    break;
                }
            }
            if (wasLoop) break;
            if (iter.hasNext()) {
                arr.add((Node) iter.currentNode());
                iter.next();
            } else break;
        } while(true);
        return wasLoop;
    }

    private class CLLIterator implements Iterator {
        private Node currentNode;

        public CLLIterator(Node currentNode3) {
            this.currentNode=currentNode3;
        }

        public Object currentNode(){
            return currentNode;
        }

        public Object nextNode(){
            return currentNode.next;
        }

        public void releaseNext(){
            currentNode.next=null;
        }

        @Override
        public boolean hasNext() {
            if (currentNode.next!=null) return true;
            return false;
        }

        @Override
        public Object next() {
            Object result = currentNode.value;
            currentNode=currentNode.next;
            return result;
        }
    }

    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        list.add("fifth");

        list.output();

        list.introduceCycle(3);

        if (list.hasLoop()) {
            System.out.println("This linked list has an infinite loop");
        }

        System.out.println(list.hasLoop());

    }

}

