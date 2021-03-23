import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Model.CrisprCasType2AModel;
import Model.ICrisprCasModel;
import Util.DNA;
import Util.Spacer;
import java.util.List;
import org.junit.Test;

/**
 * Tests to authenticate CrisprCasModel's functionality.
 */
public class CrisprCasModelTests {

  // Tests to see if the adaptation method succeeds as expected
  @Test
  public void testSuccessfulAdaptation() {
    ICrisprCasModel crisprCasModel = new CrisprCasType2AModel();
    DNA virus = new Spacer("CAAGGGCTCA", "GTTCCCGAGT");
    crisprCasModel.adaptation(virus);
    // have to test if viruses are 1. incorporated and
    assertTrue(crisprCasModel.crisprArrayContains(virus));
    // 2. incorporated in the correct order
    DNA virus2 = new Spacer("TGCGAACCGAT", "ACGCTTGGCTA");
    crisprCasModel.adaptation(virus2);
    List<DNA> arr = crisprCasModel.getCRISPRArray();
    assertEquals(virus, arr.get(1));
    assertEquals(virus2, arr.get(3));
  }

  // Test to see if the adaptation method fails as expected due to a NullPointer
  @Test
  public void testNullFailedAdaptation() {
    ICrisprCasModel crisprCasModel = new CrisprCasType2AModel();
    try {
      crisprCasModel.adaptation(null);
    } catch (NullPointerException e) {
      assertEquals("Provided virus is null", e.getMessage());
    }
  }

  // Tests to see if the adaptation method fails as expected due to trying to add the same virus
  @Test
  public void testSameVirusFailedAdaptation() {
    ICrisprCasModel crisprCasModel = new CrisprCasType2AModel();
    DNA virus = new Spacer("CAAGGGCTCA", "GTTCCCGAGT");
    crisprCasModel.adaptation(virus);
    try {
      crisprCasModel.adaptation(virus);
    } catch (IllegalArgumentException e) {
      assertEquals("Virus already exists in CRISPR array", e.getMessage());
    }
  }

  // Tests to see if the biogenesis method works as expected
  @Test
  public void testBiogenesis() {
    ICrisprCasModel crisprCasModel = new CrisprCasType2AModel();
    DNA virus = new Spacer("CAAGGGCTCA", "GTTCCCGAGT");
    DNA virus2 = new Spacer("TGCGAACCGAT", "ACGCTTGGCTA");
    crisprCasModel.adaptation(virus);
    crisprCasModel.adaptation(virus2);
    List<String> RNAs = crisprCasModel.biogenesis();
    assertEquals(2, RNAs.size());
    DNA virus3 = new Spacer("AAAATCGTTAGGGTCA", "TTTTAGCAATCCCAGT");
    crisprCasModel.adaptation(virus3);
    RNAs = crisprCasModel.biogenesis();
    assertEquals(3, RNAs.size());
  }

  // Tests to see if the interference method works as expected
  @Test
  public void testInterference() {
    ICrisprCasModel crisprCasModel = new CrisprCasType2AModel();
    DNA virus = new Spacer("CAAGGGCTCA", "GTTCCCGAGT");
    crisprCasModel.adaptation(virus);
    List<String> RNAs = crisprCasModel.biogenesis();
    // just as how interference works, the CRISPR-Cas model should now be able to detect previous
    // viruses that have "infected" it
    assertTrue(crisprCasModel.interference(RNAs, virus));
  }
}
