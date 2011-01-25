package testfirst;

import java.awt.Color;
import java.util.ArrayList;

public class Analyzer
{
	private ArrayList <Car> m_total;
	private ArrayList <Car> m_type;
	private ArrayList <Car> m_color;
	
	public Analyzer(ArrayList <Car> carList)
	{
		m_total=carList;
	}
	
	public Color lowestAverageColor()
	{
		ArrayList<Car> remainingCarList = new ArrayList<Car>();
		remainingCarList=m_total;
		Color answer = null;
		double avg;
		
		return answer;
	}
	
	public Car.Type lowestAverageType()
	{
		return Car.Type.hatchback;
	}

}
