package ${packetageDTO};

import java.lang.*;
import java.util.Date;

public class ${dtoName} {

<#list dataStrutes as dataStrute>

    private ${dataStrute.type} ${dataStrute.column};
</#list>
<#list dataStrutes as dataStrute>

    public ${dataStrute.type} get${dataStrute.pColumn}() {
        return ${dataStrute.column};
    }

    public void set${dataStrute.pColumn}(${dataStrute.type} ${dataStrute.column}) {
        this.${dataStrute.column} = ${dataStrute.column};
    }
</#list>

}