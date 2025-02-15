package sk.brainit.nfqes.pki.api.conditions;

public class DivisibleCondition implements ICondition, IEvaluable<Integer> {
    private final Integer divisor;
    private final String resultValue;

    public DivisibleCondition(Integer divisor, String resultValue) {
        this.divisor = divisor;
        this.resultValue = resultValue;
    }

    @Override
    public boolean evaluate(Integer value) {
        return value % divisor == 0;
    }

    @Override
    public String perform(Integer value) {
        return evaluate(value) ? resultValue : null;
    }
}
