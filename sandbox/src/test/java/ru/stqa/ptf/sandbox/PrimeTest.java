package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTest {

    @Test
    public void testPrime()
    {
        Assert.assertTrue(Primes.isPrime(1));
    }

    @Test
    public void testPrime1()
    {
        Assert.assertTrue(Primes.isPrime(1));
    }

    @Test(enabled = false)
    public void testPrime2()
    {
        Assert.assertTrue(Primes.isPrime(-2));
    }
}
