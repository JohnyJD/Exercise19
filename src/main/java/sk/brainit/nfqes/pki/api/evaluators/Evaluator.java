package sk.brainit.nfqes.pki.api.evaluators;

import sk.brainit.nfqes.pki.api.conditions.IEvaluable;
import sk.brainit.nfqes.pki.api.loggers.ILogger;

import java.util.List;

public abstract class Evaluator<T, I> implements IEvaluator<T, I>, IRunnable {
    protected List<IEvaluable<T, I>> evaluables;
    protected List<ILogger<I>> loggers;

    protected Evaluator(List<IEvaluable<T, I>> evaluables) {
        this.evaluables = evaluables;
    }

    protected Evaluator(List<IEvaluable<T, I>> evaluables, List<ILogger<I>> loggers) {
        this(evaluables);
        this.loggers = loggers;
    }

    protected void doLogging(T value) {
        if(loggers != null)
            loggers.forEach(x -> x.log(evaluate(value)));
    }
}
