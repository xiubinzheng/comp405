package testfirst;

import java.util.LinkedList;

import testfirst.Car.Type;

public class TypeFilter implements Filter<Car.Type> {

	@Override
	public LinkedList<Car> filter(Type type, LinkedList<Car> carList) {
		LinkedList<Car> newCarList = new LinkedList<Car>();
		for(Car c: carList)
		{
			if(c.getType().equals(type))
			{
				newCarList.add(c);
			}
		}
		return newCarList;
	}

}
