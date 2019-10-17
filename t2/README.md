# Controlador
## Resumen







## Patterns Design

Para esta tarea se ocuparon 2 patrones de diseño **(en primera instancia)** para lograr obtener los resultados que se deseaban para la creacion de las unidades, items, y mapa (con factory pattern) y poder detectar el estado del jeugo en cierto instante, como las acciones realizadas por los tacticians, a traves del patron de Observer Pattern

## Factory Pattern

El Factory Pattern es el encargado de crear objetos sin exponer la lógica de instanciación al cliente, en este caso el usuario del juego. Toda el trabajo de inicializar los objetos queda oculto. 

Se utiliza para crear los items, unidades y el mapa del juego. 

### Units y Items

Para las unidades y los items se estructuró una fabrica desde una interfaz `IFactory<Object>`  y un `AbstractFactory<Object>` para aprovechar la herencia de métodos y poder realizar llamadas `super()` al constructor del `AbstractFactory<Object>` para la creación de los objetos. 

![UML for items](C:\Users\Sebastian\Desktop\Screenshot_4.png)

Otra manera de realizar este procedimiento era creando solamente una interfaz con las clases que implementaran esta interfaz para cada tipo de unidad o item, sin embargo esto impide la reutilización de codigo que facilita realizando el abstract class.

Otra clase que fue implementada fue `FactoryProvider<Object>`, que se creo utilizando una `class enum Type<Object>`. La finalidad del Provider es poder entregarle como parámetro el tipo de la clase del objeto que se desea crear para que de esta manera el provider pueda devolver el objeto correspondiente. En este caso `FactoryProvider<Object>` devuelve la fabrica que se encarga de crear el tipo de unidad o item que se desea. 







La idea del diseño se basa en que al momento de seleccionarse una cierta unidad, el controlador debe ser capaz de poder asignarsela a un tactician





## Observer Pattern



Se utiliza para generar la interaccion entre el controlador

<insertar  una imagen uml>





## Tactician

### Descripcion

Una entidad *Tactician* representa a un jugador que es manejado por el **Controller**. *Tactician* es el encargado de  manejar todas las **instrucciones del usuario** y **delegar mensajes a los objetos del modelo** tales como las unidades y los items. Este diseño permite que el usuario **no interactúe** directamente con el modelo del juego.

Para lograr esto tactician también debe **conocer a todas las unidades que posee**, como también tener conocimiento del **mapa del juego**.

Dentro del turno del jugador este puede mover a todas sus unidades, pero **solo una vez**. Esto también aplica con los ataques, una unidad **no puede atacar 2 veces***. Para facilitar la implementación, *tactician* tiene la referencia a la unidad que tiene actualmente seleccionada.

Un jugador debe tener la capacidad de **ver los datos** de sus unidades (HP current y max, items, inventario, poder, etc). 



La pieza más importante de un Tactician es su heroe.

Si el héroe de un jugador es derrotado en el turno de cualquier otro, entonces este jugador **pierde la partida y se retira del juego** junto con todas sus unidades. Si el héroe es derrotado en el turno del mismo jugador al que pertenece entonces **se termina su turno antes de ser excluido de la partida**. Un usuario puede tener más de un héroe en juego, en cuyo caso pierde la partida si cualquiera de estos es derrotado.



### Test





## Controller





## Ganar un Juego

El controller es el encargado de revisar cuales son los jugadores que ganaron el juego en ciertas circunstancias. Los ganadores se obtienen **una vez que el juego es finalizado** o que se retiran todos los jugadores.

En detalle, para que un jugador gane:

* **Todos el resto de los jugadores se han retirado del juego:** esto significa que un jugador en una partida actual puede "colocar" me retiro. Esto implica que un tactician cambia su estado a "no seguir", esto lo vamos a definir como un boleeano true si esta activo, false si no (a penas un jugador tenga estado false debe ser eliminado de la partida con sus unidades)
* **Se alcanza una cantidad máxima de turnos** (-1 es indefinido). El ganador en este caso es el que tiene la mayor de unidades restantes. Si dos jugadores tienen la misma cantidad de unidades entonces se declara un empate entre ambos jugadores.

Por tanto si se alcanza una cantidad maxima de turnos 



## Modo de uso	





## Supuestos











Los jugadores pueden seleccionar la unidad que quieren 





para ver si un jugador puede seguir jugando se crea canPlay(). Un jugador puede seguir jugando si y solo si

Todos sus heros live









Para las unidaddes que tiene cada jugador vamos a considerar los siguientes supuestos:

Casos bordes:

* En cada ronda de juego los jugadores deben usar su turno. AL comenzar la partida se decidirá de forma aleatoria el orden en que juegan los tactician, y al **final** de cada ronda se seleccionará de manera aleatoria un **nuevo orden** de juego de **manera aleatoria**

* Si las unidades pueden tenern 0 unidades en un comienzo. En este caso el jugador no puede jugar, esto significaria que tiene que no se puede mover, lo cual quita un poco la logica del programa. Luego tambien sucede que el *tactician*  pierde en un caso cuando no tiene mas unidades. Entonces en este caso el jugador que parte sin unidades se ve como que revive. Nah que ver (DEBEN TENER MINIMO 1 UNIDAD
* Cada tactician puede mover a todas sus unidades **solo una vez**, esto implica que una vez que mueva una unidad el proceso de partida continua si hay otra unidad que se pueda mover, es decir, hay otra unidad que este en la misma posicion
* En ningun momento del juego debe haber dos unidades en la misma casilla. Por tanto, si una unidad muere, hay que verificar que la casilla en la que se encuentraba queda vacia para que otra unidad pueda ocupar ese puesto en otro turno, o incluso en el mismo.
* Se asume que el van a existir 
* Al selecccionar una unidad esta debe pertenecer al equipo del jugador. No deberia poder seleccionar a otra unidad que no sea de su equipo. MENTIRA, la unidad seleccionada no necesariamente pertenece al jugador actual





## Dudas



* Los Tacticians deben tener referencia a todo el mapa y además deben conocer el lugar que se le asigna al comienzo?

* Dado el tamaño del mapa existe una cantidad limitada de jugadores que pueden participar y una cantidad maxima de unidades que pueden tener cada uno para colocarlos en el mapa. Por tanto, se debe considerar esto al iniciarse el juego?

* Asumiendo que tener 0 unidades significa perder, pensé en crear un hero en el constructor de tactician para que tenga sentido que el juego no inicie con todos perdiendo, por ejemplo si el jugador no selecciona a un hero al inicio. Tengo la duda si esto esta bien y si el area que se le asigna al jugador debe ser aleatorio o eso es algo arbitrario? 

* Cuando se señala que el controller debe manejar inputs, esto significa que debemos implementar un BufferedReader en Controller? 

* En la tarea se debe manejar errores con IOException y todo eso? 

* En el enunciado aparece que al jugador se le asigna un área de inicio donde debe situar sus unidades

* Si un metodo retorna un valor cualquiera, es valido que en algun caso retorne nulo? por ejemplo cree un mapa que guarda en las llaves la posicion del hero en la lista de unidades de tactician, y en los valores guarda un boleano que representa el estado del hero (true vivo, false muerto). Esto hace mas facil revisar cuando un jugador perdió o ganó, porque si no habria que revisar cada HP de cada unidad siempre que se termina una batalla.

* Se asume que las unidades parten con un HP, movement??

* Tiene sentido definir como class static a la fabrica de unit e items?

  