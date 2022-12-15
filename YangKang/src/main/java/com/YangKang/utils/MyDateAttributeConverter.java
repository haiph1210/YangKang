package com.YangKang.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Converter(autoApply = true)
public class MyDateAttributeConverter implements AttributeConverter<Date, String> {

    @Override
    public String convertToDatabaseColumn(Date entityDate) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(entityDate);
    }

    @Override
    public Date convertToEntityAttribute(String databaseDate) {
    	try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return  formatter.parse(databaseDate);
        } catch (ParseException e) {
            return null;
        }
    }
}
