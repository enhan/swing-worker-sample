package eu.enhan.sample.worker.controller;

import eu.enhan.sample.worker.model.DumbProcessingList;
import eu.enhan.sample.worker.view.AppView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Controlleur de l'application
 *
 * @author Emmanuel Nhan
 */
public class AppCtrl {

    /**
     * Logger - Utilisé pour écrire des messages en console
     * Exemple :
     * 23:34:27.267 [SwingWorker-pool-1-thread-1] INFO  e.e.s.w.model.DumbProcessingList - Item #0 traité.
     *                  Au dessus, c'est le nom du thread (on voit que ce n'est pas un log dans le thread UI)
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AppCtrl.class);


    public static void main(String[] args) {
        LOGGER.info("Starting app");
        new AppCtrl();

    }

    private AppView view;
    private ProcessWorker worker;
    private AppState state;

    public AppCtrl() {
        view = new AppView();
        state = AppState.IDLE;

        // Mise en place des listeners - on attend que l'utilisateur déclanche le traitement
        view.getLaunch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchProcessing();
            }
        });
    }

    public void launchProcessing() {
        state = AppState.PROCESSING;
        view.getLaunch().setEnabled(false);
        worker = new ProcessWorker(new DumbProcessingList((Integer) view.getSpinner().getValue()));
        worker.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (worker.isDone()) {
                    // Traitement fini, on réinitialise la vue
                    view.getProgress().setValue(0);
                    state = AppState.IDLE;
                    view.getLaunch().setEnabled(true);
                } else {
                    ProcessWorker source = (ProcessWorker) evt.getSource();
                    int progress = source.getProgress();
                    view.getProgress().setValue(progress);
                }
            }
        });
        try {
            worker.execute();
        } catch (Exception e) {
            // Ne devrait jamais arriver.
            LOGGER.error("Une erreur s'est produite.",e);
        }

    }

    private enum AppState {
        IDLE,
        PROCESSING
    }

}
