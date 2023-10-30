package practice;

public class GenericMethodPractice 
{

	public static void main(String[] args) 
	{
		//Calling Normal method 
		//add();
		//Calling Generic method 
		int value = result(10,30);
		System.out.println(value);
	}
	public static void add()
	{
		int sum = 10+20;
		System.out.println(sum);
	}
	public static int result(int a, int b)
	{
		int sum = a+b;
		return sum;
	}

}
