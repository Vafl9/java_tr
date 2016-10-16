package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTest {

    @Test
    public void Test0(){
        Equation e = new Equation (1,1,1);
        Assert.assertEquals(e.rootNumber(),0);
    }

    @Test
    public void Test1(){
        Equation e = new Equation (1,2,1);
        Assert.assertEquals(e.rootNumber(),1);
    }

    @Test
    public void Test2(){
        Equation e = new Equation (1,5,6);
        Assert.assertEquals(e.rootNumber(),2);
    }

    @Test
    public void TestLiner(){
        Equation e = new Equation (0,1,1);
        Assert.assertEquals(e.rootNumber(),1);
    }

    @Test
    public void TestConst(){
        Equation e = new Equation (0,0,1);
        Assert.assertEquals(e.rootNumber(),0);
    }

    @Test
    public void TestZero(){
        Equation e = new Equation (0,0,0);
        Assert.assertEquals(e.rootNumber(),-1);
    }
}
