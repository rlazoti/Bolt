package br.com.boltframework.util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class FileUtilsTest {

  @Test
  public void testObtainContentOfFileWithInvalidFilename() {
    try {
      FileUtils.obtainContentOfFile("xpto.Lorem");
      assertTrue(false);
    }
    catch (NullPointerException e) {
      assertTrue(true);
    }
    catch (IOException e) {
      assertTrue(false);
    }
  }

  @Test
  public void testObtainContentOfFileWithEmptyClassname() {
    try {
      FileUtils.obtainContentOfFile("");
      assertTrue(false);
    }
    catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    catch (IOException e) {
      assertTrue(false);
    }
  }

  @Test
  public void testObtainContentOfFileWithNullClassname() {
    try {
      FileUtils.obtainContentOfFile(null);
      assertTrue(false);
    }
    catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    catch (IOException e) {
      assertTrue(false);
    }
  }

  @Test
  public void testObtainContentOfFileWithValidClass() {
    try {
      String content = FileUtils.obtainContentOfFile(Constants.DEFAULT_ERROR_PAGE);
      assertTrue(content != null);
    }
    catch (IOException e) {
      assertTrue(false);
    }
  }

}
