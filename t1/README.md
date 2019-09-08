# Modelo

## Resumen

Como primera etapa del proyecto se solicita implementar las entidades que servirán de base para el juego y las interacciones posibles entre dichas entidades. Las entidades que se esperan modificar en esta etapa serán **`Units`**, **`Items`** y **`Location`**, las que presentan características y restricciones que son necesarias implementar utilizando herramientas de diseño de programación dada la dependencia que tienen entre ellas y a las interacciones que realizan entre sí.



[TOC]

## Introducción

El modelo del proyecto se comienza con la base del [Template Alpaca](https://github.com/islaterm/cc3002-alpaca-project-template), [@autor](https://github.com/islaterm) islaterm, cuyas implementaciones de diseño debían ser modificados debido a errores y malas practicas que tenía a propósito, con la finalidad de lograr al final de esta etapa un modelo correcto, usando **`DoubleDispatch`**, `polimorfismo`, `herencia`, uso del *principio de Liskov*, entre otros. 

Además, se solicita para esta etapa desarrollar **combates** entre unidades, **intercambios** de items donde cada unidad puede dar y recibir objetos, y crear una nueva unidad llamada **Sorcerer** la que posee **3 items** nuevos (Light, Darkness, Soul).

En el informe se relatarán las distintas etapas para la modificación del código, los métodos creados, explicación de nuevas funcionalidades y las soluciones a problemas de diseño que se realizaron utilizando los conceptos enseñados en el curso.

## Detalles de implementación

### Análisis preliminar

Dada la existencia de errores en el código proporcionado, se listan cada uno de ellos, como también problemas pequeños e implementaciones que faltan, para una futura modificación:

* *Violación del principio de Liskov*: Cada unidad del juego tiene la caracteristica que debe portar solo un tipo de item en un comienzo, o simplemente no puede portar ningún item (esta unidad es la `alpaca`). Es por ello que, en el código proporcionado, a cada unidad se le consulta por el item que porta en el método `equipItem` usando consultas de referencia (`if objA instance of objB`). Esto rompe el principio de Liskov, pues impide que el código continúe extendiéndose.
* *Delegación*: Se solicita que cada unidad pueda realizar ataques con cada uno de sus items, si es que lo tiene equipado. Los ataques pueden presentar debilidades, fortalezas como también no tener efecto adicional. Esto es un problema de delegación, pues cada unidad no tiene la capacidad de atacar, pues este atributo es una característica del item, si la unidad tiene item y está viva junto a su contrincante, entonces puede atacar.[se explicará después](#For-the-attack) 
* *Maximos no fijos*: Los `HitPoints`y los `items` tienen valores máximos dependiendo de cada unidad. Es por ello que es indispensable tener una variable que reconozca esto valores máximos para poder manipularlos en un futuro.    
*   
* 



#### For the Attack

Una unidad puede utilizar el objeto que tiene equipado sobre otra siempre y cuando la otra unidad se encuentre dentro del rango definido por el item. Cuando esto sucede se entra en un combate.



Cuando se combate, la unidad que lo inició utiliza su objeto sobre la otra, y en caso que utilizar el objeto
resulte en un ataque y que la unidad que recibió el ataque pueda atacar, entonces realizará un contrataque. Si
en cualquier momento del combate una de las unidades participantes es derrotada, el combate finaliza.
Para esto se necesita poder diferenciar entre 2 tipos de objetos:

los tipos ataque normal

los ataque magicos



se decidió  una implementación agregando ataque como un atributo de los items, pues los items son los elementos debiles contra otros, mientras que las unidades solamente tienen la capacidad de equiparse con ellos, no así de tener el poder de ellos



|  *Item*   | *Weak against* | *Resistant against* |
| :-------: | :------------: | :-----------------: |
|  **Axe**  |     Sword      |        Spear        |
| **Sword** |     Spear      |         Axe         |
| **Spear** |      Axe       |        Sword        |



|    *Item*    | *Weak against* | *Resistant against* |
| :----------: | :------------: | :-----------------: |
|   **Soul**   |    Darkness    |        Light        |
| **Darkness** |     Light      |        Soul         |
|  **Light**   |      Soul      |      Darkness       |


#### For the Items

 Los item van a tener la capacidad de atacar, no así las unidades que son las que reciben el daño del ataque efectuado por el arma. De esta manera se logra una independencia entre la arma que porta cada unidad y el ataque que genera. Esto se consideró dado el caso borde donde en un combate puede haber una unidad que ataque sin arma, en cuyo caso simplemente no se puede atacar. También en el caso de contrataque, si el adversario no tiene arma no deberia poder atacar.



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





hay bonus x no romper liskov (hint: interfaces)

## References

- [Project Template - Alpaca Emblem](https://github.com/islaterm/cc3002-alpaca-project-template) From Ignacio Slater.
- [Double Dispatch](https://sites.google.com/site/programacionhm/conceptos/multiple-dispatch) How to use the double dispatch
- [Javadoc Tool](https://www.oracle.com/technetwork/articles/java/index-137868.html) How to write doc comments for Javadoc
- 









 