package mig.chen.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.Record;
import org.neo4j.driver.Value;


import mig.chen.domain.At;
import mig.chen.domain.Contain;
import mig.chen.domain.Environment;
import mig.chen.domain.Is;
import mig.chen.domain.StrongNegativeCorrelation;
import mig.chen.domain.StrongPositiveCorrelation;
import mig.chen.domain.WeakNegativeCorrelation;
import mig.chen.domain.WeakPositiveCorrelation;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author Ethan Chain 陈科军
 **/
public class EchartsUtil {
	/**
     * 将节点查询结果转为可视化数据格式
     * @author Ethan Chain 陈科军
     **/
	public static Map<String, Object> toEchartsFormat(Collection<Environment> evs) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		Iterator<Environment> result = evs.iterator();
		while (result.hasNext()) {
			Environment ev = result.next();
			Map<String, Object> EVOmap=mapData("name", ev.getName(), "color",ev.getColor());
			if (nodes.indexOf(EVOmap)== -1) {
				nodes.add(EVOmap);
			}
			int source = nodes.indexOf(EVOmap);
			
			if(ev.getContainer()!=null) {
				for (Contain container : ev.getContainer()) {
					Map<String, Object> eviMap = mapData("name", container.getEVI().getName(), 
							"color",ev.getColor());
					int target = nodes.indexOf(eviMap);
					if (target == -1) {
						nodes.add(eviMap);
						target = nodes.indexOf(eviMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",container.getName()));
				}
			}
			
			if(ev.getPropertiesList()!=null) {
				for (Is is : ev.getPropertiesList()) {
					Map<String, Object> propertyMap = mapData("name", is.getProperty().getValue(), 
							"color",is.getProperty().getColor());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",is.getName()));
				}
			}
			
			if(ev.getWpcList()!=null) {
				for (WeakPositiveCorrelation wpc : ev.getWpcList()) {
					Map<String, Object> propertyMap = mapData("name", wpc.getEVI().getName(), 
							"color",wpc.getEVI().getColor());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",wpc.getName()));
				}
			}
			
			if(ev.getSpcList()!=null) {
				for (StrongPositiveCorrelation spc : ev.getSpcList()) {
					Map<String, Object> propertyMap = mapData("name", spc.getEVI().getName(), 
							"color",spc.getEVI().getColor());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",spc.getName()));
				}
			}
			
			if(ev.getWncList()!=null) {
				for (WeakNegativeCorrelation wnc : ev.getWncList()) {
					Map<String, Object> propertyMap = mapData("name", wnc.getEVI().getName(), 
							"color",wnc.getEVI().getColor());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",wnc.getName()));
				}
			}
			
			if(ev.getSncList()!=null) {
				for (StrongNegativeCorrelation snc : ev.getSncList()) {
					Map<String, Object> propertyMap = mapData("name", snc.getEVI().getName(), 
							"color",snc.getEVI().getColor());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",snc.getName()));
				}
			}
			
			if(ev.getLocationList()!=null) {
				for (At at : ev.getLocationList()) {
					Map<String, Object> propertyMap = mapData("name", at.getLocation().getName(), 
							"color", at.getLocation().getColor());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",at.getName()));
				}
			}
		}
		return mapData("data", nodes, "links", rels);
	}
	
	/**
     * 将节点查询结果转为列表数据格式
     * @author Ethan Chain 陈科军
     **/
	public static List<Map<String, Object>> toListFormat(Collection<Environment> evs) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		List<Map<String, Object>> list = new ArrayList<>();
		Iterator<Environment> result = evs.iterator();
		while (result.hasNext()) {
			Environment ev = result.next();
			Map<String, Object> EVOmap=mapNodes("name", ev.getName());
			if (nodes.indexOf(EVOmap)== -1) {
				nodes.add(EVOmap);
			}
			int source = nodes.indexOf(EVOmap);
			
			if(ev.getContainer()!=null) {
				for (Contain container : ev.getContainer()) {
					Map<String, Object> eviMap = mapNodes("name", container.getEVI().getName());
					int target = nodes.indexOf(eviMap);
					if (target == -1) {
						nodes.add(eviMap);
						target = nodes.indexOf(eviMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",container.getName()));
				}
			}
			
			if(ev.getPropertiesList()!=null) {
				for (Is is : ev.getPropertiesList()) {
					Map<String, Object> propertyMap = mapNodes("name", is.getProperty().getValue());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",is.getName()));
				}
			}
			
			if(ev.getWpcList()!=null) {
				for (WeakPositiveCorrelation wpc : ev.getWpcList()) {
					Map<String, Object> propertyMap = mapNodes("name", wpc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",wpc.getName()));
				}
			}
			
			if(ev.getSpcList()!=null) {
				for (StrongPositiveCorrelation spc : ev.getSpcList()) {
					Map<String, Object> propertyMap = mapNodes("name", spc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",spc.getName()));
				}
			}
			
			if(ev.getWncList()!=null) {
				for (WeakNegativeCorrelation wnc : ev.getWncList()) {
					Map<String, Object> propertyMap = mapNodes("name", wnc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",wnc.getName()));
				}
			}
			
			if(ev.getSncList()!=null) {
				for (StrongNegativeCorrelation snc : ev.getSncList()) {
					Map<String, Object> propertyMap = mapNodes("name", snc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",snc.getName()));
				}
			}
			
			if(ev.getLocationList()!=null) {
				for (At at : ev.getLocationList()) {
					Map<String, Object> propertyMap = mapNodes("name", at.getLocation().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",at.getName()));
				}
			}
		}
		for (Map<String, Object> map : rels) {
			int source=(int)map.get("source");
			int target=(int)map.get("target");
			Map<String, Object> listInfo=mapLinks("source",nodes.get(source).get("name"),
					"relationship",map.get("name"),
					"target",nodes.get(target).get("name"));
			if (list.indexOf(listInfo) == -1) {
				list.add(listInfo);
			}
		}
		return list;
	}
	
	/**
     * 将节点查询结果转为按节点拼音首字母分类格式
     * @author Ethan Chain 陈科军
     **/
	public static Map<String, List<String>> toSortMap(Collection<Environment> evs) throws BadHanyuPinyinOutputFormatCombination {
		List<String> nodes = new ArrayList<>();
		List<String> bNodes = new ArrayList<>();//b
		List<String> pNodes = new ArrayList<>();//p
		List<String> mNodes = new ArrayList<>();//m
		List<String> fNodes = new ArrayList<>();//f
		List<String> dNodes = new ArrayList<>();//d
		List<String> tNodes = new ArrayList<>();//t
		List<String> nNodes = new ArrayList<>();//n
		List<String> lNodes = new ArrayList<>();//l
		List<String> gNodes = new ArrayList<>();//g
		List<String> kNodes = new ArrayList<>();//k
		List<String> hNodes = new ArrayList<>();//h
		List<String> jNodes = new ArrayList<>();//j
		List<String> qNodes = new ArrayList<>();//q
		List<String> xNodes = new ArrayList<>();//x
		List<String> zNodes = new ArrayList<>();//z
		List<String> cNodes = new ArrayList<>();//c
		List<String> sNodes = new ArrayList<>();//s
		List<String> rNodes = new ArrayList<>();//r
		List<String> yNodes = new ArrayList<>();//y
		List<String> wNodes = new ArrayList<>();//w
		List<String> aNodes = new ArrayList<>();//a
		List<String> oNodes = new ArrayList<>();//o
		List<String> eNodes = new ArrayList<>();//e
		List<String> iNodes = new ArrayList<>();//i
		List<String> uNodes = new ArrayList<>();//u
		List<String> vNodes = new ArrayList<>();//v
		List<String> others = new ArrayList<>();

		Iterator<Environment> result = evs.iterator();
		while (result.hasNext()) {
			Environment ev = result.next();
			if (nodes.indexOf(ev.getName())== -1) {
				nodes.add(ev.getName());
			}
			if(ev.getContainer()!=null) {
				for (Contain container : ev.getContainer()) {
					if (nodes.indexOf(container.getEVI().getName()) == -1) {
						nodes.add(container.getEVI().getName());
					}
				}
			}
			if(ev.getPropertiesList()!=null) {
				for (Is is : ev.getPropertiesList()) {
					if (nodes.indexOf(is.getProperty().getValue()) == -1) {
						nodes.add(is.getProperty().getValue());
					}
				}
			}
			if(ev.getWpcList()!=null) {
				for (WeakPositiveCorrelation wpc : ev.getWpcList()) {
					if (nodes.indexOf(wpc.getEVI().getName()) == -1) {
						nodes.add(wpc.getEVI().getName());
					}
				}
			}
			if(ev.getSpcList()!=null) {
				for (StrongPositiveCorrelation spc : ev.getSpcList()) {
					if (nodes.indexOf(spc.getEVI().getName()) == -1) {
						nodes.add(spc.getEVI().getName());
					}
				}
			}
			if(ev.getWncList()!=null) {
				for (WeakNegativeCorrelation wnc : ev.getWncList()) {
					if (nodes.indexOf(wnc.getEVI().getName()) == -1) {
						nodes.add(wnc.getEVI().getName());
					}
				}
			}
			if(ev.getSncList()!=null) {
				for (StrongNegativeCorrelation snc : ev.getSncList()) {
					if (nodes.indexOf(snc.getEVI().getName()) == -1) {
						nodes.add(snc.getEVI().getName());
					}
				}
			}
			if(ev.getLocationList()!=null) {
				for (At at : ev.getLocationList()) {
					if (nodes.indexOf(at.getLocation().getName()) == -1) {
						nodes.add(at.getLocation().getName());
					}
				}
			}
		}
		//System.out.println(JSON.toJSONString(nodes));
		for (int i = 0; i < nodes.size(); i++) {
			String chinese = nodes.get(i);
			switch (PinYinUtil.getFirstSpell(chinese)) {
			case "a":
				aNodes.add(chinese);
				break;
			case "o":
				oNodes.add(chinese);
				break;
			case "e":
				eNodes.add(chinese);
				break;
			case "i":
				iNodes.add(chinese);
				break;
			case "u":
				uNodes.add(chinese);
				break;
			case "b":
				bNodes.add(chinese);
				break;
			case "p":
				pNodes.add(chinese);
				break;
			case "m":
				mNodes.add(chinese);
				break;
			case "f":
				fNodes.add(chinese);
				break;
			case "d":
				dNodes.add(chinese);
				break;
			case "t":
				tNodes.add(chinese);
				break;
			case "n":
				nNodes.add(chinese);
				break;
			case "l":
				lNodes.add(chinese);
				break;
			case "g":
				gNodes.add(chinese);
				break;
			case "k":
				kNodes.add(chinese);
				break;
			case "h":
				hNodes.add(chinese);
				break;
			case "j":
				jNodes.add(chinese);
				break;
			case "q":
				qNodes.add(chinese);
				break;
			case "x":
				xNodes.add(chinese);
				break;
			case "z":
				zNodes.add(chinese);
				break;
			case "c":
				cNodes.add(chinese);
				break;
			case "s":
				sNodes.add(chinese);
				break;
			case "r":
				rNodes.add(chinese);
				break;
			case "y":
				yNodes.add(chinese);
				break;
			case "w":
				wNodes.add(chinese);
				break;
			case "v":
				vNodes.add(chinese);
				break;
			default:
				others.add(chinese);
				break;
			}
		}
		Map<String, List<String>> map = new HashMap<>();
		map.put("B", bNodes);		map.put("P", pNodes);		map.put("M", mNodes);
		map.put("F", fNodes);		map.put("D", dNodes);		map.put("T", tNodes);
		map.put("N", nNodes);		map.put("L", lNodes);		map.put("G", gNodes);
		map.put("K", kNodes);		map.put("H", hNodes);		map.put("J", jNodes);
		map.put("Q", qNodes);		map.put("X", xNodes);		map.put("Z", zNodes);
		map.put("C", cNodes);		map.put("S", sNodes);		map.put("R", rNodes);
		map.put("Y", yNodes);		map.put("W", wNodes);		map.put("A", aNodes);
		map.put("O", oNodes);		map.put("E", eNodes);		map.put("I", iNodes);
		map.put("U", uNodes);		map.put("V", uNodes);		map.put("Others", others);
		return map;
	}
	
	static Map<String, Object> mapNodes(String key1, Object value1) {
		Map<String, Object> result = new HashMap<String, Object>(3);
		result.put(key1, value1);
		return result;
	}
	static Map<String, Object> mapData(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}
	static Map<String, Object> mapLinks(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
		Map<String, Object> result = new HashMap<String, Object>(3);
		result.put(key1, value1);
		result.put(key2, value2);
		result.put(key3, value3);
		return result;
	}
	
	/**
     * 返回只有name字段的节点数据List
     * @author Ethan Chain 陈科军  
     **/
	public static List<String> toLevelList(Collection<Environment> evs) {
		List<String> nodes = new ArrayList<>();
		Iterator<Environment> result = evs.iterator();
		while (result.hasNext()) {
			Environment ev = result.next();
			if (nodes.indexOf(ev.getName())== -1) {
				nodes.add(ev.getName());
			}
			if(ev.getContainer()!=null) {
				for (Contain container : ev.getContainer()) {
					if (nodes.indexOf(container.getEVI().getName()) == -1) {
						nodes.add(container.getEVI().getName());
					}
				}
			}
			if(ev.getPropertiesList()!=null) {
				for (Is is : ev.getPropertiesList()) {
					if (nodes.indexOf(is.getProperty().getValue()) == -1) {
						nodes.add(is.getProperty().getValue());
					}
				}
			}
			if(ev.getWpcList()!=null) {
				for (WeakPositiveCorrelation wpc : ev.getWpcList()) {
					if (nodes.indexOf(wpc.getEVI().getName()) == -1) {
						nodes.add(wpc.getEVI().getName());
					}
				}
			}
			if(ev.getSpcList()!=null) {
				for (StrongPositiveCorrelation spc : ev.getSpcList()) {
					if (nodes.indexOf(spc.getEVI().getName()) == -1) {
						nodes.add(spc.getEVI().getName());
					}
				}
			}
			if(ev.getWncList()!=null) {
				for (WeakNegativeCorrelation wnc : ev.getWncList()) {
					if (nodes.indexOf(wnc.getEVI().getName()) == -1) {
						nodes.add(wnc.getEVI().getName());
					}
				}
			}
			if(ev.getSncList()!=null) {
				for (StrongNegativeCorrelation snc : ev.getSncList()) {
					if (nodes.indexOf(snc.getEVI().getName()) == -1) {
						nodes.add(snc.getEVI().getName());
					}
				}
			}
			if(ev.getLocationList()!=null) {
				for (At at : ev.getLocationList()) {
					if (nodes.indexOf(at.getLocation().getName()) == -1) {
						nodes.add(at.getLocation().getName());
					}
				}
			}
		}
		return nodes;
	}
	
	/**
     * 将关系查询结果转为可视化图像数据格式
     * @author Ethan Chain 陈科军
     **/
	public static Map<String, Object> rel2Echarts(List<Record> records, String relationship) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		for (Record record : records) {
			Value temp1 = record.get("a.name");
			Value temp2 = record.get("b.name");
			Value temp3 = record.get("b.value");
			String aName = temp1.asString();
			String bName = null;
			String bColor = null;
			if (relationship.equals("是")) {
				bName=temp3.asString();
				bColor="Pink";
			}else {
				if (relationship.equals("位于")) {
					bName=temp2.asString();
					bColor="Blue";
				}else {
					bName=temp2.asString();
					bColor="Red";
				}
			}
			
			Map<String, Object> EVOmap=mapData("name", aName, "color","Red");
			if (nodes.indexOf(EVOmap)== -1) {
				nodes.add(EVOmap);
			}
			int source = nodes.indexOf(EVOmap);
			
			Map<String, Object> EVImap=mapData("name", bName, "color",bColor);
			if (nodes.indexOf(EVImap)== -1) {
				nodes.add(EVImap);
			}
			int target = nodes.indexOf(EVImap);
			
			rels.add(mapLinks("source", source, "target", target,"name",relationship));
		}
		return mapData("data", nodes, "links", rels);
	}

	/**
     * 将关系查询结果转为列表数据格式
     * @author Ethan Chain 陈科军
     **/
	public static List<Map<String, Object>> rel2List(List<Record> records,String relationship) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		List<Map<String, Object>> list = new ArrayList<>();
		for (Record record : records) {
			Value temp1 = record.get("a.name");
			Value temp2 = record.get("b.name");
			Value temp3 = record.get("b.value");
			String aName = temp1.asString();
			String bName = null;
			if (relationship.equals("是")) {
				bName=temp3.asString();
			}else {
				bName=temp2.asString();
			}
			
			Map<String, Object> EVOmap=mapNodes("name", aName);
			if (nodes.indexOf(EVOmap)== -1) {
				nodes.add(EVOmap);
			}
			int source = nodes.indexOf(EVOmap);
			
			Map<String, Object> EVImap=mapNodes("name", bName);
			if (nodes.indexOf(EVImap)== -1) {
				nodes.add(EVImap);
			}
			int target = nodes.indexOf(EVImap);
			
			rels.add(mapLinks("source", source, "target", target,"name",relationship));
		}
		for (Map<String, Object> map : rels) {
			int source=(int)map.get("source");
			int target=(int)map.get("target");
			Map<String, Object> listInfo=mapLinks("source",nodes.get(source).get("name"),
					"relationship",map.get("name"),
					"target",nodes.get(target).get("name"));
			if (list.indexOf(listInfo) == -1) {
				list.add(listInfo);
			}
		}
		return list;
	}
	
	public static List<String> toAnswerListFormat(Collection<Environment> evs) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		List<String> answerList = new ArrayList<>();
		Iterator<Environment> result = evs.iterator();
		while (result.hasNext()) {
			Environment ev = result.next();
			Map<String, Object> EVOmap=mapNodes("name", ev.getName());
			if (nodes.indexOf(EVOmap)== -1) {
				nodes.add(EVOmap);
			}
			int source = nodes.indexOf(EVOmap);
			
			if(ev.getContainer()!=null) {
				for (Contain container : ev.getContainer()) {
					Map<String, Object> eviMap = mapNodes("name", container.getEVI().getName());
					int target = nodes.indexOf(eviMap);
					if (target == -1) {
						nodes.add(eviMap);
						target = nodes.indexOf(eviMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",container.getName()));
				}
			}
			
			if(ev.getPropertiesList()!=null) {
				for (Is is : ev.getPropertiesList()) {
					Map<String, Object> propertyMap = mapNodes("name", is.getProperty().getValue());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",is.getName()));
				}
			}
			
			if(ev.getWpcList()!=null) {
				for (WeakPositiveCorrelation wpc : ev.getWpcList()) {
					Map<String, Object> propertyMap = mapNodes("name", wpc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",wpc.getName()));
				}
			}
			
			if(ev.getSpcList()!=null) {
				for (StrongPositiveCorrelation spc : ev.getSpcList()) {
					Map<String, Object> propertyMap = mapNodes("name", spc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",spc.getName()));
				}
			}
			
			if(ev.getWncList()!=null) {
				for (WeakNegativeCorrelation wnc : ev.getWncList()) {
					Map<String, Object> propertyMap = mapNodes("name", wnc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",wnc.getName()));
				}
			}
			
			if(ev.getSncList()!=null) {
				for (StrongNegativeCorrelation snc : ev.getSncList()) {
					Map<String, Object> propertyMap = mapNodes("name", snc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",snc.getName()));
				}
			}
			
			if(ev.getLocationList()!=null) {
				for (At at : ev.getLocationList()) {
					Map<String, Object> propertyMap = mapNodes("name", at.getLocation().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(mapLinks("source", source, "target", target,"name",at.getName()));
				}
			}
		}
		for (Map<String, Object> map : rels) {
			int target=(int)map.get("target");
			if (answerList.indexOf((String) nodes.get(target).get("name")) == -1) {
				answerList.add((String) nodes.get(target).get("name"));
			}
		}
		return answerList;
	}
/*
     * 实现java 中 list集合中6条为一组取出
     * @param list 可穿入数据的List
     * @return map 每一Key中有6条数据的List
     
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Map<String, List<Map<String, Object>>> groupList(List list){
        int listSize=list.size();
        int toIndex=6;
        Map map = new HashMap();     //用map存起来新的分组后数据
        int keyToken = 0;
        for(int i = 0;i<list.size();i+=6){
            if(i+6>listSize){        //作用为toIndex最后没有6条数据则剩余几条newList中就装几条
                toIndex=listSize-i;
            }
        List newList = list.subList(i,i+toIndex);
        map.put("Page"+keyToken, newList);
        keyToken++;
        }
        return map;
    }
*/
}
