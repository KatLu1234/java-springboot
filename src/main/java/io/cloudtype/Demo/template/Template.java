package io.cloudtype.Demo.template;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class Template {
	
	private static ObjectMapper mapper = new ObjectMapper();

	private static Map<String, ObjectNode> templates = getTemplates();
	private static ObjectNode mainTemplate = getMainTemplate();
	
	private ObjectNode current;
	private ArrayNode outputs;
	
	public Template() {
		current = mainTemplate.deepCopy();
		outputs = (ArrayNode) (current.get("template").get("outputs"));
	}
	
	public ObjectNode get() {
		return current.deepCopy();
	}
	
	public Template simpleText(String text) {
		ObjectNode template = templates.get("simpleText").deepCopy();
		((ObjectNode) template.get("simpleText")).put("text", text);
		outputs.add(template);
		return this;
	}
	
	private static Map<String, ObjectNode> getTemplates() {
		try {
			Map<String, ObjectNode> ret = new HashMap<>();
			Resource[] resources = Loader.getResources("static/templates/*.json");
			//System.out.println(resources[0]);
			
			for (Resource resource : resources) {
				
				//System.out.println(resource.getFilename());
				if (resource.getFilename().indexOf(".json") == -1) continue;
				String name = resource.getFilename().replace(".json", "");
				if (name.equals("template")) continue;  
				ObjectNode data = mapper.readValue(resource.getInputStream(), ObjectNode.class);
				ret.put(name, data);
			}
			
			return ret;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String toString() {
		return current.toString();
	}
	
	private static ObjectNode getMainTemplate() {
		try {
		Resource resource = Loader.getResource("static/templates/template.json");
		ObjectNode object = mapper.readValue(resource.getInputStream(), ObjectNode.class);
		//System.out.println(object);
		return object;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
