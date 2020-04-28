package com.msghub.msghub.model;

/**
 * 模板
 */
public class Template {

    //模板id
    private String template_id;
    //模板文本
    private String text;
    // 模板归属
    private String affiliation;
    //模板的使用公司
    private Integer client_id;

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    @Override
    public String toString() {
        return "Template{" +
                "template_id='" + template_id + '\'' +
                ", text='" + text + '\'' +
                ", affiliation='" + affiliation + '\'' +
                ", client_id=" + client_id +
                '}';
    }
}
