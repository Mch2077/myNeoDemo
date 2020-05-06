package mig.chen.services;

import java.sql.SQLException;
import java.util.*;

import mig.chen.domain.Environment;
import mig.chen.repositories.CqlRepository;
import mig.chen.util.BayesUtil;
import mig.chen.util.ConfigNeo4jDBUtil;
import mig.chen.util.EchartsUtil;
import mig.chen.util.PinYinUtil;
import mig.chen.util.TreeUtil;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import shapeless.newtype;

import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

@Service
public class DisplayService {

    private final static Logger LOG = LoggerFactory.getLogger(DisplayService.class);

	private final CqlRepository cql;
	public DisplayService(CqlRepository cql) {
		this.cql = cql;
	}
	
	/**
     * 根据节点名查询返回列表数据
     * @author Ethan Chain 陈科军
     * @param String 节点名,支持模糊匹配
     **/
	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchList(String name) {
    		Collection<Environment> result = cql.findGraph(".*"+name+".*");
     		List<Map<String, Object>> list=EchartsUtil.toListFormat(result);
        	return list;
    }
	
	/**
     * 根据节点名查询返回可视化图像数据
     * @author Ethan Chain 陈科军
     * @param String 节点名,支持模糊匹配
     **/
    @Transactional(readOnly = true)
    public Map<String, Object> searchMap(String name) {
    		Collection<Environment> result = cql.findGraph(".*"+name+".*");
     		Map<String, Object> map=EchartsUtil.toEchartsFormat(result);
        	return map ;
    }
       
