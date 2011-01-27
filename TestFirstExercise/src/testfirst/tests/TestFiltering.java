package testfirst.tests;

import testfirst.Car;
import testfirst.ColorFilter;
import testfirst.TypeFilter;

import java.awt.Color;
import java.util.*;

import javax.imageio.spi.ServiceRegistry.Filter;

import junit.framework.TestCase;

public class TestFiltering extends TestCase 
{
	TypeFilter typeFilter;
	ColorFilter colorFilter;
	ArrayList <Car> carList;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		carList = new ArrayList <Car>();
		carList.add(new Car(Color.red, 2000, Car.Type.hatchback, 4, 150000));
		carList.add(new Car(Color.blue, 2000, Car.Type.hatchback, 0, 150000));
		carList.add(new Car(Color.yellow, 2000, Car.Type.sportscar, 20, 150000));
		carList.add(new Car(Color.orange, 2000, Car.Type.minivan, 8, 150000));
		carList.add(new Car(Color.black, 2000, Car.Type.minivan, 7, 150000));
		carList.add(new Car(Color.white, 2000, Car.Type.sportscar, (new Double(3.1415)).intValue(), 150000));
		
		typeFilter = new TypeFilter();
		colorFilter = new ColorFilter();
		
		
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
		carList.clear();
	}
	
	public void testThis()
	{	
		ArrayList<Car> carListFiltered = new ArrayList<Car>();
		ArrayList<Car> carListFiltered_2 = new ArrayList<Car>();
		
		typeFilter.filter(Car.Type.hatchback, carList, carListFiltered);
		
		for(Car c : carListFiltered) 
		{
			assertTrue(c.getType().equals(Car.Type.hatchback));
		}
		
		colorFilter.filter(Color.red, carList, carListFiltered);
		for(Car c : carListFiltered) 
		{
			assertTrue(c.getColor().equals(Color.red));
		}
		colorFilter.filter(Color.red, carList, carListFiltered);
		typeFilter.filter(Car.Type.hatchback, carListFiltered, carListFiltered_2);
		for(Car c : carListFiltered_2) 
		{
			assertTrue(c.getType().equals(Car.Type.hatchback));
			assertTrue(c.getColor().equals(Color.red));
		}
	}
}
