package mig.chen.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import mig.chen.services.DisplayService;

/**
 * @author Ethan Chain 陈科军
 **/
@RestController
@RequestMapping("/")
public class WebController {

	private final DisplayService ds;
	
	public WebController(DisplayService ds) {
		this.ds = ds;
	}

	@GetMapping("/search")
	public String search(@RequestParam(value = "name",required = false) String name,Model m) {
		Map<String, Object> map=ds.search(name);
		List<Map<String, Object>> list=ds.list(name);
		Map<String, Object> result = new HashMap<>();
		result.put("map", map);
		result.put("list", list);
		return JSON.toJSONString(result);
	}
	
	@GetMapping("/ask")
	public String test(@RequestParam(value = "question",required = false) String question,Model m) {
		List<Map<String, Object>> map = ds.anwserMap(question);
		List<List<Map<String, Object>>> list = ds.anwserList(question);
		Map<String, Object> result = new HashMap<>();
		result.put("map", map);
		result.put("list", list);
		return JSON.toJSONString(result);
	}
	
	@GetMapping("/findRel")
	public String findRel(@RequestParam(value = "startNode",required = false) String startNode,
			@RequestParam(value = "relationship") String relationship,
			@RequestParam(value = "endNode",required = false) String endNode, 
			Model m) throws SQLException {
		Map<String, Object> map=ds.findRel(startNode, relationship, endNode);
		List<Map<String, Object>> list=ds.relList(startNode, relationship, endNode);
		Map<String, Object> result = new HashMap<>();
		result.put("map", map);
		result.put("list", list);
		return JSON.toJSONString(result);
	}
	
	@GetMapping("/tree")
	public String mapTree() {
		String result=ds.tree();
		//System.out.print(result);
		return result;
	}
	
	@GetMapping("/sort")
	public String sort(@RequestParam(value = "name",required = false) String name,Model m) {
		String result=ds.sort(name);
		//System.out.print(result);
		return result;
	}
	
	@GetMapping("/level")
	public String level(@RequestParam(value = "name",required = false) String name,Model m) {
		String result=ds.level(name);
		//System.out.print(result);
		return result;
	}
	
	
/*	
	@GetMapping("/delete")
	public String create(@RequestParam(value = "name",required = false) String name,Model m) {
		System.out.print(ds.deleteNode(name));
		return ds.deleteNode(name);
	}
	
	@GetMapping("/save")
	public String saveEntity(@RequestParam(value = "name") String name,Model m) {
		String result=ds.saveEntity(name);
		//System.out.print(result);
		return result;
	}
*/	
}
