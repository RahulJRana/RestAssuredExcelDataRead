package DataDriven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class TestSample {

	//public static void main(String[] args) throws IOException {
	@Test
	void main() throws IOException {

		DataDrivenRead dr = new DataDrivenRead();
		ArrayList data = dr.getData("TestCaseName","RestAssured");
		System.out.println(data.get(0));

	}
	@Test
	public void newTestLibrary()
	{
		RestAssured.baseURI = "http://216.10.245.166";
	String resp =	given().header("Content-Type","application/json").body("		{\r\n"
				+ "			\"name\":\"Learn Appium Auto\",\r\n"
				+ "			\"isbn\":\"b11\",\r\n"
				+ "			\"aisle\":\"227\",\r\n"
				+ "			\"author\":\"John foe\"\r\n"
				+ "			}")
		.when().post("/Library/Addbook.php").then().extract().response().asPrettyString();
	System.out.println(resp);
	}
	
	@Test
	public void newTestLibUsingHashMaps()
	{
		RestAssured.baseURI = "http://216.10.245.166";
		HashMap<String,Object> td = new HashMap<String,Object>();
		td.put("name","Learnppium");
		td.put("isbn", "c23");
		td.put("aisle","324");
		td.put("author","John Cena");
	String resp =	given().header("Content-Type","application/json").body(td)
		.when().post("/Library/Addbook.php").then().extract().response().asPrettyString();
	System.out.println(resp);
	}
	@Test
	public void runUsingExcel() throws IOException
	{
		RestAssured.baseURI = "http://216.10.245.166"; 
		DataDrivenRead drd = new DataDrivenRead();
		ArrayList<String> data = drd.getData("RestAddBook","RestAssured");
		HashMap<String,Object> td = new HashMap<String,Object>();
		td.put("name",data.get(1).toString().trim());
		td.put("isbn", data.get(2).toString().trim());
		td.put("aisle",String.valueOf(data.get(3)));
		td.put("author",data.get(4).toString().trim());
		System.out.println("Excel TestData: " + data);
		System.out.println("Final Payload: " + td);
	String resp =	given().header("Content-Type","application/json").body(td)
		.when().post("/Library/Addbook.php").then().extract().response().asPrettyString();
	System.out.println(resp);
		
	}
}
