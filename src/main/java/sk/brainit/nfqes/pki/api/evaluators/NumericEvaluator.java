package sk.brainit.nfqes.pki.api.evaluators;

import sk.brainit.nfqes.pki.api.conditions.ICondition;
import sk.brainit.nfqes.pki.api.conditions.NumericCondition;
import sk.brainit.nfqes.pki.api.configuration.Config;
import sk.brainit.nfqes.pki.api.configuration.ConfigurationLoader;
import sk.brainit.nfqes.pki.api.interpreters.ConditionInterpreter;
import sk.brainit.nfqes.pki.api.loggers.ConsoleLogger;
import sk.brainit.nfqes.pki.api.loggers.FileLogger;

import java.util.logging.Logger;
import java.util.stream.Collectors;

public class NumericEvaluator extends Evaluator<Integer, String> {
    private Logger logger = Logger.getLogger(NumericEvaluator.class.getName());
    private final StringBuilder sb;
    private int start = 1;
    private int end = 100;
    private int step = 1;

    public NumericEvaluator() {
        this.sb = new StringBuilder();
        this.interpreter = new ConditionInterpreter();
        addLogger(new ConsoleLogger());
    }

    @Override
    public void run() {
        for(int i = start; i <= end; i+=step) {
            evaluate(i);
        }
        doLogging(sb.toString());
    }

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
