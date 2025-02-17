package sk.brainit.nfqes.pki.api.evaluators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sk.brainit.nfqes.pki.api.conditions.ICondition;
import sk.brainit.nfqes.pki.api.configuration.IConfigurable;
import sk.brainit.nfqes.pki.api.interpreters.IInterpreter;
import sk.brainit.nfqes.pki.api.loggers.ILogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Evaluator abstraction class. Each evaluator will have conditions and loggers. Base class
 * @param <T> Input data (determined by condition)
 * @param <I> Output data (determined by condition)
 */
@NoArgsConstructor
@Getter
public abstract class Evaluator<T, I> implements IEvaluator<T, I>, IRunnable, IConfigurable {
    protected IInterpreter interpreter;
    protected List<ICondition<T, I>> conditions = new ArrayList<>();
    protected List<ILogger<I>> loggers = new ArrayList<>();

    protected void doLogging(I object) {
        if(loggers != null)
            loggers.forEach(x -> x.log(object));
    }

    /**
     * Register new logger
     * @param logger Logger abstraction
     */
    public void addLogger(ILogger<I> logger) {
        this.loggers.add(logger);
    }

    /**
     * Register new conditions
     * @param conditions List of condition abstractions
     */
    public void addConditions(List<ICondition<T, I>> conditions) {
        this.conditions.addAll(conditions);
    }
}
