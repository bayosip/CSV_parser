package com.osicorp.adebayo_osipitan;

import com.osicorp.adebayo_osipitan.model.DataReader;
import com.osicorp.adebayo_osipitan.model.Filter;
import com.osicorp.adebayo_osipitan.model.ModelInteractor;

import org.junit.Test;

public class FilterTest {

    @Test
    public void checkIfCarDataisValidForFilter(){
        Filter filter = new Filter(0, 2000, 2009,
                "male",  new String[]{""}, new String[]{""});
        ModelInteractor reader = DataReader.getInstance();

    }
}
