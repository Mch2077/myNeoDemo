package mig.chen.domain;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	private int id;
	private int parentId;
	private String name;
	private List<TreeNode> children = new ArrayList<TreeNode>();

	public TreeNode() {
		super();
		}
	public TreeNode(int id, int parentId, String name) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the children
	 */
	public List<TreeNode> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return " [id=" + id + ", parentId=" + parentId + ", name=" + name + ", child=" + children + "]";
	}
	
	public void add(TreeNode node) {
        children.add(node);
    }

}
