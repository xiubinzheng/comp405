package testfirst.tests;

import junit.framework.TestCase;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import testfirst.Car;
import testfirst.Car.Type;

public class AnalyzerTest extends TestCase
{
	private List<Car>	m_carList;

	// private TypeAnalyzer m_typeAnalyzer;
	// private ColorAnalyzer m_colorAnalyzer;

	public void setUp() throws Exception
	{
		super.setUp();
		m_carList = new ArrayList<Car>();
		m_carList.add(new Car(Color.red, 20, Type.hatchback, 2, 2500.00));
		m_carList.add(new Car(Color.blue, 22, Type.hatchback, 4, 3500.00));
		m_carList.add(new Car(Color.red, 21, Type.hatchback, 2, 8500.00));
		m_carList.add(new Car(Color.red, 12, Type.minivan, 7, 21500.00));
		m_carList.add(new Car(Color.orange, 25, Type.minivan, 6, 22500.00));
		m_carList.add(new Car(Color.orange, 35, Type.minivan, 5, 32500.00));
		m_carList.add(new Car(Color.cyan, 5, Type.sportscar, 2, 100000.00));
		m_carList.add(new Car(Color.gray, 100, Type.suv, 5, 30000.00));

		// m_typeAnalyzer = new TypeAnalyzer();
		// m_colorAnalyzer = new ColorAnalyzer();
	}

	public void tearDown() throws Exception
	{
		super.tearDown();
		m_carList.clear();
	}

	public void testIt()
	{
		// Type returned should be hatchback.

		// assertTrue(m_typeAnalyzer.analyzeThis(m_carList).equals(Type.hatchback));
		// Color returned should be blue.

		// assertTrue(m_colorAnalyzer.analyzeThis(m_carList).equals(Color.blue));

	}

}