    /**
     * 返回当前数据库的树形结构
     * @author Ethan Chain 陈科军
     **/
    @Transactional(readOnly = true)
    public String tree() {
    	try {
    		Collection<Environment> result = cql.findGraph("瀑布沟流域水电站环境");
     		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("treeData", TreeUtil.bulid(TreeUtil.bulidList(result), 0));
    		return JSON.toJSONString(map);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
    	return "ERROR!";
    }
 
    /**
     * 根据拼音首字母分类，返回各字母对应类别的节点名
     * @author Ethan Chain 陈科军
     * @param String 支持模糊匹配
     **/
    @Transactional(readOnly = true)
    public String sort(String name) {
    	try {
    		Collection<Environment> result = cql.findGraph(".*"+name+".*");
     		Map<String, List<String>> map=EchartsUtil.toSortMap(result);
        	return JSON.toJSONString(map) ;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
    	return "ERROR!";
    }
    
    /**
     * 上下级分类，返回当前节点的上下级节点名
     * @author Ethan Chain 陈科军
     * @param String 当前节点名,只支持完全匹配
     **/
    @Transactional(readOnly = true)
    public String level(String name) {
    	try {
    		List<Environment> upperLevel = cql.findUpperLevel(name);
    		List<Environment> lowerLevel = cql.findLowerLevel(name);
     		List<String> upperList = EchartsUtil.toLevelList(upperLevel);
     		List<String> lowerList = EchartsUtil.toLevelList(lowerLevel);
     		Map<String, List<String>> map = new HashMap<>();
     		map.put("UpperLevel", upperList);
     		map.put("LowerLevel", lowerList);
        	return JSON.toJSONString(map);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
    	return "ERROR!";
    }
    
    /**
     * 根据出入度节点类型名和关系名查询返回可视化图像数据
     * @author Ethan Chain 陈科军
     * @param String 出入度节点类型名和关系名,其中出入度名可以但不能同时为空(为空:一个值为"null"的字符串)
     * @throws SQLException 
     **/
    @Transactional(readOnly = true)
	public Map<String, Object> relMap(String startNode, String relationship, String endNode) throws SQLException {
    	Collection<Environment> result;
		if (relationship.equals("null")) {
			if (startNode.equals("null")) {
				result=cql.findRelWithoutRelAndStart(endNode);
			}else {
				if (endNode.equals("null")) {
					result=cql.findRelWithoutRelAndEnd(startNode);
				}else {
					result=cql.findRelWithoutRel(startNode, endNode);
				}
			}
			Map<String, Object> map = EchartsUtil.toEchartsFormat(result);
			return map;
		}else {
			String cqlString;
			if (startNode.equals("null")) {
				cqlString="Match p=(a)-[r:"+relationship+"]->(b) where b.name=~'.*"+endNode+".*' Return a.name,b.name,b.value ";
			}else {
				if (endNode.equals("null")) {
					cqlString="Match (a)-[r:"+relationship+"]->(b) where a.name=~'.*"+startNode+".*' Return a.name,b.name,b.value ";
				}else {
					cqlString="Match (a)-[r:"+relationship+"]->(b) where a.name=~'.*"+startNode+".*' And b.name=~'.*"+endNode+".*'  Return a.name,b.name,b.value ";
				}
			}
			List<Record> records = ConfigNeo4jDBUtil.findRel(cqlString);
			Map<String, Object> map = EchartsUtil.rel2Echarts(records, relationship);
			return map;
		}
	}
    
    /**
     * 根据出入度节点类型名和关系名查询返回列表数据
     * @author Ethan Chain 陈科军
     * @param String 出入度节点类型名和关系名,其中出入度名可以但不能同时为空(为空:一个值为"null"的字符串)
     * @throws SQLException 
     **/
	public List<Map<String, Object>> relList(String startNode, String relationship, String endNode) throws SQLException {
		Collection<Environment> result;
		if (relationship.equals("null")) {
			if (startNode.equals("null")) {
				result=cql.findRelWithoutRelAndStart(endNode);
			}else {
				if (endNode.equals("null")) {
					result=cql.findRelWithoutRelAndEnd(startNode);
				}else {
					result=cql.findRelWithoutRel(startNode, endNode);
				}
			}
			List<Map<String, Object>> list = EchartsUtil.toListFormat(result);
			return list;
		}else {
			String cqlString;
			if (startNode.equals("null")) {
				cqlString="Match p=(a)-[r:"+relationship+"]->(b) where b.name=~'.*"+endNode+".*' Return a.name,b.name,b.value ";
			}else {
				if (endNode.equals("null")) {
					cqlString="Match (a)-[r:"+relationship+"]->(b) where a.name=~'.*"+startNode+".*' Return a.name,b.name,b.value ";
				}else {
					cqlString="Match (a)-[r:"+relationship+"]->(b) where a.name=~'.*"+startNode+".*' And b.name=~'.*"+endNode+".*'  Return a.name,b.name,b.value ";
				}
			}
			List<Record> records = ConfigNeo4jDBUtil.findRel(cqlString);
			List<Map<String, Object>> list = EchartsUtil.rel2List(records,relationship);
			return list;
		}
	}
	
	/**
     * 根据问题查询返回可视化图像数据
     * @author Ethan Chain 陈科军
     * @param String 问题,支持模糊匹配
	 * @throws Exception 
     **/
    @Transactional(readOnly = true)
    public Map<String, Object> answerMap(String question) throws Exception {
    	PinYinUtil.addCustomDictionary();
    	ArrayList<String> standardList = BayesUtil.toFormatQuestion(question);
		Map<String, Object> result = new HashMap<>();
    	switch (standardList.get(0)) {
		case "0":
			result = searchMap(".*"+standardList.get(1)+".*");
			break;

		default:
			result = searchMap(".*瀑布沟水电站.*");
			break;
		}
        return result;
    }
    
    /**
     * 根据问题查询返回答案列表
     * @author Ethan Chain 陈科军
     * @param String 问题
     * @throws Exception 
     **/
    @Transactional(readOnly = true)
    public List<String> answerList(String question) throws Exception {
    	PinYinUtil.addCustomDictionary();
    	ArrayList<String> standardList = BayesUtil.toFormatQuestion(question);
		Collection<Environment> result = new ArrayList<>();
		List<String> list = new ArrayList<>();
    	switch (standardList.get(0)) {
		case "0":
			result = cql.findGraph(".*"+standardList.get(1)+".*");
			list = EchartsUtil.toAnswerListFormat(result);
			break;

		default:
			result = cql.findGraph(".*瀑布沟水电站.*");
			list = EchartsUtil.toAnswerListFormat(result);
			break;
		}
        return list;
    }
/*    
    @Transactional(readOnly = true)
    public String saveEntity(String name) {
    	try {
    		Map<String, String> map = new HashMap<String,String>();
    		map.put("name",name);
    		Environment entity=JSON.parseObject(JSON.toJSONString(map), Environment.class);
    		Environment result = cql.save(entity);
        	return JSON.toJSONString(result) ;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
    	return "BAD QUERY:Argument is null!";
    }
    
@Transactional(readOnly = true)
    public String updateNode(String name,String value) {
    	cql.updateNode(name,value);
    	
    	return JSON.toJSONString(result) ;
    }
    
    @Transactional(readOnly = true)
	public String deleteNode(String name) {
		Boolean exec = cql.deleteNode(name);
		if (exec!=false) {
			return "Success";
		}else {
			return "Fail";
		}
	}
*/
    
}