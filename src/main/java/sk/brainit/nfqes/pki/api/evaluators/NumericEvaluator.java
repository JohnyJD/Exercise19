package sk.brainit.nfqes.pki.api.evaluators;

import lombok.Setter;
import sk.brainit.nfqes.pki.api.conditions.ICondition;
import sk.brainit.nfqes.pki.api.conditions.NumericCondition;
import sk.brainit.nfqes.pki.api.configuration.Config;
import sk.brainit.nfqes.pki.api.configuration.ConfigurationLoader;
import sk.brainit.nfqes.pki.api.interpreters.ConditionInterpreter;
import sk.brainit.nfqes.pki.api.loggers.ConsoleLogger;
import sk.brainit.nfqes.pki.api.loggers.FileLogger;

import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Numeric evaluator evaluates Integers as inputs and String as its output
 * Uses StringBuilder to incrementally build output String which in the end will be logged
 * Runnable configurations start, end, step are modifiable
 */
public class NumericEvaluator extends Evaluator<Integer, String> {
    private Logger logger = Logger.getLogger(NumericEvaluator.class.getName());
    private final StringBuilder sb;
    @Setter
    private int start = 1;
    @Setter
    private int end = 100;
    @Setter
    private int step = 1;

    public NumericEvaluator() {
        this.sb = new StringBuilder();
        this.interpreter = new ConditionInterpreter();
        addLogger(new ConsoleLogger());
    }

    /**
     * Iterating through integers with defined step
     */
    @Override
    public void run() {
        for(int i = start; i <= end; i+=step) {
            evaluate(i);
        }
        doLogging(sb.toString());
    }

    /**
     * Evaluates output based on set conditions
     * Conditions are applied in each iteration
     * @param input Integer value from iteration
     * @return Output value based on conditional logic
     */
    @Override
    public String evaluate(Integer input) {
        boolean match = false;
        if(conditions != null) {
            for(ICondition<Integer, String> condition : conditions) {
                String result = condition.evaluate(interpreter, input);
                if(result != null) {
                    match = true;
                    sb.append(result);
                }
            }
        }

        if(!match)
            sb.append(input);
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Loads configuration if config file was loaded
     * When config is not loaded returns null
     * @param path Config file path
     */
    @Override
    public void load(String path) {
        if(path != null && !path.isEmpty()) {
            Config config = ConfigurationLoader.loadConfig(path);
            if(config == null) {
                logger.warning("Unable to load config file: " + path);
                return;
            }
            this.start = config.getStart();
            this.end = config.getEnd();
            this.step = config.getStep();
            this.conditions = config
                    .getExpressions()
                    .stream()
                    .map(ex -> new NumericCondition(ex.getExpression(), ex.getResult()))
                    .collect(Collectors.toList());
            if(config.isEnableFileLogger()) {
                this.loggers.add(new FileLogger(config.getFileLogPath()));
            }
        }
    }
}
