package sk.brainit.nfqes.pki.api.evaluators;

import sk.brainit.nfqes.pki.api.conditions.ICondition;
import sk.brainit.nfqes.pki.api.loggers.ILogger;

import java.util.List;

public abstract class Evaluator<T, I> implements IEvaluator<T, I>, IRunnable {
    protected List<ICondition> conditions;
    protected List<ILogger<I>> loggers;

    protected Evaluator(List<ICondition> conditions) {
        this.conditions = conditions;
    }

    protected Evaluator(List<ICondition> conditions, List<ILogger<I>> loggers) {
        this(conditions);
        this.loggers = loggers;
    }

    protected void doLogging(T value) {
        loggers.forEach(x -> x.log(process(value)));
    }
}
