# MathisonCompiler
Lenguaje de programación dirigido a la enseñanza de programación en poblaciones hispanohablantes.

`Proyecto de pregrado` `Universidad de Costa Rica`
`O. Azofeifa, B, Blanco, C.Portuguez & D. Varela`

Debido a que Costa Rica es un país hispanohablante se nota cómo la brecha
idiomática influye negativamente en el interés de las personas a adentrarse en el
mundo tecnológico desde temprana edad.

## Instrucciones paso a paso para ejecutar el programa
Requisitos:
- Tener instalado Java (La usada fue la versiòn 8)
- Haber bajado el java-cup-11b.jar desde la página de CUP.
- Haber instalado jflex.
NOTA PRELIMINAR: En nuestro caso, utilizamos IntelliJ como IDE y GitHub para el control
de versiones, por lo que recomendamos que para una ejecución más rápida y sencilla se
haga un pull a este proyecto: <https://github.com/alonzazo/MathisonCompiler>

Indicaciones:
1. Para compilar el .cup hacemos uso de este comando
java -jar java-cup-11b.jar -interface -parser Parser MathisonParser1.cup
2. Este generará un Parser.java y un sym.java.
3. Compilamos el .jflex con
java -jar jflex-1.6.1.jar lexer1.jflex
4. Este generará un Lexer.java.
5. Se integran los .java de los paso 2 y 4 colocándolos en un mismo folder y se compila
con java.
javac Parser.
