import java.util.Scanner;
// Other imports go here
// Do NOT change the class name
class Node
{
	int x,y;
	char value;
	Node next;
	Node(int x,int y,char value)
	{
		this.x=x;
		this.y=y;
		this.value=value;
	}
}
public class Main{
    static char boundary[][]=new char[10][10];
	static Node head;
	static int score=0;

    public static void initialSnakeAndFood() //Set initial snake and food
	{
		head=new Node(7,5,'A');
		boundary[7][5]='A';
		Node temp=head;
		temp.next=new Node(7,4,'X');
		boundary[7][4]='X';
		temp.next.next=new Node(7,3,'X');
		boundary[7][3]='X';
		temp.next.next.next=new Node(6,3,'X');
		boundary[6][3]='X';
		temp.next.next.next.next=new Node(5,3,'Y');
		boundary[5][3]='Y';
		boundary[1][2]='F';
	}
    public static void Shift(int x,int y)//Movement of snake
	{
		Node new_head=new Node(x,y,'A');
		  new_head.next=head;
		  head=new_head;
		  head.next.value='X';
		
		Node temp=head;
		while(temp.next.value!='Y')
			temp=temp.next;
		
		  temp.value='Y';
		  boundary[temp.next.x][temp.next.y]=' ';
		  temp.next=null;
		change();
		print();
	}
	public static void change()
	{
		Node temp=head;
		while(temp!=null)
		{
			boundary[temp.x][temp.y]=temp.value;
			temp=temp.next;
		}
	}
	
	public static void foodEat(int x,int y)//Movement of snake after eating
	{
		Node new_head=new Node(x,y,'A');
		new_head.next=head;
		head=new_head;
		head.next.value='X';
		boundary[x][y]=' ';
		Node temp=head;
		int new_x=((int) (Math.random()*(8 - 1))) + 1;
		int new_y=((int) (Math.random()*(8 - 1))) + 1;
		while(temp!=null)
		{
			if((new_x==temp.x && new_y==temp.y) || new_x<1 ||new_x>8 ||new_y<1||new_y>8)
			{
				new_x=((int) (Math.random()*(8 - 1))) + 1;
				new_y=((int) (Math.random()*(8 - 1))) + 1;
				temp=head;
			}
			else
			temp=temp.next;
		}
		boundary[new_x][new_y]='F';
		score+=1;
		change();
		print();
	}
    public static void main(String[] args)
    {
		System.out.println(" Press 'A' or 'a' to move left");
		System.out.println(" Press 'D' or 'd' to move right");
		System.out.println(" Press 'W' or 'w' to move top");
		System.out.println(" Press 'S' or 's' to move bottom");
		System.out.println(" Press 'E' or 'e' to end");

        System.out.println(" A is head of snake");
		int i,j;
		for(i=0;i<10;i++)
			for(j=0;j<10;j++)
                boundary[i][j]=' ';
       initialSnakeAndFood();
	   print();
       game();
    }
    public static void game()
    {
         Scanner sc=new Scanner(System.in);
        char choice,store=0;
        int flag=0;
		do
		{
			choice=sc.next().charAt(0);
			flag=0;
			if(choice=='a'|| choice=='A')
			{
				int x=head.x;
				int y=head.y-1;
				if(y>=1)
				{
					if(boundary[x][y]==' ')
                    {
                        store=choice;
                        Shift(x,y);
                    }
					else if(boundary[x][y]=='F')
					{
						store=choice;
						foodEat(x,y);
					}
					else
					{
						if(store!='d' && store!='D')
							flag=1;
					}
				}
				else if(y<1)
					flag=1;
			}
			else if(choice=='d'|| choice=='D')
			{
				int x=head.x;
				int y=head.y+1;
				if(y<=8)
				{
					if(boundary[x][y]==' ')
                    {
                        store=choice;
                        Shift(x,y);
                    }
					
					else if(boundary[x][y]=='F')
                        {
                            store=choice;
                            foodEat(x,y);
                        }
					else
					{
						if(store!='a' && store!='A')
							flag=1;
					}
				}
				else if(y>8)
					flag=1;
			}
			else if(choice=='w' || choice=='W')
			{
				int x=head.x-1;
				int y=head.y;
				if(x>=1)
				{
					if(boundary[x][y]==' ')
                    {
                        store=choice;
                        Shift(x,y);
                    }
					else if(boundary[x][y]=='F')
                        {
                            store=choice;
                            foodEat(x,y);
                        }
							
					else
					{
						if(store!='s' && store!='S')
							flag=1;
					}
				}
				else if(x<1)
					flag=1;
			}
			else if(choice=='s' || choice=='S')
			{
				int x=head.x+1;
				int y=head.y;
				if(x<=8)
				{
					if(boundary[x][y]==' ')
                    {
                        store=choice;
                        Shift(x,y);
                    }
					else if(boundary[x][y]=='F')
                        {
                            store=choice;
                            foodEat(x,y);
                        }
					else
					{
						if(store!='w' && store!='W')
							flag=1;
					}
				}
				else if(x>8)
					flag=1;
			}
				
		}while(flag!=1 && choice!='e'&&choice!='E');
		if(flag==1)
			System.out.println("Oops! Can't move. Game Over!!");
		System.out.println("Your score is "+score);
        System.out.println("If you want to replay,Press Y");
        char replay=sc.next().charAt(0);
        if(replay=='Y' || replay=='y')
        {
            System.out.println(" Press 'a' or 'A' to move left");
		System.out.println(" Press 'd' or 'D' to move right");
		System.out.println(" Press 'w' or 'W' to move top");
		System.out.println(" Press 's' or 'S' to move bottom");
		System.out.println(" Press 'E' or 'e' to end");

        System.out.println(" A is head of snake");
		int i,j;
		for(i=0;i<10;i++)
		{
			for(j=0;j<10;j++)
            {
                boundary[i][j]=' ';
            }
        }
		score=0;
       initialSnakeAndFood();
	   print();
       game();
        }
            
    }
    public static void print() //Prints the game
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
                if(i==0 || j==0 || i==9 || j==9)
                System.out.print(".");
                else
				System.out.print(boundary[i][j]);
			}
			System.out.println();
		}
	}
}