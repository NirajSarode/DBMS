package B6;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class B6 {
	MongoClient mongo=null;
	MongoCollection collection=null;
	JSONArray arr=null;
	public B6()
	{
		mongo=new MongoClient(new MongoClientURI("mongodb://te3310:te3310@127.0.0.1:27017/te3310db?authmode=scram-sha1"));
		MongoDatabase database=mongo.getDatabase("te3310db");
		collection=database.getCollection("b6");
		System.out.println("Connected");
		arr=new JSONArray();
	}
	public void encode()
	{
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.println("Enter number of records");
			int x=sc.nextInt();
			for(int i=0;i<x;i++)
			{
				JSONObject obj=new JSONObject();
				System.out.println("Enter name");
				sc.nextLine();
				obj.put("name",sc.nextLine());
				System.out.println("Enter age");
				obj.put("age",sc.nextInt());
				System.out.println("Enter salary");
				obj.put("Salary", sc.nextDouble());
				arr.add(obj);
			}
			/*for(int i=0;i<arr.size();i++)
			{
				Document doc=Document.parse(arr.get(i).toString());
				collection.insertOne(doc);
			}*/
			List ldoc=new ArrayList();
			for(int i=0;i<arr.size();i++)
			{
				Document doc=Document.parse(arr.get(i).toString());
				ldoc.add(doc);
			}
			collection.insertMany(ldoc);
		}
		catch(Exception e)
		{
			
		}
	}
	public void decode()
	{
		try
		{
			for(int i=0;i<arr.size();i++)
			{
				JSONObject obj1=(JSONObject)arr.get(i);
				System.out.println("name  :"+obj1.get("name"));
				System.out.println("age  :"+obj1.get("age"));
				System.out.println("salary  :"+obj1.get("Salary"));
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	public static void main(String[] args) {
		B6 obj=new B6();
		obj.encode();
		obj.decode();
	}

}
