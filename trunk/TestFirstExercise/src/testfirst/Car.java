package testfirst;
import java.awt.Color;

public class Car
{
	public enum Type
	{
		sportscar, hatchback, suv, minivan
	};

	private Color m_color;
	private int m_mpg;
	private Type m_type;
	private int m_doors;
	private double m_price;

	public Car(Color color, int mpg, Type type, int doors, double price)
	{
		super();
		m_color = color;
		m_mpg = mpg;
		m_type = type;
		m_doors = doors;
		m_price = price;
	}
	
	public Color getColor()
	{
		return m_color;
	}

	public void setColor(Color color)
	{
		m_color = color;
	}

	public int getMpg()
	{
		return m_mpg;
	}

	public void setMpg(int mpg)
	{
		m_mpg = mpg;
	}

	public Type getType()
	{
		return m_type;
	}

	public void setType(Type type)
	{
		m_type = type;
	}

	public int getDoors()
	{
		return m_doors;
	}

	public void setDoors(int doors)
	{
		m_doors = doors;
	}

	public double getPrice()
	{
		return m_price;
	}

	public void setProce(double price)
	{
		m_price = price;
	}
}
