
//this class is used to define the node used in the double ended doubly linked list
public class Node {
	String key;
	int where;
	private Node next, prev;
//I have next but also previous becasue it's a doubly linked list
	public Node(String key, int where) {
		this.key = key;
		this.where = where;
		this.next = null;
		this.prev = null;
	}//Initialize the key, where for identifying where record is located, and prev and next are set to
	//null initially
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getWhere() {
		return where;
	}
	public void setWhere(int where) {
		this.where = where;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node getPrev() {
		return prev;
	}
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	
//getters and setters to be used in the other classes
}
