package com.funhotel.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * CLASS ： EpgData
 * Author : zhiyahan
 * TIME : 2017/6/5 15:12
 */
@Entity
public class EpgData {

    /**
     * id : 306
     * name : 默认轮换图片1
     * resourceId : 826
     * resourceName : 轮换图片3
     * position : null
     * text :
     * href :
     * beforeImgUrl : null
     * backImgUrl : http://219.133.42.117:18880/img//2017/05/18/16_39_03_533c40fca0581be4511872c9f278e8e355a.jpg
     * mediaUrl : null
     * showType : 1
     * sort : 2
     * type : big_background
     * templateId : 58
     * templateName : ceshi标准
     * extention1 :
     * extention2 :
     * adPositionCode : null
     * apkHref : null
     * cls : null
     * pck : null
     */

    @Id
    private int id;
    private String name;
    private int resourceId;
    private String resourceName;
    private int position;
    private String text;
    private String href;
    private String beforeImgUrl;
    private String backImgUrl;
    private String mediaUrl;
    private int showType;
    private int sort;
    private String type;
    private int templateId;
    private String templateName;
    private String extention1;
    private String extention2;
    private String adPositionCode;
    private String apkHref;
    private String cls;
    private String pck;


    @Generated(hash = 699405388)
    public EpgData(int id, String name, int resourceId, String resourceName, int position, String text,
            String href, String beforeImgUrl, String backImgUrl, String mediaUrl, int showType, int sort,
            String type, int templateId, String templateName, String extention1, String extention2,
            String adPositionCode, String apkHref, String cls, String pck) {
        this.id = id;
        this.name = name;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.position = position;
        this.text = text;
        this.href = href;
        this.beforeImgUrl = beforeImgUrl;
        this.backImgUrl = backImgUrl;
        this.mediaUrl = mediaUrl;
        this.showType = showType;
        this.sort = sort;
        this.type = type;
        this.templateId = templateId;
        this.templateName = templateName;
        this.extention1 = extention1;
        this.extention2 = extention2;
        this.adPositionCode = adPositionCode;
        this.apkHref = apkHref;
        this.cls = cls;
        this.pck = pck;
    }

    @Generated(hash = 497471848)
    public EpgData() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getBeforeImgUrl() {
        return beforeImgUrl;
    }

    public void setBeforeImgUrl(String beforeImgUrl) {
        this.beforeImgUrl = beforeImgUrl;
    }

    public String getBackImgUrl() {
        return backImgUrl;
    }

    public void setBackImgUrl(String backImgUrl) {
        this.backImgUrl = backImgUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getExtention1() {
        return extention1;
    }

    public void setExtention1(String extention1) {
        this.extention1 = extention1;
    }

    public String getExtention2() {
        return extention2;
    }

    public void setExtention2(String extention2) {
        this.extention2 = extention2;
    }

    public String getAdPositionCode() {
        return adPositionCode;
    }

    public void setAdPositionCode(String adPositionCode) {
        this.adPositionCode = adPositionCode;
    }

    public String getApkHref() {
        return apkHref;
    }

    public void setApkHref(String apkHref) {
        this.apkHref = apkHref;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getPck() {
        return pck;
    }

    public void setPck(String pck) {
        this.pck = pck;
    }


}
