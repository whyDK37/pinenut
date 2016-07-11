package framework.c7.calc;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * Created by Think on 2016/4/24.
 */
@Aspect
public class CalculatorIntroduction {
    @DeclareParents(value = "framework.c7.calc.ArithmeticCalulatorImpl"
            , defaultImpl = MaxCalculatorImpl.class)
    public MaxCalculator maxCalculator;

    @DeclareParents(value = "framework.c7.calc.ArithmeticCalulatorImpl"
            , defaultImpl = MinCalculatorImpl.class)
    public MinCalculator minCalculator;
}
