/*
  Desired functions:
    - Adaptation --> Given an invading genetic material (a String?) a portion of it is inserted into
                     the CRISPR array (a Stack for LIFO?)
    - Biogenesis --> CRISPR RNAs are transcribed (Strings as well) and forms ribonucleoprotein (RNP)
                     complexes with Cas proteins (a custom class prob)
    - Interference --> crRNA-Cas RNP complexes identifies the foreign target DNA (a String) through
                       complementary base-pairing and the target DNA is degraded

   Desired interfaces/classes:
     - ICrisprCasModel
     - CrisprCasModel
     - ICrisprCasView
     - CrisprCasView
     - Controller?
     - DNA
     - crRNA-Cas RNP complex??
   */

import Util.DNA;
import java.util.List;

/**
 * A bacteria's CRISPR-Cas system serves as an adaptive immunity system to protect them from viruses
 * , just like our nervous system does. This model attempts to algorithmically represent the three
 * main stages of a functional CRISPR-Cas system: adaptation, CRISPR RNA (crRNA) biogenesis, and
 * interference. It will also represent and maintain the state of a CRISPR-Cas system. There will be
 * certain aspects that might not be fully reflective of the true biological mechanism, but that is
 * because not all mechanisms related to the CRISPR-Cas system is fully understood yet.
 */
public interface ICrisprCasModel {

  /**
   * Extracts and incorporates a portion of the given viral DNA into the model's CRISPR array.
   *
   * @param virus The foreign viral DNA
   * @throws NullPointerException If the provided DNA is null
   * @throws IllegalArgumentException If the user tries to add the same virus twice
   */
  void adaptation(DNA virus) throws NullPointerException, IllegalArgumentException;

  /**
   * Transcribes a {@code List} of crRNA sequences that correspond to each viral DNA sequence in
   * this CRISPR-Cas system's CRISPR array.
   *
   * @return A list of each individual DNA sequences flanked by repeats as {@code String}s
   */
  List<String> biogenesis();

  /**
   * Checks if the given viral DNA matches any of the crRNAs. If it does, then it will get
   * "degraded" by the CRISPR model.
   *
   * @param virus The foreign viral DNA
   * @return If the viral DNA is degraded or not (if it matches any of the crRNAs)
   */
  boolean interference(String virus);


  /**
   * Checks if the CRISPR array has successfully adapted the provided viral DNA.
   *
   * @param virus The foreign viral DNA
   * @return {@code true} if the viral DNA is in the CRISPR array
   */
  boolean crisprArrayContains(DNA virus);

  /**
   * Produces a copy of the CRISPR array in the model.
   * @return A copy of the CRISPR array in the model
   */
  List<DNA> getCRISPRArray();
}
