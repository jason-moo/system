package ${packetageServiceImpl};

import ${dtoType};
import java.util.List;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ${entityName}ServiceImpl implements ${entityName}Service{

    @Autowired
    ${entityName}Dao ${camelCaseName}Dao;

    @Override
    public Long save(${dtoName} ${dtoNameLow}){
        ${camelCaseName}Dao.save(${dtoNameLow});
    };

    @Override
    public List<${dtoName}> query${dtoName}s（${dtoName} ${dtoNameLow}){
        return ${camelCaseName}Dao.query${dtoName}s(${dtoNameLow});
    };

}
