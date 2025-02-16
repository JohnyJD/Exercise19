package sk.brainit.nfqes.pki.api.evaluators;

import sk.brainit.nfqes.pki.api.conditions.IEvaluable;
import sk.brainit.nfqes.pki.api.loggers.ILogger;

import java.util.List;

public class NumericEvaluator extends Evaluator<Integer, String> {
    private final StringBuilder sb;
    private int start = 1;
    private int end = 100;

    public NumericEvaluator(
            List<IEvaluable<Integer, String>> evaluables,
            List<ILogger<String>> loggers
    ) {
        super(evaluables, loggers);
        this.sb = new StringBuilder();
    }

    public NumericEvaluator(
            List<IEvaluable<Integer, String>> evaluables,
            List<ILogger<String>> loggers,
            int start,
            int end
    ) {
        this(evaluables, loggers);
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for(int i = start; i <= end; i++) {
            doLogging(i);
        }
    }

    @Override
    public String evaluate(Integer value) {
        sb.setLength(0);
        if(evaluables != null) {
            for(IEvaluable<Integer, String> condition : evaluables) {
                String result = condition.evaluate(value);
                if(result != null)
                    sb.append(result);
            }
        }

        if(sb.isEmpty())
            sb.append(value);
        return sb.toString();
    }
}
