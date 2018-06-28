<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packetageDAO}.${entityName}DAO" >

    <resultMap id="${dtoName}ResultMap" type="${dtoType}" >
        <#list columnList as column>
            <#if column_index = 0>
                <id column="id" property="id"/>
            <#else>
                <result column="${column.nativeColumn}" property="${column.convertColumn}"/>
            </#if>
        </#list>
    </resultMap>

    <resultMap id="${entityName}ResultMap" type="${entityType}">
        <#list columnList as column>
            <#if column_index = 0>
                <id column="id" property="id"/>
            <#else>
                <result column="${column.nativeColumn}" property="${column.convertColumn}"/>
            </#if>
        </#list>
    </resultMap>

    <insert id="save" keyProperty="id" useGeneratedKeys="true" parameterType="${dtoType}">
        insert into ${tableName} (
            <#list columnList as column>
                <#if column_index = 0>
                <#elseif column_index = 1>
                    ${column.nativeColumn}
                <#else>
                    ,${column.nativeColumn}
                </#if>
            </#list>
        )
        values (
            <#list columnList as column>
                <#if column_index = 0>
                <#elseif column_index = 1>
                    ${column.convertColumn2}
                <#else>
                    ,${column.convertColumn2}
                </#if>
             </#list>
        )
    </insert>

    <select id="query${dtoName}s" resultMap="${dtoName}ResultMap">

        SELECT
        *
        FROM  ${tableName}

        <where>
        <#list columnList as column>
            <if test="${dtoNameLow}.${column.convertColumn} != null">
                and ${column.nativeColumn} = ${column.param}
            </if>
        </#list>
        </where>
    </select>
</mapper>