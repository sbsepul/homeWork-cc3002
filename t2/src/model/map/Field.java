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

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;

/**
 * This class represents the map where the units are located and the game is played.
 * <p>
 * The field is an undirected graph composed of <i>Location</i> nodes where the weight of every edge
 * of the graph is 1.
 * Since all cells of the map should be reachable, the graph must be connected.
 *
 * @author Sebastian Sepulveda
 * @since 1.0
 */
public class Field {

  private Map<String, Location> map = new HashMap<>();
  private Random random = new Random(212121);
  private StringBuilder builder = new StringBuilder();

  /**
   * Add cells to the map.
   *
   * @param connectAll
   *     a flag that indicates if all the cells should be connected to it's neighbours
   * @param cells
   *     the locations that are going to be added to the map
   */
  public void addCells(final boolean connectAll, @NotNull final Location... cells) {
    // Seed inserted
    for (Location cell : cells) {
      addCell(cell);
      Location[] adjacentCells = getAdjacentCells(cell);
      for (Location adjacentCell : adjacentCells) {
        if (connectAll || random.nextDouble() > 1.0 / 3 || cell.getNeighbours().size() < 1) {
          addConnection(cell, adjacentCell);
        }
      }
    }
  }

  public void setSeed(long seed){
    this.random = new Random(seed);
  }

  /**
   * Adds a cell to the map
   *
   * @param cell
   *     the location to be added
   */
  private void addCell(final Location cell) {
    map.put(cell.toString(), cell);
  }

  /**
   * Gets the possible adjacent cells to a given cell
   *
   * @param cell
   *     the location of the current cell
   * @return an array of the adjacent cells
   */
  private Location[] getAdjacentCells(final Location cell) {
    int row = cell.getRow(),
        col = cell.getColumn();
    return new Location[]{getCell(row - 1, col), getCell(row + 1, col), getCell(row, col - 1),
        getCell(row, col + 1)};
  }

  /**
   * Creates a connection between 2 cells
   */
  private void addConnection(@NotNull Location cell1, Location cell2) {
    cell1.addNeighbour(cell2);
  }

  /**
   * @param row
   *     the row of the cell
   * @param col
   *     the column of the cell
   * @return the Location that represents the cell at (row, col)
   */
  public Location getCell(final int row, final int col) {
    String id = generateID(row, col);
    return map.getOrDefault(id, new InvalidLocation());
  }

  /**
   * Creates a map key from a row and a column
   *
   * @param row
   *     the row of the cell
   * @param col
   *     the column of the cell
   * @return a string of the form (row, col)
   */
  @NotNull
  private String generateID(final int row, final int col) {
    builder.setLength(0);
    builder.append("(").append(row).append(", ").append(col).append(")");
    return builder.toString();
  }

  public Map<String, Location> getMap() {
    return Map.copyOf(map);
  }

  /**
   * Checks if the map is connected using BFS.
   *
   * @return true if the map is connected, false otherwise.
   */
  public boolean isConnected() {
    Set<Location> visitedNodes = new HashSet<>();
    Queue<Location> toVisit = new LinkedList<>();
    toVisit.add(map.entrySet().iterator().next().getValue());
    while (!toVisit.isEmpty()) {
      if (visitedNodes.size() == map.size()) {
        return true;
      }
      Location currentNode = toVisit.poll();
      for (Location neighbour :
          currentNode.getNeighbours()) {
        if (!visitedNodes.contains(neighbour)) {
          visitedNodes.add(neighbour);
          toVisit.add(neighbour);
        }
      }
    }
    return false;
  }

  /**
   * Removes a connection from two locations of the field
   */
  public void removeConnection(@NotNull final Location cell1, final Location cell2) {
    if (cell1.getNeighbours().size() > 1 && cell2.getNeighbours().size() > 1) {
      cell1.removeNeighbour(cell2);
    }
  }

  /**
   * Checks if two cells of the map are connected
   */
  public boolean checkConnection(@NotNull final Location cell1, final Location cell2) {
    return cell1.isNeighbour(cell2);
  }


  /**
   *
   * @return size of map
   */
  public int getSize() {
    return (int) Math.sqrt(getMap().size());
  }

  public Map<Integer, StringBuilder> toStringGetAdjacent(@NotNull Location cell){
    Map<Integer, StringBuilder> newMap = new HashMap<>();
    StringBuilder str1 = new StringBuilder();
    StringBuilder str2 = new StringBuilder();
    StringBuilder str3 = new StringBuilder();
    int row = cell.getRow(),
            col = cell.getColumn();
    if(checkConnection(getCell(row - 1, col), cell)) str1.append("   ");
    else str1.append(" # ");
    if(checkConnection(getCell(row + 1, col), cell)) str3.append("   ");
    else str3.append(" # ");
    if(checkConnection(getCell(row, col - 1), cell)){
      if(cell.getUnit()!=null) str2.append(" o");
      else str2.append(" +");
    }
    else {
      if(cell.getUnit()!=null) str2.append("#o");
      else str2.append("#+");
    }
    if(checkConnection(getCell(row, col + 1), cell)) str2.append(" ");
    else str2.append("#");
    newMap.put(0,str3); newMap.put(1,str2); newMap.put(2,str1);
    return newMap;
  }

  @Override
  public String toString(){
    StringBuilder str = new StringBuilder();
    for(int i = getSize()-1; i>=0; i--){
      StringBuilder str_l1 = new StringBuilder();
      StringBuilder str_l2 = new StringBuilder();
      StringBuilder str_l3 = new StringBuilder();
      for(int j = 0; j<getSize(); j++){
        Map<Integer, StringBuilder> newMap = new HashMap<>();
        Location cell = getCell(i,j);
        newMap = toStringGetAdjacent(cell);
        str_l1.append(newMap.get(0).toString());
        str_l2.append(newMap.get(1).toString());
        str_l3.append(newMap.get(2).toString());
        if(j==getSize()-1){
          str_l1.append("\n"); str_l2.append("\n"); str_l3.append("\n");
          if(!str_l1.toString().contains("#")) str_l1.toString().replaceAll(" ", "");
          else str.append(str_l1.toString());
          str.append(str_l2.toString());
          if(!str_l3.toString().contains("#")) str_l3.toString().replaceAll(" ", "");
          else str.append(str_l3.toString());
        }
      }
    }
    return str.toString();
  }


}
