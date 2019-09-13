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
  public void equipTo(IUnit unit) {
    unit.equipItemBow(this);
    this.setOwner(unit);
  }
```

Donde `setOwner` es un método nuevo que se tuvo que implementar para que el `item` que se espera equipar tenga como nuevo parámetro a la unidad a la que se le equipa.

*  Finalmente el proceso termina en la clase de `Archer`, donde el `Bow` termina como parámetro de la unidad.

```java
  public void equipItemBow(Bow item) {
    equippedItem = item;
  }
  public void equipItemSpear(Spear item) { }
```

##### Casos bordes

Se consideraron los siguientes supuestos en equipar los items a una unidad:

1. Una unidad del tipo `IUnit` puede equipar un tipo`IEquipableitem` según las restricciones del problema. Si el `item` no existe, entonces simplemente no se equipa. 
2. Una unidad no puede equiparse de más de 1 `item`, ni tampoco puede portar un `item` que ya posee una unidad. 
3. En el caso de la `alpaca`, la cual no puede equiparse un `item`, los métodos `equipItem<nom>` se dejan con un cuerpo vacío. 

##### Nuevos métodos

* Se crea el método `equals` para verificar cuando un `IEquipableitem` es igual a otro `IEquipableitem`.
* Se crea método `isItemFull`: retorna `true` si el inventario está lleno

#### Combate

##### Ataque en items

Los `item` van a tener la capacidad de atacar, no así las unidades que son las que reciben el daño del ataque efectuado por el `item`. De esta manera se logra una independencia entre la arma que porta cada unidad y el ataque que genera. 

Esto se consideró dado el caso borde donde en un combate puede haber una **unidad que ataque sin arma**, lo cual no debería ocurrir, por tanto en ese caso simplemente **no se puede atacar**. También en el caso de **contrataque**, si el adversario no tiene `item` equipado **no debería poder contraatacar**. Esto facilita también el caso de la unidad `alpaca` que no puede atacar.

Un `unit A` puede utilizar el objeto que tiene equipado sobre otra `unit B` siempre y cuando la otra unidad se encuentre dentro del rango definido por el `item A` y *ambas unidades estén vivas*. Cuando esto sucede se entra en un combate. El método implementado para verificar esto fue `initCombat()` el cual retorna `true` si se puede comenzar el combate:

```java
public boolean initCombat(IUnit unitEnemy) {
    return this.getCurrentHitPoints()>0 && unitEnemy.getCurrentHitPoints()>0
            && this.getEquippedItem()!=null && this.isInRange(unitEnemy);
  }
```

##### Recibir ataques

El ataque y contraataque varia según el tipo de unidad. Para una mayor compresión del procedimiento de ataque y contrataque, se resumen primero las características de cada unidad y los supuestos hechos:

- Alpaca: No ataca. **Recibe ataque normal** de toda `unit`. 
- Archer (item `Bow`): Ataca y recibe ataque si ambos `item` están en rango. No ataca ni contrataca si su enemigo es vecino de él. No recibe ataque si el otro `item` no lo puede atacar (no está en rango).
- Cleric (item `Staff`): Su ataque es **recuperar**. No realiza ni recibe contraataque. No puede recuperar a una unidad más allá del máximo de HP.
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

*Reciben y atacan con efecto de debilidad* a Unidades normales, esto implica que ambas unidades reciben `x1.5` de daño. Sin embargo, se asume que `Alpaca` **recibe ataque normal**, pues no tiene `item` equipado, y que el `Staff` **no aumenta la recuperación** que realiza a un `Sorcerer` que tiene equipada un `item`.

En `unit` se definen los ataques que pueden recibir según los distintos ataques de items que se mencionan. Estos son:

* `receiveAttack(IEquipable item)`: simula un ataque con el poder de daño sin modificación de `item`

* `receiveWeaknessAttack(IEquipable item):` simula un ataque donde el poder de daño de `item` aumenta `x1.5`

* `receiveResistantAttack(IEquipable item):`simula un ataque donde el poder de daño de `item` disminuye 20 puntos 

* `receiveRecovery(IEquipable item):` simula un ataque de recuperación, donde el poder de daño de `item` suma HP

Al momento de iniciarse un ataque por `unit A`, se debe verificar que la unidad a la cual se ataca **tiene algún `item` equipado**, en caso de no tenerlo **recibe un ataque normal**, el cual solamente descuenta `HitPoints` según el daño, sin modificación, que realiza el `item A`. Si la unidad que recibió el ataque **tiene `item` equipado**, primero recibirá el ataque y luego realizará un contrataque. 

```java
public void attack(IUnit enemy){
    if(this.initCombat(enemy)){
      if(enemy.getEquippedItem()!=null){
        enemy.getEquippedItem().receive<Item>Attack((<Item>) this.getEquippedItem());
      }
      else enemy.receiveAttack(this.getEquippedItem());
    }
  }
```

Como se mencionó anteriormente, el `item` es el que realiza el daño y el ataque en sí, por tanto, se implementa `receive<Item>Attack(IEquipableItem itemEnemy)` a cada `item` para que reciba el ataque según el `itemEnemy` que lo recibe como parámetro. De esta manera, `unit A` delega el trabajo de atacar al `item A` que tiene equipado y se realiza lo mismo con`unit B`, haciendo que el `item B` que tiene equipado sea quien reciba el ataque. De esta manera es más simple hacer un `DoubleDispatch` para los ataques recibidos

```java
//Example general of receive Attack
  public void receive<Item>Attack(Axe attackAxe) {
    this.receiveResistantAttack(attackAxe);
    if(this.canAttack(attackAxe)) {
      if(attackAxe.getOwner().getCurrentHitPoints()>0){
        attackAxe.getOwner().receiveAttackWeakness(this);
      }
    }
  }
