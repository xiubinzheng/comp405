package testfirst;

import java.awt.Color;
import java.util.LinkedList;

public class ColorFilter implements Filter<Color>
{

	@Override
	public LinkedList<Car> filter(Color color, LinkedList<Car> carList) 
	{
		LinkedList<Car> newCarList = new LinkedList<Car>();
		for(Car c: carList)
		{
			if(c.getColor().equals(color))
			{
				newCarList.add(c);
			}
		}
		return newCarList;
	}

}
