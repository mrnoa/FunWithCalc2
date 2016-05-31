public class Stack<T> {
	private class Node<U>{
		public Node<U> below = null;
		public U data;
		public Node(U data, Node<U> bottom){
			this.data = data;
			this.below = bottom;
		}
	}
	
	private Node<T> top = null;
	
	public T top(){
		if(top != null)
			return top.data; 
		else
			throw new RuntimeException("Stack is empty!");
	}
	
	public T pop() {
		Node<T> temp = top;
		if(top == null)
			throw new RuntimeException("Can't pop from empty stack!");
		else
			top = top.below;
		
		return temp.data;
	}

	public void push(T data) {
		top = new Node<T>(data, top);
	}
	
	public String toString() {
		String out = "";
		Node<T> n = top;
		while(n != null)
		{
			if (n == top)
				out = n.data + "";
			else
				out = n.data + " " + out;
			
			n = n.below;
		}
		return "[" + out;
	}

	public boolean isEmpty() {
		return top == null;
	}
}
