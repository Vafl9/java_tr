package ru.stqa.ptf.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

public class pointTest {

    @Test
    public void testArea(){

        Point b = new Point(1,3);
        Point c = new Point(1,8);
        System.out.println(b.distance(b,c));
        Assert.assertEquals(b.distance(b,c), 5.0);

    }
}
