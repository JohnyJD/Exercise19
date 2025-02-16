package sk.brainit.nfqes.pki.api;

import sk.brainit.nfqes.pki.api.conditions.DivisibleCondition;
import sk.brainit.nfqes.pki.api.conditions.IEvaluable;
import sk.brainit.nfqes.pki.api.evaluators.NumericEvaluator;
import sk.brainit.nfqes.pki.api.evaluators.IRunnable;
import sk.brainit.nfqes.pki.api.loggers.ConsoleLogger;
import sk.brainit.nfqes.pki.api.loggers.FileLogger;
import sk.brainit.nfqes.pki.api.loggers.ILogger;

import java.util.List;

public class EvaluationApp implements IApp {
    private static EvaluationApp instance = null;
    private List<IEvaluable<Integer, String>> evaluables;
    private List<ILogger<String>> loggers;
    private IRunnable runnable;

    private EvaluationApp() {
        evaluables = List.of(new DivisibleCondition(2, "foo"), new DivisibleCondition(4, "fuu"));
        loggers = List.of(new ConsoleLogger(), new FileLogger("/Users/jandunaj/Desktop/Exercise19/log.txt"));
        runnable = new NumericEvaluator(evaluables, loggers);
    }

    public static EvaluationApp getInstance() {
        if(instance != null) {
            return instance;
        }
        instance = new EvaluationApp();
        return instance;
    }

    public void start(String[] args) {
        if(args.length != 0) {
            loadConfig(args[0]);
        }
        runnable.run();
    }

    public void loadConfig(String path) {

    }
}
