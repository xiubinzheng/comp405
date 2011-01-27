package testfirst.tests;

import java.util.ArrayList;
import testfirst.Car;
import junit.framework.TestCase;
import testfirst.Shopper;

public class CarPricingTest extends TestCase
{
	private ArrayList<Car>	m_carList	= new ArrayList<Car>();

	protected void setUp() throws Exception
	{
		m_carList.add(new Car(null, 23, null, 0, 1600.00));
		m_carList.add(new Car(null, 13, null, 0, 1900.00));
		m_carList.add(new Car(null, 50, null, 0, 1500.00));
		m_carList.add(new Car(null, 31, null, 0, 1600.00));
		m_carList.add(new Car(null, 70, null, 0, 4600.00));
		m_carList.add(new Car(null, 100, null, 0, 2600.00));
		m_carList.add(new Car(null, 25, null, 0, 6600.00));
		m_carList.add(new Car(null, 25, null, 0, 7600.00));
		m_carList.add(new Car(null, 45, null, 0, 8600.00));
	
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
		m_carList.clear();
	}

	public void testMyCode()
	{
		Shopper m_shopper = new Shopper();
		Car cheapest;
		// finds the cheapest car
		cheapest = m_shopper.getCheapestCar(m_carList);
		assertTrue(cheapest.getPrice() == 1500.00);
		System.out.println(cheapest.getPrice());
	}

}