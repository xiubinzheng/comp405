package testfirst;

import java.util.ArrayList;
import testfirst.Car;

public class Shopper
{
	public Car getCheapest(ArrayList<Car> list)
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

	public Car bestDeal(ArrayList<Car> list)
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
}
