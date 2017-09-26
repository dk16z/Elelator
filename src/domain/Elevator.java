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
			System.out.println("��ǰ���ݴ��ڵ�"+nowStair+"��");
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
		System.out.println("�Ѿ�û��Ҫ���µĳ˿���");
	
		
	}
	
	//׼����ǰ¥���µ��ݵ����µ���
	public void outElevator()
	{
		if(!inList.isEmpty())
		{
			for(int i=0;i<inList.size();i++)
			{
				if(inList.get(i).getEndFloor()==nowStair)
					{
						System.out.println("���Ϊ"+(inList.get(i).getId())+"���˵���");
						nowWeight-=inList.get(i).getWeight();
						nowPeople--;
						inList.remove(i);						
						i--;
						
						
						
					
					}
						
				
			}
			
			
			
			
		}
			
	}
	//�ڵ������еĹ������ϵ���
	public void onElevatorForUp()
	{
		upList=onElevator(upList);	
	}
	//�ڵ����½���ʱ���ϵ���
	public void onElevatorForDown()
	{
		downList=onElevator(downList);	
	}
	//�ϵ���
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
					System.out.println("���ݳ��ػ����������࣬�����ټ����ϳ˿���");
					return list;
				}	
				else
				{
					nowWeight=+list.get(i).getWeight();			
					nowPeople++;
					System.out.println("���Ϊ"+list.get(i).getId()+"�ĳ˿����˵���");
					inList.add(list.get(i));
					
					list.remove(i);
					i--;
					
				}
				
			}
			
			
		}
		return list;
		
	}
	//�ж���һ����ͣ��λ��
	public void nextStep()
	{
		if(EleSt==ElevatorState.UP)
		{
			if(nowStair<10&&(anyoneTopper()||!inList.isEmpty()))
			{
				nowStair++;
				System.out.println("��������ǰ����"+nowStair+"��");
				
			}
			else
			{
			
				EleSt=ElevatorState.DOWN;
				System.out.println("���ݵķ���ת��Ϊ����");
			}
		}
		
		else if(EleSt==ElevatorState.DOWN)
		{
			if(nowStair>1&&(anyoneLower()||!inList.isEmpty()))
			{
				nowStair--;
				System.out.println("��������ǰ����"+nowStair+"��");
			}
			else
			{
				EleSt=ElevatorState.UP;
				System.out.println("���ݵķ���ת�������");
			}
		}
		else
		{
			
			nowStair++;
			System.out.println("��������ǰ����"+nowStair+"��");
			EleSt=ElevatorState.UP;
			System.out.println("Ŀǰ�ĵ���״̬������");
			
		}
			
		
		
	}
	//�жϵ����к͵ȴ��Ķ����л���û����
	public boolean anyoneExist()
	{
		if(inList.isEmpty()&&upList.isEmpty()&&downList.isEmpty())
			return false;
		else
			return true;
		
	}
	
	//�жϱ��������¥�㻹��û���˵ȵ���
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
	
	//�жϱ��������¥�㻹��û���˵ȵ���
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
