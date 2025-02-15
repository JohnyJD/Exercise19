package sk.brainit.nfqes.pki.api;

import sk.brainit.nfqes.pki.api.conditions.DivisibleCondition;
import sk.brainit.nfqes.pki.api.conditions.ICondition;
import sk.brainit.nfqes.pki.api.evaluators.ConditionEvaluator;
import sk.brainit.nfqes.pki.api.evaluators.IRunnable;
import sk.brainit.nfqes.pki.api.loggers.ConsoleLogger;
import sk.brainit.nfqes.pki.api.loggers.ILogger;

import java.util.List;

public class EvaluationApp implements IApp {
    private static EvaluationApp instance = null;
    private List<ICondition> conditions;
    private List<ILogger<String>> loggers;
    private IRunnable runnable;

    private EvaluationApp() {
        conditions = List.of(new DivisibleCondition(2, "foo"), new DivisibleCondition(4, "fuu"));
        loggers = List.of(new ConsoleLogger());
        runnable = new ConditionEvaluator(conditions, loggers);
    }

    public static EvaluationApp getInstance() {
        if(instance != null) {
            return instance;
        }
        instance = new EvaluationApp();
        return instance;
    }

    @Override
    public void start() {
        runnable.run();
    }
}
