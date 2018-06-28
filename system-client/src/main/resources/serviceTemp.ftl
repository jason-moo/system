package ${packetageService};

import ${dtoType};
import java.util.List;

public interface ${entityName}Service{


    Long save(${dtoName} ${dtoNameLow});


    List<${dtoName}> query${dtoName}s(${dtoName} ${dtoNameLow});

}
