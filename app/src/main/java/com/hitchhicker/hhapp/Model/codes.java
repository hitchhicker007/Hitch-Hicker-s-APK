package com.hitchhicker.hhapp.Model;

public class codes
{
    private String code_title,code_desc,code_link;

    public codes(){

    }

    public codes(String code_title, String code_desc, String code_link) {
        this.code_title = code_title;
        this.code_desc = code_desc;
        this.code_link = code_link;
    }

    public String getCode_title() {
        return code_title;
    }

    public void setCode_title(String code_title) {
        this.code_title = code_title;
    }

    public String getCode_desc() {
        return code_desc;
    }

    public void setCode_desc(String code_desc) {
        this.code_desc = code_desc;
    }

    public String getCode_link() {
        return code_link;
    }

    public void setCode_link(String code_link) {
        this.code_link = code_link;
    }
}
