package student_information;

import java.io.Serializable;

public class Student implements Serializable
{     
	String id;
	String name;
	String department;
	String phoneNumber;

   public Student(String id, String name, String department, String phoneNumber)
   {
	   this.id=id;
	   this.name=name;
	   this.department=department;
	   this.phoneNumber=phoneNumber;
	}
   
	public String toString()
	{
		String description;
		description="-�й�: "+id +"\n";
		description+="-�̸�: "+name+"\n";
		description+="-�а�: "+department+"\n";
		description+="-�ڵ��� ��ȣ: "+phoneNumber+"\n";
		
		return description;
	}

	
	public String getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getDepartment()
	{
		return department;
	}
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
}


	
