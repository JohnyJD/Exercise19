package sk.brainit.nfqes.pki.api.evaluators;

import sk.brainit.nfqes.pki.api.conditions.ICondition;
import sk.brainit.nfqes.pki.api.loggers.ILogger;

import java.util.List;

public class ConditionEvaluator extends Evaluator<Integer, String> {
    private final StringBuilder sb;

    public ConditionEvaluator(List<ICondition> conditions, List<ILogger<String>> loggers) {
        super(conditions, loggers);
        this.sb = new StringBuilder();
    }

    @Override
    public void run() {
        for(int i = 1; i <= 100; i++) {
            doLogging(i);
        }
    }

    @Override
    public String process(Integer value) {
        sb.setLength(0);
        for(ICondition condition : conditions) {
            String result = condition.perform(value);
            if(result != null)
                sb.append(result);
        }
        if(sb.isEmpty())
            sb.append(value);
        return sb.toString();
    }
}
