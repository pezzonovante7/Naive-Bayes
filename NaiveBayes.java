import java.io.*;
import java.util.*;
class NaiveBayesClassification
{
	 public double prob(List<List<String>> db,String key)
	 {
		 double res=0,count=0,nt=0;
		 nt=db.size()-1;
		 for(int i=1;i<db.size();i++)
		 {
			 List<String> temp=db.get(i);
			 String attr=temp.get(temp.size()-1);
			 if(attr.equals(key))
			 {
				 count++;
			 }
		 }
		 res=(double)(count/nt);
		 return res;
	 }
	 
//	 public double prob(List<List<String>> db,String attribute,String key)
//	 {
//		 double res=0,count=0,nt=0;
//		 nt=db.size()-1;
//		 List<String> temp=new ArrayList<>();
//		 temp=db.get(0);
//		 int j;
//		 for(int i=1;i<db.size();i++)
//		 {
//			  temp=db.get(i);
//			  String attr="";
//			  for(j=0;j<temp.size();j++)
//			  { 
//				 attr=temp.get(j);
//				 if(attr.equals(key))
//				 {
//					 count++;
//				 }
//			  }
//		 }
//		 res=(double)(count/nt);
//		 return res;
//	 }
//	 
	 // This function returns the class name of the given test tuple 
	 
	 public String getClass(List<List<String>> db, List<String> tuple)
	 {
		 String label="";
		 double respy=1,respn=1,py=0,pn=0,pyes=prob(db,"yes"),pno=prob(db,"no"),pattr;
		 List<String> attr=db.get(0);
		 List<String> temp=new ArrayList<>();
		 
		 for(int i=1;i<tuple.size()-1;i++)
		 {	 
			 py=0;
			 pn=0;
			 for(int j=1;j<db.size();j++)
			 {
				 temp=db.get(j);
				 if(temp.get(i).equals(tuple.get(i)))
				 {
					 if(temp.get(temp.size()-1).equals("yes"))
					 {
						 py=py+1;
					 }
					 else
					 {
						 pn=pn+1;
					 }
				 }
			 }
			 
			if(py!=0)
				respy*=(double)(py/(db.size()*pyes));
			if(pn!=0)
				respn*=(double)(pn/(db.size()*pno));
		 }
		 double tempy=prob(db,"yes");
		 double tempn=prob(db,"no");
		 
		 respy*=(double)(tempy);
		 respn*=(double)(tempn);
		 
		 if(respy>respn)
			 label="yes";
		 else 
			 label="no";
		 return label;
	 }
}
public class NaiveBayes {
	
	/*function for reading data from the file input.csv */
	
	public static void readData(List<List<String>> db,List<String> attribute_list)
	{
		Scanner sc=new Scanner(System.in);
		List<String> singles=new ArrayList<String>();	
		List<String> row=new ArrayList<String>();
		List<String> columns=new ArrayList<String>();
		int git=0;
		System.out.println("enter the filename");
		String fn=sc.next();
		File f=new File(fn);
		int cd=0;
		try
		{

			Scanner sc1=new Scanner(f);
			while(sc1.hasNext())
			{
				row=new ArrayList<String>();
				String gs=sc1.nextLine();
				StringTokenizer gt=new StringTokenizer(gs,",");
				int gj=0;
				while(gt.hasMoreTokens())
				{
					String mys=gt.nextToken();
					row.add(mys);
									
				}
				db.add(row);	
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);	
		}
	}

	/*function for displaying data in the file input.csv */
	
	public static void displayData(List<List<String>> db,List<String> attribute_list)
	{
		int i=0;
		System.out.println("**************************************************************************************************************************************************");
		int cnt=0;
		for(List<String> list:db)
		{
			
			for(String str:list)
			{
				if(i==0)
				{
					attribute_list.add(str);
				}
				
				cnt=20-str.length();
				while(cnt!=0)
				{
					cnt--;
					System.out.print(" ");		
				}
				System.out.print(str+"\t");
			}
			i++;
			System.out.println();
		}
		System.out.println("**************************************************************************************************************************************************");
	}
	
	/*Driver Code */
	
	public static void main(String args[])
	{
		// the .csv file is stored in this data structure except the attributes 
		List<List<String>> db=new ArrayList<List<String>>();
		
		// the attributes of the database are stored in this   
		List<String> attribute_list=new ArrayList<String>();
		
		NaiveBayesClassification d=new NaiveBayesClassification();

		readData(db,attribute_list);
		
		displayData(db,attribute_list);
		
		/*	System.out.println(d.prob(db, "yes"));
			System.out.println(d.prob(db, "no"));	*/
		
		/* 	modify the tuple to test the algorithm for different inputs
			here tuple is the sample or test data */
		List<String> tuple=db.get(8);
		tuple.remove(3);
		tuple.add(3, "yes");
		
		System.out.println("this is the test tuple used :\n"+tuple);
		System.out.println("the class of the given test tuple is :\n"+d.getClass(db, tuple));
	}
}
