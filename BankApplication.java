import java.io.*;
class Account
{
protected int accnumber;
protected double balance;
Account(int accnumber,double balance)
{
this.accnumber=accnumber;
this.balance=balance;
System.out.println("Your Acc_number is: "+accnumber);
System.out.println("Your Current_Balance: is: "+balance);
}
}
class SBAccount extends Account
{
SBAccount(int accountnumber,double init_balance)
{
super(accountnumber,init_balance);
}
public void deposit(double amount)
{
if(amount > 0)
{
System.out.println("Your Current_Balance: is: "+balance);
balance=balance+amount;
System.out.println("Your New_Balance: is: "+balance);
}
}
public void withdraw(double amount)
{
if(balance-amount > 1000)
{
System.out.println("Your Current_Balance: is: "+balance);
balance=balance-amount;
System.out.println("Your New_Balance: is: "+balance);
}
else
System.out.println("Insufficient_Amount");
}
public void calc_interest()
{
double Interest=4*balance/1200;
balance=balance+Interest;
System.out.println("Your Calc_Interest: is: "+Interest);
System.out.println("Your New_Balance: is: "+balance);
}
}
class FDAccount extends Account
{
protected int period;
FDAccount(int accno,int period,double depositamount)
{
super(accno,depositamount);
this.period=period;
}
public double calc_interest()
{
return 8.25*balance*period/1200;
}
public void close()
{
double Interest=calc_interest();
balance=balance+Interest;
System.out.println("Your New_Balance: is: "+balance);
System.out.println("Your Cal_Interest: is: "+Interest);
}
}
class Customer
{
private int cust_id;
private String name,address;
private SBAccount sb=null;
private FDAccount fd=null;
private static int sno=5894;
private static int fno=7894;
Customer(int cust_id,String name,String address)
{
this.cust_id=cust_id;
this.name=name;
this.address=address;
System.out.println("Your Customer_Id: is: "+cust_id);
}
public void createAccount(int type)throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
if(type==1)
{
System.out.println("Enter the amount: ");
double amount=Double.parseDouble(br.readLine());
sb=new SBAccount(sno,amount);
sno++;
}
else if(type==2)
{
System.out.println("Enter the amount,period: ");
double amount=Double.parseDouble(br.readLine());
int period=Integer.parseInt(br.readLine());
fd=new FDAccount(sno,period,amount);
fno++;
}
}

public void transaction(int type)throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
if(type==1)
{
System.out.println("Enter the amount: ");
double amount=Double.parseDouble(br.readLine());
sb.withdraw(amount);
}
else if(type==2)
{
System.out.println("Enter the amount: ");
double amount=Double.parseDouble(br.readLine());
sb.deposit(amount);
}
else if(type==3)
{
sb.calc_interest();
}
else if(type==4)
{
fd.close();
}
}
}
class Bank
{
public static void main(String arg[])throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
int ch1,ch2,ch3,cid,i=0;
Customer c[]=new Customer[5]; 
do
{
System.out.println("Welcome to Akash Bank");
System.out.println("1---->SB_Bank");
System.out.println("2---->FD_Bank");
System.out.println("3---->Exit");
System.out.println("Enter your choice: ");
ch1=Integer.parseInt(br.readLine());
switch(ch1)
{
case 1:
do
{
System.out.println("Welcome to SB_Bank");
System.out.println("1---->Account Opening");
System.out.println("2---->Amount Withdraw");
System.out.println("3---->Amount Deposit");
System.out.println("4---->Interest_Calculation");
System.out.println("5---->Exit");
System.out.println("Enter your choice: ");
ch2=Integer.parseInt(br.readLine());
switch(ch2)
{
case 1:
System.out.println("Enter your Name,Address: ");
String Name=br.readLine();
String Address=br.readLine();
c[i]=new Customer(i,Name,Address);
c[i].createAccount(1);
i++;
break;

case 2:
System.out.println("Enter the Customer_id: ");
cid=Integer.parseInt(br.readLine());
c[cid].transaction(1);
break;

case 3:
System.out.println("Enter the Customer_id: ");
cid=Integer.parseInt(br.readLine());
c[cid].transaction(2);
break;

case 4:
System.out.println("Enter the Customer_id: ");
cid=Integer.parseInt(br.readLine());
c[cid].transaction(3);
break;

case 5:
break;

default:
System.out.println("Invalid_Choice");
}
}while(ch2!=5);
break;

case 2:
do
{
System.out.println("Welcome to FD_Bank");
System.out.println("1---->Account Opening");
System.out.println("2---->Account Closing");
System.out.println("3---->Exit");
System.out.println("Enter your choice: ");
ch3=Integer.parseInt(br.readLine());
switch(ch3)
{
case 1:
System.out.println("Enter your Name,Address: ");
String Name=br.readLine();
String Address=br.readLine();
c[i]=new Customer(i,Name,Address);
c[i].createAccount(2);
i++;
break;

case 2:
System.out.println("Enter the Customer_id: ");
cid=Integer.parseInt(br.readLine());
c[cid].transaction(4);
break;

case 3:
break;

default:
System.out.println("Invalid_Choice");
}
}while(ch3!=3);
case 3:
break;
default:
System.out.println("Invalid_Choice");
}
}while(ch1!=3);
}
}