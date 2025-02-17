package sk.brainit.nfqes.pki.api.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Configuration class
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Config {
    private int start = 1;
    private int end = 100;
    private int step = 1;
    private List<ExpressionConfig> expressions;
    private boolean enableFileLogger;
    private String fileLogPath;
}
