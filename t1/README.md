# Model
## Details of implementation

#### For the Attack

Una unidad puede utilizar el objeto que tiene equipado sobre otra siempre y cuando la otra unidad se encuentre dentro del rango definido por el item. Cuando esto sucede se entra en un combate.



Cuando se combate, la unidad que lo inició utiliza su objeto sobre la otra, y en caso que utilizar el objeto
resulte en un ataque y que la unidad que recibió el ataque pueda atacar, entonces realizará un contrataque. Si
en cualquier momento del combate una de las unidades participantes es derrotada, el combate finaliza.
Para esto se necesita poder diferenciar entre 2 tipos de objetos:

los tipos ataque normal

los ataque magicos



se decidió  una implementación agregando ataque como un atributo de los items, pues los items son los elementos debiles contra otros, mientras que las unidades solamente tienen la capacidad de equiparse con ellos, no así de tener el poder de ellos

#### For the Items 

#### For the Units

#### For the Map

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

## Bibliografia

- [Project Template - Alpaca Emblem](https://github.com/islaterm/cc3002-alpaca-project-template) From Ignacio Slater.

- [Double Dispatch](https://sites.google.com/site/programacionhm/conceptos/multiple-dispatch) How to use the double dispatch









 