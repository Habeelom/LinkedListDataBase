

//In the IndexArray class I implement a double ended doubly linked list that stores the 
//records.
public class IndexArray {
	Node front, back, iterator;
	int size;

//here I introduce my references including back because this is double ended
	public IndexArray() {
		front = null;
		back = null;
		size = 0;
	}

	public void iteratorInitFront() {
		this.iterator = front;
	}

	public void iteratorInitBack() {
		this.iterator = back;
	}

//here i initialize the iterators

	public boolean hasNext() {
		return iterator != null;
	}

//if the iterator isn't null an element after exists
	public int getNext() {
		int where = iterator.where;
		iterator = iterator.getNext();
		return where;
	}

//grabs the next element and moves the iterator forward

	public boolean hasPrevious() {
		return iterator != null;
	}

//similar to hasNext

	public int getPrevious() {
		int where = iterator.where;
		iterator = iterator.getPrev();
		return where;
	}

//grabs the previous element then moves the iterator back

	public void addIndex(IndexRecord record) {
		Node newNode = new Node(record.getKey(), record.getWhere());

		if (front == null) {
			front = newNode;
			back = newNode;
		} else {
			Node rover = front;
			while (rover != null && rover.key.compareTo(record.getKey()) < 0) {
				rover = rover.getNext();
			}

			if (rover == front) {
				newNode.setNext(front);
				front.setPrev(newNode);
				front = newNode;
			} else if (rover == null) {

				back.setNext(newNode);
				newNode.setPrev(back);
				back = newNode;
			} else {

				newNode.setNext(rover);
				newNode.setPrev(rover.getPrev());
				rover.getPrev().setNext(newNode);
				rover.setPrev(newNode);
			}
		}

		size++;
	}
//I create a new node using the key and location of the record. I then iterate through the list finding
//the correct place for insertion. I either insert at the front if rover==front, at the back if rover
//==null or in the middle in the other cases. I then increment the size of the list. If front== null
//that means the list is empty and i set front and back= to the new node.

	public void deleteIndex(int index) {
		Node rover = front;
		while (rover != null && rover.where != index) {
			rover = rover.getNext();
		}

		if (rover != null) {
			if (rover == front) {
				front = rover.getNext();
				if (front != null) {
					front.setPrev(null);
				}
			} else if (rover == back) {
				back = rover.getPrev();
				if (back != null) {
					back.setNext(null);
				}
			} else {
				rover.getPrev().setNext(rover.getNext());
				rover.getNext().setPrev(rover.getPrev());
			}

			size--;
		}
	}

//This is the delete method where I iterate through the list while rover!= null and if the node is
//at the beginning of the list then i set the reference to point at the next node in the linked list,
//I then set the previous pointer to null. If the node is at the end of the list then I update the back
//reference to the previous node and set the next reference to null. If i am inserting into the middle
//of the list then i set the next reference of the previous node to point at rover.getNext, and I set
//the previous reference of the next node to point at rover.getPrev.

	public int search(String key) {
		Node rover = front;
		while (rover != null) {
			int compVal = rover.key.compareTo(key);
			if (compVal == 0) {
				return rover.where;
			} else if (compVal > 0) {
				return -1;
			}
			rover = rover.getNext();
		}
		return -1;
	}

//In the search method I search for a record by its key and return the location in the database. I
//use compVal to compare the current node with the key and if the keys match, then I return the
//location and if the key can't be found then I return -1 to signify not being in list
	public int getSize() {
		return size;
	}
}
