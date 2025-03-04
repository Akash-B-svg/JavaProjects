import java.io.*;
class EBBill implements Serializable
{
int house_number;
String name_of_tenant;
int starting_energymeter_reading,ending_energymeter_reading;
double bill_amount;

EBBill()
{
house_number=0;
name_of_tenant=null;
starting_energymeter_reading=0;
ending_energymeter_reading=0;
bill_amount=0.0;
}

EBBill(int house_number,String name_of_tenant,int starting_energymeter_reading,int ending_energymeter_reading)
{
this.house_number=house_number;
this.name_of_tenant=name_of_tenant;
this.starting_energymeter_reading=starting_energymeter_reading;
this.ending_energymeter_reading=ending_energymeter_reading;
}

public String toString()
{
return house_number+"\t"+name_of_tenant+"\t"+starting_energymeter_reading+"\t"+ending_energymeter_reading+"\t"+bill_amount;
}}

class EBApplication
{
EBBill e[]=null;
int houseno,Startmeterread,Endmeterread,n;
String Tenantname;

EBApplication(int n)
{
this.n=n;
}

void saveData()throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
if(e==null)
{
e=new EBBill[n];
for(int i=0;i<n;i++)
{
System.out.println("Enter "+(i+1)+" House Num,name,Starting & Ending reading: ");
houseno=Integer.parseInt(br.readLine());
Tenantname=br.readLine();
Startmeterread=Integer.parseInt(br.readLine());
Endmeterread=Integer.parseInt(br.readLine());
e[i]=new EBBill(houseno,Tenantname,Startmeterread,Endmeterread);
}
}
System.out.println("Enter a Filename for write: ");
String wfname=br.readLine();
FileOutputStream fos=new FileOutputStream(wfname);
ObjectOutputStream oos=new ObjectOutputStream(fos);
oos.writeObject(e);
oos.close();
fos.close();
}

void calcBill()throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter the House_no: ");
int House_num=Integer.parseInt(br.readLine());
for(int i=0;i<n;i++)
if(House_num==e[i].house_number)
{
e[i].bill_amount=(e[i].ending_energymeter_reading-e[i].starting_energymeter_reading)*10.0;
break;
}
saveData();
}

void read()throws Exception
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter a Filename for read: ");
String rfname=br.readLine();
FileInputStream fis=new FileInputStream(rfname);
ObjectInputStream ois=new ObjectInputStream(fis);
EBBill k[]=(EBBill[])ois.readObject();
for(EBBill m : k)
System.out.println(m);
ois.close();
fis.close();
}
}

class EBDriver
{
public static void main(String ...arg)throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
int ch,n;
System.out.println("Enter No oh houses: ");
n=Integer.parseInt(br.readLine());
EBApplication eb=new EBApplication(n);
do{
System.out.println("1----->SaveData");
System.out.println("2----->Calculation_bill");
System.out.println("3----->MenuDisplay");
System.out.println("4----->Exit");
System.out.println("Enter your choice: ");
ch=Integer.parseInt(br.readLine());
switch(ch)
{
case 1:
eb.saveData();
break;
case 2:
eb.calcBill();
break;
case 3:
try{
eb.read();
}catch(Exception ex){}
break;
case 4:
break;
default:
System.out.println("Invalid choice");
}
}while(ch!=4);
}
}