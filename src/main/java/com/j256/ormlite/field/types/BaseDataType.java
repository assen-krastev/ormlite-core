package com.j256.ormlite.field.types;

import java.lang.reflect.Field;
import java.sql.SQLException;

import com.j256.ormlite.field.DataPersister;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

/**
 * Base data type that defines the defaults the various data types.
 * 
 * <p>
 * Here's a good page about the <a href="http://docs.codehaus.org/display/CASTOR/Type+Mapping" >mapping for a number of
 * database types</a>:
 * </p>
 * 
 * @author graywatson
 */
public abstract class BaseDataType implements DataPersister {

	private final SqlType sqlType;
	private final Class<?>[] classes;

	public BaseDataType(SqlType sqlType, Class<?>[] classes) {
		this.sqlType = sqlType;
		this.classes = classes;
	}

	public abstract Object parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException;

	public abstract Object resultToJava(FieldType fieldType, DatabaseResults results, int columnPos)
			throws SQLException;

	public abstract Object javaToSqlArg(FieldType fieldType, Object obj) throws SQLException;

	public abstract boolean isValidForField(Field field);

	public Object makeConfigObject(FieldType fieldType) throws SQLException {
		return null;
	}

	public SqlType getSqlType() {
		return sqlType;
	}

	public Class<?>[] getAssociatedClasses() {
		return classes;
	}

	public boolean isStreamType() {
		return false;
	}

	public Object convertIdNumber(Number number) {
		// by default the type cannot convert an id number
		return null;
	}

	public boolean isValidGeneratedType() {
		return false;
	}

	public boolean isEscapedDefaultValue() {
		// default is to not escape the type if it is a number
		return isEscapedValue();
	}

	public boolean isEscapedValue() {
		return true;
	}

	public boolean isPrimitive() {
		return false;
	}

	public boolean isComparable() {
		return true;
	}

	public boolean isAppropriateId() {
		return true;
	}

	public boolean isSelectArgRequired() {
		return false;
	}

	public boolean isSelfGeneratedId() {
		return false;
	}

	public Object generatedId() {
		throw new IllegalStateException("Should not have tried to generate this type");
	}

	public int getDefaultWidth() {
		return 0;
	}
}
