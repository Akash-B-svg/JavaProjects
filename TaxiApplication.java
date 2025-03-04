import java.io.*;
import java.util.*;
class NoServiceException extends Exception
{
String msg;
NoServiceException()
{
super();
}

NoServiceException(String msg)
{
super(msg);
this.msg=msg;
}

public String toString()
{
return "NoServiceException: "+msg;
}
}

class Taxi
{
int tNo,bId,cId,pTime,dTime;
char from='A',to='A';
double amount,totEarn;

Taxi()
{

}

Taxi(int tNo)
{
this.tNo=tNo;
}

Taxi(Taxi e)
{
tNo=e.tNo;
bId=e.bId;
cId=e.cId;
from=e.from;
to=e.to;
pTime=e.pTime;
dTime=e.dTime;
amount=e.amount;
totEarn=e.totEarn;
}

public String toString()
{
return bId+"\t"+cId+"\t"+from+"\t"+to+"\t"+pTime+"\t"+dTime+"\t"+amount;
}
}

class TaxiApp
{
static int b_Id=123;
static int c_Id=456;
Taxi at=null;
List<Taxi> taxi;
List<Taxi> trips=new ArrayList<>();
TaxiApp(int n)
{
Taxi t[]=new Taxi[n];
taxi=new ArrayList<>(n);
for(int i=0;i<n;i++)
{
t[i]=new Taxi(i+1);
taxi.add(t[i]);
}
}

char fPoint,tPoint;
int pTime;

public boolean checking(char pp)
{
if(pp>='A' && pp<='F')
return true;
return false;
}

public Taxi findLowEarings(List<Taxi>avail)
{
Taxi mt=null;
for(Taxi k : avail)
if(k.dTime<=pTime)
mt=k;

for(Taxi k : avail)
if(k.totEarn<mt.totEarn)
mt=k;
return mt;
}

public Taxi findLowDist(List<Taxi>avail)
{
int min=0;
Taxi mt=null;
for(Taxi k : avail)
if(k.dTime<=pTime)
{
min=Math.abs(k.to-fPoint);
mt=k;
}
int amin=Math.abs(mt.to-fPoint);
for(Taxi k : avail)
if(min<amin)
{
min=amin;
mt=k;
}
return mt;
}

void booking()throws Exception
{
at=null;
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter a Pickup point: ");
fPoint=(char)br.read();
br.readLine();
if(!checking(fPoint))
throw new NoServiceException("Invalid Pickup point");
System.out.println("Enter a Drop point: ");
tPoint=(char)br.read();
br.readLine();
if(!checking(tPoint))
throw new NoServiceException("Invalid Drop point");
System.out.println("Enter a Pickup time: ");
pTime=Integer.parseInt(br.readLine());
List<Taxi> avail=new ArrayList<>();
for(Taxi k : taxi)
if(k.to==fPoint && k.dTime<=pTime)
avail.add(k);
if(avail.size()>0)
at=new Taxi(findLowEarings(avail));
else
{
for(Taxi k : taxi)
if(k.dTime<=pTime)
avail.add(k);
if(avail.size()>0)
at=new Taxi(findLowDist(avail));
}
if(at==null)
throw new NoServiceException("Booking Rejected");
System.out.println("Taxi can be alloted");
System.out.println("Taxi No - "+at.tNo);
at.bId=b_Id++;
at.cId=c_Id++;
at.from=fPoint;
at.to=tPoint;
at.pTime=pTime;
at.dTime=at.pTime+Math.abs(fPoint-tPoint);
at.amount=100+(((tPoint-fPoint)*15)-5)*10;
at.totEarn=at.totEarn+at.amount;
trips.add(at);

for(Taxi m : taxi)
if(m.tNo==at.tNo)
{
taxi.remove(m);
break;
}
taxi.add(at);
}

void disp()
{
for(Taxi k : taxi)
if(k.totEarn!=0.0)
{
System.out.println("Taxi No. - "+k.tNo+"\tTotal Earnings: "+k.totEarn);
dispAll(k);
}
}

void dispAll(Taxi e)
{
for(Taxi m : trips)
if(m.tNo==e.tNo)
System.out.println(m);
}
}

class TaxiDriver
{
public static void main(String ...arg)throws Exception
{
int n,ch=0;
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter No of taxies: ");
n=Integer.parseInt(br.readLine());
TaxiApp ta=new TaxiApp(n);
do
{
try
{
System.out.println("Welcome to Taxi stand....");
System.out.println("1.......Booking");
System.out.println("2.......Display");
System.out.println("3.......Exit");
System.out.println("Enter your choice: ");
ch=Integer.parseInt(br.readLine());
switch(ch)
{
case 1:
ta.booking();
break;
case 2: 
ta.disp();
break;
case 3: 
break;
default:
System.out.println("Invalid choice");
}
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}while(ch!=3);
}
}