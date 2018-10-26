package running.device.topband.com.citydatalib;

public class Data {
    private String code;
    private String name;
    private String province;
    private String city;
    private String county;

    public String getCode() {
        return code;
    }

    public void setCode(String mCode) {
        code = mCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String mProvince) {
        province = mProvince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String mCity) {
        city = mCity;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String mCounty) {
        county = mCounty;
    }

    @Override
    public String toString() {
        return "Data{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
