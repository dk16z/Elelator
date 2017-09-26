package domain;

import java.util.LinkedList;

public class Elevator implements Runnable {

	private ElevatorState EleSt=ElevatorState.CLOSE;
	private LinkedList<Persons> upList=new LinkedList<Persons>();
	private LinkedList<Persons> downList=new LinkedList<Persons>();
	private LinkedList<Persons> inList=new LinkedList<Persons>();
	public static final int MostWeight=100;
	public static final int MostPeople=20;
	public static final int totalLayer=10;
	private int nowStair=1;
	private int nowPeople=0;
	private int nowWeight=0;
	public Elevator(LinkedList<Persons> upList, LinkedList<Persons> downList) {
		super();
		this.upList = upList;
		this.downList = downList;
		
		
		
		
	}
	@Override
	public void run() {
		boolean flag=false;
		while(true)
		{
			System.out.println("当前电梯处于第"+nowStair+"层");
			outElevator();
			if(EleSt==ElevatorState.DOWN)
				onElevatorForDown();
			else
				onElevatorForUp();
			if(!anyoneExist())
			{
				EleSt=ElevatorState.CLOSE;
				flag=true;
				
			}
			if(flag)
				break;
			nextStep();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		System.out.println("已经没有要上下的乘客了");
	
		
	}
	
	//准备当前楼层下电梯的人下电梯
	public void outElevator()
	{
		if(!inList.isEmpty())
		{
			for(int i=0;i<inList.size();i++)
			{
				if(inList.get(i).getEndFloor()==nowStair)
					{
						System.out.println("编号为"+(inList.get(i).getId())+"下了电梯");
						nowWeight-=inList.get(i).getWeight();
						nowPeople--;
						inList.remove(i);						
						i--;
						
						
						
					
					}
						
				
			}
			
			
			
			
		}
			
	}
	//在电梯上行的过程中上电梯
	public void onElevatorForUp()
	{
		upList=onElevator(upList);	
	}
	//在电梯下降的时候上电梯
	public void onElevatorForDown()
	{
		downList=onElevator(downList);	
	}
	//上电梯
	public LinkedList<Persons> onElevator(LinkedList<Persons> list)
	{
		
		
		for(int i=0;i<list.size();i++)
		{
			int restWeight=MostWeight-nowWeight;
			int restPeople=MostPeople-nowPeople;
			if(list.get(i).getStartFloor()==nowStair)
			{
				
				if((restWeight-list.get(i).getWeight())<0||(restPeople-1)<0)
				{
					System.out.println("电梯超重或者人数过多，不能再继续上乘客了");
					return list;
				}	
				else
				{
					nowWeight=+list.get(i).getWeight();			
					nowPeople++;
					System.out.println("编号为"+list.get(i).getId()+"的乘客上了电梯");
					inList.add(list.get(i));
					
					list.remove(i);
					i--;
					
				}
				
			}
			
			
		}
		return list;
		
	}
	//判断下一步的停靠位置
	public void nextStep()
	{
		if(EleSt==ElevatorState.UP)
		{
			if(nowStair<10&&(anyoneTopper()||!inList.isEmpty()))
			{
				nowStair++;
				System.out.println("现在正在前往第"+nowStair+"层");
				
			}
			else
			{
			
				EleSt=ElevatorState.DOWN;
				System.out.println("电梯的方向转变为向下");
			}
		}
		
		else if(EleSt==ElevatorState.DOWN)
		{
			if(nowStair>1&&(anyoneLower()||!inList.isEmpty()))
			{
				nowStair--;
				System.out.println("现在正在前往第"+nowStair+"层");
			}
			else
			{
				EleSt=ElevatorState.UP;
				System.out.println("电梯的方向转变成向上");
			}
		}
		else
		{
			
			nowStair++;
			System.out.println("现在正在前往第"+nowStair+"层");
			EleSt=ElevatorState.UP;
			System.out.println("目前的电梯状态是向上");
			
		}
			
		
		
	}
	//判断电梯中和等待的队列中还有没有人
	public boolean anyoneExist()
	{
		if(inList.isEmpty()&&upList.isEmpty()&&downList.isEmpty())
			return false;
		else
			return true;
		
	}
	
	//判断本层上面的楼层还有没有人等电梯
	public boolean anyoneTopper()
	{
		for(Persons p:upList)
		{
			if(p.getEndFloor()>nowStair)
				return true;
			
		}
		
		for(Persons p:downList)
		{
			if(p.getStartFloor()>nowStair)
				return true;
			
		}

		return false;
		
		
		
	}
	
	//判断本层下面的楼层还有没有人等电梯
	public boolean anyoneLower()
	{
		for(Persons p: upList)
		{
			if(p.getStartFloor()>nowStair)
				return true;
			
		}
		
		for(Persons p:downList)
		{
			if(p.getEndFloor()>nowStair)
				return true;
			
		}

		return false;
		
		
		
	}
	
	
	
	
	

}
