package eu.enhan.sample.worker.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple classe simulant un modèle qui ne fait rien et qui attends juste.
 *
 * @author Emmanuel Nhan
 */
public class DumbProcessingList {

    private static final Logger LOGGER = LoggerFactory.getLogger(DumbProcessingList.class);

    private final int nbIterations;

    private final Set<DumbListProcessingListener> listeners;


    public DumbProcessingList(int nbIterations) {
        this.nbIterations = nbIterations;
        listeners = new HashSet<DumbListProcessingListener>();
    }

    public int getNbIterations() {
        return nbIterations;
    }

    public void addListener(DumbListProcessingListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Méthode effectuant le traitement long. Ici, on attend juste pendant 3 secondes.
     */
    public void processList() {
        try {
            for (int i = 0; i < nbIterations; i++) {

                Thread.sleep(3000);
                // Quand le traitement est fini, on notifie les observateurs
                for (DumbListProcessingListener listener : listeners) {
                    listener.onItemProcessingCompleted(i);
                }
                LOGGER.info("Item #{} traité.", i);
            }
        } catch (InterruptedException e) {
            LOGGER.info("Processing interrupted.");
        }
    }
}
