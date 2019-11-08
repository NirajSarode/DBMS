package mongo_prg;

import java.util.Iterator;
import java.util.Scanner;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;

public class C1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*MongoClient mongoClient = new MongoClient("localhost",27017);
			MongoCredential credential = MongoCredential.createCredential("pk", "", "".toCharArray()); // databse,username,password*/
		Scanner sc = new Scanner(System.in);
		MongoClient client = new MongoClient("localhost",27017);
		MongoDatabase db = client.getDatabase("31452");
		MongoCollection<Document>col = db.getCollection("sample");
		
		JSONObject obj = new JSONObject();
		obj.put("Name","Pratiksha");
		obj.put("age", 20);
		Document doc = Document.parse(obj.toString());
		col.insertOne(doc);
		
		
		
		col.updateOne(Filters.eq("Name","Pratiksha"),Updates.set("Name", "Nayan"));
		
		col.deleteOne(Filters.eq("Name","Pratiksha"));
		
		FindIterable<Document> iter = col.find();
		Iterator i = iter.iterator();
		
		while(i.hasNext()) {
			doc = (Document)i.next();
			String json = JSON.serialize(doc);
			obj=(JSONObject)JSONValue.parse(json); 
			System.out.println(obj.get("Name"));
		}
		
		
	}

}
