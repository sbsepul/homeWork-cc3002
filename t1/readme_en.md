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
2. A unit can not be equipped with more of one `item`, nor can it carry an `item` that already has a unit.
3. In the case of the `alpaca`, which can not to equip a item, the methods `equipItem<nom>` are empty.

##### Nuevos métodos

* New method`equals` to verify when a`IEquipableitem` is equal to other`IEquipableitem`.
* New method `isItemFull`: return`true` if the inventory is full

#### Combat

##### Attack in items

The `item` will be able to attack, don't the `unit`, because the `unit` receive the damage of attack done for a `item`. In this way an independence is achieved between the weapon that carry each `unit` and the attack that produce.

This help in the edge case where a `unit` **attack without a weapon**, which should not occur. Thereore in this case simply the `unit` **can not attack**. The case of counterattack is analogous. A opponent without item equipped **can not counterattack**. The `alpaca` simply can not attack (method attack without corp)

A `unit A` can use the item that have equipped over the` unit B` if and only if the other unit is in the range defined for the `item A` and both units are living. When this happen, start a combat. The method implemented for verify this conditions is `initCombat()`, which return `true` if a combat can start:

```java
public boolean initCombat(IUnit unitEnemy) {
    return this.getCurrentHitPoints()>0 && unitEnemy.getCurrentHitPoints()>0
            && this.getEquippedItem()!=null && this.isInRange(unitEnemy);
  }
```

##### Receive attack

The attack and counter attack change according to the type of the `unit`, and `item` particularly. For greater compresion of attack and counterattack process, are summarized the characteristics of each `unit` and the assumtions

- `Alpaca`: Can not attack. **Receive normal attack** from any`unit`. 
- `Archer` (item `Bow`): Attack y receive attack if both items (`item A, B`) are in range. Does not attack or counterattack if the enemy is neighbor of him. Does not receive attack if the other `item` can not attack him. (`Item B` out of range).
- `Cleric` (item `Staff`): Attack is **recovery**. Does not attack, no receive counterattack. Does not can recovery a `unit` beyond the maximum `HitPoints`.
- For the other units and their items review the following tables:

\***Type normal attack**: Fighter (Axe), SwordMaster (Sword), Hero (Spear)

|  *Item*   | *Weak against* | *Resistant against* |
| :-------: | :------------: | :-----------------: |
|  **Axe**  |     Sword      |        Spear        |
| **Sword** |     Spear      |         Axe         |
| **Spear** |      Axe       |        Sword        |

\**Normal attack* to Alpaca, Archer, Cleric.

**Tipos ataque mágicos**: Sorcerer (Light - Darkness - Soul)

|    *Item*    | *Weak against* | *Resistant against* |
| :----------: | :------------: | :-----------------: |
|   **Soul**   |    Darkness    |        Light        |
| **Darkness** |     Light      |        Soul         |
|  **Light**   |      Soul      |      Darkness       |

*Receive attack and it attack with effect of weakness* to normal `unit`, this implies that both units receive `x1.5` of damage. However, was assumpted that `Alpaca` receive normal attack, because `alpaca` does not have `item` equipped, and that `Staff` **recover normal** to `Sorcerer`

In each `unit` are defined the attacks that can to receive depending on the differents attacks of items previosly mencioned. These are:

