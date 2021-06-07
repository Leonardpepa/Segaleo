package SortingSearching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import order.Product;




public class Search {

	private List<Product> array = new ArrayList<Product>();

	public List<Product> expoSearch(List<Product> prod_array, String KeyWord)
	{

		this.array = prod_array;
		new Sort();
		Collections.sort(array, Sort.prodNameComparator);

		int i=1;
		int smallest= (KeyWord.length()+ array.get(i).getName().length() - Math.abs(KeyWord.length()- array.get(i).getName().length()))/2;
		String name=array.get(i).getName().substring(0,  smallest);

		while(i<array.size() && name.compareTo(KeyWord)<=0)
		{
			i=i*2;
			smallest = (KeyWord.length() + array.get(i).getName().length()
					- Math.abs(KeyWord.length() - array.get(i).getName().length())) / 2;
			 name = array.get(i).getName().substring(0, smallest);
			if(i*2>=array.size())
			{
				int start=reverse_expoSearch(prod_array, KeyWord, array.size());
				return serSearch(array.size(), start, KeyWord, array);

			}
		}
		if(i<array.size())
		{
			
			int start = reverse_expoSearch(prod_array, KeyWord, i);
			return serSearch(i,start,KeyWord, array);
		}
		else {return null;}
	} 


	public int reverse_expoSearch(List<Product> prod_array, String KeyWord,int end)
	{
		@SuppressWarnings("unused")
		int start;

		int i = 1;
		int smallest = (KeyWord.length() + array.get(end-i).getName().length()
				- Math.abs(KeyWord.length() - array.get(end-i).getName().length())) / 2;
		String name = array.get(end-i).getName().substring(0, smallest);

		while (i < end && name.compareTo(KeyWord) >= 0) {
			i = i * 2;
			smallest = (KeyWord.length() + array.get(end - i).getName().length()
					- Math.abs(KeyWord.length() - array.get(end-i).getName().length())) / 2;
			name = array.get(end-i).getName().substring(0, smallest);
			if (i * 2 >= end) {
				return start=0;

			}
		}
		return start= end-i;
	}

	public List<Product> serSearch(int end, int start, String KeyWord, List<Product> array)
	{
		this.array = array;
		List<Product> Found = new ArrayList<Product>();

		for(int i=start;i<end;i++)
		{
			int smallest = (KeyWord.length() + array.get(i).getName().length()
					- Math.abs(KeyWord.length() - array.get(i).getName().length())) / 2;
			String name = array.get(i).getName().substring(0, smallest);
			if(name.equalsIgnoreCase(KeyWord))
			{
				Found.add(array.get(i));
			}
		}
		return Found;
	}



}
