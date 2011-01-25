package testfirst;

import java.awt.Color;
import java.util.LinkedList;

public interface Filter<Type>
{
	LinkedList<Car> filter(Type type, LinkedList<Car> carList);
}
