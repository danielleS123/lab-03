package com.example.listycitylab3;

import java.util.Collection;

public class City{
    private String name;
    private String province;

    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName()
    {
        return name;
    }

    public String getProvince()
    {
        return province;
    }

    public void setName(String name1)
    {
        name = name1;
    }

    public void setProvince(String province1)
    {
        province = province1;
    }
}
