<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN"
        "http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd">
<mapper namespace="${package}.dao.${tableName}Dao">

    <resultMap id="resultMap" type="${packageName}.dto.${tableName}Entity">
        <#if columns??>
            <#list columns as column>
                <result property="${column.columnName}" column="${column.columnName}"
                        jdbcType="${column.jdbcType}"></result>
            </#list>
        </#if>
    </resultMap>
    <sql id="tableName">${tableName}</sql>
    <sql id="columns">
        <#if columns??>
            <#list columns as column>
                <#if column?is_last>
                    ${column.columnName}
                <#else >
                    ${column.columnName},
                </#if>

            </#list>
        </#if>
    </sql>

    <insert id="save">
        insert into <include refid="tableName"/> (<include refid="columns"/>) values
        (
        <#if columns??>
            <#list columns as column>
                ${r'#{entity'+'.'+column.columnName+'}'}
            </#list>
        </#if>
        )
    </insert>
    
    <insert id="insertBatch">
        insert into <include refid="tableName"/> (<include refid="columns"/>) values
        <foreach collection="entities" open="(" close=")" item="entity" separator=",">

        </foreach>,
    </insert>

    <select id="getByKey" resultMap="resultMap">
        select <include refid="columns"/> from <include refid="tableName"/>
    </select>

    <select id="findDTOById" parameterType="String" resultMap="${tableName}DTOResultMap">
        <include refid="findDtoSql"></include>
        <where>
            and t.id = ${r'#{id}'}
        </where>
    </select>

    <select id="find${tableName}Page" parameterType="${packageName}.dto.${tableName}DTO"
            resultMap="${tableName}DTOResultMap">
        <include refid="findDtoSql"/>
        <where>

        </where>
    </select>

</mapper>