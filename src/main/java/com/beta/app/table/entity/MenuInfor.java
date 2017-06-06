package com.beta.app.table.entity;

import java.io.Serializable;

/**
 * 
 * <p>application name:{菜单实体}</p>
 * <p>application describing:{菜单实体}</p>
 * <p>Copyright：Copyright 2017</p>
 * <p>company:neusoft</p>
 * <p>time:2017年6月6日</p>
 * @author {FireMonkey}
 * @version {1.0}
 */
public class MenuInfor   implements Serializable {
    /**
     * {序列化ID}
     */
    private static final long serialVersionUID = 1L;
    
    //系统菜单id
    private String id;
    //父级菜单id
    private String parentId;
    //菜单名称
    private String name;
  //点击事件跳转的url
    private String url;
  //菜单图标
    private String icon;
  //同级节点间显示顺序
    private Integer displayOrder;
  //菜单路径
    private String path;
  //备注
    private String note;
  //是否锁定
    private Integer locked;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public Integer getLocked() {
        return locked;
    }
    public void setLocked(Integer locked) {
        this.locked = locked;
    }
    
    
}
