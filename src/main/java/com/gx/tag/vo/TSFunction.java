package com.gx.tag.vo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *菜单权限表
 * @author  张代浩
 */
public class TSFunction implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private TSFunction TSFunction;//父菜单
	private String functionName;//菜单名称
	private Short functionLevel;//菜单等级
	private String functionUrl;//菜单地址
	private Short functionIframe;//菜单地址打开方式
	private String functionOrder;//菜单排序
	private TSIcon TSIcon = new TSIcon();//菜单图标
	private TSIcon TSIconDesk;//云桌面菜单图标
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public TSIcon getTSIconDesk() {
        return TSIconDesk;
    }
    public void setTSIconDesk(TSIcon TSIconDesk) {
        this.TSIconDesk = TSIconDesk;
    }

	private List<TSFunction> TSFunctions = new ArrayList<TSFunction>();
	public TSIcon getTSIcon() {
		return TSIcon;
	}
	public void setTSIcon(TSIcon tSIcon) {
		TSIcon = tSIcon;
	}
	public TSFunction getTSFunction() {
		return this.TSFunction;
	}

	public void setTSFunction(TSFunction TSFunction) {
		this.TSFunction = TSFunction;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Short getFunctionLevel() {
		return this.functionLevel;
	}

	public void setFunctionLevel(Short functionLevel) {
		this.functionLevel = functionLevel;
	}
	public String getFunctionUrl() {
		return this.functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}
	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}
	public List<TSFunction> getTSFunctions() {
		return TSFunctions;
	}
	public void setTSFunctions(List<TSFunction> TSFunctions) {
		this.TSFunctions = TSFunctions;
	}
	public Short getFunctionIframe() {
		return functionIframe;
	}
	public void setFunctionIframe(Short functionIframe) {
		this.functionIframe = functionIframe;
	}
	
	public boolean hasSubFunction(Map<Integer, List<TSFunction>> map) {
		if(map.containsKey(this.getFunctionLevel()+1)){
			return hasSubFunction(map.get(this.getFunctionLevel()+1));
		}
		return false;
	}
	public boolean hasSubFunction(List<TSFunction> functions) {
		for (TSFunction f : functions) {
			if(f.getTSFunction()!=null){
				if(f.getTSFunction().getId().equals(this.getId())){
					return true;
				}
			}
			
		}
		return false;
	}

}