package ${packetageDAO};

import ${dtoType};
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ${entityName}DAO {


    Long save(${dtoName} ${dtoNameLow});


    List<${dtoName}> query${dtoName}s(@Param("${dtoNameLow}") ${dtoName} ${dtoNameLow});

}
