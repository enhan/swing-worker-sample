package eu.enhan.sample.worker.view;

import javax.swing.*;
import java.awt.*;

/**
 * Vue de l'application
 *
 * L'application est composée de :
 * <ul>
 *     <li>Un bouton de lancement du traitement</li>
 *     <li>Un spinner qui permet de régler la taille de la liste de traitements</li>
 *     <li>Une progress bar indiquant l'avancement</li>
 * </ul>
 * @author Emmanuel Nhan
 */
public class AppView extends JFrame {

    private final JButton launch;
    private final JProgressBar progress;
    private final JSpinner spinner;


    /**
     * Mise en place de l'environnement.
     * @throws HeadlessException
     */
    public AppView() throws HeadlessException {
        super("Swing worker sample");
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        launch = new JButton("Launch processing");
        progress = new JProgressBar(0, 100);
        progress.setValue(0);
        final SpinnerNumberModel spinnerModel = new SpinnerNumberModel(5, 0, 30, 1);
        spinner = new JSpinner(spinnerModel);
        this.setLayout(layout);
        this.add(launch, BorderLayout.NORTH);
        this.add(progress, BorderLayout.SOUTH);
        this.add(spinner);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    public JButton getLaunch() {
        return launch;
    }

    public JProgressBar getProgress() {
        return progress;
    }

    public JSpinner getSpinner() {
        return spinner;
    }
}
