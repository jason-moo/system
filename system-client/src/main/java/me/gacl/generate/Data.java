package me.gacl.generate;

/**
 * Created by jason_moo on 2018/5/24.
 */
public class Data {

    private String nativeColumn;

    private String convertColumn;

    private String convertColumn2;

    private String param;

    public String getNativeColumn() {
        return nativeColumn;
    }

    public void setNativeColumn(String nativeColumn) {
        this.nativeColumn = nativeColumn;
    }

    public String getConvertColumn() {
        return convertColumn;
    }

    public void setConvertColumn(String convertColumn) {
        this.convertColumn = convertColumn;
        this.convertColumn2 = "#{" + convertColumn + "}";
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getConvertColumn2() {
        return convertColumn2;
    }

    public void setConvertColumn2(String convertColumn2) {
        this.convertColumn2 = convertColumn2;
    }

}
