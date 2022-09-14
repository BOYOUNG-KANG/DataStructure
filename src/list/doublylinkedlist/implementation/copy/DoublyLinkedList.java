package list.doublylinkedlist.implementation.copy;

public class DoublyLinkedList {
	//첫번재 노드를 가리키는 필드
	private Node head;
	private Node tail;
	private int size = 0;
	private class Node{
		//데이터가 저장될 필드
		private Object data;
		//다음 노드를 가리키는 필드
		private Node next;
		private Node prev;
		public Node(Object input) {
			this.data = input;
			this.next = null;
			this.prev = null;
		}
		//노드의 내용을 쉽게 출력해서 확인해볼 수 있는 기능
		public String toString() {
			return String.valueOf(this.data);
		}
	}
	public void addFirst(Object input) {
		
		Node newNode = new Node(input);
		newNode.next = head;
		
		if(head != null) {
			head.prev = newNode;
		}
		head = newNode;
		size++;
		if(head.next == null) {
			tail = head;
		}		
	}
	public void addLast(Object input){
		Node newNode = new Node(input);
		if(size == 0) {
			addFirst(input);
		}else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			size++;
		}
	}
	public Node node(int index){
		if(index < size/2) {
			Node x = head;
			for(int i =0; i < index; i++) {
				x = x.next;
			}
			return x;
		}else {
			Node x = tail;
			for(int i = size-1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	} 
		
	public void add(int k , Object input){
		if(k == 0) {
			addFirst(input);
		}else {
			Node temp1 = node(k-1); //index = 1
			Node temp2 = temp1.next; //index = 2
			Node newNode = new Node(input);
			
			temp1.next = newNode;
			newNode.prev = temp1;
			newNode.next = temp2;
			if(temp2 != null) { //temp2가 없을 수도 있음
				temp2.prev = newNode;
			}
			size++;
			if(newNode.next == null){
				tail = newNode;
			}
		}
	}
	public String toString() {
		if(head == null) {
			return "[]";
		}
		Node temp = head;
		String str = "[";
		
		while(temp.next != null) {
			str += temp.data + ",";
			temp = temp.next;
		}
		str += temp.data;
		return str+"]";
	}
	public Object removeFirst() {
		Node temp = head;
		head = head.next;
		Object returnData = temp.data;
		temp = null;
		size--;
		return returnData;
	}
	public Object remove(int k) {
		if(k==0) {
			return removeFirst();
		}
		Node temp = node(k-1);
		Node todoDeleted = temp.next;
		temp.next = temp.next.next;
		Object returnDate = todoDeleted.data;
		if(todoDeleted == tail) {
			tail = temp;
		}
		todoDeleted = null;
		size--;
		return returnDate;
	}
	public Object removeLast() {
		return remove(size-1);
	}
	public int size() {
		return size;
	}
	public Object get(int k) {
		Node temp = node(k);
		return temp.data;				
		}
	public int indexOf(Object data) {
		Node temp = head;
		int index = 0;
		while(temp.data != data) {
			temp = temp.next;
			index++;
			if(temp == null) {
				return -1;//검색 종료
			}
		}
		return index;
	}
	public ListIterator listIterator() {
		return new ListIterator();
	}
	public class ListIterator{
		private Node next;
		private Node lastReturned;//전역 변수
		private int nextIndex;
		
		ListIterator(){
			next = head;
		}
		
		public Object next() {
			lastReturned = next;
			next = next.next;
			nextIndex++;
			return lastReturned.data;
		}	
		public boolean hasNext(){
			return nextIndex < size();		
		}
		public void add(Object input) {
			Node newNode = new Node(input);
			if(lastReturned == null) {

			//처음 위치에 노드 추가
			head = newNode;
			newNode.next = next;
			}else {
				//중간 위치에 노드 추가
				lastReturned.next = newNode;
				newNode.next = next;	
			}	
			lastReturned = newNode;
			nextIndex++;
			size++;
		}
		public void remove() {
			if(nextIndex ==0) {
				throw new IllegalStateException();//아무것도 선택되지 않았을때, 오류 띄우기
			}
			DoublyLinkedList.this.remove(nextIndex-1);
			nextIndex--;
		}
	}	
}

