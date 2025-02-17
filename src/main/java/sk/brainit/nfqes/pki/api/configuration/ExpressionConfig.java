package sk.brainit.nfqes.pki.api.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Configuration file representing conditions
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpressionConfig {
    private String expression;
    private String result;
}
