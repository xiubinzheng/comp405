package testfirst;

import java.awt.Color;
import java.util.ArrayList;

public class ColorFilter
{
	public void filter(Color color, ArrayList<Car> carList , ArrayList<Car> filteredList) 
	{
		filteredList.clear();
		for(Car c: carList)
		{
			if(c.getColor().equals(color))
			{
				filteredList.add(c);
			}
		}
	}
}
