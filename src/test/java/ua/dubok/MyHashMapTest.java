package ua.dubok;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Unit test for simple MyHashMap
 */
public class MyHashMapTest {

    MyHashMap<Integer, Long> map;
    private int loadSize = 10000;

    @Before
    public void setUp(){
        map = new MyHashMap<Integer, Long>();
        for(int i = 0; i < loadSize; i++){
            map.put(i, i+0L);
        }
    }

    @Test
    public void basicTest(){
        map.put(1, 5L);
        map.put(3, 4L);
        Assert.assertEquals((Long) 4L, map.get(3));
        Assert.assertEquals((Long) 5L, map.get(1));
    }

    @Test
    public void Size_CorrectSize_True(){
        Assert.assertEquals(map.size(), loadSize);
    }

    @Test(expected = NullPointerException.class)
    public void Put_KeyAsNull_IfNull() {
        map.put(null, 2L);
    }

    @Test
    public void Get_Value_IfNull(){
        TestCase.assertNull(map.get(1000221));
    }

    @Test
    public void Get_Value_IfNotNull(){
        assertNotNull(map.get(842));
    }

    @After
    public void clean(){
        map = null;
    }

}
