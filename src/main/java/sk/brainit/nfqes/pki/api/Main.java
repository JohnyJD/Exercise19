package sk.brainit.nfqes.pki.api;

import sk.brainit.nfqes.pki.api.app.EvaluationApp;
import sk.brainit.nfqes.pki.api.app.IApp;
import sk.brainit.nfqes.pki.api.configuration.IConfigurable;

public class Main {

    public static void main(String[] args) {
        IApp app = EvaluationApp.getInstance();
        // First argument = path to configuration file
        // e.g. /Users/example/Desktop/project/config.json
        if(args.length == 1 && app instanceof IConfigurable iConfigurable)
                iConfigurable.load(args[0]);
        app.start();
    }
}