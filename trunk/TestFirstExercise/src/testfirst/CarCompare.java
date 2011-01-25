package testfirst;

import java.util.Comparator;

public class CarCompare implements Comparator
{
	private String m_type;
	
	public CarCompare(String type)
	{
		m_type = type;
	}
	
	@Override
	public int compare(Object arg0, Object arg1)
	{
		Car car1 = (Car) arg0;
		Car car2 = (Car) arg1;
		int carComp = 0;
		
		if(m_type.equals("MPG"))
		{
			if(car1.getMpg() > car2.getMpg())
			{
				carComp = 1;
			}
			else if(car1.getMpg() == car2.getMpg())
			{
				carComp = 0;
			}
			else 
			{
				carComp = -1;
			}
		}
		
		if(m_type.equals("Doors"))
		{
			if(car1.getDoors() > car2.getDoors())
			{
				carComp = 1;
			}
			else if(car1.getDoors() == car2.getDoors())
			{
				carComp = 0;
			}
			else 
			{
				carComp = -1;
			}
		}
		
		return carComp;
	}	
}
