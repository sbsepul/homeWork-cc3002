/*
 * The MIT License
 *
 * Copyright (c) 2019 Google, Inc. http://angularjs.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package model.map;

import model.units.Archer;
import model.units.IUnit;
import model.units.factoryUnit.FactoryProviderUnit;
import model.units.factoryUnit.IFactoryUnit;
import model.units.factoryUnit.UnitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sebastian Sepulveda
 * @since 1.0
 */
class LocationTest {

  /** Test locations */
  private Location
      locationA0,
      locationB0,
      locationA1;

  @BeforeEach
  void setUp() {
    locationA0 = new Location(0, 0);
    locationB0 = new Location(1, 0);
    locationA1 = new Location(0, 1);
  }

  /**
   * Tests that the id of the locations can be retrieved correctly
   */
  @Test
  public void testLocationId() {
    assertEquals("(0, 0)", locationA0.toString());
    assertEquals("(1, 0)", locationB0.toString());
  }

  @Test
  void testEquals() {
    Location sameLocation = new Location(0, 0);
    assertEquals(sameLocation, locationA0);
    assertNotEquals(locationA1, locationA0);
    assertNotEquals(locationB0, locationA0);
  }

  /**
   * Tests that the neighbourhood operations operates correctly.
   * <p>
   * All locations should start without neighbours. Also note that the neighbourhood is a symmetric
   * relation.
   * <p>
   * After the first addition, the expected graph is:
   * <pre>
   *   A0 -- B0
   *
   *   A1
   * </pre>
   * After the second addition:
   * <pre>
   *   A0 -- B0
   *   |
   *   A1
   * </pre>
   * And the third addition <b>shouldn't change</b> the graph because it adds an already existing
   * connection.
   */
  @Test
  void testNeighbourhood() {
    assertTrue(locationA0.getNeighbours().isEmpty());

    locationA0.addNeighbour(locationB0);
    assertEquals(1, locationA0.getNeighbours().size());
    assertEquals(1, locationB0.getNeighbours().size());
    assertTrue(locationA1.getNeighbours().isEmpty());
    assertTrue(locationA0.isNeighbour(locationB0));
    assertTrue(locationB0.isNeighbour(locationA0));
    assertFalse(locationA0.isNeighbour(locationA1));
    assertFalse(locationB0.isNeighbour(locationA1));

    locationA0.addNeighbour(locationA1);
    assertEquals(2, locationA0.getNeighbours().size());
    assertEquals(1, locationB0.getNeighbours().size());
    assertEquals(1, locationA1.getNeighbours().size());
    assertTrue(locationA0.isNeighbour(locationA1));
    assertTrue(locationA1.isNeighbour(locationA0));
    assertTrue(locationA0.isNeighbour(locationB0));
    assertFalse(locationB0.isNeighbour(locationA1));

    locationA0.addNeighbour(locationB0);
    locationA0.addNeighbour(locationA1);
    assertEquals(2, locationA0.getNeighbours().size());
  }

  @Test
  public void testSetUnit(){
    FactoryProviderUnit factory = new FactoryProviderUnit();
    IUnit unit = factory.makeUnit(UnitType.ARCHER).createUnit();
    assertNull(locationA0.getUnit());
    locationA0.setUnit(unit);
    assertEquals(unit, locationA0.getUnit());
  }

  @Test
  public void testInvalidLocation(){
    Location wrongLocation = new InvalidLocation();
    wrongLocation.addNeighbour(locationA0);
    assertEquals(0,wrongLocation.getNeighbours().size());
  }

  /**
   * Checks if the neighbour relation can be removed correctly.
   */
  @Test
  public void testRemoveNeighbour() {
    locationA0.addNeighbour(locationB0);
    locationA0.addNeighbour(locationA1);

    assertEquals(2, locationA0.getNeighbours().size());

    locationA0.removeNeighbour(locationB0);
    assertEquals(1, locationA0.getNeighbours().size());
    assertFalse(locationA0.getNeighbours().contains(locationB0));
  }
}