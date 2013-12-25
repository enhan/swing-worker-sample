package eu.enhan.sample.worker.controller;

import eu.enhan.sample.worker.model.DumbListProcessingListener;
import eu.enhan.sample.worker.model.DumbProcessingList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * @author Emmanuel Nhan
 */
public class ProcessWorker extends SwingWorker<Void, Integer> implements DumbListProcessingListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessWorker.class);

    private final DumbProcessingList model;

    public ProcessWorker(DumbProcessingList model) {
        this.model = model;
        model.addListener(this);
    }

    @Override
    protected Void doInBackground() throws Exception {
        LOGGER.debug("Background processing");
        model.processList();
        LOGGER.debug("Processing done");
        return null;
    }

    @Override
    public void onItemProcessingCompleted(int itemIndex) {
        // Calcul de l'avancement en %
        this.setProgress(((itemIndex + 1) * 100) / model.getNbIterations());
    }
}
