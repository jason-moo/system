package ${packetageServiceImpl};

import ${dtoType};
import java.util.List;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${packetageDAO}.*;
import ${packetageService}.${entityName}Service;

public class ${entityName}ServiceImpl implements ${entityName}Service{

    @Autowired
    ${entityName}DAO ${camelCaseName}DAO;

    @Override
    public Long save(${dtoName} ${dtoNameLow}){
        ${camelCaseName}DAO.save(${dtoNameLow});
        return ${dtoNameLow}.getId();
    }

    @Override
    public List<${dtoName}> query${dtoName}s(${dtoName} ${dtoNameLow}){
        return ${camelCaseName}DAO.query${dtoName}s(${dtoNameLow});
    }

}
