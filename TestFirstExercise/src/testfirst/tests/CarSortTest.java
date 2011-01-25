package testfirst.tests;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import testfirst.Car;
import testfirst.Car.Type;

import junit.framework.TestCase;

public class CarSortTest extends TestCase
{
	private List<Car> m_carList;
	private List<Car> m_carListSorted;

	protected void setUp() throws Exception
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
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
		m_carList.clear();
	}

	/*public void testMyCode()
	{
		m_carListSorted = SortMPG(m_carList, ascending);		
		assertTrue(m_carListSorted.size() == m_carList.size());
		for(int i=0; i < m_carListSorted.size()-1; i++)
		{		
			assertTrue(m_carListSorted.get(i+1).getMpg() >= m_carListSorted.get(i).getMpg());
		}
		
		m_carListSorted = SortMPG(m_carList, descending);		
		assertTrue(m_carListSorted.size() == m_carList.size());
		for(int i=0; i < m_carListSorted.size()-1; i++)
		{		
			assertTrue(m_carListSorted.get(i+1).getMpg() <= m_carListSorted.get(i).getMpg());
		}
		
		m_carListSorted = SortDoors(m_carList, ascending);		
		assertTrue(m_carListSorted.size() == m_carList.size());
		for(int i=0; i < m_carListSorted.size()-1; i++)
		{		
			assertTrue(m_carListSorted.get(i+1).getDoors() >= m_carListSorted.get(i).getDoors());
		}
		
		m_carListSorted = SortDoors(m_carList, descending);		
		assertTrue(m_carListSorted.size() == m_carList.size());
		for(int i=0; i < m_carListSorted.size()-1; i++)
		{		
			assertTrue(m_carListSorted.get(i+1).getDoors() <= m_carListSorted.get(i).getDoors());
		}
		
	}*/
	
}

