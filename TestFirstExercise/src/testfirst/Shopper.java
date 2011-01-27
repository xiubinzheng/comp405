package testfirst;

import java.util.ArrayList;
import testfirst.Car;

public class Shopper
{
	public Car getCheapestCar(ArrayList<Car> list)
	{
		Car smallest = list.get(0);

		assert (!list.isEmpty());
		assert (list.size() == 9);

		for (int i = 1; i < list.size() - 1; ++i)
		{
			if (list.get(i).getPrice() < smallest.getPrice())
			{
				smallest = list.get(i);

			}
		}

		return smallest;
	}

	public Car getTotalPrice(ArrayList<Car> list)
	{
		Car smallest = list.get(0);
		int gallons = 0;
		double totalCost = 0.0;
		
		assert (!list.isEmpty());
		assert (list.size() == 9);

		for (int i = 1; i < list.size() - 1; ++i)
		{
			if (list.get(i).getPrice() < smallest.getPrice())
			{
				smallest = list.get(i);

			}
		}
		//computes total price of car with gas price
		gallons = 10000 / smallest.getMpg();
		totalCost = gallons * 3;
		smallest.setPrice(totalCost + smallest.getPrice());
		
		return smallest;
	}
}
