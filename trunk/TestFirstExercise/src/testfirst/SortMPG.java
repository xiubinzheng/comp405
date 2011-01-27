package testfirst;

import java.util.ArrayList;

public class SortMPG
{
	private CarCompare m_compare = new CarCompare("MPG");
	private SortType m_type;
	
	public enum SortType
	{
		ascending,descending
	};
	
	
	public SortMPG(SortType type)
	{
		m_type = type;
		
	}
	
	public void sort(ArrayList<Car> carList)
	{
		switch(m_type)
		{
			case ascending:
				int smallestCar = 0;
				for( int i = 0; i < carList.size()-1; i++)
				{
					for(int j = i + 1; j < carList.size()-1; j++)
					{
						if(m_compare.compare(carList.get(smallestCar), carList.get(j)) > 0)
						{
							smallestCar = j;
						}
					}
				}
		}
	}
	
}
