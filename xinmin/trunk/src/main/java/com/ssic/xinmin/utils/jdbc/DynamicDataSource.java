package com.ssic.xinmin.utils.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
    	
        return DynamicDataSourceHolder.getDataSouce();
    }

}