package domain;

import java.util.LinkedList;
import java.util.Random;

public class ElevatorTset {
	
	private LinkedList<Persons> upList=new LinkedList<Persons>();
	private LinkedList<Persons> downList=new LinkedList<Persons>();
	private int pes=20;
	
	public ElevatorTset()
	{
		personFactory(pes);
		Elevator e=new Elevator(upList,downList);
		Thread t=new Thread(e);
		t.start();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new ElevatorTset();
		
		
	}
	
	//生成Persons并保存到相应的List
	public void personFactory(int num)
	{
		Random rand=new Random();
		PersonState Ps;
		int end;
		int start;
		int weight;
		
		for(int i = 0;i<num;i++)
		{
			while(true)
			{
			end=rand.nextInt(10)+1;
			start=rand.nextInt(10)+1;
			if(end!=start)
				break;
			}
			weight=100-rand.nextInt(50);
			
			if(end>start)
			{
				Ps=PersonState.UP;
				Persons pp=new Persons(start,end,weight,Ps);
				upList.addLast(pp);
			}
			else
			{
				Ps=PersonState.DOWN;
				Persons pp=new Persons(start,end,weight,Ps);
				downList.addLast(pp);
			}
			
		}
			
		
	}
	

}
