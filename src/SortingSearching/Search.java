package SortingSearching;

import java.util.ArrayList;
import java.util.Collections;

import order.Product;

import java.util.Comparator;

public class Search {

	private ArrayList<Product> array = new ArrayList<Product>();

	public ArrayList<Product> expoSearch(ArrayList<Product> prod_array, String KeyWord)
	{

		this.array = prod_array;
		new Sort();
		Collections.sort(array, Sort.prodNameComparator);

		int i=1;
		String name=array.get(i).getName().substring(0, KeyWord.length());

		while(i<array.size() && name.compareTo(KeyWord)<=0)
		{
			i=i*2;
			name = array.get(i).getName().substring(0, KeyWord.length());
			if(i*2>=array.size())
			{
				return serSearch(array.size(), i-1, KeyWord, array);

			}
		}
		if(i<array.size())
		{
			int start=i/2;
			return serSearch(i,start,KeyWord, array);
		}
		else {return null;}
	} 

	public ArrayList<Product> serSearch(int end, int start, String KeyWord, ArrayList<Product> array)
	{
		this.array = array;
		ArrayList<Product> Found = new ArrayList<Product>();

		for(int i=start;i<end;i++)
		{
			String name=array.get(i).getName().substring(0, KeyWord.length());
			if(name.equals(KeyWord))
			{
				Found.add(array.get(i));
			}
		}
		return Found;
	}



}
