public class ListMy {
    Node head;

    public void add(int val) {
        Node nd = new Node();
        nd.value = val;
        nd.next = head;
        head = nd;
    }
    public String serialize() {
        Node nd = head;
        String result = Integer.toString(nd.value);
        nd = nd.next;
        while(nd != null) {
            result = result.concat(", " + Integer.toString(nd.value));
            nd = nd.next;
        }
        return result;
    }
    public void revert() {
        if(head != null && head.next != null) {
            Node n1, n2, n3 = new Node();
            n1 = head;
            n2 = head.next;
            n3 = n2.next;
            n1.next = null;
            while (n3 != null) {
                head = n2;
                head.next = n1;

                n1 = n2;
                n2 = n3;
                n3 = n3.next;
            }
            head = n2;
            head.next = n1;
        }
    }
}
