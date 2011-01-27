package testfirst;

import java.util.ArrayList;

import testfirst.Car.Type;

public class TypeFilter
{
	public void filter(Type type, ArrayList<Car> carList, ArrayList<Car> filteredList) 
	{
		filteredList.clear();
		for(Car c: carList)
		{
			if(c.getType().equals(type))
			{
				filteredList.add(c);
			}
		}
	}
}
