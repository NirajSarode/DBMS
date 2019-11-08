package mongo7;

import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class B7 {

	MongoClient client=null;
	MongoCollection collection=null;
	public B7()
	{
		try
		{
			client=new MongoClient(new MongoClientURI("mongodb://te3310:te3310@127.0.0.1/te3310db?authmode=scram-sha1"));
			MongoDatabase database=client.getDatabase("te3310db");
			collection=database.getCollection("b7");
			System.out.println("Connected");
		}
		catch(Exception e)
		{
			
		}
	}
	public void insertDoc()
	{
		JSONObject obj=new JSONObject();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Name");
		obj.put("name", sc.nextLine());
		System.out.println("Enter age");
		obj.put("age", sc.nextInt());
		collection.insertOne(Document.parse(obj.toString()));
	}
	public void deleteDoc()
	{
		System.out.println("Enter name");
		Scanner sc=new Scanner(System.in);
		collection.deleteOne(Filters.eq("name", sc.nextLine()));
	}
	public void editDoc()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name");
		String name=sc.nextLine();
		System.out.println("Enter new age");
		int age=sc.nextInt();
		Bson filter=new Document("name",name);
		Bson newobj=new Document("age",age);
		Bson updatedoc=new Document("$set",newobj);
		collection.updateOne(filter, updatedoc);
	}
	public void fun()
	{
		Scanner sc=new Scanner(System.in);
		do
		{
			System.out.println("1.Add document\n2.delete document\n3.edit document\n4.Exit");
			int ch=sc.nextInt();
			switch(ch)
			{
				case 1: insertDoc();
						break;
				case 2: deleteDoc();
						break;
				case 3: editDoc();
						break;
				case 4: return;
			}
		}while(true);
	}
	public static void main(String[] args) {
		B7 obj=new B7();
		obj.fun();
	}

}
