package eu.enhan.sample.worker.model;

/**
 * Interface listener du modèle
 *
 * @author Emmanuel Nhan
 */
public interface DumbListProcessingListener {

    /**
     * Methode qui est appelé par les instances de DumbProcessingList lorsqu'une itération est complétée.
     *
     * @param itemIndex numéro de l'itération
     */
    void onItemProcessingCompleted(int itemIndex);

}
