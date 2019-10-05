# Controlador
## Resumen



## Modo de uso	





## Supuestos



Los jugadores pueden seleccionar la unidad que quieren 





para ver si un jugador puede seguir jugando se crea canPlay(). Un jugador puede seguir jugando si y solo si

Todos sus heros live





Para que un jugador gane:

* Todos el resto de los jugadores se han retirado del juego: esto significa que un jugador en una partida actual puede "colocar" me retiro. Esto implica que un tactician cambia su estado a "no seguir", esto lo vamos a definir como un boleeano true si esta activo, false si no (a penas un jugador tenga estado false debe ser eliminado de la partida con sus unidades)

* Se alcanza una cantidad máxima de turnos (-1 es indefinido). El ganador en este caso es el que tiene la mayor de unidades restantes. Si dos jugadores tienen la misma cantidad de unidades entonces se declara un empate entre ambos jugadores.





Para las unidaddes que tiene cada jugador vamos a considerar los siguientes supuestos:

Casos bordes:

* En cada ronda de juego los jugadores deben usar su turno. AL comenzar la partida se decidirá de forma aleatoria el orden en que juegan los tactician, y al **final** de cada ronda se seleccionará de manera aleatoria un **nuevo orden** de juego de **manera aleatoria**

* Si las unidades pueden tenern 0 unidades en un comienzo. En este caso el jugador no puede jugar, esto significaria que tiene que no se puede mover, lo cual quita un poco la logica del programa. Luego tambien sucede que el *tactician*  pierde en un caso cuando no tiene mas unidades. Entonces en este caso el jugador que parte sin unidades se ve como que revive. Nah que verrrratatatatata (DEBEN TENER MINIMO 1 UNIDAD
* Cada tactician puede mover a todas sus unidades **solo una vez**, esto implica que una vez que mueva una unidad el proceso de partida continua si hay otra unidad que se pueda mover, es decir, hay otra unidad que este en la misma posicion
* En ningun momento del juego debe haber dos unidades en la misma casilla. Por tanto, si una unidad muere, hay que verificar que la casilla en la que se encuentraba queda vacia para que otra unidad pueda ocupar ese puesto en otro turno, o incluso en el mismo.
* Se asume que el van a existir 
* Al selecccionar una unidad esta debe pertenecer al equipo del jugador. No deberia poder seleccionar a otra unidad que no sea de su equipo.





## Dudas



* Los Tacticians deben tener referencia a todo el mapa y además deben conocer el lugar que se le asigna al comienzo?

* Dado el tamaño del mapa existe una cantidad limitada de jugadores que pueden participar y una cantidad maxima de unidades que pueden tener cada uno para colocarlos en el mapa. Por tanto, se debe considerar esto al iniciarse el juego?

* Asumiendo que tener 0 unidades significa perder, pensé en crear un hero en el constructor de tactician para que tenga sentido que el juego no inicie con todos perdiendo, por ejemplo si el jugador no selecciona a un hero al inicio. Tengo la duda si esto esta bien y si el area que se le asigna al jugador debe ser aleatorio o eso es algo arbitrario? 

* Cuando se señala que el controller debe manejar inputs, esto significa que debemos implementar un BufferedReader en Controller? 

* En la tarea se debe manejar errores con IOException y todo eso? 

* En el enunciado aparece que al jugador se le asigna un área de inicio donde debe situar sus unidades

* Si un metodo retorna un valor cualquiera, es valido que en algun caso retorne nulo? por ejemplo cree un mapa que guarda en las llaves la posicion del hero en la lista de unidades de tactician, y en los valores guarda un boleano que representa el estado del hero (true vivo, false muerto). Esto hace mas facil revisar cuando un jugador perdió o ganó, porque si no habria que revisar cada HP de cada unidad siempre que se termina una batalla.

* 

  