# Modelo

## Resumen

Como primera etapa del proyecto se solicita implementar las entidades que servirán de base para el juego y las interacciones posibles entre dichas entidades. Las entidades que se esperan modificar en esta etapa serán **`Units`**, **`Items`** y **`Location`**, las que presentan características y restricciones que son necesarias implementar utilizando herramientas de diseño de programación dada la dependencia que tienen entre ellas y a las interacciones que realizan entre sí.

## Introducción

El modelo del proyecto se comienza con la base del [Template Alpaca](https://github.com/islaterm/cc3002-alpaca-project-template), [@autor](https://github.com/islaterm) islaterm, cuyas implementaciones de diseño debían ser modificados debido a errores y malas practicas que tenía a propósito, con la finalidad de lograr al final de esta etapa un modelo correcto, usando **`DoubleDispatch`**, `polimorfismo`, `herencia`, uso del *principio de Liskov*, entre otros. 

Además, se solicita para esta etapa desarrollar **combates** entre unidades, **intercambios** de items donde cada unidad puede dar y recibir objetos, y crear una nueva unidad llamada **Sorcerer** la que posee **3 items** nuevos (Light, Darkness, Soul).

En el informe se relatarán las distintas etapas para la modificación del código, los métodos creados, explicación de nuevas funcionalidades y las soluciones a problemas de diseño que se realizaron utilizando los conceptos enseñados en el curso.

## Detalles de implementación

### Análisis preliminar

Dada la existencia de errores en el código proporcionado, se listan cada uno de ellos, como también problemas pequeños e implementaciones que faltan, para una futura modificación:

* *Violación del principio de Liskov*: Cada unidad del juego tiene la caracteristica que debe portar solo un tipo de item en un comienzo, o simplemente no puede portar ningún item (esta unidad es la `alpaca`). Es por ello que, en el código proporcionado, a cada unidad se le consulta por el item que porta en el método `equipItem` usando consultas de referencia (`if objA instance of objB`). Esto rompe el principio de Liskov, pues impide que el código continúe extendiéndose.
* *Delegación*: Se solicita que cada unidad pueda realizar ataques con cada uno de sus items, si es que lo tiene equipado. Los ataques pueden presentar debilidades, fortalezas como también no tener efecto adicional. Esto es un problema de delegación, dado a que unidad no tiene la capacidad de atacar, pues este atributo es una característica del `item`, si la unidad tiene equipada un `item` y está viva junto a su contrincante, entonces puede atacar. Este proceso [está descrito en Combate](#Combate) 
* *Máximos no fijos*: Los `HitPoints`y los `items` tienen valores máximos dependiendo de cada unidad. Es por ello que es indispensable tener variables que sean inicializadas en el constructor, que puedan guardar estos valores máximos para poder manipularlos en un futuro.

### Desarrollo de funcionalidades

#### Items

##### `EquippedItem`

El primer problema que se empezó a solucionar fue el uso de `if objA instance of objB` en el código de cada unidad creada, donde a cada unidad se le equipaba un `item` si esa era la instancia de `item` que le correspondía.

```java
@Override
  public void equipItem(final IEquipableItem item) {
    if (item instanceof Bow) {
      equippedItem = item;
    }
```

La solución al problema fue utilizar `DoubleDispatch` sobre cada `unit` e `item`. En `IUnit` se crearon métodos `equippedItem<nom>` para que cada unidad implementara esta funcionalidad de acuerdo al `item` que debería poder equiparse y a los que no (se escoge el nombre del método de esta manera para no recurrir en un `@Overloading`). Así también se resuelve el problema de la `alpaca`, la cual no puede **equiparse de ningún item**, pero puede almacenar una cantidad ilimitada de ellos. 

Los llamados que se realizan con el `DoubleDispatch` se resumirá tomando de ejemplo equipar a un `Archer` con un `Bow`:

* Un `Archer` puede equiparse de un `Bow` si este posee el `item` en su inventario, en caso contrario no se lo puede equipar. Para que funcione el equipar un `item` la unidad debe tener el `item` en su inventario, es por eso que se implementa primero un método `addItem` el cual añade un `item` al inventario. Luego es posible equiparlo con lo siguiente:

```java
@Override
  public void equipItem(IEquipableItem item){
    if(item!=null){
      if(items.contains(item)){
        item.equipTo(this);
      }
    }
  }
```

* El `method lookup` de `Bow` comienza buscando `equipTo` en la clase del objeto `Bow`:

```java
  @Override
  public void equipTo(IUnit unit) {
    unit.equipItemBow(this);
    this.setOwner(unit);
  }
```

Donde `setOwner` es un método nuevo que se tuvo que implementar para que el `item` que se espera equipar tenga como nuevo parámetro a la unidad a la que se le equipa.

*  Finalmente el proceso termina en la clase de `Archer`, donde el `Bow` termina como parámetro de la unidad.

```java
  @Override
  public void equipItemBow(Bow item) {
    equippedItem = item;
  }
  @Override
  public void equipItemSpear(Spear item) { }
```

##### Casos bordes

Se consideraron los siguientes supuestos en equipar los items a una unidad:

1. Una unidad del tipo `IUnit` puede equipar un tipo`IEquipableitem` según las restricciones del problema. Si el `item` no existe, entonces simplemente no se equipa. Esto se realiza implementando en cada unidad `equipItemOther`, el cual no realiza nada.
2. Una unidad no puede equiparse de más de 1 `item`, ni tampoco puede portar un `item` que ya posee una unidad. 
3. En el caso de la `alpaca`, la cual no puede equiparse un `item`, los métodos `equipItem<nom>` se dejan con un cuerpo vacío. 

##### Nuevos métodos

* Se crea el método `equals` para verificar cuando un `IEquipableitem` es igual a otro `IEquipableitem`.
* Se crea método `isItemFull`: retorna `true` si el inventario está lleno

#### Combate

##### Ataque en items

Los `item` van a tener la capacidad de atacar, no así las unidades que son las que reciben el daño del ataque efectuado por el `item`. De esta manera se logra una independencia entre la arma que porta cada unidad y el ataque que genera. 

Esto se consideró dado el caso borde donde en un combate puede haber una **unidad que ataque sin arma**, lo cual no debería ocurrir, por tanto en ese caso simplemente **no se puede atacar**. También en el caso de **contrataque**, si el adversario no tiene arma **no debería poder contraatacar**. Esto facilita también el caso de la unidad `alpaca` que no puede atacar.

Un `unit A` puede utilizar el objeto que tiene equipado sobre otra `unit B` siempre y cuando la otra unidad se encuentre dentro del rango definido por el `item A` y *ambas unidades estén vivas*. Cuando esto sucede se entra en un combate.

##### Recibir ataques

El ataque y contraataque varia según el tipo de unidad. Para una mayor compresión del procedimiento de ataque y contrataque, se resumen primero las características de cada unidad y los supuestos hechos:

- Alpaca: No ataca. **Recibe ataque normal** de toda `unit`. 
- Archer (item `Bow`): Ataca y recibe ataque si ambos `item` están en rango. No ataca ni contrataca si su enemigo es vecino de él. No recibe ataque si el otro `item` no lo puede atacar (no está en rango).
- Cleric (item `Staff`): Su ataque es **recuperar**. No realiza ni recibe contraataque.
- Para las otras unidades, se resumen las características de los `item` en las siguientes tablas:

**Tipos ataque normal**: Fighter (Axe), SwordMaster (Sword), Hero (Spear)

|  *Item*   | *Weak against* | *Resistant against* |
| :-------: | :------------: | :-----------------: |
|  **Axe**  |     Sword      |        Spear        |
| **Sword** |     Spear      |         Axe         |
| **Spear** |      Axe       |        Sword        |

*Atacan normal* a Alpaca, Archer, Cleric.

**Tipos ataque mágicos**: Sorcerer (Light - Darkness - Soul)

|    *Item*    | *Weak against* | *Resistant against* |
| :----------: | :------------: | :-----------------: |
|   **Soul**   |    Darkness    |        Light        |
| **Darkness** |     Light      |        Soul         |
|  **Light**   |      Soul      |      Darkness       |

*Reciben y atacan con efecto de debilidad* a Unidades normales. Alpaca recibe ataque normal, pues no tiene `item` equipado



Al momento de iniciarse un ataque por `unit A`, se debe verificar que la unidad a la cual se ataca **tiene algún `item` equipado**, en caso de no tenerlo **recibe un ataque normal**, el cual solamente descuenta `HitPoints` según el daño, sin modificación, que realiza el `item A`. Si la unidad que recibió el ataque **tiene `item` equipado**, primero recibirá el ataque y luego realizará un contrataque. 

Como se mencionó anteriormente, el `item` es el que realiza el daño y el ataque en sí, por tanto, se implementa `receive<Item>Attack(IEquipableItem itemEnemy)` a cada `item` para que reciba el ataque según el `itemEnemy` que lo recibe como parámetro. De esta manera, `unit A` delega el trabajo de atacar al `item A` que tiene equipado y se realiza lo mismo con`unit B`, haciendo que el `item B` que tiene equipado sea quien reciba el ataque. De esta manera es más simple hacer un `DoubleDispatch` para los ataques recibidos

<code>

`item B` al recibir el ataque, deriva el daño a `unit B` según el `item A` que lo atacó. Suponiendo que `unit B` puede seguir atacando, y recibe un daño, según [las tablas anteriores](#Recibir-ataques) `item B` puede realizar distintos tipos de ataques sobre la unidad. 







#### Exchange

Todas las unidades pueden dar y recibir objetos de otras, siempre y cuando estas est´ en a distancia 1 entre ellas y que no se supere la cantidad máxima de objetos que puede portar.

#### For the Units



##### For the Cleric

La curacion que hace se implementa como ataque, y los puntos que recupera son sin añadirles más puntos 

Para esta unidad el ataque no podia ser implementado por restricciones del problema. Además como el ataque se implementó sobre los items, si un staff no tiene item entonces este no puede recuperar. SIn embargo, una unidad que no tenga arma igual puede recuperarse

#### For the Map





## Tests



Al momento de implementar los test de ataques se eligió una distribución para cada unidad que sería testeada. La asignación de posiciones para cada unidad fue la siguiente:



| Staff   | Hero   | Archer      |
| ------- | ------ | ----------- |
| Fighter | Cleric | Sorcerer    |
|         | Alpaca | SwordMaster |



### Supposed



## How to use?

*short*



Explain the structure that was used,  the designs patterns and why did you use it? 

### Example Code in Java

```java
public class SuperClass {
    public int getNb() {
         //specify what must happen
        return 1;
     }

     public int getNb2() {
         //specify what must happen
        return 2;
     }
 }

```



### Questions

1. La interfaz de unidad es una clase que no tiene un implement, eso hay que crearlo en un nuevo archivo que tenga implement  o hay que implementarlo en la misma clase de la interfaz??
2. Cuales son los métodos que se pueden implementar en las subclases del `AbstractUnit`, `AbstractItem`?? o mas bien, hay que crear si o si los metodos declarados en `Abstract` ?
3. La carpeta Test debe quedar oculta? En la tarea 0 note que Intellij lo ocultaba, pero dado a que mi tarea esta de la siguiente manera:





COMBATE -------->

ATAQUE --------->

CONTRA <---------





caso borde:



# Cosas que faltan

* Implementar un dd en units, dependiendo del ataque que recibe se va a cierta arma y bla.. así la recuperación deberia poder recibirse de un item staff y no de otro.
* Completar algunos test que faltan



Readme: Debe hacer un readme especificando los detalles de su implementación, los supuestos que realice y una breve explicación de cómo ejecutar el programa. Adicionalmente se le solicita dar una
explicación general de la estructura que decidió utilizar, los patrones de dise˜ no y la razón por la cual
los utiliza.





hay bonus x no romper liskov (hint: interfaces)

## References

- [Project Template - Alpaca Emblem](https://github.com/islaterm/cc3002-alpaca-project-template) From Ignacio Slater.
- [Double Dispatch](https://sites.google.com/site/programacionhm/conceptos/multiple-dispatch) How to use the double dispatch
- [Javadoc Tool](https://www.oracle.com/technetwork/articles/java/index-137868.html) How to write doc comments for Javadoc
- 









 