package br.ufrpe.revcare.avaliacao.negocio;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import br.ufrpe.revcare.usuario.dominio.Usuario;

/**
 * Crédito por este algoritmo:
 * Site: https://www.programcreek.com/java-api-examples/index.php?source_dir=HappyResearch-master/happyresearch/src/main/java/happy/research/cf/SlopeOne.java
 */

@SuppressWarnings("ConstantConditions")
class SlopeOne {

    private Map<Usuario, Map<String, Double>> data = new HashMap<>();
    private final Map<Usuario, Map<String, Double>> mData;
    private Map<String, HashMap<String, Double>> diffMatrix;
    private Map<String, HashMap<String, Integer>> freqMatrix;

    public SlopeOne(Map<Usuario, Map<String, Double>> data) {
        mData = data;
        buildDiffMatrix();
    }

    private void buildDiffMatrix() {
        diffMatrix = new HashMap<String, HashMap<String, Double>>();
        freqMatrix = new HashMap<String, HashMap<String, Integer>>();
        for (Map<String, Double> user : mData.values()) {
            for (Map.Entry<String, Double> entry : user.entrySet()) {
                String i1 = entry.getKey();
                double r1 = entry.getValue();
                if (!diffMatrix.containsKey(i1)) {
                    diffMatrix.put(i1, new HashMap<String, Double>());
                    freqMatrix.put(i1, new HashMap<String, Integer>());
                }
                for (Map.Entry<String, Double> entry2 : user.entrySet()) {
                    String i2 = entry2.getKey();
                    double r2 = entry2.getValue();
                    int cnt = 0;
                    if (Objects.requireNonNull(freqMatrix.get(i1)).containsKey(i2))
                        cnt = Objects.requireNonNull(freqMatrix.get(i1)).get(i2);
                    double diff = 0.0;
                    if (Objects.requireNonNull(diffMatrix.get(i1)).containsKey(i2))
                        diff = Objects.requireNonNull(diffMatrix.get(i1)).get(i2);
                    double new_diff = r1 - r2;
                    Objects.requireNonNull(freqMatrix.get(i1)).put(i2, cnt + 1);
                    Objects.requireNonNull(diffMatrix.get(i1)).put(i2, diff + new_diff);
                }
            }
        }
        for (String j : diffMatrix.keySet()) {
            for (String i : Objects.requireNonNull(diffMatrix.get(j)).keySet()) {
                Double oldvalue = Objects.requireNonNull(diffMatrix.get(j)).get(i);
                int count = Objects.requireNonNull(freqMatrix.get(j).get(i)).intValue();
                Objects.requireNonNull(diffMatrix.get(j)).put(i, oldvalue / count);
            }
        }
    }
    public Map<String, Double> predict(Map<String, Double> user) {
        HashMap<String, Double> predictions = new HashMap<>();
        HashMap<String, Integer> frequencies = new HashMap<>();
        for (String j : diffMatrix.keySet()) {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (String j : user.keySet()) {
            for (String k : diffMatrix.keySet()) {
                try {
                    Double newval = (Objects.requireNonNull(diffMatrix.get(k)).get(j) + user.get(j)) * Objects.requireNonNull(freqMatrix.get(k).get(j)).intValue();
                    predictions.put(k, predictions.get(k) + newval);
                    frequencies.put(k, frequencies.get(k) + Objects.requireNonNull(freqMatrix.get(k).get(j)).intValue());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        HashMap<String, Double> cleanpredictions = new HashMap<>();
        for (String j : predictions.keySet()) {
            if (frequencies.get(j) > 0) {
                cleanpredictions.put(j, predictions.get(j) / Objects.requireNonNull(frequencies.get(j)).intValue());
            }
        }
        for (String j : user.keySet()) {
            cleanpredictions.put(j, user.get(j));
        }
        return cleanpredictions;
    }

}

