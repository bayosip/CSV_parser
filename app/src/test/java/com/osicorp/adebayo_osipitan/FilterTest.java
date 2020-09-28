package com.osicorp.adebayo_osipitan;

import android.text.TextUtils;
import android.util.Log;

import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.model.DataReader;
import com.osicorp.adebayo_osipitan.model.Filter;
import com.osicorp.adebayo_osipitan.model.ModelInteractor;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;


@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class FilterTest {

    @Before
    public void setup() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
    }

    @Test
    public void checkIfCarDataisValidForFilter(){
        Filter filter = new Filter(0, 1990, 2009,
                "",  null, null);
        Filter filter1 = new Filter(1, 1997, 2009,
                "",  null, null);
       Car_Owners_Data data = new Car_Owners_Data(new String[]{String.valueOf(1),"Scot","Hainning",
               "shainning0@so-net.ne.jp","Thailand","Lincoln",String.valueOf(1996),	"Maroon","Male",
               "Staff Accountant III","Cras mi pede"});
       assertTrue(filter.checkEachFilterCategoryForMatch(data));
       assertFalse(filter1.checkEachFilterCategoryForMatch(data));
    }
}
