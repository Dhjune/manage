package com.fanfan.manage.sdk.hibernate;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;

public class ExtendsOrclDialect extends Oracle10gDialect{
	
	public  ExtendsOrclDialect(){
		super();
		registerColumnType(Types.TIMESTAMP, "date" );
	}
	
}
