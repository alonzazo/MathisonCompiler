# MathisonCompiler
Lenguaje de programación dirigido a la enseñanza de programación en poblaciones hispanohablantes.

`Proyecto de pregrado` `Universidad de Costa Rica`
`O. Azofeifa, B, Blanco, C.Portuguez & D. Varela`

Debido a que Costa Rica es un país hispanohablante se nota cómo la brecha
idiomática influye negativamente en el interés de las personas a adentrarse en el
mundo tecnológico desde temprana edad.

## Ejemplos

### Criba de Eratóstenes

```
publico num[] cribaEratostenes(num n){

	num limite = raiz(n)
	num[n] numeros 	                        //Vector de num de tamaño n
	num[] resultado	                        //Vector de num sin tamaño definido
	para num i desde 0 hasta n avance 1		//Un ciclo for
	{
        	numeros[i] = 0
    	}
	num marcado = 0
	para num i desde 2 hasta n avance 1
	{
		si (numeros[i] == 0) 			//Revisa si está marcado
        	{
			resultado[marcado] = i  	//Está sin marcar
			marcado = marcado + 1	    //Aumente en uno este num
		}					            //Marcar múltiplos
        	si(i < limite)
		{
            		para num j desde i hasta limite avance i
			{
                		numeros[j] = i
			}
		}
	}
	devolver resultado
}
```

### Sucesion de Fibonacci

```
num fiboRec ( num n ) {
    num resultado
    si ( n <= 1 ) //CASO BASE 
    {
        imprimir (1)
	resultado = 1
    }
    sino          //CASO TRIVIAL
    {
        num enesimo = fiboRec ( n - 1 ) + fiboRec ( n - 2 )
	imprimir (enesimo)
	resultado = enesimo
    }
    devolver resultado
}
```

### Impresión de un triángulo en ASCII

```
publico proc imprimirTriangulo(num n)
{
	imprimir("Digite el caracter que desea imprimir en un triangulo")
	cad c
	leer(c)
	imprimir("Digite el numero de filas que desea que tenga el triangulo")
	//num n
	leer(n)
	cad fila = ""
	para num i desde 1 hasta n avance 1
    	{
        	para num j desde 1 hasta i avance 1
        	{
            		fila = fila + c
            		imprimir(fila)
        	}
        	imprimir("\n")

    	}
}
```
## Diagrama de clases

![Diagrama de clases](https://raw.githubusercontent.com/alonzazo/MathisonCompiler/master/src/main/resources/Diagrams/diagram.svg?sanitize=true)
<img src="https://raw.githubusercontent.com/alonzazo/MathisonCompiler/master/src/main/resources/Diagrams/diagram.svg?sanitize=true">

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
