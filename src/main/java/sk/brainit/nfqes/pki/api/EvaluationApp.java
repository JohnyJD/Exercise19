package sk.brainit.nfqes.pki.api;

import sk.brainit.nfqes.pki.api.configuration.IConfigurable;
import sk.brainit.nfqes.pki.api.evaluators.NumericEvaluator;
import sk.brainit.nfqes.pki.api.evaluators.IRunnable;

public class EvaluationApp implements IApp, IConfigurable {
    private static EvaluationApp instance = null;
    private IRunnable runnable;

    private EvaluationApp() {
        // "/Users/jandunaj/Desktop/Exercise19/log.txt"
        runnable = new NumericEvaluator();
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

    @Override
    public void load(String path) {
        if(runnable instanceof IConfigurable iconfigurable) {
            iconfigurable.load(path);
        }
    }
}
