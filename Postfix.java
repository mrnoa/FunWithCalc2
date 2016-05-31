import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Postfix {
	
	class CalcException extends RuntimeException {
		private static final long serialVersionUID = 5821214639920732864L;

		public CalcException(String string) {
			super(string);
		}
		
	}

	
	public int evaluate(String pfx) {
		String[] tokens = pfx.split(" ");
		Stack<Integer> stack = new Stack<>();
		
		for(String t : tokens){
			switch (t){
				case "+":
				case "-":
				case "*":
				case "/":
				case "^":
					int b = (stack.pop());
					int a = (stack.pop());
					switch (t)
					{
						case "+":
							stack.push(a+b);
						break;
						case "-":
							stack.push(a-b);
						break;
						case "*":
							stack.push(a*b);
						break;
						case "/":
							stack.push(a/b);
						break;
						case "^":
							stack.push((int)Math.pow(a, b));
						break;
					}
				break;
				default: 
					try{
					stack.push(Integer.parseInt(t));
					} catch (NumberFormatException e){
						throw new CalcException("Not a number: " + t);
					}
				break;
			}
		}
//		if(!stack.isEmpty())
//			throw new RuntimeException("Input string invalid: Stack not empty!");
		return stack.top();
	}
	
//	public String infixToPostfix (String ifx){
//
//		String[] tokens = ifx.split(" ");
//		Stack<String> stack = new Stack<>();
//		
//		String pfx = "";
//		
//		final HashSet<String> operators = new HashSet<>();
//		operators.add("+");
//		operators.add("-");
//		operators.add("/");
//		operators.add("*");
//		operators.add("^");
//		
//		final HashMap<String, Integer> precedence = new HashMap<>();
//		precedence.put("+", 0);
//		precedence.put("-", 0);
//		precedence.put("*", 1);
//		precedence.put("/", 1);
//		precedence.put("^", 2);
//		
////		System.out.println("---" + ifx);
//		
//		for (String t : tokens) {
//			
//			if(!operators.contains(t) && !t.equals("(") && !t.equals(")"))//t.matches("-?\\d+"))
//			{
//				pfx = pfx + t + " ";
//			}
//			else if (t.equals("("))
//			{
//				stack.push(t);
//			}
//			else if (t.equals(")"))
//			{
//				if(stack.isEmpty()){
//					throw new CalcException("Too much ')'!");
//				}
//				
//				while (!stack.top().equals("("))
//				{
//					pfx = pfx + stack.pop() + " ";
//
//					if(stack.isEmpty()){
//						throw new CalcException("Too much ')'!");
//					}
//				}
//				stack.pop();
//			}
//			else if(operators.contains(t))
//			{
//				while(!stack.isEmpty() && operators.contains(stack.top()) && precedence.get(stack.top()) >= precedence.get(t) )
//					pfx = pfx + stack.pop() + " ";
//					
//				stack.push(t);
//			}
//			else
//				throw new CalcException("Not an operator: '" + t + "'");
//		
//
////			System.out.println(stack.toString());
////			System.out.println(pfx);
//		}
//		
//		while(!stack.isEmpty())
//		{
//			if(!operators.contains(stack.top()))
//				throw new CalcException("Stack residue: " + stack.top());
//			pfx = pfx + stack.pop() + " ";
//		}
//		
//		return pfx.trim();
//	}
	public String[] toElementArray(String input){
		char[] characterArray = input.toCharArray();
		Queue<Character> digits = new LinkedList<>();
		ArrayList<String> tempResult = new ArrayList<>();
		for(char element: characterArray){
			if(element >= 48 && element <= 57){
				digits.add(element);
			}
			if(element >= 40 && element <= 47 && element != 44 && element != 46){
				String digit = "";
				while(!digits.isEmpty()){
					digit += digits.poll();
				}
				if(digit.length() != 0)
				tempResult.add(digit);
				tempResult.add(String.valueOf(element));
			}
		}
		String digit = "";
		while (!digits.isEmpty()){
			digit += digits.poll();
		}
		if(digit.length() != 0)
		tempResult.add(digit);
		String[] test = tempResult.toArray(new String[tempResult.size()]);
		return test;
		
	}
	
	private int getPrecidence(String operator) {
		switch (operator) {
		case "+":
			return 0;
		case "-":
			return 0;
		case "*":
			return 1;
		case "/":
			return 1;
		case "^":
			return 2;
		default:
			return -1;
		}
	}

	public String infixToPostfix(String ifx) throws CalcException {
		Stack<String> operators = new Stack<>();
		String[] elements = toElementArray(ifx);
		String result = "";
		for (String element : elements) {
			if (element.matches("\\d{1,}")) { //digits are always added to the result
				result += " " + element;
			}
			else if (element.equals("(")) //keep track of the opening parenthesis
				operators.push(element);
			else if (element.equals(")")) {
				while (!operators.top().equals("(")) {
					result += " " + operators.pop(); //pop all operators in the parenthesizes
				}
				operators.pop();
			}

			else if (element.matches("[+\\-*/^]")) { //element is operator

				while (!operators.isEmpty() && !(getPrecidence(operators.top()) < getPrecidence(element))) {
					result += " " + operators.pop(); //pop all operators with lower precidence

				}
				operators.push(element); //push the current operator
			}
			else throw new CalcException("unexpected input"); //unexpected input
		}
		while (!operators.isEmpty()) {
			result += " " + operators.pop();
		}
		return result.trim();
	}

	
	public void evaluateFromConsole() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String str = reader.readLine();
			if (str.equals("quit") || str.equals("exit"))
				break;
			else
				try {
					String pfx = infixToPostfix(str);
					System.out.println("postfix: " + pfx);
					System.out.println("result: " + evaluate(pfx));
				} catch (CalcException e) {
					System.out.println(e.getMessage());
				}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Postfix().evaluateFromConsole();
	}
}
