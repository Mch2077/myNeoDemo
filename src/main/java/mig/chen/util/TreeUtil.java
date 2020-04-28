package mig.chen.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mig.chen.domain.At;
import mig.chen.domain.TreeNode;
import mig.chen.domain.Contain;
import mig.chen.domain.Environment;
import mig.chen.domain.Is;
import mig.chen.domain.StrongNegativeCorrelation;
import mig.chen.domain.StrongPositiveCorrelation;
import mig.chen.domain.WeakNegativeCorrelation;
import mig.chen.domain.WeakPositiveCorrelation;

 
/**
 * 类名称：MenuTreeUtil
 * 类描述：递归构造树型结构
 */
/**
 * 树形结构工具类
 *
 * 将一组list对象转成树形结构
 * 该list需符合设定的字段类型
 *
 */
public class TreeUtil {

	public static List<TreeNode> bulidList(Collection<Environment> evs) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		Iterator<Environment> result = evs.iterator();
		while (result.hasNext()) {
			Environment ev = result.next();
			Map<String, Object> EVOmap=EchartsUtil.mapNodes("name", ev.getName());
			if (nodes.indexOf(EVOmap)== -1) {
				nodes.add(EVOmap);
			}
			int source = nodes.indexOf(EVOmap);
			
			if(ev.getContainer()!=null) {
				for (Contain container : ev.getContainer()) {
					Map<String, Object> eviMap = EchartsUtil.mapNodes("name", container.getEVI().getName());
					int target = nodes.indexOf(eviMap);
					if (target == -1) {
						nodes.add(eviMap);
						target = nodes.indexOf(eviMap);
					}
					rels.add(EchartsUtil.mapData("source", source, "target", target));
				}
			}
			
			if(ev.getPropertiesList()!=null) {
				for (Is is : ev.getPropertiesList()) {
					Map<String, Object> propertyMap = EchartsUtil.mapNodes("name", is.getProperty().getValue());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(EchartsUtil.mapData("source", source, "target", target));
				}
			}
			
			if(ev.getWpcList()!=null) {
				for (WeakPositiveCorrelation wpc : ev.getWpcList()) {
					Map<String, Object> propertyMap = EchartsUtil.mapNodes("name", wpc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(EchartsUtil.mapData("source", source, "target", target));
				}
			}
			
			if(ev.getSpcList()!=null) {
				for (StrongPositiveCorrelation spc : ev.getSpcList()) {
					Map<String, Object> propertyMap = EchartsUtil.mapNodes("name", spc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(EchartsUtil.mapData("source", source, "target", target));
				}
			}
			
			if(ev.getWncList()!=null) {
				for (WeakNegativeCorrelation wnc : ev.getWncList()) {
					Map<String, Object> propertyMap = EchartsUtil.mapNodes("name", wnc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(EchartsUtil.mapData("source", source, "target", target));
				}
			}
			
			if(ev.getSncList()!=null) {
				for (StrongNegativeCorrelation snc : ev.getSncList()) {
					Map<String, Object> propertyMap = EchartsUtil.mapNodes("name", snc.getEVI().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(EchartsUtil.mapData("source", source, "target", target));
				}
			}
			
			if(ev.getLocationList()!=null) {
				for (At at : ev.getLocationList()) {
					Map<String, Object> propertyMap = EchartsUtil.mapNodes("name", at.getLocation().getName());
					int target = nodes.indexOf(propertyMap);
					if (target == -1) {
						nodes.add(propertyMap);
						target = nodes.indexOf(propertyMap);
					}
					rels.add(EchartsUtil.mapData("source", source, "target", target));
				}
			}
		}
		List<TreeNode> nodeInfo = new ArrayList<>();
		for (int i = 0; i < nodes.size(); i++) {
			String name=(String) nodes.get(i).get("name");
			for (int j = 0; j < rels.size(); j++) {
				if ((int)rels.get(j).get("target")==i) {
					TreeNode baseNode = new TreeNode(i,(int)rels.get(j).get("source"),name);
					nodeInfo.add(baseNode);
				}
			}
		}
		return nodeInfo;
	}
	/**********************CUT LINE***************************/
	/**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static <T extends TreeNode> List<T> bulid(List<T> treeNodes, Object root) {

        List<T> trees = new ArrayList<>();

        for (T treeNode : treeNodes) {

            if (root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }

            for (T it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (treeNode.getId() == it.getParentId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
/*	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("treeData", buildTree(deptList, 1));
		System.out.println(JSON.toJSONString(map));
	}
*/
}

