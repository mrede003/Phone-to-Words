import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class PhoneNumber {
	
	private final HashMap<Character, String> phoneToLetters=new HashMap<Character, String>();
	private ArrayList<Character> temp=new ArrayList<Character>();
	private ArrayList<String> commonList=new ArrayList<String>();
	public ArrayList<String> results=new ArrayList<String>();
	
	public PhoneNumber()
	{
		phoneToLetters.put('2', "ABC");
		phoneToLetters.put('3', "DEF");
		phoneToLetters.put('4', "GHI");
		phoneToLetters.put('5', "JKL");
		phoneToLetters.put('6', "MNO");
		phoneToLetters.put('7', "PRS");
		phoneToLetters.put('8', "TUV");
		phoneToLetters.put('9', "XYZ");
		phoneToLetters.put('1', "1");
		phoneToLetters.put('0', "0");
	}
	public String stripHyphen(String n)
	{
		if(n.contains("-"))
		{
			StringBuilder sb=new StringBuilder(n);
			sb.deleteCharAt(n.indexOf('-'));
			n=sb.toString();
		}
		return n;
	}
	public HashMap<Character, String> getMap()
	{
		return phoneToLetters;
	}
	//backtracking makes my brain hurt 
	public void getString(String digits)
	{
		//exit condition
		if(digits.length()==0)
		{
			char[] superTemp=new char[temp.size()];
			for(int i =0; i<temp.size();i++)
			{
				superTemp[i]=temp.get(i);
			}
			results.add(String.valueOf(superTemp));
			return;
		}
		char current=digits.toCharArray()[0];
		//true condition
		for(int i=0; i<phoneToLetters.get(current).length(); i++)
		{
			temp.add(phoneToLetters.get(current).toCharArray()[i]);
			getString(digits.substring(1));
			temp.remove(temp.size()-1);
		}
		
	}
//	public void loadCommonList()
//	{
//		Scanner fileScanner1;
//		try {
//			fileScanner1 = new Scanner(new File("non_sorted.txt"));
//			while(fileScanner1.hasNext())
//			{
//				commonList.add(fileScanner1.next());
//			}
//			fileScanner1.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ArrayList<String> commonResults=new ArrayList<String>();
//		for(int i=0; i<commonList.size(); i++)
//		{
//			for(int j=0; j<results.size(); j++)
//			{
//				if(results.get(j).toLowerCase().contains(commonList.get(i).toLowerCase()))
//					System.out.println(commonList.get(i));
//			}
//		}
//		System.out.println("done");
//		
//	}
	public void loadPopularList()
	{
		
	}
	public static void main(String args[])
	{
		System.out.print("Enter phone number:");
		
		PhoneNumber p=new PhoneNumber();
		Scanner scanner=new Scanner(System.in);
		String number=scanner.next();
		number=p.stripHyphen(number);
		
		p.getString(number);
		for(int i=0; i<10; i++)
			System.out.println(i+": "+p.results.get(i));
		System.out.println("...");
		for(int i=2177; i<2187; i++)
			System.out.println(i+": "+p.results.get(i));
		scanner.close();
	}
}