* `receiveAttack(IEquipable item)`: Simulates a attack with normal damage (without modification) of `item``
* `receiveWeaknessAttack(IEquipable item):` Simulates a attack where the damage of `item` increase `x1.5`
* `receiveResistantAttack(IEquipable item):`Simulates a attack where the damage of `item` decrease 20 points.
* `receiveRecovery(IEquipable item):` Simulates a recovery attack, where the damage of `item` increase the HP of the enemy unit

In the moment of start a attack for `unit A`, must be verified that the `unit` attacked **have any `item` equipped**, in case of does not have **receive a normal attack**, which only deduct `HitPoints` according to the damage, without modification, that was realized for the `item A`. If the `unit B` **have `item` equipped**, first will receive the attack of `item A` and then will counterattack.

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

As mentioned earlier, the `item` is who damage and make the attack, therefore, it's implemented `receive<Item>Attack(IEquipableItem itemEnemy)` to each `item` to receive the attack according to `itemEnemy` that receives as a parameter. In this way, `unit A` delegate the job of attack to `item A` equipped and the `unit B` make the same, doing that the `item B` equipped to `unit B` receive the attack. This way it is simpler to make a `DoubleDispatch` for the attacks received

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

`item B` receive the attack and delegate the damage to `unit B` according to the `item A` that attacked him. Supposing that `unit B` can keep attacking, in spite of the damage received, `item B` make the counterattack with respect to `item A`. Finally, the combat ends.

##### Receive magic attack

As `Sorcerer` can equip 3 differents items, is necessary to distinguish which `itemMagic` attack to the `enemy`. This is solved with the method `receiveMagicAttack()` implemented in the attack that make `Sorcerer` counter other `unit`.

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

The method receive as parameter the enemy's `item` and sends it to the class of the `itemMagic` that was equipped to `Sorcerer`, where it's recognise the `itemMagic` that `Sorcerer` carry and the procedure attack and counterattack continues between the units.

```java
public void receiveMagicAttack(IEquipableItem enemyAttack){
        enemyAttack.receiveLightAttack(this);
    }
```

#### Exchange

All the units can give and receive items from other, as long as they **are to 1 distance between them**, **do not exceed** the maximum item capacity of the unit that will receive the `item` and the `unit` that will give the `item` **contain the `item`**. This is verified in `canExchange`

```java
public boolean canExchange(IUnit unit, IEquipableItem item) {
    return !this.getItems().isEmpty() && !unit.isItemFull()
            && this.getLocation().distanceTo(unit.getLocation())==1
            && this.getItems().contains(item);
  }
```

In `AbstractUnit` is implemented the method `giveItem` for to make the exchange and all the units inherit this method 

```java
public void giveItem(IUnit unit, IEquipableItem item) {
    if(canExchange(unit,item)){
      IEquipableItem itemA = this.removeItem(item);
      unit.addItem(itemA);
    }
  }
```

The exchange has no distinction between units, so no more restrictions that those already mentioned are added 

## How to use?





Para poder obtener la última versión del programa, dirigirse a [Tags](https://github.com/sesepulveda17/homeWork-cc3002/releases) donde encontrará la versión más estable del programa.

Dada la etapa del proyecto, hasta el momento solo es posible probar la funcionalidad de los métodos creados desde el directorio `Test/model` del repositorio. 

Los `test` que se realizaron se concentran en las clases de `unitTest` debido a que los métodos con más casos bordes se generan en esta clase, la cual genera llamados a métodos que están creados en `item`, lo cual verifica que aquellos métodos igual funcionan correctamente.

El `coverage` logrado en esta etapa fue de un 100% en `Class`, 100% en `Method` y 100% en `Lines`, lo cual fue comprobado al utilizar la opción `Run 'Test in 'model'' with coverage` proporcionado por `IntelliJ`. 

Por último, el [Informe](https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t1/Report_Model_AlpacaEmblem.pdf) y el [enunciado](https://github.com/sesepulveda17/homeWork-cc3002/blob/master/t1/Enunciado_Model.pdf) de la tarea se encuentran presentes en el repositorio de Github.

## References

- [Project Template - Alpaca Emblem](https://github.com/islaterm/cc3002-alpaca-project-template) From Ignacio Slater.
- [Double Dispatch](https://sites.google.com/site/programacionhm/conceptos/multiple-dispatch) How to use the double dispatch
- [Javadoc Tool](https://www.oracle.com/technetwork/articles/java/index-137868.html) How to write doc comments for Javadoc