package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.json.XML;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;

@RestController
public class MainController {

	@GetMapping(path = "/xmlToJSON")
	@ResponseBody
	public String xmltoJSON(@RequestParam("xmlInp") String xmlInp)
	{
		
		//converting xml to json
		JSONObject obj = XML.toJSONObject(xmlInp);
		
		System.out.println(obj.toString());
		return obj.toString();
	}  
	
	@GetMapping(path = "/jsonToXML")
	@ResponseBody
	public Document jsontoXML(@RequestParam("jsonInp") String jsonInp)
	{
		//String json_data = "{\"student\":{\"name\":\"Neeraj Mishra\", \"age\":\"22\"}}";
		System.out.println(jsonInp);
		JSONObject obj = new JSONObject(jsonInp);
		
		//converting json to xml
		String xml_data = XML.toString(obj);
		
		System.out.println(xml_data);
		//Use method to convert XML string content to XML Document object
        Document doc = convertStringToXMLDocument( xml_data );
		
		return doc;
	}
	
		
	private static Document convertStringToXMLDocument(String xmlString) 
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
}
