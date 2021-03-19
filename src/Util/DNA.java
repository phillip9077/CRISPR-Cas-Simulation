package Util;

import java.util.Map;

/**
 * Represents the simple double-stranded nature of DNA.
 */
public abstract class DNA {

  /*
  Desired methods:
    - transcription --> turn DNA into crRNA sequence
    - complementary pairing --> make sure both strands are complementary of another
   */

  protected final String parentStrand;
  protected final String tempStrand;
  public static final Map<Character, Character> BASE_PAIRS;

  static {
    BASE_PAIRS = Map.of('A', 'T', 'T', 'A', 'C', 'G', 'G', 'C');
  }

  /**
   * Default constructor for the {@code DNA} class.
   *
   * @param parentStrand The parent strand of the DNA
   * @param tempStrand   The template strand of the DNA
   * @throws IllegalArgumentException If either strings are empty or the provided sequences aren't
   *                                  complementary
   */
  public DNA(String parentStrand, String tempStrand) throws IllegalArgumentException {
    if (parentStrand.equals("") || tempStrand.equals("")) {
      throw new IllegalArgumentException("The provided sequence(s) cannot be empty");
    } else if (!this.isComplementary(parentStrand, tempStrand)) {
      throw new IllegalArgumentException("The provided sequences must be complementary");
    }
    this.parentStrand = parentStrand;
    this.tempStrand = tempStrand;
  }

  /**
   * Represents the action of a Cas endonuclease recognizing secondary hairpin structures formed by
   * the repeats and cleaves it, which produces a sticky end for the start of a mature crRNA.
   *
   * @return a shortened section from a {@code String} palindromic repeat
   * @throws UnsupportedOperationException If a Spacer class ever calls this method
   */
  public abstract String endonucleaseRNACleave() throws UnsupportedOperationException;

  /**
   * Takes the template strand and synthesizes an RNA strand from it in the 5' to 3' direction.
   *
   * @return the transcribed RNA strand
   */
  public String DNA2RNA() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < this.tempStrand.length(); i++) {
      result.append(DNA.BASE_PAIRS.get(this.tempStrand.charAt(i)));
    }
    return result.toString();
  }

  /**
   * Returns the parent DNA sequence
   *
   * @return The parent DNA sequence
   */
  public String getParentStrand() {
    return this.parentStrand;
  }

  /**
   * Returns the template DNA sequence
   *
   * @return The template DNA sequence
   */
  public String getTempStrand() {
    return this.tempStrand;
  }

  /**
   * Determines if the provided parent strand and template strand are indeed complementary.
   *
   * @param parentStrand The parent strand sequence
   * @param tempStrand   The template strand sequence
   * @return If both strands are complementary of one another or not
   */
  private boolean isComplementary(String parentStrand, String tempStrand) {
    for (int i = 0; i < parentStrand.length(); i++) {
      char p = parentStrand.charAt(i);
      char t = tempStrand.charAt(i);
      if (!DNA.BASE_PAIRS.get(p).equals(t)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return this.parentStrand.hashCode() + this.tempStrand.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof DNA)) {
      return false;
    }
    DNA dna = (DNA) obj;
    return this.parentStrand.equals(dna.parentStrand) &&
        this.tempStrand.equals(dna.tempStrand);
  }
}
