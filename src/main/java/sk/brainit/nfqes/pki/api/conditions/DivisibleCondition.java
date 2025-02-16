package sk.brainit.nfqes.pki.api.conditions;

public class DivisibleCondition implements ICondition<Integer>, IEvaluable<Integer, String> {
    private final Integer divisor;
    private final String resultValue;

    public DivisibleCondition(Integer divisor, String resultValue) {
        this.divisor = divisor;
        this.resultValue = resultValue;
    }

    @Override
    public boolean check(Integer value) {
        return value % divisor == 0;
    }

    @Override
    public String evaluate(Integer value) {
        return check(value) ? resultValue : null;
    }
}