```

`item B` al recibir el ataque, deriva el daño a `unit B` según el `item A` que lo atacó. Suponiendo que `unit B` puede seguir atacando, a pesar del daño recibido, según [las tablas anteriores](#Recibir-ataques) `item B` realiza el contrataque que le corresponde respecto al `item A` que lo atacó. Luego, el combate termina.

##### Recibir ataque mágico

Dado a que `Sorcerer` puede equiparse de 3 items distintos, es necesario distinguir con qué `item` ataca a su enemigo. Esto se resuelve con el método `receiveMagicAttack()` implementado en el ataque que realiza `Sorcerer` contra otra unidad. 

```java
public void attack(IUnit enemy) {
        if (this.initCombat(enemy)){
            if(enemy.getEquippedItem()!=null) {
            this.getEquippedItem().receiveMagicAttack(enemy.getEquippedItem());
            }
            else enemy.receiveAttack(this.getEquippedItem());
        }
    }
```

El método recibe como parámetro el item del enemigo y lo envía a la clase del `item` portado por el `Sorcerer` donde se reconoce el `item` que porta el `Sorcerer` y se sigue el procedimiento descrito anteriormente de ataque y contrataque. 

```java
public void receiveMagicAttack(IEquipableItem enemyAttack){
        enemyAttack.receiveLightAttack(this);
    }
```

#### Exchange

Todas las unidades pueden dar y recibir objetos de otras, siempre y cuando estas **estén a distancia 1 entre ellas**, que **no se supere la cantidad máxima** de items que puede portar la unidad que va a recibir el `item` y la unidad que dará el `item` **contenga el `item`** que va a dar en su inventario. Esto se verifica en `canExchange`

```java
public boolean canExchange(IUnit unit, IEquipableItem item) {
    return !this.getItems().isEmpty() && !unit.isItemFull()
            && this.getLocation().distanceTo(unit.getLocation())==1
            && this.getItems().contains(item);
  }
```

El método `giveItem` realiza el intercambio y se implementa en `AbstractUnit` para que todas las unidades hereden este método.

```java
public void giveItem(IUnit unit, IEquipableItem item) {
    if(canExchange(unit,item)){
      IEquipableItem itemA = this.removeItem(item);
      unit.addItem(itemA);
    }
  }
```

El intercambio no tiene distinción entre unidades, por lo que no se añaden más restricciones de las ya mencionadas.

## Test

Los test fueron realizados utilizando el formato proporcionado por el *Template A.E.*, donde en cada `package` se prueban los métodos particulares de cada objeto. Sin embargo, donde se realizan la mayor cantidad de lineas de test es en `package unit` . Esto se debe a que los casos bordes que se debían comprobar para verificar que un combate se estaba realizando correctamente. Además, no todas las unidades pueden atacar y el combate igual puede ocurrir a pesar que una unidad no tenga `item` por lo que es menester testear los métodos de las unidades.

Los test que eran repetidos para ciertas unidades fueron colocados en `AbstractTestUnit`, al igual que en el caso de los items, donde fueron colocados en `AbstractTestItem`, aprovechando de esta manera la herencia entre clases.

Las unidades `Archer`, `Alpaca`, `Sorcerer` y `Cleric` poseen test particulares pues tienen características especiales que necesitan ser probadas a parte y no en conjunto con todas las demás. Además, cada unidad tiene un método `giveToUnit<name>Test` que verifica que pueda intercambiar items solamente con alguna unidad que esta a distancia 1 y que no supere el máximo de items o pueda dar sin tener items. Como también se crea un `giveToUnitAlpacaTest` para verificar que a la `alpaca` se le puede dar una cantidad ilimitada de items.

Cada test tienen el encabezado @Test para probarlo, por lo que aquellos métodos que no eran necesarios para ciertas clases, (por ejemplo `weaknessAttackTest()` para `alpaca`, pues alpaca no puede realizar ataques de debilidad, ni recibirlos) tienen un cuerpo vacio, pero no se les coloca el encabezado @Test.

El `coverage` logrado en esta etapa fue de un 100% en `Class`, 100% en `Method` y 100% en `Lines`, lo cual fue comprobado al utilizar la opción `Run 'Test in 'model'' with coverage` proporcionado por `IntelliJ`. 

## Uso de app

Para poder obtener la última versión del programa, dirigirse a [Tags](https://github.com/sesepulveda17/homeWork-cc3002/releases) donde encontrará la versión más estable.

Dada la etapa del proyecto, hasta el momento solo es posible probar la funcionalidad de los métodos creados desde el directorio `Test/model` del repositorio. 

El `coverage` logrado en esta etapa fue de un 100% en `Class`, 100% en `Method` y 100% en `Lines`, lo cual fue comprobado al utilizar la opción `Run 'Test in 'model'' with coverage` proporcionado por `IntelliJ`. 

Por último, el [Informe](https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t1/Report_Model_AlpacaEmblem.pdf) y el [enunciado](https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t1/Enunciado_Model.pdf) de la tarea se encuentran presentes en el repositorio de Github.

## Referencias

- [Project Template - Alpaca Emblem](https://github.com/islaterm/cc3002-alpaca-project-template) From Ignacio Slater.

- [Double Dispatch](https://sites.google.com/site/programacionhm/conceptos/multiple-dispatch) How to use the double dispatch

- [Javadoc Tool](https://www.oracle.com/technetwork/articles/java/index-137868.html) How to write doc comments for Javadoc

  









 