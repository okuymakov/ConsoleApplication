using System;
using CalculationApp;
using NUnit.Framework;

namespace CalculationAppTests
{
    public class UnitTests
    {
        private Calculator _calc;

        [SetUp]
        public void Setup()
        {
            _calc = new Calculator();
        }
        
        [Test]
        public void Add_TwoNumbers_ReturnsSumOfNumbers()
        {
            Assert.AreEqual(_calc.Add(5,4), 9);
        }
        
        [Test]
        public void Subtract_TwoNumbers_ReturnsDifferenceOfNumbers()
        {
            Assert.AreEqual(_calc.Subtract(5,7.5), -2.5);
        }
        
        [Test]
        public void Multiply_TwoNumbers_ReturnsProductOfNumbers()
        {
            Assert.AreEqual(_calc.Multiply(11,4), 44);
        }
        
        [Test]
        public void Divide_TwoNumbers_ReturnsQuotientOfNumbers()
        {
            Assert.AreEqual(_calc.Divide(10,5), 2);
        }
        
        [Test]
        public void Divide_DivisorIsZero_ThrowsDivideByZeroException()
        {
            Assert.Throws<DivideByZeroException>(() => _calc.Divide(10,0));
        }
    }
}