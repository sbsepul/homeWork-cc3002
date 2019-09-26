package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

/**
 * Un Tactician debe ser capaz de conocer todas las unidades que posee, y tener conocimiento del mapa del
 * juego. Dentro de su turno un jugador puede mover a todas sus unidades, pero una sola vez. Para facilitar
 * la implementación el jugador debe mantener una referencia a la unidad actualmente seleccionada.
 *
 * @author Sebastian Sepulveda
 * @version 2.0
 * @since 2.0
 *
 */


public class Tactician {
    private final String mark;
    private final BufferedReader in;
    private final List<IUnit> units = new ArrayList<>();

    /**
     * Constructor to specify an alternative source of moves
     *
     * @param initMark name for mark to player
     * @param initIn
     */
    public Tactician(String initMark, BufferedReader initIn){
        mark = initMark;
        in = initIn;
    }

    /**
     * Constructor normal to use with input
     *
     * @param initMark
     */
    public Tactician(String initMark) { this(initMark, new BufferedReader(new InputStreamReader(System.in))); }

    /**
     * Special constructor to make a Player that plays a fixed
     * set of moves from a String.
     *
     * Used to define test cases.
     */
    public Tactician(String initMark, String moves) {
        this(initMark, new BufferedReader(new StringReader(moves)));
    }

    public String getName() {
        return mark;
    }

}
