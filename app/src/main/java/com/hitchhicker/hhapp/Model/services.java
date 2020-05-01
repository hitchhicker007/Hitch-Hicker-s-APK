package com.hitchhicker.hhapp.Model;

public class services
{
    private String service_name,service_desc,service_logo;

    public services(){

    }

    public services(String service_name, String service_desc, String service_logo) {
        this.service_name = service_name;
        this.service_desc = service_desc;
        this.service_logo = service_logo;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_desc() {
        return service_desc;
    }

    public void setService_desc(String service_desc) {
        this.service_desc = service_desc;
    }

    public String getService_logo() {
        return service_logo;
    }

    public void setService_logo(String service_logo) {
        this.service_logo = service_logo;
    }
}
