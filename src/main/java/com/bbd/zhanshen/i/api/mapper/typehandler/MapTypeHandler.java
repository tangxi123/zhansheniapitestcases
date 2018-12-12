package com.bbd.zhanshen.i.api.mapper.typehandler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableMap;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@MappedTypes({Map.class})
public class MapTypeHandler extends BaseTypeHandler<Map<String, ? extends Object>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, ? extends Object> parameter, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        try {
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i,json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Map<String,? extends Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        try {
            return objectMapper.readValue(rs.getString(columnName),Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, ? extends Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        try {
            return objectMapper.readValue(rs.getString(columnIndex),Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, ? extends Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        try {
            return objectMapper.readValue(cs.getString(columnIndex),Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
