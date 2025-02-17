package sk.brainit.nfqes.pki.api.app;

import sk.brainit.nfqes.pki.api.configuration.IConfigurable;
import sk.brainit.nfqes.pki.api.evaluators.NumericEvaluator;
import sk.brainit.nfqes.pki.api.evaluators.IRunnable;

/**
 * Evaluation application - evaluates numbers according to specified conditions
 */
public class EvaluationApp implements IApp, IConfigurable {
    private static EvaluationApp instance = null;
    private final IRunnable runnable;

    /**
     * Singleton
     */
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

    /**
     * Start application
     */
    @Override
    public void start() {
        runnable.run();
    }

    /**
     * Load configuration data from file (.json)
     * Object mapper deserialization
     * @param path file path to configuration file (.json)
     */
    @Override
    public void load(String path) {
        if(runnable instanceof IConfigurable iconfigurable) {
            iconfigurable.load(path);
        }
    }
}
