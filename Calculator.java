package calculator;

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.Scanner;


public class Calculator {

	static Stack<Integer> numbers = new Stack<Integer>();
	static Stack<String> operators = new Stack<String>();
	
	public static void main(String[] args) {
		//user will give an input, and we have to save it somewhere
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your expression :");
		String expression = keyboard.nextLine();
		String[] array = tokenize(expression);
		System.out.println(expression);
		for (String s : array)
		{
			if (isOperator(s))
			{
				while(canReduce(s))
				{
					reduce();
				}
				operators.add(s);
			}
			else
			{
				numbers.push(Integer.parseInt(s));
			}
		}
		while (canReduce())
		{
			reduce();
		}
		int answer = numbers.pop();
		System.out.println("Answer = " + answer);
	}
	public static boolean canReduce()
	{
		if (operators.size() > 0)
		{
			return true;
		}
		return false;
	}
	public static void reduce()
	{
		int b = numbers.pop();
		int a = numbers.pop();
		String op = operators.pop();
		int result = reduce(a, op, b);
		numbers.push(result);
	}
	
	public static boolean canReduce(String input)
	{
		if (operators.size() > 0)
		{
			String op = operators.peek();
			if (precedence(input) <= precedence(op))
				return true;
		}
		return false;
	}
	
	public static boolean isOperator(String input)
	{
		if (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/"))
			return true;
		return false;
	}
	
	public static String[] tokenize(String input)
	{
		String clean = input.replaceAll(" ", "");
		ArrayList<String> tokens = new ArrayList<String>();
		String number = "";
		for (char c: clean.toCharArray())
		{
			if(Character.isDigit(c))
			{
				number = number + c;
			}
			else
			{
				if (number.length() > 0)
				{
					tokens.add(number);
					number = "";
				}
				tokens.add("" + c);
			}
		}
		return tokens.toArray(new String[tokens.size()]);
	}
	
	public static int precedence(String op)
	{
		if (op.equals("+"))
		{
			return 1;
		}
		if (op.equals("-"))
		{
			return 1;
		}
		if (op.equals("*"))
		{
			return 2;
		}
		if (op.equals("/"))
		{
			return 2;
		}
		return 0;
	}
	
	public static int reduce(int a, String op, int b)
	{
		if (op.equals("+"))
		{
			return a + b;
		}
		else if (op.equals("-"))
		{
			return a - b;
		}
		else if (op.equals("*"))
		{
			return a * b;
		}
		else
		{
			return a / b;
		}
		
	}

}
