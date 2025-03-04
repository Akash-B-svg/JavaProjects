import java.util.*;
import java.io.*;
class IncorrectInputException extends Exception
{
String m;
IncorrectInputException(String m)
{
super(m);
this.m=m;
}
public String toString()
{
return "IncorrectInputException: "+m;
}
}

class Book
{
String author,title,publisher;
double price;
int stock;
Book()
{
author=null;
title=null;
price=0.0;
publisher=null;
stock=0;
}
Book(String author,String title,double price,String publisher,int stock)
{
this.author=author;
this.title=title;
this.price=price;
this.publisher=publisher;
this.stock=stock;
}
}

class BookShop 
{
int copies;
java.util.List<Book> list;
BookShop()
{
list=null;
}

BookShop(java.util.List<Book> list)
{
this.list=list;
}

void bookList(String btitle,String bauthor)throws IOException,IncorrectInputException
{
boolean f=false;
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
for(Book m : list)
if((m.title.equalsIgnoreCase(btitle)) && (m.author.equalsIgnoreCase(bauthor)))
{
System.out.println("Book_title: "+m.title+"\t"+"Book_author: "+m.author+"\t"+"Book_publisher: "+m.publisher+"\t"+"Book_Price: "+m.price+"\t"+"Book_Stock: "+m.stock);
try{
System.out.println("How many copies required?");
copies=Integer.parseInt(br.readLine());

if(copies<0)
throw new IncorrectInputException("Number of copies cannot be negative");

else if(copies<=m.stock)
{
int k=m.stock;
System.out.println("Book_title: "+m.title+"\t"+"Book_author: "+m.author+"\t"+"Book_publisher: "+m.publisher+"\t"+"Book_Price: "+m.price+"\t"+"Book_Cost: "+m.price*copies);
m.stock-=copies;
System.out.println("The Remainig Books: "+m.stock);
if(m.stock==0)
{
m.stock=k;
System.out.println("The New stack are updated: "+m.stock);
}
}

else if(copies>m.stock)
System.out.println("Required copies not in stock");
else
System.out.println("Invalid input...Please try again");
f=true;
}catch(NumberFormatException ne)
{
f=true;
System.out.println("Invalid input...Please try again");
}
}

if(f==false)
System.out.println("Book Not available");
}
}

class BookDriver
{
public static void main(String ...arg)throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
String author,title,publisher,ctitle,cauthor;
double price;
int copy,n;
boolean g;
System.out.println("Enter No of Books: ");
n=Integer.parseInt(br.readLine());
Book b[]=new Book[n];
for(int i=0;i<n;i++)
{
System.out.println("Enter Book Author,Title,Publisher,price,copy:");
author=br.readLine();
title=br.readLine();
publisher=br.readLine();
price=Double.parseDouble(br.readLine());
copy=Integer.parseInt(br.readLine());
b[i]=new Book(author,title,price,publisher,copy);
}
java.util.List<Book>l= new ArrayList<Book>();
for(Book k : b)
l.add(k);
BookShop bs=new BookShop(l);
do{
g=false;
System.out.println("Enter Customer Book Title,Author:");
ctitle=br.readLine();
cauthor=br.readLine();
try{
bs.bookList(ctitle,cauthor);
}catch(IncorrectInputException ie)
{
System.out.println(ie.getMessage());
}
System.out.println("Do you want to search for another book?(Yes/No)");
String ch=br.readLine();
if(ch.equalsIgnoreCase("Yes"))
g=true;
}while(g==true);
}
}