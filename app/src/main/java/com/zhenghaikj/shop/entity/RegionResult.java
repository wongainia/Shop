package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class RegionResult implements Serializable {

    /**
     * Id : 1
     * Name : 北京
     * ShortName : 北京
     * Sub : [{"Id":2,"Name":"北京市","ShortName":null,"Sub":[{"Id":3,"Name":"东城区","ShortName":null,"Sub":null},{"Id":4,"Name":"西城区","ShortName":null,"Sub":null},{"Id":5,"Name":"朝阳区","ShortName":null,"Sub":null},{"Id":6,"Name":"丰台区","ShortName":null,"Sub":null},{"Id":7,"Name":"石景山区","ShortName":null,"Sub":null},{"Id":8,"Name":"海淀区","ShortName":null,"Sub":null},{"Id":9,"Name":"门头沟区","ShortName":null,"Sub":null},{"Id":10,"Name":"房山区","ShortName":null,"Sub":null},{"Id":11,"Name":"通州区","ShortName":null,"Sub":null},{"Id":12,"Name":"顺义区","ShortName":null,"Sub":null},{"Id":13,"Name":"昌平区","ShortName":null,"Sub":null},{"Id":14,"Name":"大兴区","ShortName":null,"Sub":null},{"Id":15,"Name":"怀柔区","ShortName":null,"Sub":null},{"Id":16,"Name":"平谷区","ShortName":null,"Sub":null},{"Id":17,"Name":"密云县","ShortName":null,"Sub":null},{"Id":18,"Name":"延庆县","ShortName":null,"Sub":null},{"Id":46463,"Name":"崇文区","ShortName":null,"Sub":null},{"Id":46471,"Name":"宣武区","ShortName":null,"Sub":null},{"Id":46603,"Name":"其它区","ShortName":null,"Sub":null}]}]
     */

    private String Id;
    private String Name;
    private String ShortName;
    private List<RegionResult> Sub;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
    }

    public List<RegionResult> getSub() {
        return Sub;
    }

    public void setSub(List<RegionResult> Sub) {
        this.Sub = Sub;
    }
}
