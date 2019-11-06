# Controlador

## Resumen

La segunda etapa del proyecto del juego `Alpaca Emblem` consiste en programar la funcionalidad de la interacción entre el usuario y el modelo, es decir la conexión lógica entre la vista y el modelo, creando dos entidades relevantes:

* **`Tactician`**: Es la entidad que representará a los jugadores del juego y que deberá tener conocimiento de las unidades que poseen, el estado de sus unidades, sus caracteristicas y las acciones que puede realizar
* **`Controller`**: Es la entidad encargada de manejar el estado del juego en todo momento y de interactuar con el jugador del juego. Tiene la capacidad de reiniciar el juego cada vez que se requiera.

Durante el desarrollo del juego se hará uso de patrones de diseño aprendidos durante el curso y que son necesarios para obtener la funcionalidad que es solicitada.

## Mejoras respecto a v1.0

Tras la revisión de [versión 1.0](https://github.com/sesepulveda17/homeWork-cc3002/tree/master/t1) se mejoraron ciertos problemas de diseño que existían en la presente versión 2.0.

Lo primero que se modificó fue el uso de interfaces para diferenciar a los items que atacaban con los items que pueden recuperar. La principal desventaja de no ocupar interfaces es hacer un código que no sea extensible, por lo que si se desea añadir nuevos items que sirvan para recuperar, para atacar o incluso algún item que sea para otro fin, no existe una diferencia de tipos. Con el nuevo diseño es posible diferenciar entre los items y a la vez generar clases abstractas para no duplicar código. 

Los items `IAttack` son aquellos que pueden atacar, donde está incluido también los items mágicos. Mientras que los items `IHeap` son aquellos que solo pueden recuperar, y por tanto no realizan contraataque y reciben un daño normal excepto de las unidades mágicas, de las cuales reciben un daño crítico.

Para una mejor visualización de los resultados logrados con este cambio se muestra el diagrama de clases de la versión anterior comparada a la actual versión:

**Versión 1.0**

![]( https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t2/img/version1/pack_items.png )

**Versión 2.0**

![](https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t2/img/pack_itemsv2.png)

Un procedimiento similar se realizó con las unidades. En este caso sin embargo, fue distinto el motivo pues el problema que originó el cambio nació tras las condiciones del juego, que se detallan en [detalle aquí](https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t2/enunciado_controlador.pdf), que explican que existe una unidad especial ***Hero*** que en caso de que un Tactician **tenga esta unidad y la pierda**, entonces automáticamente este jugador **pierde**. Caso distinto es si solo tiene unidades que no son Hero, pues el jugador puede perder más de una de estas, pero puede seguir jugando. 

Por tanto, se crean dos interfaces `SpecialUnit` y `NormalUnit`para especificar esta diferencia entre las unidades. La principal diferencia entre los dos tipos de unidad se especifica en [observer pattern](#Observer Pattern), mientras que en las siguientes imágenes se muestran las diferencias entre la versión anterior y la actual:

**Versión 1.0**

![]( https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t2/img/version1/pack_unit.png )

**Versión 2.0**

![]( https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t2/img/pack_unitsv2.png )

Por último, se añade un objeto null en `IUnit` y `IEquipableItem` siguiendo `Null Pattern`. Este cambio se realizó dado a la cantidad de condiciones necesarias para verificar que los objetos no eran nulos y los problemas que generaba este hecho, como `Exception nullpointerexception`. Para más detalles revisar [Null Pattern](#Null Pattern)

## Patterns Design

Para esta tarea se ocuparon principalmente 2 patrones de diseño para lograr obtener los resultados que se deseaban para la creación de las unidades, items, y mapa (con `Factory Pattern`) y poder detectar el estado del juego en cierto instante, como las acciones realizadas por los tacticians, a través de `Observer Pattern`.

En las siguientes secciones se explicará cada uno de los patrones de diseño mencionados y en qué se ocuparon.

## Factory Pattern

El Factory Pattern es el encargado de crear objetos sin exponer la lógica de instanciación al cliente, en este caso el usuario del juego. Toda el trabajo de inicializar los objetos queda oculto. 

Se utiliza para crear los items, unidades y el mapa del juego. 

### Estructura Factory en Units y Items

Para las unidades y los items se estructuró una fabrica desde una interfaz `IFactory<Object>`  y un `AbstractFactory<Object>` para aprovechar la herencia de métodos y poder realizar llamadas `super()` al constructor del `AbstractFactory<Object>` para la creación de los objetos. 

![UML for items](https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t2/img/factory_pattern.png)

Otra manera de realizar este procedimiento era creando solamente una interfaz con las clases que implementaran esta interfaz para cada tipo de unidad o item, sin embargo esto genera duplicación de código, pero que es posible solucionarlo utilizando el patrón de diseño **Template Method** creando un `abstract class`.

Otra clase que fue implementada fue `FactoryProvider<Object>` utilizando una `class enum Type<Object>`. La finalidad del Provider es poder obtener un tipo de fabrica de manera simple, entregándole como parámetro el tipo de la clase del objeto que se desea crear. Sin embargo, esta clase sólo es testeada para verificar su funcionamiento y *no es utilizada en el resto de código*, siguiendo los requerimientos de la tarea.

Por último, antes de crear cada objeto de su respectiva fabrica, el usuario debe ser capaz de conocer los parámetros por defecto que van a crear los objetos, por los que se crean métodos `getters` de estos parámetros para conocerlos.

#### Factory Item

La fabrica de items debe ser capaz de generar un item con los parámetros por defectos definidos en el inicio del proyecto, estos parámetros fueron: 

* Name (String): nombre del objeto en minúsculas

  Ejemplo: "axe"  

* Power (int): 10 

* Min-Max Range (int):

  * (min: 1 - max: 2) para items de corto alcance
  * (min: 2 - max: 3) para item `bow` 

Dado a que en esta etapa no se solicita cambiar los parámetros de los items creados no se crean métodos *setters*, y en esta implementación solo es posible revisar los ajustes por defecto.



#### Factory Unit

La fabrica de units es capaz de crear unidades con los parámetros por defectos definidos en el inicio del proyecto, los parámetros son:

* HitPoint (double): 50
* Movement (int): 2
* Location: `InvalidLocation()`
* itemsAll: Coleccion de `IEquipableitem `de tamaño 0

Además de los métodos `getters` además se creó un método `AddItemForDefault()` donde en cada fábrica se **crea un item** y se añade a la unidad que va a ser creada algún item que pueda equiparse. Para aquellas unidades que no pueden equiparse item, como la alpaca, se añade un `bow` por defecto. 

### Test

Para verificar el correcto funcionamiento de las fabricas se crearon test que verifican si la clase de la unidad o item creado por la fabrica es igual a la clase de la unidad o item esperado. Se utiliza un `AbstractFactoryTest` para que realizar los test sin duplicación de código.

```java
@Test
public void createUnitTest(){
    IUnit fabUnit = getFactory().createUnit();
    // verificar datos por defeto
    assertEquals(getUnitCreated().getClass(), fabUnit.getClass());
    assertEquals(getUnitCreated().getLocation(), fabUnit.getLocation());
    assertEquals(getUnitCreated().getCurrentHitPoints(), fabUnit.getCurrentHitPoints());
    assertEquals(getUnitCreated().getMovement(), fabUnit.getMovement());
    // crear cambios
    Location loc = new Location(0,0);
    getFactory().setLocation(loc);
    getFactory().setItems(getExpectedItem());
    // verificar que unidad creada contiene cambios
    IUnit unitChange = getFactory().createUnit();
    assertEquals(getExpectedItem(), unitChange.getItems().get(0));
    assertEquals(loc,unitChange.getLocation());
}
```


### Factory Map

Además de realizar fabricas de las unidades y los items, se prefirió añadir una fabrica para el mapa pues esto facilitaba el no tener exceso de código y que el diseño del mapa fuera independiente del controlador. De esta manera también es posible generar mapas aleatorios, el mismo mapa o mapas con formas distintas, solo depende de la configuración que se tenga en el Factory.

En el caso de esta versión, el mapa es un conjunto de `Location` conectados en un `Field` aleatoriamente, y que su forma es cuadrada (tamaño es especificado y significa el lado del cuadrado).

## Observer Pattern

Se utiliza para generar la interacción entre el controlador y los cambios generados sobre los jugadores de la partida y sus unidades.

En java 12 la utilización de las clases Observer y Observable ***están deprecadas*** y no se recomienda su uso. Por tanto, el diseño utilizado para la implementación de este patrón fue a través del uso de la interfaz `PropertyChangeListener` haciendo el trabajo de la interfaz Observer (el objeto que observa), y de la clase `PropertyChangeSupport` haciendo el trabajo del Observable (el objeto observado). En los textos el Observer es llamado `Handler` o `Response`. En esta versión se consideran lo mismo, pero se ocupan para diferenciar los `listener` de los cambios en una unidad (Response), con respecto a los `listener` de los cambios de un `Tactician` (Handler). Por tanto, en adelante se ocuparan estos nombres para diferenciar de qué se está hablando en la implementación.

#### Observer Unit 

La implementación del patrón `observer` en las unidades está pensado para que los `observers `envien el mensaje de cambio a `Tactician`, el cual con algún método haga el cambio correspondiente a su estado. Así, cada `Response` debe tener referencia al `Tactician` que contiene a la unidad. Esta referencia se realiza al momento que la unidad es añadida al inventario de `Tactician`. 

```java
public void addUnitInventory(@NotNull NormalUnit unitAdded) {    
    final ResponseNormalUnit respNormalUnit = new ResponseNormalUnit(this);
    final ResponseMovementUnit responseMovementUnit = new ResponseMovementUnit(this);
    unitAdded.addResponseNormalUnit(respNormalUnit);
    unitAdded.addObserverMovement(responseMovementUnit);
    unitAdded.setTactician(this);    
    units.add(unitAdded);
}
```

Se asume que una unidad no puede ser cambiada hacia otro `Tactician`, por lo que cada unidad además tiene referencia al `Tactician` que lo posee.

Las unidades son los objetos primordiales en el juego, y es necesario conocer algunos estados en cada instante, o por lo menos en su punto critico. Es por ello que se crean:

* `ResponseNormalUnit`: Revisa el estado de vida de la unidad `normal`. Si el estado de vida es menor o igual a 0 entonces la unidad es eliminada del inventario del `Tactician` que lo contiene. 

  ```java
  public void propertyChange(PropertyChangeEvent evt) {
        if((double) evt.getNewValue() <= 0) 
            player.removeUnit((NormalUnit) evt.getSource());
    }
  ```

* `ResponseSpecialUnit`: Revisa el estado de vida de la unidad `special`. Si el estado de vida es menor o  igual a 0, entonces la unidad es eliminada del inventario del `Tactician`. A diferencia del `Response` anterior, si el `Tactician` pierde esta unidad, entonces pierde el juego. 

  Para esta versión se asume que al momento de perder el juego, el `Tactician` debe ser removido del juego, por lo que en este Response se cambia el estado de `Tactician` a `false` para que sea removido. 

  ```java
  public void propertyChange(PropertyChangeEvent evt) {
      if((double) evt.getNewValue() <= 0) 
          player.removeSpecialUnit((SpecialUnit) evt.getSource());
  }
  ```

* `ResponseMovementUnit`: Revisa el movimiento que realiza una unidad. Si la unidad es movida 1 vez, esto es notificado al `Response` el cual genera un mensaje al `Tactician` que contiene a la unidad, para que sea añadida a la lista de unidades movidas.

```java
public void propertyChange(PropertyChangeEvent evt) {    
    IUnit unitMoved = (IUnit) evt.getSource();    
    player.addUnitMoved(unitMoved);
}
```

#### Test

Para realizar los test de estos `Observers` fue necesario crear una interfaz `IResponseToTactician`, para poder añadir a cada `observer` de la unidad un `getTactician`, para verificar que efectivamente se realizaba el cambio afectando al `Tactician` propietario de la unidad 

#### Observer Tactician

Dado a que los cambios generados en la unidad provocan un cambio en el estado del `Tactician` este debe notificarlo al `Controller` para que realice los cambios necesarios para continuar el juego. Es por ello que, al igual que con la unidad, se crean `Observers` para notificar desde el `Tactician` los cambios generados sobre sus unidades. Para esto se crearon los siguientes `Handlers`:

* `NormalUnitLoseHandler`: Es el encargado de detectar el cambio en la cantidad de unidades normales de la unidad. Si una unidad normal muere, entonces esto es notificado al `controller`el cual decide qué hacer con el `Tactician`. En este caso, solo si la cantidad de unidades normales es 0, entonces se elimina al `Tactician` del juego.

```java
public void propertyChange(@NotNull PropertyChangeEvent evt) {
        Tactician player = (Tactician) evt.getSource();
        if((int) evt.getNewValue()==0) controller.removeTactician(player.getName());
    }
```

* `SpecialUnitLoseHandler:` Es el encargado de detectar el cambio en la cantidad de unidades especiales del `Tactician`. Si el `Tactician` pierde una unidad especial, inmediatamente es removido del juego

```java
public void propertyChange(@NotNull PropertyChangeEvent evt) {
        Tactician player = (Tactician) evt.getSource();
        controller.removeTactician(player.getName());
    }
```

* `StatusTacticianHandler`: Es el encargado de notificar el cambio en el estado del `Tactician`. Dado a que el estado inicial del `Tactician` es `true`, el cambio de estado provoca que el `Tactician` se retire del juego, por tanto es removido.

```java
public void propertyChange(@NotNull PropertyChangeEvent evt) {
        Tactician player = (Tactician) evt.getSource();
        controller.removeTactician(player.getName());
    }
```

* `UnitMovedHandler`: Es el encargado de notificar al `controller` las unidades movidas por el `Tactician`, las cuales no se pueden mover más de una vez por turno. Este `observer` añade a la lista de unidades movidas del controller a la unidad movida.

```java
public void propertyChange(@NotNull PropertyChangeEvent evt) {
        IUnit unitMoved = (IUnit) evt.getNewValue();
        controller.addUnitMoved(unitMoved);
    }
```

## Null Pattern

Este patrón es el encargado de simular la ausencia de un `IEquipableItem` en las unidades o un owner `IUnit` en los items. 

Por defecto, al crear las unidades, estas no presentan un `IEquipableItem` equipado (pues no es necesario que lo tenga desde un inicio, o puede no equiparlo nunca). Esto genera un problema al diseñar distintas funcionalidades en una unidad, pues en varias es necesario que el Item ya lo tenga equipado. Lo mismo sucede en los Items, donde necesitan un propetario para ser utilizado, pero en caso de no tenerlo no pueden realizar ataques (esto no tendría sentido).

Esto conlleva a crear un `ItemNull` y un `UnitNull` que heredan los métodos de sus respectivas Interfaces, pero que no heredan necesariamente todas los métodos por defecto del `AbstractItem` o `AbstractUnit`, pues es el encargado de simular la ausencia del item o de la unidad respectivamente.

### ItemNull

Para este objeto, se heredaron todos los métodos definidos en la interfaz de `IEquipableItem` y se colocaron por defecto a todos los métodos que devolvían `boolean` como false, los que devolvían enteros como 0 y String "". Para los demás métodos simplemente se devuelve `null`, lo cual no influye en los test anteriormente hechos en el modelo

### UnitNull

Al igual que para los items, `UnitNull` devuelve false en los métodos que retornaban `boleean`, 0 en los que retornaban enteros, y `null` en los demás. Para este caso, sin embargo, se utilizo la ventaja de tener el `AbstractUnit` para no equiparle items a `UnitNull`, parecido a lo que sucede con `Alpaca`, pero que en este caso tampoco puede añadirse item. Otro cambio hecho fue asignarle un valor true al método `isItemFull()`.

### Otros Null

Además de los objetos ya mencionados, no se escoge aplicar el patrón sobre `Tactician` pues en el desarrollo del código no fue necesario asegurarse que una unidad no tiene `Tactician` como propietario. 

Mientras que para `Location`, la clase `InvalidLocation` cumple la función del `NullPattern`, por lo que simplemente se descarta hacer otro. 

#### Test

Para el test de `ItemNull` y `UnitNull` se verifica que cada método entregue el resultado esperado o que no realice ningún cambio sobre el objeto



## Tactician

### Descripcion

Una entidad *Tactician* representa a un jugador que es manejado por el **Controller**. *Tactician* es el encargado de manejar todas las **instrucciones del usuario** y **delegar mensajes a los objetos del modelo** tales como las unidades y los items. Este diseño permite que el usuario **no interactúe** directamente con el modelo del juego.

Para lograr esto tactician también debe **conocer a todas las unidades que posee**, como también  tener conocimiento del **mapa del juego** a través de sus unidades.

Dentro del turno del jugador este puede mover a todas sus unidades, pero **solo una vez**. Sin embargo, para los ataques y el intercambio de items no hay una especificación de la cantidad de veces que se pueden realizar, por lo que **en esta versión las unidades pueden cambiar items y atacar más de una vez**. Para facilitar la implementación, *tactician* tiene la referencia a la unidad que tiene actualmente seleccionada el `controller`.

Un jugador debe tener la capacidad de **ver los datos** de sus unidades (HP current y max, items, inventario, poder, etc). Por lo que `Tactician`posee métodos que muestran todos estos datos.

#### Unidades de Tactician

La pieza más importante de un Tactician es su heroe. Como se menciona en [Mejoras respecto a v1.0](#Mejoras respecto a v1.0) esta unidad está clasificada como `SpecialUnit`.

Si el héroe de un jugador es derrotado en el turno de cualquier otro, entonces este jugador **pierde la partida y se retira del juego** junto con todas sus unidades. Si el héroe es derrotado en el turno del mismo jugador al que pertenece entonces **se termina su turno antes de ser excluido de la partida**. Un usuario puede tener más de un héroe en juego, en cuyo caso pierde la partida si cualquiera de estos es derrotado.

Si el `Tactician` solo posee `NormalUnit` entonces perderá la partida al momento de que todas las unidades hayan muerto. 

##### Restricciones en el juego

La unidad de un `Tactician` no puede intercambiar items con otra unidad que no esté en el inventario de unidades del `Tactician` correspondiente.

Las unidades que combaten no pueden atacar a las unidades del mismo `Tactician`.

Las unidades que recuperan no pueden recuperar a las unidades de otro `Tactician`.

Las unidades no pueden moverse hacia una posición ocupada por otra unidad.

Se asume que las unidades pueden pasar por sobre cualquier otra al momento de moverse.

### Test

Para los test se verificó que cada `tactician`  entregara los datos correctos de sus unidades al momento de ser añadidas. También se verificó que se obtener los datos de la unidad añadida.

Para la interacción con otros `Tactician` se crean test en `Controller` que verifican el correcto funcionamiento. Lo mismo se realiza con los cambios generados sobre los `Handlers` de `Tactician`, los cuales están testeados en el test de `Controller`. 

## Controller

El controller es una pieza fundamental para manejar el estado del juego en cierto instante y para realizar la interacción entre el jugador actual y los otros jugadores con o sin sus unidades. El `Controller` es el encargado de posicionar las unidades en el mapa del juego, generar intercambios de items, generar ataques sobre otras unidades, mover unidades, entre otras funciones.

Los **requisitos bases** para el controlador son:

* Crear un mapa cuadrado de tamaño n x n , donde n es el lado del cuadrado entregado como parámetro en el constructor. 
* Crear una cantidad fija, entregada en el constructor, de `Tactician` **sin unidades** 
* Poder devolver la cantidad de `Tactician`, el mapa del juego, el `Tactician` que está jugando actualmente, el número de Rounds que se han realizado, el máximo de Rounds que se pueden realizar (-1 es un juego sin fin).  
* Terminar un turno de algún jugador 
* Remover a un `Tactician`
* Iniciar el juego con un máximo de turnos.
* Conocer a la unidad seleccionada en alguna posición en el mapa, poder seleccionarla, equiparla, manipularla para que ataque o intercambie con otra unidad y seleccionar algún item en su inventario.

Las **funcionalidades añadidas** fueron las siguientes:

* Referencia a la unidad actualmente seleccionada del jugador del turno actual que se está jugando. 
* Colocar una unidad en alguna posición en el mapa
* Mover  a la unidad en alguna posición del mapa.
* Obtener Fabricas de las unidades que se pueden crear, y poder añadirle items a la unidad de cada `Tactician`.

Para esta versión se asume que el **controlador puede manejar a todas las unidades del mapa** en cualquier momento, por lo que no es necesario estar en la partida de algún jugador para realizar cambios en el juego. Sin embargo, al momento de mover unidades, por ejemplo, solo es posible realizarlo 1 sola vez por cada una.

En cuanto a los intercambios entre unidades, el mecanismo para intercambiar entre otras unidades depende del `Tactician` que posee a la unidad que está intercambiando. Solo en caso de que la unidad no tenga un `Tactician` asociado entonces esta unidad puede intercambiar items con cualquier unidad.

### Partidas

Al iniciarse el juego puede existir una cantidad máxima de rondas, o una cantidad limitada de ellos (-1 por defecto). La manera en la que se avanza de rondas es luego de que **todos los jugadores** hayan terminado su turno con `endTurn()`. Sin embargo, al momento de terminar cada ronda el orden de partida de los jugadores se **reordena aleatoriamente** . Esto impide testear correctamente los turnos, por lo que se crea un método `changeToNextTurn()` que permite cambiar de avanzar de turnos sin avanzar en rondas, y de esta manera es posible **asignarle unidades a cada `Tactician`** y la posibilidad de **pasar de turno**.

### Ganar un Juego

El `controller` es el encargado de revisar cuales son los jugadores que ganaron el juego en ciertas circunstancias. Los ganadores se obtienen **una vez que el juego es finalizado** o que se retiran todos los jugadores. Por tanto si se alcanza una cantidad máxima de turnos entonces el juego debe ser terminado y retornar los ganadores de la partida. 

En detalle, para que un jugador gane:

* **Todos el resto de los jugadores se han retirado del juego:** En esta versión un jugador no puede retirse, sin embargo, un `Tactician`, al momento de perder a sus unidades, cambia su estado a "no seguir", esto lo vamos a definir como un boleeano `true` si esta activo, `false` si no (a penas un jugador tenga estado `false` es eliminado de la partida con sus unidades con la ayuda de `UnitNormalLoseHandler` o `UnitSpecialLoseHandler`)
* **Se alcanza una cantidad máxima de turnos** (-1 es indefinido). El ganador en este caso es el que tiene la mayor de unidades restantes. Si dos jugadores tienen la misma cantidad de unidades entonces se declara un empate entre ambos jugadores, y ambos jugadores salen 

#### Test

Para testear a los ganadores del juego se crean dos test relevantes `getWinners()` y `getWinnersMaxUnit()`, en donde el primero verifica que al terminar los el número de partidas máxima se logra obtener al ganador cuando los `tactician` no tiene unidades, y se obtiene el ganador en una partida con rondas indefinidas cuando solo queda 1 jugador en juego. 

En `getWinnersMaxUnit()` se complementa a `getWinners()` para verificar que los jugadores con una misma cantidad de unidades tienen empate al terminar el juego. 



## Modo de uso

Para revisar el `coverage` en los test se debe presionar botón derecho sobre el package que contiene a los test. Por ejemplo, para revisar los test de `Unit` , `Item`, `Map` hay que seleccionar al package `model` que los contiene. Lo mismo hay que realizar con `controller`. En ambos paquetes están presentes los test para los `Listener` y las nuevas clases creadas.

## Dudas



* El lugar del mapa que se le asigna a un Tactician se debe realizar en la siguiente entrega?
* Asumiendo que tener 0 unidades significa perder, pensé en crear un hero en el constructor de tactician para que tenga sentido que el juego no inicie con todos perdiendo, por ejemplo si el jugador no selecciona a un hero al inicio. Tengo la duda si esto esta bien y si el area que se le asigna al jugador debe ser aleatorio o eso es algo arbitrario? 

* Una unidad puede atacar e intercambiar solo 1 vez en las siguientes entregas? un Cleric puede recuperar a toda unidad que quiera o tiene que ser de su equipo?  Se puede intercambiar con quien sea? https://www.u-cursos.cl/ingenieria/2019/2/CC3002/1/foro/o/23985016
* Como se añade un item a una unidad?
* el controller debe poder mover todo lo que quiera cuando quiera o depende del jugador que esta jugando actualmente?? https://www.u-cursos.cl/ingenieria/2019/2/CC3002/1/foro/o/23994416
* La unidad del jugador actual se puede mover encima de la unidad de otro jugador? o debe ser una restricción del juego?
* Al selecccionar una unidad esta debe pertenecer al equipo del jugador. No deberia poder seleccionar a otra unidad que no sea de su equipo. MENTIRA, la unidad seleccionada no necesariamente pertenece al jugador actual
* Se puede asumir que los `Tactician` deben partir con 1 unidad minimo? yo asumí que pueden estar sin unidades porque en los test proporcionados no hay una asignación de unidades.
