# Model

## Abstract

This first stage of project was request to implement the entities that are the base for the game, and the interactions possible between them. The entities that it wait to modific in this stage will be  **`Units`**, **`Items`** y **`Location`**, those that have qualities and restrictions that are neccessaries to implement used designs of programming tools give in the course.

## Introduction

The project's model begin with the base of [Template Alpaca](https://github.com/islaterm/cc3002-alpaca-project-template), [@autor](https://github.com/islaterm) islaterm, whose implements of design should to be modificied due to bugs and bad habits that have intentionality, with the objective of to reach a model correctly designed, used **`DoubleDispatch`**, `polymorphism`, `inherite`, the **Liskov** Substitution **Principle**, between others. 

Beside, it's requested for this stage to develop **combats**, between units, **exchange** of items where each unit can to give and to receive objects, and to create a new unit called **Sorcerer** which can to equip **3 news items** (Light, Darkness, Soul).

In the report will describe the differents stages for the modification of code, the methods created, explanation of new functionalities and the solves to the problems of design that was done used the concepts learn in the course.

## Details of implementation

### Initial analize

Given the exists of bugs in the code of Template, each one of them was examined and changed, like also little problems and implementations that were missing, for a future modification:

* *Break [Liskov substitution principle](http://www.oodesign.com/liskov-s-substitution-principle.html)*: Each unit of game have must to be equipped for only a type of item, in a begin, or simply can't equip him anything item (this unit was called `alpaca`). The mistake in the code was the use of  `if instance of` in the package item, where each `item` had a method `equipItem` that used this keyword to test if an *object* (instance) was a subtype of item. That broke the Liskov substitution principle because could potentially break the existing code and reduce the reusability.
* *Delegation*: Each `unit` must to realize attacks with each one of his items, if it's equipped. The attacks could to have weakness, resistants like also don't have aditional effects (damage 0). This is a problem of *delegation*, because `unit` don't have the capabiity of attack, this attribute is a`item`'s quality. If the `unit` have equipped a `item` and it's life both with the opponent, then can attack. The total process is described in [Combat](#Combat) 
* Maximum not fixed: The `HitPoints`and `items` have values maximums depended of each `unit`. That is why it's essential to have variables that are initialized in the constructor, that can to save that values maximums to be able to manipulate them in a future.

### Functionality development

#### Items

##### `EquippedItem`

As stated above, the first problem that was solved, was the use of `instance of` in the code of each `unit` created, where each `unit` was equipped with a `item`. In this example, in the class `Archer` the `item` must to be a instance `Bow` for to be equipped:

```java
@Override
  public void equipItem(final IEquipableItem item) {
    if (item instanceof Bow) {
      equippedItem = item;
    }
```

The solution to the problem was to implement `DoubleDispatch` over `unit` and `item`. In `IUnit` were created methods `equippedItem<name>`so that each unit implement this functionality according to the `item` that should be able to equip it and those who should not (the names of the methods weren't implemented with `@Overloading`). This also covers the case of `alpaca`, which **can't equip anything item**, but can to store a unlimited amount of them.

The implementation of `DoubleDispatch`will be summarised in a example with the `Archer` and his item `Bow`:

* A `Archer` can equip a `Bow` if he have a `item` in his inventory, otherwise can't. So, necessarily a `unit` must have a `item` in the inventory for to be able equip it, and the method created for this job was `addItem`, which add a `item` to the `List<IEquipableItems> items`. Once the `item` is added, the unit can equip it with:

```java
  public void equipItem(IEquipableItem item){
    if(item!=null){
      if(items.contains(item)){
        item.equipTo(this);
      }
    }
  }
```

* This is the method `equipTo` of `Bow`. The `method lookup` of `Bow` begin searched`equipTo` in the class of`Bow`:

```java
  public void equipTo(IUnit unit) {
    unit.equipItemBow(this);
    this.setOwner(unit);
  }
```

Where`setOwner` is a new method that set the attribute `Owner` in `Bow`

*  Finally, the process finish in the class of `Archer`, where `Bow` is equipped correctly 

```java
  public void equipItemBow(Bow item) {
    equippedItem = item;
  }
  public void equipItemSpear(Spear item) { }
```

##### Edge cases

The assumptions to equip a item were: 

1. If a `item` don't exist, then it is not equipped. For example, a item null.
2. Una unidad no puede equiparse de más de 1 `item`, ni tampoco puede portar un `item` que ya posee una unidad. 
3. En el caso de la `alpaca`, la cual no puede equiparse un `item`, los métodos `equipItem<nom>` se dejan con un cuerpo vacío. 

##### Nuevos métodos

* Se crea el método `equals` para verificar cuando un `IEquipableitem` es igual a otro `IEquipableitem`.
* Se crea método `isItemFull`: retorna `true` si el inventario está lleno

#### Combat

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

* `receiveResistantAttack(IEquipable item):`simula un ataque donde el poder de daño de `item` disminuye 20 puntos el HP

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

## Uso de app

Para poder obtener la última versión del programa, dirigirse a [Tags](https://github.com/sesepulveda17/homeWork-cc3002/releases) donde encontrará la versión más estable del programa.

Dada la etapa del proyecto, hasta el momento solo es posible probar la funcionalidad de los métodos creados desde el directorio `Test/model` del repositorio. 

Los `test` que se realizaron se concentran en las clases de `unitTest` debido a que los métodos con más casos bordes se generan en esta clase, la cual genera llamados a métodos que están creados en `item`, lo cual verifica que aquellos métodos igual funcionan correctamente.

El `coverage` logrado en esta etapa fue de un 100% en `Class`, 100% en `Method` y 100% en `Lines`, lo cual fue comprobado al utilizar la opción `Run 'Test in 'model'' with coverage` proporcionado por `IntelliJ`. 

Por último, el [Informe](https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t1/Report_Model_AlpacaEmblem.pdf) y el [enunciado](https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t1/Enunciado_Model.pdf) de la tarea se encuentran presentes en el repositorio de Github.

## References

- [Project Template - Alpaca Emblem](https://github.com/islaterm/cc3002-alpaca-project-template) From Ignacio Slater.
- [Double Dispatch](https://sites.google.com/site/programacionhm/conceptos/multiple-dispatch) How to use the double dispatch
- [Javadoc Tool](https://www.oracle.com/technetwork/articles/java/index-137868.html) How to write doc comments for Javadoc